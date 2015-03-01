package org.khacks.singandlearn;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.khacks.singandlearn.datastore.Song;
import org.khacks.singandlearn.datastore.SongsDatastore;
import org.khacks.singandlearn.datastore.SongsOpenHelper;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ArrayList<Song> songList;
    private ListView songView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainAct", "oncreate");
        setContentView(R.layout.activity_main);




        songView = (ListView)findViewById(R.id.song_list);
        songList = new ArrayList<>();


        SongsDatastore datastore = new SongsDatastore(this);
        SimpleCursorAdapter songAdapter = new SimpleCursorAdapter(this,R.layout.song, datastore.getAllSongs(),
                new String[]{SongsOpenHelper.SONG_NAME, SongsOpenHelper.SONG_ARTIST},
                new int[] {R.id.song_title, R.id.song_artist}, 0);


        songView.setAdapter(songAdapter);
    }


    // NOT USED YET, WILL BE USED ONCE WE HAVE A REMOTE LYRIC API, sometime
    private void getSongList() {
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);


                //songList.add(new SongOld(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }



}
