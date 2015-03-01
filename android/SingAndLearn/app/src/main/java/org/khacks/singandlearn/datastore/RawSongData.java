package org.khacks.singandlearn.datastore;

import java.util.List;
import java.util.Map;

/**
 * Created by iain on 3/1/15.
 */
public class RawSongData {
    public String title;
    public String artist;
    public String album;

    public Map<String, RawWordData> words;
    public List<RawLyricsData> lyrics;
    public String id;
}
