package com.example.mock_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class update_contact extends AppCompatActivity {
    private TextView tv_title;
    private EditText txt_name, txt_numberphone;
    private Button btn_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");
        Log.d("update", "contact name :" + contact.getName());
        setContentView(R.layout.activity_update_contacty);
        btn_update = findViewById(R.id.btn_update_contact);
        tv_title = findViewById(R.id.tv_title);
        txt_name = findViewById(R.id.txt_name);
        txt_numberphone = findViewById(R.id.txt_numberphone);
        tv_title.setText("UPDATE CONTACT ID ");
        txt_name.setText(contact.getName());
        txt_numberphone.setText(contact.getNumberphone());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(contact.getId());
            }
        });
    }

    private void update(String id) {
        String url = "https://5fd065f51f2374001663170f.mockapi.io/contact";

        String name = txt_name.getText().toString();
        String numberphone = txt_numberphone.getText().toString();
        if (name.isEmpty() || numberphone.isEmpty()) {
            Toast.makeText(this, "Chưa điền đủ thông tin !!!", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.PUT, url+ "/"+ id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(update_contact.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(update_contact.this, "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }) {
                @Override
                protected Map<String, String> getParams()
                        throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("numberphone", numberphone);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

}