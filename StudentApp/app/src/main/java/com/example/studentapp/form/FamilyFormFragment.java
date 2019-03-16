package com.example.studentapp.form;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.studentapp.R;

import java.util.HashMap;
import java.util.Map;

public class FamilyFormFragment extends Fragment {

    Button button;
    EditText Faname,Famob,Faocc,Maname,Mamob,Maocc;
    public static final String url = "http://192.168.43.34/android/Student/InsertFamily.php";
    AlertDialog.Builder builder;


    public FamilyFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family_form, container, false);

        button = view.findViewById(R.id.subbtn);
        builder = new AlertDialog.Builder(getContext());
        Faname = view.findViewById(R.id.fathername);
        Famob = view.findViewById(R.id.fmob);
        Faocc = view.findViewById(R.id.focc);
        Maname = view.findViewById(R.id.mname);
        Mamob = view.findViewById(R.id.mmob);
        Maocc = view.findViewById(R.id.mocc);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FName,FMobNo,FOcc,MName,MMobNo,MOcc;
                FName = Faname.getText().toString().trim();
                FMobNo = Famob.getText().toString().trim();
                FOcc = Faocc.getText().toString().trim();
                MName = Maname.getText().toString().trim();
                MMobNo =Mamob.getText().toString().trim();
                MOcc = Maocc.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("Server Response");
                                builder.setMessage("Response :"+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Faname.setText("");
                                        Famob.setText("");
                                        Faocc.setText("");
                                        Maname.setText("");
                                        Mamob.setText("");
                                        Maocc.setText("");
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(),"Error...",Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("FName",FName);
                        params.put("FMobNo",FMobNo);
                        params.put("FOcc",FOcc);
                        params.put("MName",MName);
                        params.put("MMobNO",MMobNo);
                        params.put("MOcc",MOcc);
                        return params;
                    }
                };
                MySingleton.getInstance(getContext()).addToRequestQue(stringRequest);
            }
        });

        return view;
    }

}
