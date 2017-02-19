package com.example.android.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Playlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        TextView songs=(TextView)findViewById(R.id.songs);
        TextView artists=(TextView)findViewById(R.id.artist);
        TextView genres=(TextView)findViewById(R.id.genre);
        TextView nowplaying=(TextView)findViewById(R.id.now_playing);
        nowplaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Playlist.this,MainActivity.class);
                startActivity(i);
            }
        });

        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Playlist.this,Songs.class);
                startActivity(i);
            }
        });
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Playlist.this,Artists.class);
                startActivity(i);
            }
        });
        genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Playlist.this,Genres.class);
                startActivity(i);
            }
        });


    }
}
