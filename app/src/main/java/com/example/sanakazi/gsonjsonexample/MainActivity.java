package com.example.sanakazi.gsonjsonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.sanakazi.gsonjsonexample.forArray.DataModel;
import com.example.sanakazi.gsonjsonexample.forObject.DataModel1;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    private static final String REGISTER_URL_FOR_ARRAY = "http://webservices.shaadielephant.com/usersVendors.asmx/usersVendorListCategoryGrouped?userID=20194";
    private static final String REGISTER_URL_FOR_OBJECT = "http://fitdev.cloudapp.net:8080/B2BAPIPlatform/GetCategoryGroup/v1?cityId=1";
    private static final String TAG= MainActivity.class.getSimpleName();
    private ArrayList<DataModel1.Categories> categories_arrayList;
    private ArrayList<DataModel.ParentModel> parent_arrayList;
    private ArrayList<DataModel.ParentModel.ChildModel> child_arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories_arrayList = new ArrayList<>();
        parent_arrayList = new ArrayList<>();
        child_arrayList = new ArrayList<>();
       volleyService_forObject();
      volleyService_forArray(); // can be used in expandable view


    }

    private void volleyService_forObject()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL_FOR_OBJECT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w(TAG,"Response is " + response.toString());

                       Gson gson= new Gson();
                        DataModel1 jsonResponse = gson.fromJson(response, DataModel1.class);
                       Log.w(TAG,"Status code is " + jsonResponse.getStatusCode().toString());
                        Log.w(TAG,"Values are ");
                     categories_arrayList=jsonResponse.getData().getCategories();
                        for(int i=0;i<categories_arrayList.size();i++)
                        Log.w(TAG, categories_arrayList.get(i).getName());



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void volleyService_forArray()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL_FOR_ARRAY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.w(TAG, "Response for array is " + response.toString());

                        Gson gson = new Gson();
                        DataModel jsonResponse = gson.fromJson(response, DataModel.class);
                        if (jsonResponse.getStatus() == 1) {
                            parent_arrayList = jsonResponse.getMessage();
                            Log.w(TAG, "Values for Parent are " );

                            for (int i = 0; i < parent_arrayList.size(); i++) {
                                Log.w(TAG, parent_arrayList.get(i).getFullName());
                              if(parent_arrayList.get(i).getVendorList()!=null)
                              {

                                  int childArraySize=parent_arrayList.get(i).getVendorList().size();
                                  Log.w(TAG, "Values for Child are " );
                                  child_arrayList = parent_arrayList.get(i).getVendorList();
                                  for(int j=0;j<childArraySize;j++)
                                  {
                                      Log.w(TAG, child_arrayList.get(j).getFullName());
                                  }
                              }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
