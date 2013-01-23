package com.example.sandbox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class PriseDuQrcode extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise_du_qrcode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_prise_du_qrcode, menu);
        return true;
    }
    
    public void goToAccueil(View view) {
    	Intent intent = new Intent(this, Accueil.class);
    	
    	startActivity(intent);
    }
    
    public void goToTakeInformation(View view) {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }

}
