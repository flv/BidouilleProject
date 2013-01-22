package com.example.sandbox;

import com.example.sqllite.*;

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

abstract class Utils {

	public static void popDebug(Context context, String message) {
		Toast.makeText(context, message, 1000).show();
	}

}

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
			
			String message = my_intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

			String message2 = my_intent.getStringExtra(MainActivity.TEST_MESSAGE);
			
			// Utilisation de la bd sqlite
			
			LivresBDD livreBdd = new LivresBDD(this);
			livreBdd.open();
			TextView book1 = new TextView(this);
			TextView book2 = new TextView(this);
			book1.setText(livreBdd.getLivreWithTitre("GoT").toString());
			book2.setText(livreBdd.getLivreWithTitre("GoT2").toString());
			book1.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			book2.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			// Create the text views
			TextView textView = new TextView(this);
			textView.setTextSize(40);
			textView.setText(message);
			textView.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			
			TextView textView2 = new TextView(this);
			textView2.setTextSize(40);
			textView2.setText(message2);
			textView2.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));

			// Add the text views to the activity layout

			((ViewGroup) linearLayout).addView(textView);
			((ViewGroup) linearLayout).addView(textView2);
			((ViewGroup) linearLayout).addView(book1);
			((ViewGroup) linearLayout).addView(book2);
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
