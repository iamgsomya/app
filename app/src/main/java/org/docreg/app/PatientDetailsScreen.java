package org.docreg.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class PatientDetailsScreen extends AppCompatActivity {
    String name,image,aadhar,gender,address, phone,bloodGroup, patientId;
    int age;
    double height, weight;
    TextView tvName, tvAadhar, tvGender, tvAddress, tvPhone, tvBloodGroup, tvAge, tvHeight, tvWeight;
    ImageView imageView;
    Toolbar toolbar;
    Button appointment, viewAppointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_screen);
        toolbar = findViewById(R.id.pds_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();

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

        viewAppointment = findViewById(R.id.view_appointments);
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

        viewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AppointmentsListScreen.class);
                i.putExtra("patientId",patientId);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}