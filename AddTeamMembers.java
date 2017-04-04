package com.example.dailyreportingtool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTeamMembers extends Activity {
	Button b;
	String uid;
	Spinner proid1, m1, m2, m3, m4;
	String str[];
	String M1, M2, M3, M4, dur;
	TextView tmtitle1;
	EditText Dur;
	ArrayAdapter<String> spinnerAdapter, sp1, sp2, sp3, sp4;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_team_members);

		proid1 = (Spinner) findViewById(R.id.proid1);
		m1 = (Spinner) findViewById(R.id.m1);
		m2 = (Spinner) findViewById(R.id.m2);
		m3 = (Spinner) findViewById(R.id.m3);
		m4 = (Spinner) findViewById(R.id.m4);
		b = (Button) findViewById(R.id.searchpro1);
		Dur=(EditText)findViewById(R.id.dur);

		tmtitle1 = (TextView) findViewById(R.id.tmtitle1);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		proid1.setAdapter(spinnerAdapter);
		spinnerAdapter.add("Choose");
		sp1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		sp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		m1.setAdapter(sp1);
		sp1.add("Choose");
		sp2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		sp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		m2.setAdapter(sp2);
		sp2.add("Choose");
		sp3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		sp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		m3.setAdapter(sp3);
		sp3.add("Choose");
		sp4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		sp4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		m4.setAdapter(sp3);
		sp4.add("Choose");
		nomicationdet2();

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				M1 = m1.getSelectedItem().toString().trim();
				M2 = m2.getSelectedItem().toString().trim();
				M3 = m3.getSelectedItem().toString().trim();
				M4 = m4.getSelectedItem().toString().trim();
				dur=Dur.getText().toString().trim();

				if (!M1.equalsIgnoreCase("choose") && !M2.equalsIgnoreCase("choose")
						&& !M3.equalsIgnoreCase("choose")&& !M2.equalsIgnoreCase("choose")&& !dur.equalsIgnoreCase("choose")) {

					Soap_Url = "http://tempuri.org/Allocate_Project_Members";
					Soap_Method = "Allocate_Project_Members";

					ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
					SoapObject request = new SoapObject(Soap_Name, Soap_Method);
					SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					aEnvelope.dotNet = true;
					request.addProperty("pid", proid1.getSelectedItem().toString());
					request.addProperty("m1", M1);
					request.addProperty("m2", M2);
					request.addProperty("m3", M3);
					request.addProperty("m4", M4);
					request.addProperty("dur", dur);

					aEnvelope.setOutputSoapObject(request);
					HttpTransportSE HTSP = new HttpTransportSE(url);
					try {
						HTSP.call(Soap_Url, aEnvelope);
					} catch (IOException e1) {

						e1.printStackTrace();
					} catch (XmlPullParserException e1) {

						e1.printStackTrace();
					}
					final SoapObject result = (SoapObject) aEnvelope.bodyIn;
					runOnUiThread(new Runnable() {
						public void run() {
							Log.e("", result.getProperty(0).toString());
							if (result.getProperty(0).toString()
									.equalsIgnoreCase("Success")) {
								Intent i = new Intent(getApplicationContext(),
										AddTeamMembers.class);
								Toast.makeText(getApplicationContext(),
										"Added successfully",
										Toast.LENGTH_LONG).show();
							
								startActivity(i);
							} else {
								Toast.makeText(getApplicationContext(),
										"Try again... ", Toast.LENGTH_LONG)
										.show();
							}
						}

					});

				} else {

					Toast.makeText(getApplicationContext(),
							"Enter All Fields...", Toast.LENGTH_LONG).show();

				}

			}
		});

		proid1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				int selectedPosition = arg2;
				// Here is your selected position

				tmtitle1.setText(str[selectedPosition]);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		nomicationdet3();
		nomicationdet4();
		nomicationdet5();
		nomicationdet6();

	}

	public void nomicationdet2() {
		Soap_Url = "http://tempuri.org/View_Project";
		Soap_Method = "View_Project";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;
			str = new String[100];
			str[0] = "wait";
			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();
				String name = aresponse.getProperty("b").toString();

				Log.e("id", name);
				str[i + 1] = name;
				spinnerAdapter.add(valone);

			}
			spinnerAdapter.notifyDataSetChanged();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void nomicationdet3() {
		Soap_Url = "http://tempuri.org/View_TeamLeader";
		Soap_Method = "View_TeamLeader";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();

				Log.e("id", valone);
				sp1.add(valone);

			}
			sp1.notifyDataSetChanged();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void nomicationdet4() {
		Soap_Url = "http://tempuri.org/View_TeamLeader";
		Soap_Method = "View_TeamLeader";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();

				Log.e("id", valone);
				sp2.add(valone);

			}
			sp2.notifyDataSetChanged();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void nomicationdet5() {
		Soap_Url = "http://tempuri.org/View_TeamLeader";
		Soap_Method = "View_TeamLeader";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();

				Log.e("id", valone);
				sp3.add(valone);

			}
			sp3.notifyDataSetChanged();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void nomicationdet6() {
		Soap_Url = "http://tempuri.org/View_TeamLeader";
		Soap_Method = "View_TeamLeader";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();

				Log.e("id", valone);

				sp4.add(valone);

			}
			sp4.notifyDataSetChanged();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
