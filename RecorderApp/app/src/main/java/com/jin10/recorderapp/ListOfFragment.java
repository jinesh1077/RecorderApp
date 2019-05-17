package com.jin10.recorderapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class ListOfFragment extends Fragment {

    private ListView songView;
    private MyDbHandler dbHandler;
    private MediaPlayer mediaPlayer;
    private String[] str;
    private String path;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_list_of,container,false);
        songView=view.findViewById(R.id.songView);
        dbHandler = new MyDbHandler(getContext(),null,null,1);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RecordsList/";


        str=dbHandler.getVal();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),R.layout.list_design,R.id.songTitle,str);
        songView.setAdapter(adapter1);

        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = str[position];
                String st=path+item+".mp3";

                Intent i = new Intent(getContext(),playing.class);
                i.putExtra("save",item);
                i.putExtra("path",st);
                startActivity(i);

                /*
                mediaPlayer=new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(st);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();*/


            }
        });


        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }


}
