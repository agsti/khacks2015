package org.khacks.singandlearn.datastore;

import java.util.List;

/**
 * Created by iain on 3/1/15.
 */
public class RawWordData {
    public int seen;
    public int complexity;
    public double score;
    public String word;

    public String song_id;
    public List<WordAtRawData> at;

    public class WordAtRawData {
        int i;
        String r;
    }
}
