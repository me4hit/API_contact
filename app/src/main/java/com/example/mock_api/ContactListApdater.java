package com.example.mock_api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class ContactListApdater extends RecyclerView.Adapter<ContactListApdater.ContactViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<Contact> contactList;
    private static final String TAG = "MyActivity";
    private  Context context;
    public ContactListApdater(Context context,ArrayList<Contact> contactList ) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.contactList= contactList;
    }

    @NonNull
    @Override
    public ContactListApdater.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_contact, parent, false);
        return new ContactViewHolder(view, this);



    }

    @Override
    public void onBindViewHolder(@NonNull ContactListApdater.ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.tv_id.setText(contact.getId());
        holder.tv_name.setText(contact.getName());
        holder.tv_numberphone.setText(contact.getNumberphone());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "xóa"+contact.getId());

                deleteContact(contact.getId(), position);
            }
        });
        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update_contact.class);
                intent.putExtra("contact", contact);
                context.startActivity(intent);

            }
        });
    }

    private void deleteContact(String id, int position){
        String url = "https://5fd065f51f2374001663170f.mockapi.io/contact";
        Log.d(TAG, url);

        StringRequest stringRequest= new StringRequest(Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                contactList.remove(position);
                ContactListApdater.this.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }



    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_id, tv_name, tv_numberphone;
        public ImageButton btn_update, btn_delete;
        ContactListApdater contactListApdater;

        public ContactViewHolder(@NonNull View itemView, ContactListApdater contactListApdater) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_numberphone = itemView.findViewById(R.id.tv_numberphone);
            btn_update = itemView.findViewById(R.id.btn_update);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            this.contactListApdater = contactListApdater;
        }
    }
}
