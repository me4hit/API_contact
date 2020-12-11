package com.example.mock_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class add_newcontact extends AppCompatActivity {
    private Button btn_add;
    private EditText txt_name, txt_numberphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_newcontact);
        btn_add = findViewById(R.id.btn_update_contact);
        txt_name = findViewById(R.id.txt_name);
        txt_numberphone = findViewById(R.id.txt_numberphone);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnewcontact();
            }
        });

    }


    private void addnewcontact() {
        String url = "https://5fd065f51f2374001663170f.mockapi.io/contact";

        String name = txt_name.getText().toString();
        String numberphone = txt_numberphone.getText().toString();
        if (name.isEmpty() || numberphone.isEmpty()) {
            Toast.makeText(this, "Chưa điền đủ thông tin !!!", Toast.LENGTH_SHORT).show();
        } else {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, url , new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(add_newcontact.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(add_newcontact.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
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