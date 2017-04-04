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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ViewLeaderHistory extends Activity {
ListView lv;
public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
private static final String Soap_Name = "http://tempuri.org/";
private static String Soap_Url = "";
private static String Soap_Method = "";
String tmid;
ArrayList<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_leader_history);
		lv=(ListView)findViewById(R.id.ledhistory);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
StrictMode.setThreadPolicy(policy);
Intent i = getIntent();
tmid = i.getExtras().getString("tmid");
Log.e("id", tmid);
nomicationdet1();


	}
	public void nomicationdet1() {
		Soap_Url = "http://tempuri.org/ViewHistory";
		Soap_Method = "ViewHistory";

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

				String name = aresponse.getProperty("a").toString();
				String name1 = aresponse.getProperty("b").toString();
				String name2 = aresponse.getProperty("c").toString();
				String name3= aresponse.getProperty("d").toString();
				
				
			
				list.add("IN TIME - "+name);
				list.add("OUT TIME - "+name1);
				list.add("IN ADDRESS - "+name2);
				list.add("OUT ADDRESS - "+name3);
				nomicationdet2();
				
	

			}
	
			ListAdapter ls = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, list);
			lv.setAdapter(ls);
			
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void nomicationdet2() {
		Soap_Url = "http://tempuri.org/TeamMemberstatus";
		Soap_Method = "TeamMemberstatus";

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
				String valone1 = aresponse.getProperty("b").toString();

				list.add("PROJECT ID - "+valone);
				list.add("STATUS - "+valone1+"%");
				list.add("-----------------------");
				
				

			}
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
}
