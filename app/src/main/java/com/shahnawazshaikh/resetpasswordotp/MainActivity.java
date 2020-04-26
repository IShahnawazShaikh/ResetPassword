package com.shahnawazshaikh.resetpasswordotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText Number, OTP;
    ProgressBar progressBar;
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number = findViewById(R.id.Number);
        OTP = findViewById(R.id.OTP);
        progressBar = findViewById(R.id.Progressbar);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        findViewById(R.id.OTPButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //progressBar.setVisibility(View.VISIBLE);
                Random random=new Random();
                number=random.nextInt(9999);
                System.out.println("OTP= "+number);
                Toast.makeText(MainActivity.this,""+Number.getText().toString(),Toast.LENGTH_SHORT).show();

                try {
                    // Construct data
                    String apiKey = "apikey=" + "rDGxUtW2WUU-5s8UsSYFqkcxj74PiC0ORLcsHpZxnv";
                    String message = "&message=" + "This OTP is used to Reset password "+number;
                    String sender = "&sender=" + "6SpringBytes";
                    String numbers = "&numbers=" + "919716946677";
                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();
                    Toast.makeText(MainActivity.this,"OTP sent successfully",Toast.LENGTH_SHORT).show();
                   progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    System.out.println("Error SMS "+e);
                }
            }
        });
        findViewById(R.id.LoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number==Integer.parseInt(OTP.getText().toString())){
                    Toast.makeText(MainActivity.this,"Logged In successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,ResetActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
