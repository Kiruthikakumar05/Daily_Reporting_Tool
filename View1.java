package com.example.dailyreportingtool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

public class View1 extends Activity {

	ListView lv;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";
	ArrayList<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		lv = (ListView) findViewById(R.id.listView21);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Intent i = getIntent();
		String s = i.getExtras().getString("leader");
		Log.e("id", s);
		List<String> list = new ArrayList<String>();
		
		if(s.equalsIgnoreCase("1"))
		{
			nomicationdet1();
		}
		else if(s.equalsIgnoreCase("2"))
		{
			nomicationdet2();
		}
		else if(s.equalsIgnoreCase("3"))
		{
			nomicationdet3();
		}
		

	

	}
	
	
	public void nomicationdet1() {
		Soap_Url = "http://tempuri.org/View_TeamMember1";
		Soap_Method = "View_TeamMember1";

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
				String name1 = aresponse.getProperty("b").toString();
				String name2 = aresponse.getProperty("c").toString();
				String name3= aresponse.getProperty("d").toString();
				String name4 = aresponse.getProperty("e").toString();
				String name5 = aresponse.getProperty("f").toString();
				
			
				list.add((i+1)+". "+name);
				list.add(name2);
				list.add(name3);
				list.add(name4);
				list.add(name5);
				list.add("-----------------------");
	

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
				String name = aresponse.getProperty("a").toString();
				String name1 = aresponse.getProperty("b").toString();
				String name2 = aresponse.getProperty("c").toString();
				String name3= aresponse.getProperty("d").toString();
				String name4 = aresponse.getProperty("e").toString();
				String name5 = aresponse.getProperty("f").toString();
				
				Log.e("id", name);	Log.e("id", name1);	Log.e("id", name2);	Log.e("id", name3);	Log.e("id", name4);	Log.e("id", name5);
				list.add((i+1)+". "+name);
				list.add(name2);
				list.add(name3);
				list.add(name4);
				list.add(name5);
				list.add("-----------------------");
	

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
	public void nomicationdet3() {
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
				String name1 = aresponse.getProperty("b").toString();

				String name2 = aresponse.getProperty("c").toString();


				list.add(name);
				list.add(name1);
				list.add(name2);
				list.add("-----------------------");

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

}
