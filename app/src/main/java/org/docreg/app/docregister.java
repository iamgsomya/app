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

class docregister extends Fragment {
    private EditText InputdocId,Inputdocname,Inputadhaar,Inputloc,Inputspec,Inputbio,Inputrating,Inputdocemail,Inputdocphone,Inputclinicname;
    private Button RegisterButton;
    RequestQueue queue;
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

        RegisterButton=view.findViewById(R.id.Register_btn);
         return view;

    }
}
