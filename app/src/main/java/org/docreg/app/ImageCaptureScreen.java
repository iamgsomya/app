package org.docreg.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageCaptureScreen extends AppCompatActivity {
    ImageView captureImage;
    ProgressBar progressBar;
    int CAMERA_PIC_REQUEST = 0;
    Bitmap imageData;
    Button submitButton;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture_screen);
        captureImage = findViewById(R.id.captureImage);
        progressBar = findViewById(R.id.captureProgressBar);
        submitButton = findViewById(R.id.imgsubmit);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
        submitButton.setEnabled(false);
        requestQueue = Volley.newRequestQueue(this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final byte[] imageByte = getFileDataFromBitmap(imageData);
                String url = Constants.SendImageUrl;
                VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        String resultResponse = new String(response.data);
                        System.out.println(resultResponse);
                        //TODO: handle response here
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: handle error here
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("token", Constants.authToken);
                        return params;
                    }

                    @Override
                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();
                        params.put("image", new DataPart("image.png", imageByte , "image/png"));
                        return params;
                    }
                };
                requestQueue.add(multipartRequest);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
                imageData = (Bitmap) data.getExtras().get("data");
                submitButton.setEnabled(true);
                captureImage.setImageBitmap(imageData);
                captureImage.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            } else if (resultCode == RESULT_CANCELED){
                progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public byte[] getFileDataFromBitmap(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
