package org.docreg.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FetchPatientScreen extends AppCompatActivity {
    int CAMERA_PIC_REQUEST = 0;
    Bitmap imageData;
    RequestQueue requestQueue;
    LinearLayout progressLayout;
    RecyclerView recyclerView;
    PatientListAdapter patientListAdapter;
    Toolbar toolbar;
    TextView noMatchTv, errorTv;

    Auth auth;
    ArrayList<PatientModel> patientList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_patient_screen);
        //ending code
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        requestQueue = Volley.newRequestQueue(this);
        progressLayout = findViewById(R.id.progressLayout);
        recyclerView = findViewById(R.id.patient_list_recycler_view);
        toolbar = findViewById(R.id.fpsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noMatchTv = findViewById(R.id.fps_nomatchfound);
        errorTv = findViewById(R.id.fps_error);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        patientListAdapter = new PatientListAdapter(patientList, this);
        auth = new Auth(this);
    }
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            progressLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            imageData = (Bitmap) data.getExtras().get("data");
            //crop
            imageData = squareCrop(imageData);
            final byte[] imageByte = getFileDataFromBitmap(imageData);
            String url = Constants.fetchPatientsUrl;
            VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
                @Override
                public void onResponse(NetworkResponse response) {
                    String resultResponse = new String(response.data);
                    System.out.println(resultResponse);
                    //TODO: handle response here
                    try {
                        JSONObject jsonObject= new JSONObject(resultResponse);
                        int code = jsonObject.getInt("code");
                        if (code == 305){
                            progressLayout.setVisibility(View.GONE);
                            noMatchTv.setVisibility(View.VISIBLE);
                        }
                        if (code == 200){
                            JSONArray resultArray = jsonObject.getJSONArray("result");
                            for (int i = 0;i<resultArray.length();i++){
                                JSONObject obj = resultArray.getJSONObject(i);
                                patientList.add(new PatientModel(
                                        obj.getString("id"),
                                        obj.getString("image"),
                                        obj.getString("name"),
                                        obj.getString("aadhar"),
                                        obj.getInt("age"),
                                        obj.getString("sex"),
                                        obj.getString("address"),
                                        String.valueOf(obj.getLong("phone")),
                                        obj.getDouble("height"),
                                        obj.getDouble("weight"),
                                        obj.getString("blood_group")
                                ));
                            }
                            System.out.println(patientList.size());
                            progressLayout.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            patientListAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(patientListAdapter);
                            return;
                        }
                        progressLayout.setVisibility(View.GONE);
                        errorTv.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        Toast.makeText(FetchPatientScreen.this, "Unexpected Response", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        progressLayout.setVisibility(View.GONE);
                        errorTv.setVisibility(View.VISIBLE);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //TODO: handle error here
                    System.out.println(error.toString());
                    progressLayout.setVisibility(View.GONE);
                    errorTv.setVisibility(View.VISIBLE);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("auth", auth.getToken());
                    return params;
                }
                @Override
                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    params.put("image", new DataPart("image.jpg", imageByte , "image/jpg"));
                    return params;
                }
            };
            System.out.println(imageData);
            requestQueue.add(multipartRequest);
        } else if (resultCode == RESULT_CANCELED){
            finish();
        }
        System.out.println(requestCode);
        super.onActivityResult(requestCode, resultCode, data);
    }
    public byte[] getFileDataFromBitmap(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    public Bitmap squareCrop(Bitmap bmp){
        int width = bmp.getWidth();
        return Bitmap.createBitmap(bmp,0,0,width,width);
    }
}
