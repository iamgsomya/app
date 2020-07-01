package org.docreg.app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class patient_reg extends AppCompatActivity {
    private EditText patientname,aadharno,age,address,phone,height,weight;
    private Button regButton;
    Spinner genderSpin,bloodGroupSpin;
    Bitmap imageData;
    int CAMERA_PIC_REQUEST = 0;
    RequestQueue requestQueue;
    ImageView captureImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reg);
        genderSpin = findViewById(R.id.Patient_gender_input);
        bloodGroupSpin=findViewById(R.id.Patient_blood_group_input);
        patientname=findViewById(R.id.Patient_name);
        aadharno=findViewById(R.id.Patient_aadhar_input);
        age=findViewById(R.id.Patient_age_input);
        address=findViewById(R.id.Patient_address_input);
        phone=findViewById(R.id.Patient_Phone_input);
        regButton = findViewById(R.id.pat_reg_button);
        captureImage = findViewById(R.id.reg_image_view);
        height=findViewById(R.id.Patient_height_input);
        weight=findViewById(R.id.Patient_weight_input);
        final ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_dropdown_item);
        genderSpin.setAdapter(adapter);
        final ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.blood_group,android.R.layout.simple_spinner_dropdown_item);
        bloodGroupSpin.setAdapter(adapter1);
        requestQueue = Volley.newRequestQueue(this);
        final Auth auth = new Auth(this);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(bloodGroupSpin.getSelectedItemId());
                System.out.println(genderSpin.getSelectedItemId());
                if (!validateForm()) return;
                final byte[] imageByte = getFileDataFromBitmap(imageData);
                String url = Constants.registerPatientUrl;
                VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        String resultResponse = new String(response.data);
                        System.out.println(resultResponse);
                        //TODO: handle response here
                        try {
                            JSONObject jsonObject= new JSONObject(resultResponse);
                            int code = jsonObject.getInt("code");
                            switch (code){
                                case 200:
                                    Toast.makeText(patient_reg.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                                case 301:
                                case 302:
                                    Toast.makeText(patient_reg.this, "AUTH EXPIRED or failed ", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(patient_reg.this,MainActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    auth.removeUser();
                                    startActivity(i);
                                    break;
                                case 300:
                                case 304:
                                case 305:
                                    Toast.makeText(patient_reg.this, "Something went wrong (code-"+code+")", Toast.LENGTH_LONG).show();
                                    break;
                                case 303:
                                    Toast.makeText(patient_reg.this, "Duplicate Entry", Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    Toast.makeText(patient_reg.this, "Unexpected Response", Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            Toast.makeText(patient_reg.this, "Unexpected Response", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: handle error here
                        System.out.println(error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        String gender = "ERROR";
                        String bloodGroup = "ERROR";
                        switch (genderSpin.getSelectedItemPosition()){
                            case 0:
                                gender = "Female";
                                break;
                            case 1:
                                gender = "Male";
                                break;
                            case 2:
                                gender = "OTHERS";
                                break;
                        }
                        switch (bloodGroupSpin.getSelectedItemPosition()){
                            case 0:
                                bloodGroup = "O-";
                                break;
                            case 1:
                                bloodGroup = "O+";
                                break;
                            case 2:
                                bloodGroup = "A-";
                                break;
                            case 3:
                                bloodGroup = "A+";
                                break;
                            case 4:
                                bloodGroup = "B-";
                                break;
                            case 5:
                                bloodGroup = "B+";
                                break;
                            case 6:
                                bloodGroup = "AB-";
                                break;
                            case 7:
                                bloodGroup = "AB+";
                                break;
                        }
                        Map<String, String> params = new HashMap<>();
                        params.put("auth", auth.getToken());
                        params.put("user_id", auth.getUserId());
                        params.put("name", patientname.getText().toString());
                        params.put("aadhar", aadharno.getText().toString());
                        params.put("age", age.getText().toString());
                        params.put("sex", gender);
                        params.put("address", address.getText().toString());
                        params.put("phone", phone.getText().toString());
                        params.put("height", height.getText().toString());
                        params.put("weight", weight.getText().toString());
                        params.put("blood_group", bloodGroup);
                        return params;
                    }
                    @Override
                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();
                        params.put("image", new DataPart("image.jpg", imageByte , "image/jpg"));
                        return params;
                    }
                };
                requestQueue.add(multipartRequest);
            }
        });
        regButton.setEnabled(false);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, CAMERA_PIC_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            imageData = (Bitmap) data.getExtras().get("data");
            //crop
            imageData = squareCrop(imageData);
            System.out.println(imageData);
            captureImage.setImageBitmap(imageData);
            regButton.setEnabled(true);
        } else if (resultCode == RESULT_CANCELED){
//            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            finish();
        }
        System.out.println(requestCode);
        super.onActivityResult(requestCode, resultCode, data);
    }
    public boolean validateForm(){
            if (patientname.getText().toString().equals("")){
                patientname.setError("Field required");
                patientname.requestFocus();
                return false;
            }
            if (age.getText().toString().equals("") || Integer.parseInt(age.getText().toString())>120){
                age.setError("Entry invalid");
                age.requestFocus();
                return false;
            }
            if (address.getText().toString().equals("")){
                address.setError("Field required");
                address.requestFocus();
                return false;
            }
            if (phone.getText().toString().equals("")){
                phone.setError("Field required");
                phone.requestFocus();
                return false;
            }
            return true;
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

