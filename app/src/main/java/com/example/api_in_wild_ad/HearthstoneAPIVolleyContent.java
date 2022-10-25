package com.example.api_in_wild_ad;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
    public static final List<HearthstoneAPILabelModal> HEARTHSTONE_LABELS = new ArrayList<HearthstoneAPILabelModal>();
    public static final Map<String, HearthstoneAPILabelModal> LABEL_MODAL_MAP = new HashMap<String, HearthstoneAPILabelModal>();

    private boolean haveIt = false;
    private boolean isBuilt = false;
    public boolean doneLoading = false;

    private List<String> classNamesList = new ArrayList<String>();


    private void addVolleyToList(HearthstoneAPIModel hearthstoneAPIModel){
        haveIt = false;
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

    private void addLabelToList(HearthstoneAPILabelModal hearthstoneAPILabelModal){
        haveIt = false;
        if(HEARTHSTONE_LABELS.size() != 0){
            for(int i = 0; i<HEARTHSTONE_LABELS.size(); i++ ){
                if(HEARTHSTONE_LABELS.get(i) == hearthstoneAPILabelModal){
                    haveIt = true;
                }
                else if(HEARTHSTONE_LABELS.get(i) == null){
                    haveIt = true;
                }
            }
        }
        if(!haveIt){
            HEARTHSTONE_LABELS.add(hearthstoneAPILabelModal);
            LABEL_MODAL_MAP.put(hearthstoneAPILabelModal.getLabels(), hearthstoneAPILabelModal);
        }
    }

    public void HearthStoneAPITesting(Activity activity){
        String url = res.getString(R.string.url);
        RequestQueue queue = Volley.newRequestQueue(activity);

        /**JSONObject postparameters = new JSONObject();
        try {
            postparameters.put("X-RapidAPI-Key", "f3e79d612amsh960c556b32ebebbp192b99jsn470f0169f532");
            postparameters.put("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com");
        } catch (JSONException error){

        }*/
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, response.toString(), duration);
                        //toast.show();
                        //The response HAS things in it.. but it won't make those things into the what I need
                        try{
                            //JSONObject object = response.getJSONObject("patch");
                            //toast = Toast.makeText(context, object.toString(), duration);
                            //toast.show();
                            /** classes has an array of strings! Not jsonobjects... */
                            JSONArray classesArray = response.getJSONArray("classes");
                            JSONArray setsArray = response.getJSONArray("sets");
                            JSONArray typesArray = response.getJSONArray("types");
                            JSONArray factionsArray = response.getJSONArray("factions");
                            JSONArray qualitiesArray = response.getJSONArray("qualities");
                            JSONArray racesArray = response.getJSONArray("races");
                            HEARTHSTONE_LABELS.clear();
                            HEARTHSTONE_API_STUFFS.clear();
                            VOLLEY_MAP.clear();
                            List<String> Labelss = new ArrayList<String>();
                            Labelss.add("Classes");
                            Labelss.add("Sets");
                            Labelss.add("Races");
                            for(int i = 0; i<racesArray.length(); i++){
                                /** The toast below works! */
                                //14 strings
                                String classNames = (String) classesArray.get(i);
                                //50 strings
                                String setNames = (String) setsArray.get(i);
                                //7 strings
                                //String typeNames = (String) typesArray.get(i);
                                //3 strings
                                //String factionNames = (String) factionsArray.get(i);
                                //5 strings
                                //String qualityNames = (String) qualitiesArray.get(i);
                                //12 strings
                                String raceNames = (String) racesArray.get(i);
                                //toast = Toast.makeText(context, classNames, duration);
                                //toast.show();
                                //String classssNames = String.valueOf(classNames);
                                //Gson gson = new Gson();
                                HearthstoneAPIModel HAM = new HearthstoneAPIModel(classNames, setNames, raceNames);
                                addVolleyToList(HAM);
                            }
                            for(int i = 0; i < 3; i++){
                                HearthstoneAPILabelModal HALM = new HearthstoneAPILabelModal(Labelss.get(i));
                                addLabelToList(HALM);
                            }



                        }
                        catch(JSONException e){
                            toast = Toast.makeText(context, String.valueOf(e), duration);
                            toast.show();
                        }
                        if(!isBuilt){
                            isBuilt = true;
                            activity.recreate();
                        }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                //error.printStackTrace();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "That didn't work...", duration);
                toast.show();
            }
        })
        {@Override
        public Map<String,String> getHeaders() throws AuthFailureError{
            HashMap<String,String> headers = new HashMap<String,String>();
            headers.put("X-RapidAPI-Key", "f3e79d612amsh960c556b32ebebbp192b99jsn470f0169f532");
            headers.put("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com");
            return headers;
        }
        };
        queue.add(request);
    }


}
