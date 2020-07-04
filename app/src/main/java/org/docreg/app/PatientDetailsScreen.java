package org.docreg.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PatientDetailsScreen extends AppCompatActivity {
    String name,image,aadhar,gender,address, phone,bloodGroup;
    int age;
    double height, weight;
    TextView tvName, tvAadhar, tvGender, tvAddress, tvPhone, tvBloodGroup, tvAge, tvHeight, tvWeight;
    ImageView imageView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_screen);
        toolbar = findViewById(R.id.pds_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent oi = getIntent();
        name = oi.getStringExtra("name");
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
}