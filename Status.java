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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Status extends Activity {
String tmid;
EditText status1;
Spinner sp1;
Button b;
ArrayAdapter<String> spinnerAdapter,spinnerAdapter1;
public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
private static final String Soap_Name = "http://tempuri.org/";
private static String Soap_Url = "";
private static String Soap_Method = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		Intent i=getIntent();
		tmid=i.getExtras().getString("id"); 
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
StrictMode.setThreadPolicy(policy);
		sp1=(Spinner)findViewById(R.id.pid);
		b=(Button)findViewById(R.id.bstatus);
		status1=(EditText)findViewById(R.id.stausvalue);
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(spinnerAdapter);
		spinnerAdapter.add("Choose");
		nomicationdet2();
		
		
		
		
		
		
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			String	pid = sp1.getSelectedItem().toString().trim();
			String	status = status1.getText().toString().trim();
				

				if (!pid.equalsIgnoreCase("Choose") && !status.equalsIgnoreCase("")
						&& !tmid.equalsIgnoreCase("")) {
					if(Integer.parseInt(status)>100)
					{
						Toast.makeText(getApplicationContext(),
								"Enter lesser than or equal 100 %...", Toast.LENGTH_LONG).show();
					}
					else
						
					{
					Soap_Url = "http://tempuri.org/DailyStatus";
					Soap_Method = "DailyStatus";

					ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
					SoapObject request = new SoapObject(Soap_Name, Soap_Method);
					SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					aEnvelope.dotNet = true;
					String  date1 = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

					request.addProperty("tmid", tmid);
					request.addProperty("pid", pid);
					request.addProperty("status", status);
					request.addProperty("date", date1);
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
										"Updated successfully",
										Toast.LENGTH_LONG).show();
								Intent i = new Intent(getApplicationContext(),
										TeamMemberSide.class);
								i.putExtra("id", tmid);
								startActivity(i);
							} else {
								Toast.makeText(getApplicationContext(),
										"Try again... ", Toast.LENGTH_LONG)
										.show();
							}
						}

					});
				}

				} else {

					Toast.makeText(getApplicationContext(),
							"Enter All Fields...", Toast.LENGTH_LONG).show();

				}

			}
		});

		
		
	}
	public void nomicationdet2() {
		Soap_Url = "http://tempuri.org/TeamMemberPid";
		Soap_Method = "TeamMemberPid";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		
		aRequest.addProperty("tmid", tmid);
		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;
			
			for (int i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				String valone = aresponse.getProperty("a").toString();
		

			
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


	
}
