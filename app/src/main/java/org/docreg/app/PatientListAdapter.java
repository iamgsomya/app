package org.docreg.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.ViewHolder> {
    private ArrayList<PatientModel> patientList;
    private Context context;

    public PatientListAdapter(ArrayList<PatientModel> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.patient_card_layout,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PatientModel patientModel = patientList.get(position);
        holder.textView.setText(patientModel.getName());
        Picasso.get().load(patientModel.getImageUrl()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(),PatientDetailsScreen.class);
                i.putExtra("patientId",patientModel.getId());
                i.putExtra("name",patientModel.getName());
                i.putExtra("aadhar",patientModel.getAadhar());
                i.putExtra("age",patientModel.getAge());
                i.putExtra("gender",patientModel.getGender());
                i.putExtra("address",patientModel.getAddress());
                i.putExtra("phone",patientModel.getPhone());
                i.putExtra("height",patientModel.getHeight());
                i.putExtra("weight",patientModel.getWeight());
                i.putExtra("bloodGroup",patientModel.getBloodGroup());
                i.putExtra("imageUrl",patientModel.getImageUrl());
                context.startActivity(i) ;
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.cardView = itemView.findViewById(R.id.patient_card);
            this.imageView = itemView.findViewById(R.id.card_image);
            this.textView = itemView.findViewById(R.id.card_name);
        }
    }
}
