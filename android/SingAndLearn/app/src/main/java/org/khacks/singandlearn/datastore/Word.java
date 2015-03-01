package org.khacks.singandlearn.datastore;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by iain on 2/28/15.
 */
public class Word {

    private List<RawWordData.WordAtRawData> at;


    String id;
    private String word;
    private String song;
    boolean is_star;
    int correct;
    int attempts;

    double score;
    int seen;
    int complexity;

    String translation;
    private boolean attempt;

    public Word(Cursor cursor) {
        this.word = cursor.getString(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_WORD));
        this.song = cursor.getString(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_SONG)
        );
        this.is_star = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_ISSTAR)
        ) != 0;
        this.correct = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_CORRECT)
        );
        this.attempts = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_ATTEMPTS)
        );
        this.translation = cursor.getString(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_TRANSLATION)
        );

        this.complexity = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_COMPLEXITY)
        );
        this.seen = cursor.getInt(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_SEEN)
        );
        this.score = cursor.getDouble(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_SCORE)
        );

        String atRaw = cursor.getString(
                cursor.getColumnIndexOrThrow(WordsOpenHelper.WORD_AT)
        );

        Gson gson = new Gson();
        Type deseralizeType = new TypeToken<List<RawWordData.WordAtRawData>>(){}.getType();
        this.at = gson.fromJson(atRaw, deseralizeType);
    }

    public Word(String word, String song, String translation, String[] similar_list) {

    }

    public QuizInfo getQuiz(SongsDatastore songsDatastore, Song song) {
        int songIndex = this.at.get(0).i;
        RawLyricsData lyricLine = song.getLineAtIndex(songIndex);
        String text = lyricLine.getText();
        int blankStarts = text.indexOf(this.at.get(0).r);
        int blankEnds = blankStarts + this.at.get(0).r.length();
        String blankedText = text;
        return new QuizInfo(text, blankStarts, blankEnds, blankedText, lyricLine);
    }

    public String getWord() {
        return word;
    }

    public  String getSong() {
        return song;
    }


    public void setAttempt(boolean attempt) {
        this.attempts += 1;
        if (attempt) {
            this.correct += 1;
        }
    }

    public class QuizInfo {

        public final int blankStarts;
        public final String text;
        public final int blankEnds;
        public final String blankedText;
        public final RawLyricsData lyrics;

        public QuizInfo(String text, int blankStarts, int blankEnds, String blankedText, RawLyricsData lyricLine) {
            this.text = text;
            this.blankStarts = blankStarts;
            this.blankEnds = blankEnds;
            this.blankedText = blankedText;
            this.lyrics = lyricLine;
        }
    }
}
