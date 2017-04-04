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
import android.widget.EditText;
import android.widget.Toast;

public class AddProject extends Activity {
	EditText prid1, prname1, des;
	Button prsub;
	String pid1, pname1, des1;
	ArrayAdapter<String> spinnerAdapter, spinnerAdapter1;
	private static int RESULT_LOAD_IMAGE = 1;
	public static String url = "http://cyberstudents.in/android/1617Staff/Manish/Daily%20Reporting%20Tool/Service.asmx";
	private static final String Soap_Name = "http://tempuri.org/";
	private static String Soap_Url = "";
	private static String Soap_Method = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_project);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		prid1 = (EditText) findViewById(R.id.prid);
		prname1 = (EditText) findViewById(R.id.prname);
		des = (EditText) findViewById(R.id.prdes);
		prsub = (Button) findViewById(R.id.prsub);
		prsub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				pid1 = prid1.getText().toString().trim();
				pname1 = prname1.getText().toString().trim();
				des1 = des.getText().toString().trim();

				if (!pid1.equalsIgnoreCase("") && !pname1.equalsIgnoreCase("")
						&& !des1.equalsIgnoreCase("")) {

					Soap_Url = "http://tempuri.org/Insert_Projects";
					Soap_Method = "Insert_Projects";

					ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
					SoapObject request = new SoapObject(Soap_Name, Soap_Method);
					SoapSerializationEnvelope aEnvelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					aEnvelope.dotNet = true;
					request.addProperty("pid", pid1);
					request.addProperty("pname", pname1);
					request.addProperty("des", des1);

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
										"Added successfully",
										Toast.LENGTH_LONG).show();
								Intent i = new Intent(getApplicationContext(),
										AddProject.class);
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

}
