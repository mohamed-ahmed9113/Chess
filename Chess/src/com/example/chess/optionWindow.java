package com.example.chess;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

@SuppressLint("NewApi")
public class optionWindow extends Activity {

	CheckBox humanvscomp, humanvshuman, online, timer;
	Spinner hardness;
	Button done;
	Context con;

	// Chat c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optionscreen);
		con = this;
		humanvscomp = (CheckBox) findViewById(R.id.CVH);
		humanvscomp.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				if (humanvscomp.isChecked()) {
					humanvshuman.setChecked(false);
					online.setChecked(false);
					timer.setChecked(false);
				}

			}
		});
		humanvshuman = (CheckBox) findViewById(R.id.HVH);
		humanvshuman.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (humanvshuman.isChecked()) {
					humanvscomp.setChecked(false);
					online.setChecked(false);

				}
			}
		});
		online = (CheckBox) findViewById(R.id.Online);
		online.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (online.isChecked()) {
					humanvshuman.setChecked(false);
					humanvscomp.setChecked(false);

				}
			}
		});

		timer = (CheckBox) findViewById(R.id.timer);
		timer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (humanvscomp.isChecked())
					timer.setChecked(false);

			}
		});
		hardness = (Spinner) findViewById(R.id.Hardness);
		List<String> list = new ArrayList<String>();
		list.add("easy");
		list.add("medium");
		list.add("hard");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		hardness.setAdapter(dataAdapter);
		// c = new Chat();
		// send = (Button) findViewById(R.id.send);
		// send.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // c.send(write.getText().toString());
		// // write.setText("");
		// }
		// });
		// c.receive();
		done = (Button) findViewById(R.id.done);
		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (humanvscomp.isChecked()) {
					Intent intent = new Intent(con, GameActivity.class);
					int pos = hardness.getSelectedItemPosition();
					intent.putExtra("hard", pos);
					startActivity(intent);
				} else if (humanvshuman.isChecked()) {
					Intent intent = new Intent(con, GameActivityMulti.class);
					intent.putExtra("timer", timer.isChecked());
					startActivity(intent);

				} else if (online.isChecked()) {
					Intent intent = new Intent(con, GameActivityOnline.class);
					startActivity(intent);
				}

			}
		});
		// read = (EditText) findViewById(R.id.read);
		// write = (EditText) findViewById(R.id.written);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	// public static void message(String m) {
	// read.setText(read.getText().toString() + m);
	// }
}
