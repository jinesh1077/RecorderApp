package com.jin10.recorderapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class playing extends AppCompatActivity{
    private int width,height;
    private ImageButton playBtn,restart;
    private MediaPlayer mediaPlayer;
    private String path;
    private SeekBar seekBar;
    private int mediaPos,mediaMax;
    private Handler handler;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        String save=getIntent().getStringExtra("save");
        path=getIntent().getStringExtra("path");
        //Toast.makeText(playing.this, path, Toast.LENGTH_LONG).show();
        playBtn=(ImageButton) findViewById(R.id.playBtn);
        restart=(ImageButton) findViewById(R.id.restart);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        textView=(TextView) findViewById(R.id.songName);
        textView.setText(save);
        handler= new Handler();


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.7),(int)(height*0.3));
        playsong();





        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    playBtn.setImageResource(R.drawable.ss);
                }else{
                    mediaPlayer.start();

                    mediaPos=mediaPlayer.getCurrentPosition();
                    mediaMax=mediaPlayer.getDuration();
                    seekBar.setMax(mediaMax);
                    seekBar.setProgress(mediaPos);

                    handler.removeCallbacks(moveSeekBarThread);
                    handler.postDelayed(moveSeekBarThread, 100);

                    playBtn.setImageResource(R.drawable.ps);
                }

            }
        });



        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.release();

                playsong();

            }
        });

    }

    public void playsong(){


        mediaPlayer=new MediaPlayer();

        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        playBtn.setImageResource(R.drawable.ps);



        mediaPos=mediaPlayer.getCurrentPosition();
        mediaMax=mediaPlayer.getDuration();
        seekBar.setMax(mediaMax);
        seekBar.setProgress(mediaPos);

        handler.removeCallbacks(moveSeekBarThread);
        handler.postDelayed(moveSeekBarThread, 100);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){

                    mediaPlayer.seekTo(i);
                    seekBar.setProgress(i);




                }



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }
        });


    }

    Runnable moveSeekBarThread = new Runnable() {
        public void run() {
            if(mediaPlayer.isPlaying()){

                int mediaPos_new = mediaPlayer.getCurrentPosition();
                int mediaMax_new = mediaPlayer.getDuration();
                seekBar.setMax(mediaMax_new);
                seekBar.setProgress(mediaPos_new);

                handler.postDelayed(this, 100); //Looping the thread after 0.1 second
            }

        }
    };




    @Override
    protected void onStop () {
        super.onStop();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            mediaPlayer.stop();

        }else{
        mediaPlayer.pause();
        mediaPlayer.stop();
        mediaPlayer.release();}

    }


}
