package org.khacks.singandlearn;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import org.khacks.singandlearn.datastore.SingToLearnOpenHelper;
import org.khacks.singandlearn.datastore.Song;
import org.khacks.singandlearn.datastore.SongsDatastore;
import org.khacks.singandlearn.datastore.Word;
import org.khacks.singandlearn.datastore.WordsDatastore;

/**
 * Created by iain on 3/1/15.
 */
public class TestSongsDatabase extends AndroidTestCase {
    private SongsDatastore songsDatastore;
    private WordsDatastore wordsDatastore;

    public void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        songsDatastore = new SongsDatastore(context);
        wordsDatastore = new WordsDatastore(context);
    }
    public void testGetEntries() {
        SingToLearnOpenHelper.getInstance(getContext()).onSetup();
        assertEquals(songsDatastore.getAllSongs().getCount(), 5);
        Song song = songsDatastore.getSong("LY6be37788-bfb7-11e4-a9a8-20c9d0465fa9");
        Word word = wordsDatastore.getRandomWords(1, song._id)[0];
        Word.QuizInfo quiz = word.getQuiz(songsDatastore, song);

        Log.i("SONG", song._id);
    }
}