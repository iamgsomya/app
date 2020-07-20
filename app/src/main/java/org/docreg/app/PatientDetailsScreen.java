package org.docreg.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PatientDetailsScreen extends AppCompatActivity {
    String name,image,aadhar,gender,address, phone,bloodGroup, patientId;
    int age;
    double height, weight;
    TextView tvName, tvAadhar, tvGender, tvAddress, tvPhone, tvBloodGroup, tvAge, tvHeight, tvWeight;
    ImageView imageView;
    Toolbar toolbar;
    Button appointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_screen);
        toolbar = findViewById(R.id.pds_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent oi = getIntent();
        name = oi.getStringExtra("name");
        patientId=oi.getStringExtra("patientId");
        aadhar = oi.getStringExtra("aadhar");
        age = oi.getIntExtra("age",-1);
        gender = oi.getStringExtra("gender");
        address = oi.getStringExtra("address");
        phone = oi.getStringExtra("phone");
        height = oi.getDoubleExtra("height",-1);
        weight = oi.getDoubleExtra("weight", -1);
        bloodGroup = oi.getStringExtra("bloodGroup");
        image = oi.getStringExtra("imageUrl");

        tvName = findViewById(R.id.pdname);
        tvAadhar = findViewById(R.id.pdaadhar);
        tvGender = findViewById(R.id.pdgender);
        tvAddress = findViewById(R.id.pdaddress);
        tvPhone = findViewById(R.id.pdphone);
        tvBloodGroup = findViewById(R.id.pdblood_group);
        tvAge = findViewById(R.id.pdage);
        tvHeight = findViewById(R.id.pdheight);
        tvWeight = findViewById(R.id.pdweight);
        imageView = findViewById(R.id.pdimage);

        tvName.setText(name!=null?name:"-");
        tvAadhar.setText(aadhar!=null?aadhar:"-");
        tvGender.setText(gender!=null?gender:"-");
        tvAddress.setText(address!=null?address:"-");
        tvPhone.setText(phone!=null?phone:"-");
        tvBloodGroup.setText(bloodGroup!=null?bloodGroup:"-");
        tvAge.setText(age!=-1? String.valueOf(age) : "-");
        tvHeight.setText(height!=-1?String.valueOf(height):"-");
        tvWeight.setText(weight!=-1?String.valueOf(weight):"-");
        Picasso.get().load(image).into(imageView);


    }
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fronthome, container, false);
        appointment = view.findViewById(R.id.appointment);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientDetailsScreen.this, appointment.class);
                startActivity(i);
            }
        });
    return view;
    }
}