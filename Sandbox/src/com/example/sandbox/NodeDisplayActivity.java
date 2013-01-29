package com.example.sandbox;

import java.util.ArrayList;

import com.example.NoeudsSQLite.Noeud;
import com.example.NoeudsSQLite.NoeudsBDD;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NodeDisplayActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);    

		try 
		{
			setContentView(R.layout.activity_display_noeuds);

			View linearLayout = findViewById(R.id.received_message_layout);

			NoeudsBDD nbdd = new NoeudsBDD(this);
			nbdd.open();

			int nbLignes = nbdd.getNbNoeuds();
			ArrayList<Button> buttons = new ArrayList<Button>();

			for (int i = 0; i < nbLignes; i ++)
			{
				Noeud tmp = nbdd.getNoeudById(i);
				buttons.add(new Button(this));
				Button btmp = buttons.get(buttons.size() - 1);
				btmp.setText("Noeud : " + tmp.getId() +" " + tmp.getNom() + "\n " 
						+ "Id père : " + tmp.getPere());
				btmp.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT));
			}
			nbdd.close();

			for (Button btn : buttons)
			{
				((ViewGroup) linearLayout).addView(btn);
			}


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
