package com.example.api_in_wild_ad;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HearthstoneAPIVolleyContent {
    Context context = App.getContext();
    Resources res = context.getResources();

    public static final Map<String, HearthstoneAPIModel> VOLLEY_MAP = new HashMap<String, HearthstoneAPIModel>();
    public static final List<HearthstoneAPIModel> HEARTHSTONE_API_STUFFS = new ArrayList<HearthstoneAPIModel>();

    private boolean haveIt = false;
    private boolean isBuilt = false;


    private void addVolleyToList(HearthstoneAPIModel hearthstoneAPIModel){
        if(HEARTHSTONE_API_STUFFS.size() != 0){
            for(int i = 0; i < HEARTHSTONE_API_STUFFS.size(); i++){
                if(HEARTHSTONE_API_STUFFS.get(i) == hearthstoneAPIModel){
                    haveIt = true;
                }
                else if(HEARTHSTONE_API_STUFFS.get(i) == null){
                    haveIt = true;
                }
            }
        }
        if(!haveIt){
            HEARTHSTONE_API_STUFFS.add(hearthstoneAPIModel);
            VOLLEY_MAP.put(hearthstoneAPIModel.getClassName(), hearthstoneAPIModel);
        }
    }

    public void HearthStoneAPITesting(Activity activity){
        String url = res.getString(R.string.url);
        RequestQueue queue = Volley.newRequestQueue(activity);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray classesArray = response.getJSONArray("classes");
                            HEARTHSTONE_API_STUFFS.clear();
                            VOLLEY_MAP.clear();
                            for(int i = 0; i<classesArray.length(); i++){
                                JSONObject classNames = classesArray.getJSONObject(i);
                                String classssNames = String.valueOf(classNames);
                                Gson gson = new Gson();
                                HearthstoneAPIModel HAM = gson.fromJson(classssNames, HearthstoneAPIModel.class);
                                addVolleyToList(HAM);
                            }

                        }
                        catch(JSONException e){

                        }
                        if(!isBuilt){
                            isBuilt = true;
                            activity.recreate();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){error.printStackTrace();}
        });
        queue.add(request);
    }


}
