package com.jin10.songtel;

import android.Manifest;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.cmc.music.common.ID3WriteException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> songList;
    private ListView songView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                return;
            }
        }



        songList = new ArrayList<Song>();

        getSongList();


        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.get_title().compareTo(b.get_title());
            }
        });

        songView=(ListView)findViewById(R.id.songView);

        SongAdapter adapter = new SongAdapter(this,songList);
        songView.setAdapter(adapter);







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getSongList()  {

        ContentResolver musicRes = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = musicRes.query(musicUri, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            int songTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songId = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int songArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songAlbum = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);


            do {

                int _Id = cursor.getInt(songId);
                String _Title = cursor.getString(songTitle);
                String _Artist = cursor.getString(songArtist);
                String _album = cursor.getString(songAlbum);

                //String songPath = String.valueOf(MediaStore.Audio.Media.getContentUriForPath(_Title));
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();

                String songPath = String.valueOf(MediaStore.Audio.Media.ALBUM);


                File src = new File(songPath+"");
                org.cmc.music.metadata.MusicMetadataSet src_set = null;
                try {
                    src_set = new org.cmc.music.myid3.MyID3().read(src);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } // read metadata

                if (src_set == null) // perhaps no metadata
                {
                    Log.i("NULL", "NULL");
                }
                else
                {
                    try{
                        org.cmc.music.metadata.IMusicMetadata metadata = src_set.getSimplified();
                        String artist = metadata.getArtist();
                        String album = metadata.getAlbum();
                        String song_title = metadata.getSongTitle();
                        Number track_number = metadata.getTrackNumber();
                        songList.add(new Song(_Id, songPath, _Artist, album));
                        Log.i("artist", artist);
                        Log.i("album", album);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                   /* File dst = new File("");
                    org.cmc.music.metadata.MusicMetadata meta = new org.cmc.music.metadata.MusicMetadata("name");

                    //meta.setAlbum("Chirag");
                    //meta.setArtist("CS");
                    try {
                        new org.cmc.music.myid3.MyID3().write(src, dst, src_set, meta);
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }  // write updated metadata
                    catch (ID3WriteException e) {
                        e.printStackTrace();
                    }*/

                }


                // if(_album.contains("[muzmo.ru]")) {



                //}
            } while (cursor.moveToNext());

        }

    }


}
