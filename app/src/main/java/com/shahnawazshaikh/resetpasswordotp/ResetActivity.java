package com.shahnawazshaikh.resetpasswordotp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class ResetActivity extends AppCompatActivity {
   EditText passwordID,confrimPID;
   Button changeButton;
   ProgressBar progressbar;
    private static String RESET_URL="https://amygdaloid-cleat.000webhostapp.com/digitalcatnyx/resetpassword.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        passwordID=findViewById(R.id.Password);
        confrimPID=findViewById(R.id.ConfirmPassword);
        changeButton=findViewById(R.id.ChangeButton);
        progressbar=findViewById(R.id.Progressbar);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(!passwordID.getText().toString().equals(confrimPID.getText().toString())){
                      Toast.makeText(ResetActivity.this,"Password not mathched",Toast.LENGTH_SHORT).show();
                  }
                  else{
                      progressbar.setVisibility(View.VISIBLE);
                        resetPassword();
                  }
            }
        });


    }

    private void resetPassword() {
        StringRequest request = new StringRequest(Request.Method.POST,RESET_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            progressbar.setVisibility(View.GONE);
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(),""+ jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        }
                        catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Login Error"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Login Error"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email","shameer@gmail.com");     //Should change according to the requirement
                params.put("password",passwordID.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}

