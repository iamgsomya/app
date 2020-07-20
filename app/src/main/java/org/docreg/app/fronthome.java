package org.docreg.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.Date;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class fronthome extends Fragment {
 Button reg_paitent;
 Button fetch_patient;


    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fronthome, container, false);
        fetch_patient = view.findViewById(R.id.fetchpatient);
        fetch_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), FetchPatientScreen.class);
                startActivity(i);
            }
        });
        reg_paitent = view.findViewById(R.id.paitent_register_btn);
        reg_paitent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent i = new Intent(getContext(), patient_reg.class);
        startActivity(i);
            }
        });


        return view;
    }




}
