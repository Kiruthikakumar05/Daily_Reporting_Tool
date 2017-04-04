package com.example.dailyreportingtool;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity {
	ImageButton manager,member,leader;
	String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        manager=(ImageButton)findViewById(R.id.manager);
        member=(ImageButton)findViewById(R.id.member);
        leader=(ImageButton)findViewById(R.id.leader);
        manager.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str="Manager Login ";
				String str1="1";
				Intent i=new Intent(getApplicationContext(),LoginPage.class);
				i.putExtra("str", str);
				i.putExtra("str1", str1);
				startActivity(i);
				
			}
		});
        member.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str="TeamMember Login ";
				String str1="2";
				Intent i=new Intent(getApplicationContext(),LoginPage.class);
				i.putExtra("str1", str1);
				i.putExtra("str", str.trim());
				startActivity(i);
				
			}
		});
        leader.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str="TeamLeader Login ";
				String str1="3";
				Intent i=new Intent(getApplicationContext(),LoginPage.class);
				i.putExtra("str1", str1);
				i.putExtra("str", str);
				startActivity(i);
				
			}
		});
        
        
    }


  
}
