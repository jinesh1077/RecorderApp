package com.jin10.songdo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.cmc.music.common.ID3WriteException;
import org.cmc.music.metadata.IMusicMetadata;
import org.cmc.music.metadata.MusicMetadata;
import org.cmc.music.metadata.MusicMetadataSet;
import org.cmc.music.myid3.MyID3;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> songList;
    private ListView songView;
    private String artist;
    private String album ;
    private String song_title;


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

        songView = (ListView) findViewById(R.id.songView);

        SongAdapter adapter = new SongAdapter(this, songList);
        songView.setAdapter(adapter);


    }


    private void getSongList() {

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
                String _path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                if(_path.contains("muzmo_ru")) {

                    String str=_path;

                    int a=str.indexOf("muzmo");
                    String src=str.substring(0,a);


                    int b;
                    String s1=_path.substring(a,_path.length());
                    String s2=s1;
                    s2=s2.replace("muzmo_ru_","");
                    b=s2.lastIndexOf("_");
                    s2=s2.substring(0,b)+".mp3";
                    s2=s2.replaceAll("_","");

                    File from = new File(src+"",s1+"");
                    File to = new File(src+"",s2+"");

                    from.renameTo(to);



                    songList.add(new Song(_Id, s2, _Artist, s1));

                    //songList.add(new Song(_Id, _path, _Artist, _path));

                    //songList.add(new Song(_Id, _Title, _Artist, _album));
                }
            }while (cursor.moveToNext());

        }


    }





}
