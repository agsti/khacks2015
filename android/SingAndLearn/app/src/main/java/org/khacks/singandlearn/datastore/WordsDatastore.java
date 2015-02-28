package org.khacks.singandlearn.datastore;

import java.util.ArrayList;

/**
 * Created by iain on 2/28/15.
 */
public class WordsDatastore extends SingToLearnDatastore {
    public ArrayList<Word> getWordsStatus(String songId) {
        // fetch all words matching song with a nonzero score
        // serialize and return as Word objects w/ a score and attempts
        return new ArrayList<Word>();
    }
    public int getSongScore(String songId) {
        // fetch the overall score for all words in the song (averging)
        // return the avg score
        return 0; // range 0 - 100 as an integer
    }
    public Word getWordChallenge(String wordId) {
        // fetch the word with the word id
        // load the related definitions from datastore
        // load the translation
        return null;
    }



}
