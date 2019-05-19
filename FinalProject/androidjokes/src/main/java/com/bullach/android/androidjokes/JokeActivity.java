package com.bullach.android.androidjokes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView textview = (TextView) findViewById(R.id.tvJoke);

        //Retrieve the joke from the Intent Extras
        String JokeResult = null;
        Intent intent = getIntent();
        JokeResult = intent.getStringExtra(getString(R.string.jokeExtra));

        if (JokeResult != null) {
            textview.setText(JokeResult);
        } else {
            textview.setText(R.string.fallbackJoke);
        }
    }
}
