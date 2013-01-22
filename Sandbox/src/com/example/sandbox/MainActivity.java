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
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public final static String TEST_MESSAGE = "Test";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

		LivresBDD livreBdd = new LivresBDD(this);
		Livre livre = new Livre("045446831", "GoT");
		Livre livre2 = new Livre("021546843", "GoT2");
		
		livreBdd.open();
		livreBdd.insertLivre(livre);
		livreBdd.insertLivre(livre2);
		livreBdd.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void sendMessage(View view){
    	
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	intent.putExtra(TEST_MESSAGE, "Test");
    	
    	startActivity(intent);
    }
}
