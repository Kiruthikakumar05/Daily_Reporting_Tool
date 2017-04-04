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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEmployees extends Activity {
	Spinner Etype;
	EditText Eid, Ename, Epass, Edes, Eage, Ephone, Eaddr;
	String eid, ename, epass, edes, eage, ephone, eaddr, etype;
	Button esub;
	ArrayAdapter<String> spinnerAdapter, spinnerAdapter1;
	private static int RESULT_LOAD_IMAGE = 1;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_employees);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
StrictMode.setThreadPolicy(policy);
		Eid = (EditText) findViewById(R.id.eid);
		Ename = (EditText) findViewById(R.id.ename);
		Epass = (EditText) findViewById(R.id.epass);
		Edes = (EditText) findViewById(R.id.edes);
		Eage = (EditText) findViewById(R.id.eage);
		Ephone = (EditText) findViewById(R.id.phone);
		Eaddr = (EditText) findViewById(R.id.eaddr);
		Etype = (Spinner) findViewById(R.id.etype);
		esub = (Button) findViewById(R.id.esub);
		
		spinnerAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Etype.setAdapter(spinnerAdapter1);
		spinnerAdapter1.add("Choose");
		spinnerAdapter1.add("Team Leader");
		spinnerAdapter1.add("Team Member");
		spinnerAdapter1.notifyDataSetChanged();
		
		esub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				eid = Eid.getText().toString().trim();
				ename = Ename.getText().toString().trim();
				epass = Epass.getText().toString().trim();
				edes = Edes.getText().toString().trim();
				eage = Eage.getText().toString().trim();
				ephone = Ephone.getText().toString().trim();
				eaddr = Eaddr.getText().toString().trim();
				etype = Etype.getSelectedItem().toString();
				
				
				
				if(!eid.equalsIgnoreCase("")&& !ename.equalsIgnoreCase("")
						&& !epass.equalsIgnoreCase("")&& !edes.equalsIgnoreCase("")&& !eage.equalsIgnoreCase("")&& !ephone.equalsIgnoreCase("")&& !eaddr.equalsIgnoreCase("")
						&& !etype.equalsIgnoreCase("Choose"))
				{
					
					nomicationdet1();
					
				}
				else
				{
					
					Toast.makeText(getApplicationContext(),"Enter All Fields...", Toast.LENGTH_LONG).show();
					
				}
				

			}
		});

	}
	
	public void nomicationdet1() {
		Soap_Url = "http://tempuri.org/Insert_Employee";
		Soap_Method = "Insert_Employee";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject request = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		request.addProperty("type", etype);
		request.addProperty("id", eid);
		request.addProperty("name", ename);
		request.addProperty("Passwords", epass);
		request.addProperty("desig", edes);
		request.addProperty("age", eage);
		request.addProperty("number", ephone);
		request.addProperty("addres", eaddr);
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
				if (result.getProperty(0).toString()
						.equals("Success")) {

					Toast.makeText(getApplicationContext(),
							"Added Successfully",
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(
							getApplicationContext(),
							AddEmployees.class);
					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(),
							"Try again... ", Toast.LENGTH_LONG)
							.show();
				}
			}

		});
	
	}


}
