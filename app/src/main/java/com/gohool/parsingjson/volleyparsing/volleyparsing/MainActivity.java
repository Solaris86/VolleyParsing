package com.gohool.parsingjson.volleyparsing.volleyparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "64abcd284a09ff045768cb23884daf66";
    private static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US&page=1";
    private static final String URL_STRING = "http://magadistudio.com/complete-android-developer-course-source-files/string.html";
    private static final String URL_EQ = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("URL: ", URL);

        queue = Volley.newRequestQueue(this);

//        getJsonArray(URL);
//        getStringObject(URL_STRING);
        getJsonObject(URL_EQ);

    }

    public void getJsonArray(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movieObject = results.getJSONObject(i);
                        Log.d("Items: ", movieObject.getString("title") + " / " + "Release Date: " + movieObject.getString("release_date"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: ", error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void getStringObject(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("My String: ", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: ", error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    private void getJsonObject(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    Log.d("Object: ", response.getString("type"));

//                    JSONObject metadata = response.getJSONObject("metadata");
//                    Log.d("Metadata: ", metadata.toString());
//                    Log.d("MetaInfo: ", metadata.getString("generated"));
//                    Log.d("MetaInfo: ", metadata.getString("url"));
//                    Log.d("MetaInfo: ", metadata.getString("title"));

                    JSONArray features = response.getJSONArray("features");
                    for (int i = 0; i < features.length(); i++) {
                        JSONObject propertiesObj = features.getJSONObject(i).getJSONObject("properties");
                        Log.d("Place", propertiesObj.getString("place"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
    }
}
