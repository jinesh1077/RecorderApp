package com.jin10.recorderapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class RecordFragment extends Fragment {

    private ImageButton rcdBtn,stopBtn;
    private MediaRecorder recorder;
    private String path,save;
    private File folder;
    private boolean playing=false;
    private boolean pause=false;
    private MyDbHandler dbHandler;
    private int k=0;
    private TextView slot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record,container,false);
        rcdBtn = view.findViewById(R.id.rcdBtn);
        stopBtn = view.findViewById(R.id.stopBtn);
        dbHandler = new MyDbHandler(getContext(),null,null,1);
        slot=view.findViewById(R.id.timpSlot);

        String[] stp=dbHandler.getVal();
        if(stp.length==0){
            k=0;
        }else{
            String sk=stp[stp.length-1];
            k=Integer.parseInt(sk.replace("recording_",""));
            k++;
        }
        folder = Environment.getExternalStorageDirectory();
        save="recording_"+String.valueOf(k);
        rcdBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"done",Toast.LENGTH_SHORT).show();
                if(!playing) {
                    rcdBtn.setImageResource(R.drawable.pause);
                    playing = true;

                    if (!pause) {

                        File f = new File(folder, "RecordsList");
                        if (!f.exists())
                            if (!f.mkdir()) {
                                // Toast.makeText(getContext(), folder+" can't be created.", Toast.LENGTH_LONG).show();

                            } else {
                                // Toast.makeText(getContext(), folder+" can be created.", Toast.LENGTH_LONG).show();
                            }
                        else {
                            // Toast.makeText(getContext(), folder + " already exits.", Toast.LENGTH_LONG).show();
                        }
                        save="recording_"+String.valueOf(k);
                        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RecordsList/" + save + ".mp3";
                        //Toast.makeText(getContext(), path, Toast.LENGTH_LONG).show();

                        setup();

                        try {
                            recorder.prepare();
                            recorder.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        slot.setText("Recording...");
                        //Toast.makeText(getContext(), "Recording...", Toast.LENGTH_SHORT).show();


                    } else {

                       recorder.resume();
                       slot.setText("Recording...");
                       //Toast.makeText(getContext(), "Recording..", Toast.LENGTH_SHORT).show();


                    }
                }else{
                    rcdBtn.setImageResource(R.drawable.rcdimg);
                    recorder.pause();
                    pause=true;
                    playing = false;
                    slot.setText("Paused");
                    //Toast.makeText(getContext(), "Recording Stop", Toast.LENGTH_SHORT).show();

                }




            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.stop();
                pause=false;
                playing=false;
                rcdBtn.setImageResource(R.drawable.rcdimg);
                Toast.makeText(getContext(), "Recorded", Toast.LENGTH_SHORT).show();
                dbHandler.addData(save);
                slot.setText("Recorder");

            }
        });





        return view;


    }

    public void setup(){
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(path);
        k++;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }



}
