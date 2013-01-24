package com.example.sandbox;
import Utils.*;

import com.example.NoeudsSQLite.*;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static String NAME = "Name_stub";
	public static String QRCODE = "QR_stub";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try
		{
			NoeudsBDD nbdd = new NoeudsBDD(this);
			nbdd.open();
			Cursor c = nbdd.getBDD().rawQuery("select * from TABLE_NOEUDS;", null);
			Utils.textViewDebug(this, this, R.id.main_layout, "Lignes dans TABLE_NOEUDS : "+c.getCount());
			nbdd.close();
		}
		catch (Exception e)
		{
			Utils.popDebug(this, "Exception : " + e.getMessage());
		}

	}

	public void onResume()
	{
		super.onResume();
		NAME = "Name_stub";
		QRCODE = "QR_stub";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void sendMessage(View view){

		Intent intent = new Intent(this, DisplayMessageActivity.class);

		EditText name = (EditText) findViewById(R.id.isbn_edit_message);
		EditText qrcode = (EditText) findViewById(R.id.title_edit_message);

		if (!name.getText().toString().equals(""))
		{
			NAME = name.getText().toString();
		}
		if (!qrcode.getText().toString().equals(""))
		{
			QRCODE = qrcode.getText().toString();
		}

		try {
			
			NoeudsBDD nbdd = new NoeudsBDD(this);
			Utils.textViewDebug(this, this, R.id.main_layout, "Bd instanciée");
			nbdd.open();
			Utils.textViewDebug(this, this, R.id.main_layout, "Bd ouverte");
			Utils.textViewDebug(this, this, R.id.main_layout, "Noeud créé");
			Utils.textViewDebug(this, this, R.id.main_layout, "Nb noeuds : " + nbdd.getNbNoeuds());	
			Utils.textViewDebug(this, this, R.id.main_layout, "Résultat de l'insert : " + nbdd.insertNoeudTests("Nom", "Code", 0, 0));
			Utils.textViewDebug(this, this, R.id.main_layout, "Nb noeuds : " + nbdd.getNbNoeuds());
			nbdd.close();
			Utils.textViewDebug(this, this, R.id.main_layout, "Bd fermée");

		}
		catch (Exception e)
		{
			Utils.textViewDebug(this, this, R.id.main_layout, e.getMessage());
		}
		//Utils.popDebug(this, "Main Activity ok");
		startActivity(intent);
	}
}
