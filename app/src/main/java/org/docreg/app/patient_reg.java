package org.docreg.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;

public class patient_reg extends AppCompatActivity {
    private EditText Patientname,aadharno,age,address,Phone,height,weight;
    private Button RegButton;
    Spinner spin,spin1;
    String text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reg);
        spin =findViewById(R.id.Patient_gender_input);
        spin1=findViewById(R.id.Patient_blood_group_input);
        Patientname=findViewById(R.id.Patient_name);
        aadharno=findViewById(R.id.Patient_aadhar_input);
        age=findViewById(R.id.Patient_age_input);
        address=findViewById(R.id.Patient_address_input);
        Phone=findViewById(R.id.Patient_Phone_input);
        height=findViewById(R.id.Patient_height_input);
        weight=findViewById(R.id.Patient_weight_input);
        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //text = parent.getItemAtPosition(i).toString();
                text1 = adapterView.getItemAtPosition(i).toString();
            }
        });

        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.blood_group,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adapter1);
        spin1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                text2 = adapterView.getItemAtPosition(i).toString();
            }
        });

        }


}

