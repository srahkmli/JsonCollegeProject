package com.example.jsontry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class musicplayer extends AppCompatActivity {


    RecyclerView recyclerView;
    MymusicAdaptor MymusicAdaptor;
    List<com.example.jsontry.musics> musics;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);

        Intent intent = getIntent();
        musics = new ArrayList<>();
        setMusicValues();
        recyclerView = findViewById(R.id.RView);
        MymusicAdaptor = new MymusicAdaptor(getApplicationContext(),musics );
        recyclerView.setAdapter(MymusicAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }


    public void setMusicValues(){

        int[] paths = getResources().getIntArray(R.array.paths);
        String[] name = getResources().getStringArray(R.array.names);
        musics.clear();
        for (int i=0;i<4;i++){
            musics music = new musics(name[i],paths[i]);
            musics.add(music);
        }
    }

}