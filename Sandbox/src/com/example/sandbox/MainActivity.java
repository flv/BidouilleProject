package com.example.sandbox;

import Utils.*;

import com.example.sqllite.Livre;
import com.example.sqllite.LivresBDD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static String ISBN = "Nom ";
    public static String TITLE = "Description";
    public static String PARENT = "parent";
    public static String parent = "Entre un parent";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        EditText paramParent = null;
        paramParent = (EditText)findViewById(R.id.parent_edit_message);
        
        Intent my_intent = getIntent();
        parent = my_intent.getStringExtra(BaseDEDonnee.EXTRA_MESSAGE);
        Toast.makeText(MainActivity.this, parent, Toast.LENGTH_SHORT).show();
        if(parent != null && parent != "Entre en parent")
        	paramParent.setHint(parent);
        
        
        try {
        	LivresBDD lbdd = new LivresBDD(this);
        	lbdd.open();
        	lbdd.raz();
        	lbdd.close();
        }
        catch (Exception e)
        {
        	Utils.popDebug(this, "Exception : " + e.getMessage());
        }
        
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
    	EditText parent = (EditText) findViewById(R.id.parent_edit_message);
    	if (!isbn.getText().toString().equals(""))
    	{
    		ISBN = isbn.getText().toString();
    	}
    	if (!title.getText().toString().equals(""))
    	{
    		TITLE = title.getText().toString();
    	}
    	if (!parent.getText().toString().equals(""))
    	{
    		PARENT = parent.getText().toString();
    	}
    	
    	LivresBDD livreBdd = new LivresBDD(this);
    	livreBdd.open();
    	/****prendre parent dans la base de donné****/
    	livreBdd.insertLivre(new Livre(ISBN, TITLE));
    	livreBdd.close();    	
    	
    	startActivity(intent);
    }
    
    public void goToAccueil(View view) {
    	Intent intent = new Intent(this, Accueil.class);
    	
    	startActivity(intent);
    }
    
    public void rechercherParent(View view) {
    	Intent intent = new Intent(this, BaseDEDonnee.class);
    	
    	startActivity(intent);
    }
    
    public void validerDonnées(View view) {
    	Intent intent = new Intent(this, PriseDuQrcode.class);
    	
    	startActivity(intent);
    }
}
