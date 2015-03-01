#!/bin/bash`


DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

for file in ./*.lrc
do 

file_wo_ext="${file/_lyric.lrc/_data.json}"

python3 "${DIR}/load_lyrics.py" $file > ${file_wo_ext}

# processing 


done
