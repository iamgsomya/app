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
    private static final int CAMERA_PIC_REQUEST = 1337;

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fronthome, container, false);
        reg_paitent = view.findViewById(R.id.paitent_register_btn);
        reg_paitent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
        Intent i = new Intent(getContext(), ImageCaptureScreen.class);
        startActivity(i);
            }
        });

        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_PIC_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Bitmap imageData = (Bitmap) data.getExtras().get("data");
//
////                ImageView image = (ImageView) findViewById(R.id.imageView1);
////                image.setImageBitmap(imageData);
//            } else if (resultCode == RESULT_CANCELED){
////                tv.setText("Cancelled");
//            }
//        }
//    }
    //     void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == CAMERA_PIC_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                tv.setText("Got picture!");
//                imageData = (Bitmap) data.getExtras().get("data");
//                ImageView image = (ImageView) findViewById(R.id.imageView1);
//                image.setImageBitmap(imageData);
//            } else if (resultCode == RESULT_CANCELED){
//                tv.setText("Cancelled");
//            }
//        }
//    }


}
