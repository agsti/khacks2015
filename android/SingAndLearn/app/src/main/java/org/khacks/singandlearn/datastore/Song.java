package org.khacks.singandlearn.datastore;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by iain on 2/28/15.
 */
public class Song {

    public final String name;
    public final String rawLyrics;
    public final int res_id;
    private WordsScore score;
    public final String _id;
    public final String artist;
    public final String fileName;
    public final List<RawLyricsData> lyrics;

    public Song(Cursor cursor) {
        _id = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_ID)
        );

        artist = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_ARTIST)
        );

        name = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_NAME)
        );

        res_id = cursor.getInt(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_RES_ID)
        );

        rawLyrics = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_LYRICS)
        );

        final Gson gson = new Gson();
        Type listType = new TypeToken<List<RawLyricsData>>(){}.getType();
        lyrics = gson.fromJson(rawLyrics, listType);

        fileName = cursor.getString(
                cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_FILENAME)
        );

        // score = cursor.getDouble(
        //        cursor.getColumnIndexOrThrow(SongsOpenHelper.SONG_SCORE));
    }

    public void fetchScores(WordsDatastore wordsDatastore) {
        score = wordsDatastore.getSongWordsScore(this._id);
    }

    public LyricsResult getLyricsAtPosition(float position) {
        if (lyrics.size() == 0) return null;
        RawLyricsData atLyric = lyrics.get(0);
        for (RawLyricsData lyric : lyrics) {
            if (lyric.getTime() >= position) {
                double atPosition;
                if (atLyric == lyric) {
                    atPosition = lyrics.get(1).getTime();
                } else {
                    atPosition = atLyric.getTime();
                }
                return new LyricsResult(Math.abs(atPosition - position), atLyric);
            } else {
                atLyric = lyric;
            }
        }
        return new LyricsResult(0, lyrics.get(lyrics.size() - 1));
    }

    public boolean hasScore() {
        return score != null;
    }

    public double getRawTries() {
        return score.number_attempts;
    }
    public double getRawScore() {
        return score.number_successes / score.number_attempts;
    }

    public RawLyricsData getLineAtIndex(int index) {
        return lyrics.get(index);
    }

    public class LyricsResult {
        private final double position;
        private final RawLyricsData lyrics;

        public LyricsResult(double position, RawLyricsData rawLyricsData) {
            this.lyrics = rawLyricsData;
            this.position = position;
        }
        public RawLyricsData getLyrics() {
            return this.lyrics;
        }

        public double getPosition() {
            return position;
        }
    }
}
