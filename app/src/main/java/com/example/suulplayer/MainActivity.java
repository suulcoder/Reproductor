/*Informacion tomada de:
https://code.tutsplus.com/tutorials/create-a-music-player-on-android-project-setup--mobile-22764
 */

package com.example.suulplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.suulplayer.adapter.SongAdapter;
import com.example.suulplayer.models.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<Song> songList;//Array con la lista de cancion
    private ListView listView;//Vista en activity_main.xml
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_play:
                    mTextMessage.setText("Play");
                    return true;
                case R.id.navigation_back:
                    mTextMessage.setText("Back");
                    return true;
                case R.id.navigation_first:
                    mTextMessage.setText("First");
                    return true;
                case R.id.navigation_last:
                    mTextMessage.setText("Last");
                    return true;
                case R.id.navigation_next:
                    mTextMessage.setText("Next");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getList();//Obtenemos la lista

        Collections.sort(songList, new Comparator<Song>(){//Desplegamos esta informacion en la interfaz de usuario.
            public int compare(Song primera, Song segunda){
                return primera.getTitle().compareTo(segunda.getTitle());
            }
        });

        SongAdapter songAdt = new SongAdapter(this, songList);
        listView.setAdapter(songAdt);
    }

    public void getList() {
        //Creamos una instancia del cursor, para tener la lista de las canciones presentes en el telefono.
        ContentResolver musicResolver = getContentResolver();
        Uri music = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(music, null, null, null, null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //agregamos cancion a la lista
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }

}
