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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends Activity {

	TextView txt1;
	EditText uname, pass;
	String u, p;
	Button log;
	String str,str1;
	private static int RESULT_LOAD_IMAGE = 1;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		txt1 = (TextView) findViewById(R.id.title);
		Intent i = getIntent();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		str = i.getExtras().getString("str");
		str1 = i.getExtras().getString("str1");
		txt1.setText(str);
		uname = (EditText) findViewById(R.id.tlusername);

		pass = (EditText) findViewById(R.id.tlpassword);

		log = (Button) findViewById(R.id.dlogin);
		log.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				u = uname.getText().toString().trim();
				p = pass.getText().toString().trim();

				if (str1.equalsIgnoreCase("1") ) {
					if( u.equalsIgnoreCase("asdf") && p.equalsIgnoreCase("asdf")){
					Intent i = new Intent(getApplicationContext(),
							ManagerSide.class);
					Toast.makeText(getApplicationContext(), "Manager Page",
							Toast.LENGTH_LONG);
					startActivity(i);
					}
					else
					{
						
						Toast.makeText(getApplicationContext(),
								"Invalid Username ",
								Toast.LENGTH_LONG).show();
						
						
					}
					
					

				} else if (!u.equalsIgnoreCase("") && !p.equalsIgnoreCase("")
						&& str1.equalsIgnoreCase("2")) {
					Soap_Url = "http://tempuri.org/TeamMemberrLogin";
					Soap_Method = "TeamMemberrLogin";

					ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
					SoapObject request = new SoapObject(Soap_Name, Soap_Method);
					SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					aEnvelope.dotNet = true;
					u = uname.getText().toString().trim();
					p = pass.getText().toString().trim();
					request.addProperty("name", u);
					request.addProperty("pass", p);

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
										"Login successful",
										Toast.LENGTH_LONG).show();
								Intent i = new Intent(getApplicationContext(),
										TeamMemberSide.class);
								i.putExtra("id", u);
								Toast.makeText(getApplicationContext(),
										"TeamMemberSide Page",
										Toast.LENGTH_LONG);
								startActivity(i);
							} else {
								Toast.makeText(getApplicationContext(),
										"Invalid Username or Passwords... ",
										Toast.LENGTH_LONG).show();
							}
						}

					});

				}

				else if (!u.equalsIgnoreCase("") && !p.equalsIgnoreCase("")&& str1.equalsIgnoreCase("3")) {

					Soap_Url = "http://tempuri.org/TeamLeaderLogin";
					Soap_Method = "TeamLeaderLogin";

					ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
					SoapObject request = new SoapObject(Soap_Name, Soap_Method);
					SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					aEnvelope.dotNet = true;

					u = uname.getText().toString().trim();
					p = pass.getText().toString().trim();
					request.addProperty("name", u);
					request.addProperty("pass", p);
					Log.e("cvdf", u);
					Log.e("fdgdf", p);

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
										"Registration successfully",
										Toast.LENGTH_LONG).show();
								Intent i = new Intent(getApplicationContext(),
										TeamLeaderSide.class);
								i.putExtra("id", u);
								Toast.makeText(getApplicationContext(),
										"TeamMemberSide Page",
										Toast.LENGTH_LONG);
								startActivity(i);
							} else {
								Toast.makeText(getApplicationContext(),
										"Invalid Username or Passwords... ",
										Toast.LENGTH_LONG).show();
							}
						}

					});

				} else {
					Toast.makeText(getApplicationContext(),
							"Enter All fields... ", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

}
