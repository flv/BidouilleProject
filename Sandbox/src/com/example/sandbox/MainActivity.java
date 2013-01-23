package com.example.sandbox;

import com.example.sqllite.Livre;
import com.example.sqllite.LivresBDD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    public static String ISBN = "ISBN stub";
    public static String TITLE = "Title stub";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void sendMessage(View view){
    	
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	
    	EditText isbn = (EditText) findViewById(R.id.isbn_edit_message);
    	EditText title = (EditText) findViewById(R.id.title_edit_message);
    	
    	if (!isbn.toString().equals(null))
    	{
    		ISBN = isbn.toString();
    	}
    	if (!title.toString().equals(null))
    	{
    		TITLE = title.toString();
    	}
    	
    	LivresBDD livreBdd = new LivresBDD(this);
    	livreBdd.open();
    	livreBdd.insertLivre(new Livre(ISBN, TITLE));
    	livreBdd.close();    	
    	
    	startActivity(intent);
    }
}
