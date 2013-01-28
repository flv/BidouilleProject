package com.example.sandbox;

import com.example.NoeudsSQLite.Metadata;
import com.example.NoeudsSQLite.NoeudsBDD;

import Utils.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MetaInsertActivity extends Activity {
	private static String META_ID = "0";
	private static String META_TYPE = "Generic metadata type";
	private static String META_CONTENT = "Generic metadata content";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_meta);
	}
	
	public void onResume()
	{
		super.onResume();
		META_ID = "0";
		META_TYPE = "Generic metadata type";
		META_CONTENT = "Generic metadata content";
	}
	
	public void createMeta(View view)
	{
		Intent intent = new Intent(this, MetaDisplayActivity.class);
		EditText id = (EditText) findViewById(R.id.metaIdTextView);
		EditText type = (EditText) findViewById(R.id.metaTypeTextView);
		EditText content = (EditText) findViewById(R.id.metaContentTextView);

		if (!id.getText().toString().equals(""))
		{
			META_ID = id.getText().toString();
		}
		if (!type.getText().toString().equals(""))
		{
			META_TYPE = type.getText().toString();
		}
		if (!content.getText().toString().equals(""))
		{
			META_CONTENT = content.getText().toString();
		}
		
		try 
		{
			NoeudsBDD nbdd = new NoeudsBDD(this);
			nbdd.open();
			nbdd.insertMeta(new Metadata(Integer.parseInt(META_ID), META_TYPE, META_CONTENT));
			nbdd.close();
			startActivity(intent);
		}
		catch (Exception e)
		{
			Utils.textViewDebug(this, this, R.id.meta_debug_layout, e.getMessage());
		}
	}

}
