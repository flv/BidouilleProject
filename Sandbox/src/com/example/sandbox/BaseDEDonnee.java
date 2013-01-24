package com.example.sandbox;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class BaseDEDonnee extends Activity {
	public final static String EXTRA_MESSAGE = "null";
	public String parent = "null";
	private Spinner spinner2;
	private Button btnSubmit;
	private RadioGroup radioView;
	private RadioButton radioButton;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_dedonnee);
        
        addItemsOnSpinner2();
        addListenerOnButton();
        
        /*****************scollll*******************************/
        radioView = (RadioGroup) this.findViewById(R.id.radioAterissage);
        
        for(int i=0;i<=10;i++){
        	radioButton = new RadioButton(this);
        	radioButton = (RadioButton) findViewById(R.id.radioButton);
        	radioButton.setText("sadfasdfasdfasdfasdfasd fasdfsadfsadf");
        }
        
        
    }
    
    public void addItemsOnSpinner2() {
    	 
    	spinner2 = (Spinner) findViewById(R.id.spinner2);
    	List<String> list = new ArrayList<String>();
    	list.add("list 1");
    	list.add("list 2");
    	list.add("list 3");

    	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner2.setAdapter(dataAdapter);
      }
    
   
     
      // get the selected dropdown list value
      public void addListenerOnButton() {
     
    	spinner2 = (Spinner) findViewById(R.id.spinner2);
    	btnSubmit = (Button) findViewById(R.id.btnSubmit);
     
    	btnSubmit.setOnClickListener(new OnClickListener() {
     
    	  @Override
    	  public void onClick(View v) {
     
    	    Toast.makeText(BaseDEDonnee.this,
    		"OnClickListener : " + "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
    			Toast.LENGTH_SHORT).show();
    	  }
     
    	});
    	
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_base_dedonnee, menu);
        return true;
    }
    
    public void goToAccueil(View view) {
    	Intent intent = new Intent(this, Accueil.class);
    	startActivity(intent);
    }
    
    public void retourParent(View view) {
    	Intent intent = new Intent(this, MainActivity.class);
    	String message = parent = String.valueOf(spinner2.getSelectedItem());
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
}

