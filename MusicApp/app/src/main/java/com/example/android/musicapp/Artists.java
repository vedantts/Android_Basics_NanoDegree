package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Artists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        TextView songs=(TextView)findViewById(R.id.songs);
        TextView playlist=(TextView)findViewById(R.id.playlist);
        TextView genres=(TextView)findViewById(R.id.genre);
        TextView nowplaying=(TextView)findViewById(R.id.now_playing);
        nowplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Artists.this,MainActivity.class);
                startActivity(i);
            }
        });

        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Artists.this,Songs.class);
                startActivity(i);
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Artists.this,Playlist.class);
                startActivity(i);
            }
        });
        genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Artists.this,Genres.class);
                startActivity(i);
            }
        });

    }
}

