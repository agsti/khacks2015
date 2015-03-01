package org.khacks.singandlearn.datastore;

import java.util.List;

/**
 * Created by iain on 3/1/15.
 */
public class RawLyricsData {
    private int i;
    private double c;
    private String t;
    public List<String> l;
    public String getText() {
        return t;
    }
    public double getTime() {
        return c;
    }
    public int getIndex() {
        return i;
    }
    public List<String> getList() { return l; }
}
