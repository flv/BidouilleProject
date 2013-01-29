package com.example.sandbox;

import java.util.ArrayList;

import Utils.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.example.NoeudsSQLite.Metadata;
import com.example.NoeudsSQLite.NoMatchableNodeException;
import com.example.NoeudsSQLite.NoeudsBDD;

public class MetaDisplayActivity extends Activity{
	
	private static ArrayList<TextView> views;
	private static Metadata[] metas;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_meta);
		View linearLayout = findViewById(R.id.meta_display_layout);
		metas = new Metadata[0];
		try 
		{
			views = new ArrayList<TextView>();
			ArrayList<Button> buttons = new ArrayList<Button>();
			NoeudsBDD nbdd = new NoeudsBDD(this);
			nbdd.open();
			int nbNoeuds = nbdd.getNbNoeuds();
			//Utils.popDebug(this, "nbNoeuds dans la bd: " + nbNoeuds);
			// On cherche pour chaque noeud toutes ses métadatas
			for (int i = 0; i < nbNoeuds; i ++)
			{

				metas = nbdd.getMetasById(i);
				if (metas.length != 0) {
					for (Metadata meta : metas)
					{
						buttons.add(new Button(this));				
						Button btmp = buttons.get(buttons.size() - 1);
						btmp.setText("Metadonnée : " + meta.getId() + " type " + meta.getType() + " \n" 
													 + "Contenu : " + meta.getData());
						btmp.setLayoutParams(new LayoutParams(
								LayoutParams.FILL_PARENT,
								LayoutParams.WRAP_CONTENT));
						
						
						/*views.add(new TextView(this));
						TextView tmp = views.get(views.size()-1);
						tmp.setText("Metadonnée : " + meta.toString());
						tmp.setLayoutParams(new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
						*/
						
					}
				}

			}
/*
			for (TextView text : views)
			{
				((ViewGroup) linearLayout).addView(text);
			}*/
			
			for (Button btn : buttons)
			{
				((ViewGroup) linearLayout).addView(btn);
			}
			nbdd.close();

		}
		catch(Exception e)
		{
			Utils.textViewDebug(this, this, R.id.meta_display_debug, "Exception : " + e.getMessage());
		}
	}
	
	public void onResume() 
	{
		super.onResume();
		metas = new Metadata[0];
	}


}
