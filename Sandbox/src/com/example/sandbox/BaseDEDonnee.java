package com.example.sandbox;

import java.util.ArrayList;
import java.util.List;

import Utils.Utils;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
	private String[] parent;
	private int numParent;
	private Spinner spinner2;
	private Button btnSubmit;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_dedonnee);
        
        addItemsOnSpinner2();
        addListenerOnButton();
        
        
        /*****************scollll*******************************/    
        
        ListView listeNom = (ListView) this.findViewById(R.id.mylist);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
        		"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        		"Linux", "OS/2" };
        parent = values;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listeNom.setAdapter(adapter); 
        listeNom.setOnItemClickListener(new ListView.OnItemClickListener(){
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int i, long l){
        		try {
        			Toast.makeText(getApplicationContext(), "youhou : " + Integer.toString(i), 3).show();
        			numParent = i;
        			openOptionsMenu();
        			//TODO Appeler activite suivante avec en paramètre l'id de l'element selectionne
        			//TODO reussir a lier l'id de la liste avec l'id du noeud (metier)     			
        			
        		}
        		catch (Exception e) {
        			Toast.makeText(getApplicationContext(), "osef ", 3).show();
        		}
        	}
        });
//        findViewById(R.id.menu_settings).setOnTouchListener(retourMain);
        
    }
    
    
    public void addItemsOnSpinner2() {
    	 
    	spinner2 = (Spinner) findViewById(R.id.spinner2);
    	List<String> list = new ArrayList<String>();
    	list.add("list 1");
    	list.add("list 2");
    	list.add("list 4");

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
     
//    	    Toast.makeText(BaseDEDonnee.this,
//    		"OnClickListener : " + "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem()),
//    			Toast.LENGTH_SHORT).show();
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
    	String message = parent[numParent];
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
}

