package com.example.paras.illumnusandroidinternshipassignment.DetailsPage;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.paras.illumnusandroidinternshipassignment.DetailsPage.RepositoriesDetails.DetailsPageRepoAdapter;
import com.example.paras.illumnusandroidinternshipassignment.DetailsPage.RepositoriesDetails.DetailsPageRepoDataModel;
import com.example.paras.illumnusandroidinternshipassignment.DetailsPage.UserDetails.DetailsPageViewAdapter;
import com.example.paras.illumnusandroidinternshipassignment.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsPageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView recyclerViewRepos;

    private static final String BASE_URL = "https://api.github.com/";
    private static final String USER_URL = "users";
    private RequestQueue queue, queue2;
    String userNamePassed;
    String repoUrl;
    DetailsPageRepoDataModel[] userRepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);


        userNamePassed = getIntent().getStringExtra("USER_LOGIN_ID");

        queue = Volley.newRequestQueue(this);
        if (connectionIsEstablished()) {
            parseUserData();

        } else {
            Toast.makeText(this, "Internet Connection Error", Toast.LENGTH_SHORT).show();
        }

        recyclerView = (RecyclerView) findViewById(R.id.detailRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewRepos = (RecyclerView) findViewById(R.id.reposRecyclerView);
        recyclerViewRepos.setLayoutManager(new LinearLayoutManager(this));

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

    private void parseUserData() {

        StringRequest request = new StringRequest(BASE_URL + USER_URL + "/" + userNamePassed, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject person = new JSONObject(response);
                    repoUrl = person.getString("repos_url");
                    parseReposData();
                    String url = person.getString("avatar_url");
                    String name = person.getString("login");
                    String company = person.getString("company");
                    String email = person.getString("email");
                    String repos = person.getString("public_repos");
                    String followers = person.getString("followers");
                    String following = person.getString("following");
                    String location = person.getString("location");

                    recyclerView.setAdapter(new DetailsPageViewAdapter(DetailsPageActivity.this, name, url, company, email, repos, followers, following, location));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue = Volley.newRequestQueue(this);
        queue.add(request);


    }


    private DetailsPageRepoDataModel[] parseReposData() {

        StringRequest request2 = new StringRequest(repoUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder2 = new GsonBuilder();
                Gson gson2 = gsonBuilder2.create();

                userRepos = gson2.fromJson(response, DetailsPageRepoDataModel[].class);
                recyclerViewRepos.setAdapter(new DetailsPageRepoAdapter(DetailsPageActivity.this, userRepos));
                //Toast.makeText(DetailsPageActivity.this, userRepos.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue2 = Volley.newRequestQueue(this);
        queue2.add(request2);
        return userRepos;
    }
}
