package org.docreg.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class docregister extends Fragment {
    private EditText InputdocId,Inputdocname,Inputadhaar,Inputloc,Inputspec,Inputbio,Inputrating,Inputdocemail,Inputdocphone,Inputclinicname;
    private Button RegisterButton;
    RequestQueue queue;
    Auth auth;
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_docregister, container, false);
        InputdocId=view.findViewById(R.id.Doc_id);
        Inputdocname=view.findViewById(R.id.Docname_input);
        Inputadhaar=view.findViewById(R.id.Doc_adhaar_input);
        Inputloc=view.findViewById(R.id.Location_input);
        Inputbio=view.findViewById(R.id.Bio_input);
        Inputrating=view.findViewById(R.id.Rating_input);
        Inputspec=view.findViewById(R.id.Spec_input);
        Inputdocemail=view.findViewById(R.id.Docemail_input);
        Inputdocphone=view.findViewById(R.id.Docphone_input);
        Inputclinicname=view.findViewById(R.id.clinic_name_input);

        RegisterButton=view.findViewById(R.id.Doc_Register_btn);
        queue = Volley.newRequestQueue(getContext());
        auth = new Auth(getContext());
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = Constants.docRegisterUrl;
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                try{
                                    JSONObject jsonObject=new JSONObject(response);
                                    int code=jsonObject.getInt("code");
                                    String message=jsonObject.getString("message");
                                    System.out.println("code"+code+"\nmessage:"+message);
                                    if(code == 200) {
                                        Toast toast = Toast.makeText(getContext(),
                                                "doc registered",
                                                Toast.LENGTH_SHORT);

                                        toast.show();

                                        Intent intent = new Intent(getContext(), fronthome.class);
                                        startActivity(intent);
                                    }
                                    else if(code == 300) {
                                        Toast toast = Toast.makeText(getContext(),
                                                "already registered",
                                                Toast.LENGTH_SHORT);

                                        toast.show();

                                        Intent intent = new Intent(getContext(), fronthome.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast toast = Toast.makeText(getContext(),
                                                "doc not registered",
                                                Toast.LENGTH_SHORT);

                                        toast.show();
                                        Intent intent = new Intent(getContext(), fronthome.class);
                                        startActivity(intent);

                                    }

                                }
                                catch (Exception e){
                                    System.out.println("exception caught");

                                }
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d("Error.Response", error.toString());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("auth", auth.getToken());
                        params.put("id", InputdocId.getText().toString());
                        params.put("name", Inputdocname.getText().toString());
                        params.put("aadhaar_no",Inputadhaar.getText().toString() );
                        params.put("clinic_location", Inputloc.getText().toString());
                        params.put("specialization", Inputspec.getText().toString());
                        params.put("bio", Inputbio.getText().toString());
                        params.put("rating", Inputrating.getText().toString());
                        params.put("email",Inputdocemail .getText().toString());
                        params.put("phone", Inputdocphone.getText().toString());
                        params.put("clinic_name",Inputclinicname.getText().toString() );


                        return params;
                    }
                };
                queue.add(postRequest);



            }
        });
        return view;


    }
}
