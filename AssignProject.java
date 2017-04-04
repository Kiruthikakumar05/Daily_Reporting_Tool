package com.example.dailyreportingtool;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class AssignProject extends Activity {

	Spinner proid,tleader;
	String str[];
	TextView protit;
	Button aslog;
	EditText dur;
	String a,b,c,d;
	ArrayAdapter<String> spinnerAdapter,spinnerAdapter1;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assign_project);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
StrictMode.setThreadPolicy(policy);
		proid=(Spinner)findViewById(R.id.proid);
		protit=(TextView)findViewById(R.id.protit);
		tleader=(Spinner)findViewById(R.id.tleader);
		dur=(EditText)findViewById(R.id.dur);
		aslog=(Button)findViewById(R.id.aslog);
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		proid.setAdapter(spinnerAdapter);
		spinnerAdapter.add("Choose");
		spinnerAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tleader.setAdapter(spinnerAdapter1);
		spinnerAdapter1.add("Choose");
		
		nomicationdet2();
		nomicationdet3();
		
		proid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	        @Override
	        public void onItemSelected(AdapterView<?> arg0, View arg1,
	                int arg2, long arg3) {

	             int selectedPosition = arg2;
	             //Here is your selected position
	             protit.setText(str[selectedPosition]);

	        }

	        @Override
	        public void onNothingSelected(AdapterView<?> arg0) {
	            // TODO Auto-generated method stub

	        }       

	    });
		
		aslog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				a = proid.getSelectedItem().toString();
				b = protit.getText().toString().trim();
				c = tleader.getSelectedItem().toString();
				d= dur.getText().toString().trim();

				if (!a.equalsIgnoreCase("") && !b.equalsIgnoreCase("")
						&& !c.equalsIgnoreCase("")&& !d.equalsIgnoreCase("")) {

					Soap_Url = "http://tempuri.org/Assign_Project";
					Soap_Method = "Assign_Project";

					ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
					SoapObject request = new SoapObject(Soap_Name, Soap_Method);
					SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					aEnvelope.dotNet = true;
					request.addProperty("pid", a);
					request.addProperty("pname", b);
					
					request.addProperty("teamleader", d);
					request.addProperty("duration", c);
				
				
					Calendar c = Calendar.getInstance();
					SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
					request.addProperty("date", df.format(c.getTime()));

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
									.equals("Success")) {

								Toast.makeText(getApplicationContext(),
										"Assigned successfully",
										Toast.LENGTH_LONG).show();
								Intent i = new Intent(getApplicationContext(),
										AssignProject.class);
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
			 str=new String[100];
			str[0]="0";
			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();
				String name = aresponse.getProperty("b").toString();

				Log.e("id", valone);
				str[i+1]=name;
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

	
	public ArrayList<HashMap<String, String>> nomicationdet3() {
		Soap_Url = "http://tempuri.org/View_TeamLeaders";
		Soap_Method = "View_TeamLeaders";

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

				spinnerAdapter1.add(valone);

			}
			spinnerAdapter1.notifyDataSetChanged();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

}
