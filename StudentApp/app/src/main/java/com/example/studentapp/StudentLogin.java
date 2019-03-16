package com.example.studentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class StudentLogin extends AppCompatActivity {
    EditText username,password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        username = findViewById(R.id.uname);
        password = findViewById(R.id.upass);
        button = findViewById(R.id.logbtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                Login();
            }
        });
    }
    private void Login() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.34/android/v1/StudentLogin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("True")) {
                    Toast.makeText(StudentLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StudentLogin.this,StudentHome.class);
                    startActivity(intent);finish();
                }else if (response.equals("False")){
                    Toast.makeText(StudentLogin.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    button.setVisibility(View.VISIBLE);


                }else{
                    Toast.makeText(StudentLogin.this, "Something went wrong" , Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    button.setVisibility(View.VISIBLE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentLogin.this, "Volley error" + error, Toast.LENGTH_SHORT).show();
                username.setText("");
                password.setText("");
                button.setVisibility(View.VISIBLE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(StudentLogin.this).add(stringRequest);

    }
}
