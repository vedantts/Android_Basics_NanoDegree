package com.example.android.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView songs=(TextView)findViewById(R.id.songs);
        TextView artists=(TextView)findViewById(R.id.artist);
        TextView genres=(TextView)findViewById(R.id.genre);
        TextView playlist=(TextView)findViewById(R.id.playlist);
        ImageView bwd=(ImageView)findViewById(R.id.bwd);
        ImageView play=(ImageView)findViewById(R.id.play);
        ImageView pause=(ImageView)findViewById(R.id.pause);
        ImageView fwd=(ImageView)findViewById(R.id.fwd);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Songs.class);
                startActivity(i);
            }
        });
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Artists.class);
                startActivity(i);
            }
        });
        genres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Genres.class);
                startActivity(i);
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Playlist.class);
                startActivity(i);
            }
        });
        bwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t=Toast.makeText(getApplicationContext(),"Previous song",Toast.LENGTH_SHORT);
                t.show();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t=Toast.makeText(getApplicationContext(),"Play song",Toast.LENGTH_SHORT);
                t.show();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t=Toast.makeText(getApplicationContext(),"Pause song",Toast.LENGTH_SHORT);
                t.show();
            }
        });
        fwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t=Toast.makeText(getApplicationContext(),"Play next song",Toast.LENGTH_SHORT);
                t.show();
            }
        });


    }
}

