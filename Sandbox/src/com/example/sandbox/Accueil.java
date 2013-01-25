package com.example.sandbox;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Accueil extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_accueil, menu);
        return true;
    }
    
    public void goToBiduleBDD(View view) {
    	Intent intent = new Intent(this, BaseDEDonnee.class);
    	startActivity(intent);
    }
    
    public void goToTakeQrcode(View view) {
    	Intent intent = new Intent(this, PriseDuQrcode.class);
    	startActivity(intent);
    }
}
