package com.example.paras.illumnusandroidinternshipassignment.FollowersPage;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.paras.illumnusandroidinternshipassignment.EntryPage.EntryPageMainActivity;
import com.example.paras.illumnusandroidinternshipassignment.EntryPage.EntryPageUserDataModel;
import com.example.paras.illumnusandroidinternshipassignment.EntryPage.EntryPageViewAdapter;
import com.example.paras.illumnusandroidinternshipassignment.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FollowersPageActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    private static final String BASE_URL = "https://api.github.com/";
    private static final String USER_URL = "users/";
    private RequestQueue queue;
    private String passedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_page);

        passedName = getIntent().getStringExtra("USER_LOGIN_ID2");
        Toast.makeText(this, "Followers of "+passedName , Toast.LENGTH_SHORT).show();

        queue = Volley.newRequestQueue(this);
        if (connectionIsEstablished()) {
            parseData();
        } else {
            Toast.makeText(this, "Internet Connection Error", Toast.LENGTH_SHORT).show();
        }

        recyclerView = (RecyclerView) findViewById(R.id.followersRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public boolean connectionIsEstablished() {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            isConnected = true;
        }
        return isConnected;
    }


    private void parseData() {

        StringRequest request = new StringRequest(BASE_URL + USER_URL + passedName + "/followers", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                FollowersPageDataModel[] users = gson.fromJson(response, FollowersPageDataModel[].class);
                recyclerView.setAdapter(new FollowersPageViewAdapter(FollowersPageActivity.this, users));
                //Log.d("CODE PARAS: ",users[0].getUserName());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Log.d("request paras: ", request.toString());

        queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}
