package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Genres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        TextView songs=(TextView)findViewById(R.id.songs);
        TextView artists=(TextView)findViewById(R.id.artist);
        TextView nowplaying=(TextView)findViewById(R.id.now_playing);
        TextView playlist=(TextView)findViewById(R.id.playlist);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Genres.this,Songs.class);
                startActivity(i);
            }
        });
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Genres.this,Artists.class);
                startActivity(i);
            }
        });
        nowplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Genres.this,MainActivity.class);
                startActivity(i);
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Genres.this,Playlist.class);
                startActivity(i);
            }
        });
    }
}
