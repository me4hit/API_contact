package com.example.mock_api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private Button btn_add;
    private RecyclerView rev_contact;
    ArrayList<Contact> contactArrayList = new ArrayList<>();
    private ContactListApdater contactListApdater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rev_contact = findViewById(R.id.rev_contact);
        btn_add = findViewById(R.id.btn_add);
        contactListApdater = new ContactListApdater(this, contactArrayList);
        rev_contact.setAdapter(contactListApdater);
        rev_contact.setLayoutManager(new LinearLayoutManager(this));
        loadallcontact();


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_newcontact.class);
                startActivity(intent);

            }
        });



    }
    @Override
    protected void onRestart() {
        super.onRestart();
        loadallcontact();
    }

    public void loadallcontact() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://5fd065f51f2374001663170f.mockapi.io/contact";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                contactArrayList.clear();
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject object =(JSONObject) response.get(i);
                        Contact contact= new Contact();
                        contact.setId(object.getString("id").toString());
                        contact.setName(object.getString("name").toString());
                        contact.setNumberphone(object.getString("numberphone").toString());
                        contactArrayList.add(contact);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "lỗi");

                    }
                }
                contactListApdater.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
