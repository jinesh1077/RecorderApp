package com.jin10.songtel;


public class Song {

    private int _id;
    private String _title;
    private String _artist;
    private String _album;


    public Song(int songId, String songTitle, String songArtist,String songAlbum) {
        _id = songId;
        _artist = songArtist;
        _title = songTitle;
        _album = songAlbum;

    }

    public int getId(){
        return _id;
    }

    public String get_title(){
        return _title;
    }

    public String get_artist(){
        return _artist;
    }

    public String get_album(){
        return _album;
    }

}
