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
			nbdd.raz();
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

	public void toMeta(View view) {
		Intent intent = new Intent(this, MetaInsertActivity.class);
		startActivity(intent);
	}
	
	public void sendMessage(View view){

		Intent intent = new Intent(this, NodeDisplayActivity.class);

		EditText name = (EditText) findViewById(R.id.node_name_message);
		EditText qrcode = (EditText) findViewById(R.id.qrcode_content_message);

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
			nbdd.open();
			nbdd.insertNoeud(new Noeud(NAME, QRCODE, 0, nbdd.getNbNoeuds()));
			nbdd.close();

		}
		catch (Exception e)
		{
			Utils.textViewDebug(this, this, R.id.main_layout, e.getMessage());
		}
		startActivity(intent);
	}
}
