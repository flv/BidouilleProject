package com.example.sandbox;

import com.example.sqllite.*;
import Utils.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);    
		
		try 
		{
			setContentView(R.layout.activity_display_message);
			
			View linearLayout = findViewById(R.id.received_message_layout);
			
			// Get origin intent
			Intent my_intent = getIntent();
			
			// Utilisation de la bd sqlite
			
			LivresBDD livreBdd = new LivresBDD(this);
			livreBdd.open();
			
			//Utils.popDebug(this, "Bd ouverte");
			int nbLignes = livreBdd.getNbLivre();
			Utils.popDebug(this, "nbLignes obtenu : " + nbLignes);
			TextView[] books = new TextView[nbLignes];
			Utils.popDebug(this, "TextView[] créé");
			for (int i = 0; i < nbLignes; i ++)
			{
				Utils.popDebug(this, "Passage dans la boucle " + i);
				// Create the text view
				books[i] = new TextView(this);
				Utils.popDebug(this, "TextView créé");
				books[i].setText(livreBdd.getLivreWithId(i).toString());
				Utils.popDebug(this, "GetLivreWithId effectué : " + livreBdd.getLivreWithId(i).toString());
				books[i].setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				Utils.popDebug(this, "LayoutParams set");
				
				// Add the text view to the activity layout
				
				((ViewGroup) linearLayout).addView(books[i]);
				Utils.popDebug(this, "TextView ajouté au layout");
			}
			
			livreBdd.close();
			


		}
		catch (Exception e)
		{
			Utils.popDebug(this, "Exception : " + e.getMessage());
		}

	}

	/*@Override
	public void onResume(){

		super.onResume();

	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_display_message, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }*/
		return super.onOptionsItemSelected(item);
	}

}
