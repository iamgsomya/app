package org.docreg.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ImageCaptureScreen extends AppCompatActivity {
    ImageView captureImage;
    ProgressBar progressBar;
    int CAMERA_PIC_REQUEST = 0;
    Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture_screen);
        captureImage = findViewById(R.id.captureImage);
        progressBar = findViewById(R.id.captureProgressBar);
        submitButton = findViewById(R.id.imgsubmit);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
                Bitmap imageData = (Bitmap) data.getExtras().get("data");
                imageData.CompressFormat.PNG.
                captureImage.setImageBitmap(imageData);
                captureImage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            } else if (resultCode == RESULT_CANCELED){
                progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
