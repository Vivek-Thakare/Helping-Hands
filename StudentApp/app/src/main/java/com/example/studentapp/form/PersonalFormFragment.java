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

import static com.example.studentapp.R.id.subbtn;

public class PersonalFormFragment extends Fragment {
    Button button;
    EditText Fullname1,Padrs1,Tadrs1,Mob1,Email1,Gender1,Blood1,Dob1,Height1,Weight1,Uid1,Pan1;
    public static final String url = "http://192.168.43.34/android/Student/InsertPersonal.php";
    AlertDialog.Builder builder;


    public PersonalFormFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_form, container, false);
        button = view.findViewById(R.id.subbtn);
        builder = new AlertDialog.Builder(getContext());
        Fullname1 = view.findViewById(R.id.fullname);
        Padrs1 = view.findViewById(R.id.Paddress);
        Tadrs1 = view.findViewById(R.id.Taddress);
        Mob1 = view.findViewById(R.id.mob);
        Email1 = view.findViewById(R.id.email);
        Gender1 = view.findViewById(R.id.geer);
        Blood1 = view.findViewById(R.id.bgroup);
        Dob1 = view.findViewById(R.id.dob);
        Height1 = view.findViewById(R.id.height);
        Weight1 = view.findViewById(R.id.weight);
        Uid1 = view.findViewById(R.id.uid);
        Pan1 = view.findViewById(R.id.pan);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Fullname,Paddress,Taddress,MobNo,Email,gender,Blood,DOB,Height,weight,UID,PAN;
                Fullname = Fullname1.getText().toString().trim();
                Paddress = Padrs1.getText().toString().trim();
                Taddress = Tadrs1.getText().toString().trim();
                MobNo = Mob1.getText().toString().trim();
                Email =Email1.getText().toString().trim();
                gender =Gender1.getText().toString().trim();
                Blood =Blood1.getText().toString().trim();
                DOB =Dob1.getText().toString().trim();
                Height =Height1.getText().toString().trim();
                weight =Weight1.getText().toString().trim();
                UID =Uid1.getText().toString().trim();
                PAN =Pan1.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                builder.setTitle("Server Response");
                                builder.setMessage("Response :"+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                         Fullname1.setText("");
                                         Padrs1.setText("");
                                         Tadrs1.setText("");
                                         Mob1.setText("");
                                         Email1.setText("");
                                         Gender1.setText("");
                                         Blood1.setText("");
                                         Dob1.setText("");
                                         Height1.setText("");
                                         Weight1.setText("");
                                         Uid1.setText("");
                                         Pan1.setText("");
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
                        Map<String, String> params = new HashMap<String,String>();
                        params.put("Fullname",Fullname);
                        params.put("Paddress",Paddress);
                        params.put("Taddress",Taddress);
                        params.put("MobNo",MobNo);
                        params.put("Email",Email);
                        params.put("gender",gender);
                        params.put("Blood",Blood);
                        params.put("DOB",DOB);
                        params.put("Height",Height);
                        params.put("weight",weight);
                        params.put("UID",UID);
                        params.put("PAN",PAN);

                        return params;
                    }
                };
                MySingleton.getInstance(getContext()).addToRequestQue(stringRequest);
            }
        });
        return view;
    }

}
