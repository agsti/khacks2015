package org.khacks.singandlearn;


class SongOld {
    private long id;
    private String title;
    private String artist;

    public SongOld(long songID, String songTitle, String songArtist) {
        id = songID;
        title = songTitle;
        artist = songArtist;
    }

    public long getID() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getArtist(){
        return artist;
    }
}