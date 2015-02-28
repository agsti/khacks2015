import sys, re
import json

def split_meta(line):
	line = re.search(r'^\[([^\]]+)\](.+)?$', line)
	return line.groups()

def transform_meta(meta_in):
	transforms = {'ti': 'title', 'ar': 'artist', 'al': 'album'}
	out = {}
	for transform_field in transforms.keys():
		if transform_field in meta_in:
			out[transforms[transform_field]] = meta_in[transform_field]
	return out

def readlines(file):
	with open(file, 'r') as f:
		return [split_meta(line) for line in f.readlines()]

def cleanup_empty_lines(lyrics):
	out = []
	running_count = 0.0
	index = 0
	for lyric in lyrics:
		if lyric[1] == '':
			running_count += lyric[0]
		else:
			out.append((index, running_count + lyric[0], lyric[1]))
			index += 1
			running_count = 0
	return out

def cleanup_word(word):
	return re.sub(r'[^\D+]', '', word, flags=re.UNICODE)

def extract_words(lyrics):
	words = {}
	seen_max = 0
	complexity_max = 0
	for lyric in lyrics:
		word_raw_list = lyric[2].split(' ')
		for word_raw in word_raw_list:
			word_clean = cleanup_word(word_raw)
			if (word_clean == ''): continue
			if word_clean not in words:
				word = {
					'seen': 1,
					'at': [(lyric[0], word_raw)],
					'complexity': len(word_clean),
					'score': 0
				}
				words[word_clean] = word
			else:
				word = words[word_clean]
				word['seen'] += 1
				seen_max = max(word['seen'], seen_max)
				complexity_max = max(word['complexity'], complexity_max)
				word['at'].append((lyric[0], word_raw))
	for word in words.keys():
		words[word]['score'] += float(seen_max) / words[word]['seen']
		words[word]['score'] += float(words[word]['complexity']) / complexity_max
	return words

def parse_timecode(raw_timecode):
	return float(raw_timecode.replace('.', '').replace(':', '.'))

def parse_lyrics_file(file):
	lines = readlines(file)
	meta = {}
	lyrics = []
	for line in lines:
		if not line[0][0].isdigit():
			line_parse = line[0].split(':')
			meta[line_parse[0]] = line_parse[1]
		else:
			if not line[1]:
				line = (line[0], '')
			lyrics.append((parse_timecode(line[0]), line[1].rstrip('\r')))
	meta_fixed = transform_meta(meta)
	meta_fixed['lyrics'] = cleanup_empty_lines(lyrics)
	meta_fixed['words'] = extract_words(meta_fixed['lyrics'])
	return meta_fixed


if __name__ == '__main__':
	print json.dumps(parse_lyrics_file(sys.argv[1]))