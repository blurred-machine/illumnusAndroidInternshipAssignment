package com.example.paras.illumnusandroidinternshipassignment.EntryPage;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.paras.illumnusandroidinternshipassignment.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EntryPageMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private static final String BASE_URL = "https://api.github.com/";
    private static final String USER_URL = "users";
    private RequestQueue queue;
    boolean backPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        queue = Volley.newRequestQueue(this);
        if (connectionIsEstablished()){
            parseData(false,null);
        }else {
            Toast.makeText(this, "Internet Connection Error", Toast.LENGTH_SHORT).show();
        }

        recyclerView = (RecyclerView)findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //String[] testData = {"dsfa", "fasdf"};
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem userSearch = menu.findItem(R.id.menuSearch);

        SearchView searchView = (SearchView)userSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                    parseData(true, s);
                    return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.homeSearch){
            parseData(false, null);
        }
        return super.onOptionsItemSelected(item);
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


    private void parseData(boolean isSearched, String s) {
        String url;
        if (isSearched){
             url = BASE_URL + "search/" + USER_URL + "?q="+s;

            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONArray array = null;
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        array = jsonObject.getJSONArray("items");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    EntryPageUserDataModel[] users = gson.fromJson(array.toString(), EntryPageUserDataModel[].class);
                    recyclerView.setAdapter(new EntryPageViewAdapter(EntryPageMainActivity.this, users));
                    //Log.d("CODE PARAS: ",users[0].getUserName());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue = Volley.newRequestQueue(this);
            queue.add(request);

        }else{
            url = BASE_URL + USER_URL;

            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    EntryPageUserDataModel[] users = gson.fromJson(response, EntryPageUserDataModel[].class);
                    recyclerView.setAdapter(new EntryPageViewAdapter(EntryPageMainActivity.this, users));
                    //Log.d("CODE PARAS: ",users[0].getUserName());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue = Volley.newRequestQueue(this);
            queue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.backPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backPressedOnce=false;
            }
        }, 2000);
    }

}
