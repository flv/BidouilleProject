package com.example.sandbox;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Accueil extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_accueil, menu);
        return true;
    }
}
