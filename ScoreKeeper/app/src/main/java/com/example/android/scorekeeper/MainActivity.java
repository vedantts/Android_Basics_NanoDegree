package com.example.android.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreA=0;
    int scoreB=0;
    int foulA=0;
    int foulB=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayScoreForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayFoulForTeamA(int fouls) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_fouls);
        scoreView.setText(String.valueOf(fouls));
    }
    public void displayScoreForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayFoulForTeamB(int fouls) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_fouls);
        scoreView.setText(String.valueOf(fouls));
    }
    public void addGoalTeamA(View view)
    {
        scoreA=scoreA+1;
        displayScoreForTeamA(scoreA);
    }
    public void addFoulTeamA(View view)
    {
        foulA=foulA+1;
        displayFoulForTeamA(foulA);
    }

    public void addGoalTeamB(View view)
    {
        scoreB=scoreB+1;
        displayScoreForTeamB(scoreB);
    }
    public void addFoulTeamB(View view)
    {
        foulB=foulB+1;
        displayFoulForTeamB(foulB);
    }

    public void reset(View V)
    {
        scoreA=0;
        scoreB=0;
        foulB=0;
        foulA=0;
        displayScoreForTeamA(scoreA);
        displayScoreForTeamB(scoreB);
        displayFoulForTeamA(foulA);
        displayFoulForTeamB(foulB);
    }
}
