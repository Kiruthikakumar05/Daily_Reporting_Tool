package com.example.dailyreportingtool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TeamMemberSide extends Activity {
	Button dpr, view;
	ArrayAdapter<String> spinnerAdapter, spinnerAdapter1;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";
	String tmid;
	Spinner sp1;
	String locationAddress;
	Button attendence,history;
	TextView set;
	String pid;
	String lat,lan;
	String date,date1;
	AppLocationService appLocationService;
	int d = 0;
	Button checkin,checkout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_member_side);
		history = (Button) findViewById(R.id.history);
		dpr = (Button) findViewById(R.id.dpr);
		attendence = (Button) findViewById(R.id.attendence);
		appLocationService = new AppLocationService(TeamMemberSide.this);
		view = (Button) findViewById(R.id.viewstatus);
		set = (TextView) findViewById(R.id.set);
		checkin = (Button) findViewById(R.id.checkin);
		checkout = (Button) findViewById(R.id.checkout);
		sp1 = (Spinner) findViewById(R.id.viewpid);
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(spinnerAdapter);
		spinnerAdapter.add("Choose");
		Intent t = getIntent();
		tmid = t.getExtras().getString("id");
		nomicationdet2();
		setloc();
	
		
			setadd();
		
		
		
		history.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),ViewMemberHistory.class);
				i.putExtra("tmid", tmid);
				startActivity(i);
				
				
				
			}
		});
		attendence.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			if(!date.equalsIgnoreCase("")&&!locationAddress.equalsIgnoreCase(""))
					{
				
				nomicationdet1();
				
					}
			else if(!date1.equalsIgnoreCase("")&&!locationAddress.equalsIgnoreCase("")){
				
				
				nomicationdet5();
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Provide check in or Check out time", Toast.LENGTH_LONG).show();
				
			}
			}
		});
		checkin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				 checkin.setBackgroundResource(R.drawable.green);
				
				 
				
			}
		});
		checkout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 date1 = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				
				 checkout.setBackgroundResource(R.drawable.green);
				
				 
			}
		});
		dpr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Status.class);
				i.putExtra("id", tmid);
				startActivity(i);

			}
		});
		view.setOnClickListener(new OnClickListener() {
			Intent t = getIntent();
			String id = t.getExtras().getString("id");

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pid = sp1.getSelectedItem().toString().trim();

				if (!pid.equalsIgnoreCase("Choose")
						&& !tmid.equalsIgnoreCase("")) {
					nomicationdet21();

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

	public void nomicationdet21() {
		Soap_Url = "http://tempuri.org/Status";
		Soap_Method = "Status";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		aRequest.addProperty("pid", pid);
		aRequest.addProperty("tmid", tmid);

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;
			int i;
			for (i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				d = Integer.parseInt(aresponse.getProperty("b").toString());

			}
			double f = (double) d;
			set.setText("Status : " + f + "%");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void nomicationdet11() {
		Soap_Url = "http://tempuri.org/Status";
		Soap_Method = "Status";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject aRequest = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		aRequest.addProperty("pid", pid);
		aRequest.addProperty("tmid", tmid);

		aEnvelope.setOutputSoapObject(aRequest);
		HttpTransportSE aHtrans = new HttpTransportSE(url);
		try {
			aHtrans.call(Soap_Url, aEnvelope);
			SoapObject aResult = (SoapObject) aEnvelope.getResponse();
			ArrayAdapter<String> adapter;
			int i;
			for (i = 0; i < aResult.getPropertyCount(); i++) {
				SoapObject aresponse = (SoapObject) aResult.getProperty(i);

				d = d + Integer.parseInt(aresponse.getProperty("b").toString());

			}
			double f = (double) (d / (i));
			set.setText("Status : " + f + "%");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void nomicationdet1() {
		Soap_Url = "http://tempuri.org/Attendence";
		Soap_Method = "Attendence";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject request = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		request.addProperty("tmid", tmid);
		request.addProperty("checkin", date);
		request.addProperty("checkout", "0");
		request.addProperty("inaddress", locationAddress);
		request.addProperty("outaddress", "0");
		request.addProperty("flag", 0);

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
							"Registration successfully",
							Toast.LENGTH_LONG).show();
					 checkin.setBackgroundResource(R.drawable.mystyle1);
					date="";
//					Intent i = new Intent(
//							getApplicationContext(),
//							TeamMemberSide.class);
//					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(),
							"Try again... ", Toast.LENGTH_LONG)
							.show();
				}
			}

		});
	
	}
	public void nomicationdet5() {
		Soap_Url = "http://tempuri.org/Attendence";
		Soap_Method = "Attendence";

		ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		SoapObject request = new SoapObject(Soap_Name, Soap_Method);
		SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		aEnvelope.dotNet = true;
		request.addProperty("tmid", tmid);
		request.addProperty("checkin", date);
		request.addProperty("checkout", date1);
		request.addProperty("inaddress", locationAddress);
		request.addProperty("outaddress", locationAddress);
		request.addProperty("flag", 1);

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
							"Registration successfully",
							Toast.LENGTH_LONG).show();
					 checkout.setBackgroundResource(R.drawable.mystyle1);
					date1="";
//					Intent i = new Intent(
//							getApplicationContext(),
//							TeamMemberSide.class);
//					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(),
							"Try again... ", Toast.LENGTH_LONG)
							.show();
				}
			}

		});
	
	}
	
	public void setadd() {

		Location location = appLocationService
				.getLocation(LocationManager.GPS_PROVIDER);

		// you can hard-code the lat & long if you have issues with getting it
		// remove the below if-condition and use the following couple of lines
		// double latitude = 37.422005;
		// double longitude = -122.084095

		if (location != null) {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			LocationAddress locationAddress = new LocationAddress();
			locationAddress.getAddressFromLocation(latitude, longitude,
					getApplicationContext(), new GeocoderHandler());
		} else {
			showSettingsAlert();
		}
	}
	public void setloc() {

		Location gpsLocation = appLocationService
				.getLocation(LocationManager.GPS_PROVIDER);
		if (gpsLocation != null) {
			double latitude = gpsLocation.getLatitude();
			double longitude = gpsLocation.getLongitude();
			String result = "Latitude: " + gpsLocation.getLatitude()
					+ " Longitude: " + gpsLocation.getLongitude();
			Log.e("", result);
			Toast toast = Toast.makeText(getApplicationContext(), result,
					Toast.LENGTH_SHORT);
			lat=String.valueOf(gpsLocation.getLatitude());
			lan=String.valueOf( gpsLocation.getLongitude());

			toast.show();

		} else {
			showSettingsAlert();
		}
	}

	private class GeocoderHandler extends Handler {
		@Override
		public void handleMessage(Message message) {
		
			switch (message.what) {
			case 1:
				Bundle bundle = message.getData();
				locationAddress = bundle.getString("address");
				break;
			default:
				locationAddress = null;
			}
			Log.e("", locationAddress);
			Toast toast = Toast.makeText(getApplicationContext(),
					locationAddress, Toast.LENGTH_LONG);
		

		}
	}
	
	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				TeamMemberSide.this);
		alertDialog.setTitle("SETTINGS");
		alertDialog 
				.setMessage("Enable Location Provider! Go to settings menu?");
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						TeamMemberSide.this.startActivity(intent);
					}
				});
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		alertDialog.show();
	}
}
