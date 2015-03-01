package org.khacks.singandlearn;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.khacks.singandlearn.datastore.SingToLearnOpenHelper;
import org.khacks.singandlearn.datastore.SongsDatastore;
import org.khacks.singandlearn.datastore.SongsOpenHelper;


public class MainActivity extends Activity {

    private ListView songLV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainAct", "oncreate");
        setContentView(R.layout.activity_main);





        songLV = (ListView)findViewById(R.id.song_list);
        songLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, TestActivity.class);

                SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);
                int colnum = cursor.getColumnIndex(SongsOpenHelper.SONG_ID);

                i.putExtra(TestActivity.SONG_ID, cursor.getString(colnum));
                startActivity(i);
            }
        });

        SongsDatastore datastore = new SongsDatastore(this);
        SimpleCursorAdapter songAdapter = new SimpleCursorAdapter(this,R.layout.song, datastore.getAllSongs(),
                new String[]{SongsOpenHelper.SONG_NAME, SongsOpenHelper.SONG_ARTIST},
                new int[] {R.id.song_title, R.id.song_artist}, 0);


        songLV.setAdapter(songAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Init DB");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("Init DB")){
            SingToLearnOpenHelper.getInstance(this).onSetup();
            return  true;
        }
        return false;
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
