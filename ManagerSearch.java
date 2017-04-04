package com.example.dailyreportingtool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerSearch extends Activity {
	Spinner serpro,vtm;
	Button b,b1;
	TextView tv1,tv;
	String name1;
	ArrayAdapter<String> spinnerAdapter, spinnerAdapter1;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";
	TextView sta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_search);
		b = (Button) findViewById(R.id.searchpro);
		tv=(TextView)findViewById(R.id.tv);
		tv1=(TextView)findViewById(R.id.tv1);
		sta=(TextView)findViewById(R.id.sta);
		serpro = (Spinner) findViewById(R.id.serpro);
	
		vtm = (Spinner) findViewById(R.id.spinner11);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		serpro.setAdapter(spinnerAdapter);
		spinnerAdapter.add("Choose");
		spinnerAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vtm.setAdapter(spinnerAdapter1);
		spinnerAdapter1.add("Team Members");
		
		nomicationdet2();
		
		serpro.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                String imc_met=serpro.getSelectedItem().toString();
               

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
		
		
		
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  String imc_met=serpro.getSelectedItem().toString();
				 if (!imc_met.equalsIgnoreCase("choose")){
		                nomicationdet3(imc_met);
		        
						} else {
							Toast.makeText(getApplicationContext(),
									"Choose Any one...", Toast.LENGTH_LONG).show();
						}

			}
		});
	}

	
	public void nomicationdet6() {
		Soap_Url = "http://tempuri.org/TeamMemberstatus";
		Soap_Method = "TeamMemberstatus";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		aRequest.addProperty("pid", serpro.getSelectedItem().toString().trim());
		aRequest.addProperty("tmid", tv1.getText().toString());
		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();
				String valone1 = aresponse.getProperty("b").toString();

				sta.setText("Status : "+valone1+"%");
				
				

			}
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String name = aresponse.getProperty("a").toString();

				Log.e("id", name);

				spinnerAdapter.add(name);

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
	public void nomicationdet3(String c) {
		Soap_Url = "http://tempuri.org/View_One_Project";
		Soap_Method = "View_One_Project";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		aRequest.addProperty("id", c);
		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String name = aresponse.getProperty("a").toString();
				name1 = aresponse.getProperty("b").toString();
				String name2 = aresponse.getProperty("c").toString();

		
				Log.e(" ",name1);
				


			}
			tv.setText(name1);
			
			nomicationdet4( );

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	public void nomicationdet4( ) {
		Soap_Url = "http://tempuri.org/View_One_Leader";
		Soap_Method = "View_One_Leader";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		String c=tv.getText().toString().trim();
		
		aRequest.addProperty("id", c);
		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;

			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String name = aresponse.getProperty("a").toString();
				String name2  = aresponse.getProperty("b").toString();
				name1= aresponse.getProperty("c").toString();

		
				Log.e(" ",name1);
				


			}
			tv1.setText(name1);
			nomicationdet5();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public ArrayList<HashMap<String, String>> nomicationdet5() {
		Soap_Url = "http://tempuri.org/Assign_team_Members";
		Soap_Method = "Assign_team_Members";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
	String c= serpro.getSelectedItem().toString();
	String c1=tv1.getText().toString().trim();
		aRequest.addProperty("id", c);

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;
			
			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);
				String valone = aresponse.getProperty("a").toString();
				String valone1 = aresponse.getProperty("b").toString();
				String valone2 = aresponse.getProperty("c").toString();
				String valone3 = aresponse.getProperty("d").toString();

			

				spinnerAdapter1.add(valone);
				spinnerAdapter1.add(valone1);
				spinnerAdapter1.add(valone2);
				spinnerAdapter1.add(valone3);

			}
			spinnerAdapter1.notifyDataSetChanged();
			nomicationdet6();

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
