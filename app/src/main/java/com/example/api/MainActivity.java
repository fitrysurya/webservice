package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    private String TAMP_URL = "http://192.168.43.153/mobile_api/list_json_mhs.php";
    private TextView txtresult;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtresult = (TextView) findViewById(R.id.tampil_npm);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, TAMP_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("iki hasil e  :    "+response.toString());
                try {
                    JSONArray json = response.getJSONArray("list_info");
                    for (int i = 0; i < json.length(); i++) {
                        JSONObject info = json.getJSONObject(i);
                        String npm = info.getString("npm");
                        txtresult.append("\n npm : " + npm + "\n");
                    }
                    txtresult.append("====\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("iki eror e :::" + e);
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
