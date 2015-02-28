package org.khacks.singandlearn.datastore;

import android.database.Cursor;

/**
 * Created by iain on 2/28/15.
 */
public class Word {
    String id;
    String word;
    String song;
    String correct;
    boolean is_star;
    int attempts;
    String translation;
    String similar_list;

    public Word(Cursor cursor) {

    }
    public Word(String word, String song, String translation, String[] similar_list) {

    }
}
