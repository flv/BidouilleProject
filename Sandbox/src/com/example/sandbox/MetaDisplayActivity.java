package com.example.sandbox;

import java.util.ArrayList;

import Utils.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
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
		
		try 
		{
			views = new ArrayList<TextView>();
			NoeudsBDD nbdd = new NoeudsBDD(this);
			nbdd.open();
			int nbNoeuds = nbdd.getNbNoeuds();
			//Utils.popDebug(this, "nbNoeuds dans la bd: " + nbNoeuds);
			for (int i = 0; i < nbNoeuds; i ++)
			{
				//Utils.popDebug(this, "boucle : i=" + i);

				metas = nbdd.getMetasById(i);
				//Utils.popDebug(this, "metas == null ? " + metas.equals(null));
				if (! metas.equals(null)) {
					for (Metadata meta : metas)
					{	
						views.add(new TextView(this));
						TextView tmp = views.get(views.size()-1);
						tmp.setText("Metadonnée : " + meta.toString());
						tmp.setLayoutParams(new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
					}
				}

			}

			for (TextView text : views)
			{
				((ViewGroup) linearLayout).addView(text);
			}
			nbdd.close();

		}
		catch(Exception e)
		{
			Utils.textViewDebug(this, this, R.id.meta_display_debug, "Exception : " + e.getMessage());
		}
	}


}
