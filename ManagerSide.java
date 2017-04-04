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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ManagerSide extends Activity {
	Button maddemp, addpro, a, spro,viewdetails;
	Button his;
Spinner sp,sp1;
	ArrayAdapter<String> spinnerAdapter, spinnerAdapter1;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_side);
		maddemp = (Button) findViewById(R.id.maddemp);
		sp1 = (Spinner) findViewById(R.id.tid);
		addpro = (Button) findViewById(R.id.addpro);
		his = (Button) findViewById(R.id.tln);
		viewdetails = (Button) findViewById(R.id.viewdetails);
		sp=(Spinner)findViewById(R.id.sp);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		a = (Button) findViewById(R.id.A);
		spro = (Button) findViewById(R.id.spro);
	

		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(spinnerAdapter);
		spinnerAdapter.add("Choose");
		
		spinnerAdapter.add("Team Leader Details");
		
		spinnerAdapter.add("Team Member Details");
		
		spinnerAdapter.add("Project Details");
		spinnerAdapter.notifyDataSetChanged();
		
		
		
		
		spinnerAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(spinnerAdapter1);
		spinnerAdapter1.add("Choose");
		
		nomicationdet2() ;
		
		
		his.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s=sp1.getSelectedItem().toString().trim();
				if(!s.equalsIgnoreCase("choose")){
				Intent i=new Intent(getApplicationContext(),ViewLeaderHistory.class);
				i.putExtra("tmid",s);
				startActivity(i);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Choose any one...", Toast.LENGTH_LONG).show();;
				}
				
			}
		});
		

		

		maddemp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						AddEmployees.class);
				startActivity(i);

			}
		});
		spro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent i = new Intent(getApplicationContext(), ManagerSearch.class);
	
				startActivity(i);
			

			}
		});

		addpro.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						AddProject.class);
				startActivity(i);

			}
		});
		a.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						AssignProject.class);
				startActivity(i);

			}
		});
		
		viewdetails.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String spin=sp.getSelectedItem().toString().trim();
				if(spin.equalsIgnoreCase("Team Leader Details"))
				{
					String t="1";
					Intent i=new Intent(getApplicationContext(),View1.class);
					i.putExtra("leader", t);
					startActivity(i);
				}
				else if(spin.equalsIgnoreCase("Team Member Details"))
				{
					String t="2";
					Intent i=new Intent(getApplicationContext(),View1.class);
					i.putExtra("leader", t);
					startActivity(i);
				}
				else if(spin.equalsIgnoreCase("Project Details"))
				{
					String t="3";
					Intent i=new Intent(getApplicationContext(),View1.class);
					i.putExtra("leader", t);
					startActivity(i);
				}
				

			}
		});
	}
	public void nomicationdet2() {
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

				String name = aresponse.getProperty("a").toString();

				Log.e("id", name);

				spinnerAdapter1.add(name);

			}
			spinnerAdapter1.notifyDataSetChanged();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
