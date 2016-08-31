package gofabby.com;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Window;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vivek on 8/13/2016.
 */
public class Common_Methods extends Activity {
    Dialog dl;
    public String Shared_Pref_Country="country";
    public String Serv_Address="https://mobileapi.gofabby.com/home/";
    public String Serv_Address_Banner="https://mobileapi.gofabby.com/Image/";
    public String Imgae_Address_Banner="https://mobileapi.gofabby.com//Datas/images/banners/";
    public  String Shared_pref_Country_id="country_id";
    public  String Shared_pref_Country_name="country_name";

    public String Selected_country_name(Context context)
    {
        SharedPreferences sf=context.getSharedPreferences(Shared_Pref_Country,0);
        return sf.getString(Shared_pref_Country_name,"");

    }
    public String Selected_country_id(Context context)
    {
        SharedPreferences sf=context.getSharedPreferences(Shared_Pref_Country,0);
        return sf.getString(Shared_pref_Country_id,"1");

    }
   public String Cat_name (int i)
    {
        switch (i)
        {
            case 1:return "CLASSIFIEDS";
            case 2:return "MOTORS";
            case 3:return "PROPERTY FOR SALE";
            case 4:return "PROPERTY FOR RENT";
            case 5:return "JOBS";
            case 6:return "BARTER";
            case 7:return "DIRECTORY";
            case 8:return "COMMUNITY";
            case 9:return "FORUM";
            case 10:return "EVENTS";
            case 11:return "PERSONAL PAGE";
            case 12:return "BUSINESS PAGE";
        }
        return "";

    }
    public String Cat_id (int i)
    {

        switch (i)
        {
            case 1:return "998";
            case 2:return "999";
            case 3:return "997";
            case 4:return "990";
            case 5:return "996";
            case 6:return "995";
            case 7:return "994";
            case 8:return "993";
            case 9:return "991";
            case 10:return "PERSONAL PAGE";
            case 11:return "BUSINESS PAGE";
        }
        return "";

    }

    public String Image_Folder_location (int i)
    {

        switch (i)
        {
            case 1:return "cl";
            case 2:return "motor";
            case 3:return "prop";
            case 4:return "prop";
            case 5:return "996";
            case 6:return "995";
            case 7:return "994";
            case 8:return "993";
            case 9:return "991";
            case 10:return "PERSONAL PAGE";
            case 11:return "BUSINESS PAGE";
        }
        return "";

    }
    public String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }


        return result.toString();
    }
   public ArrayList<String> Load_banners(String url,Context context) {
        final ArrayList<String> list=new ArrayList<>();
        try {

//            StringRequest request = new StringRequest(Request.Method.GET, cm.Serv_Address + "ClassifiedFeaturedAdFill?" + cm.getPostDataString(map).toString(), new Response.Listener<String>() {
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("", "data response" + response);
                    //{"countryid":1,"SectionID":"998","CategoryId":1,"CategoryName":"Accessories","itemcount":"1523"}
                    try {
                        JSONArray jarr = new JSONArray(response);
                        for (int i = 0; i < jarr.length(); i++) {
                            JSONObject job = jarr.getJSONObject(i);

                            list.add(job.getString("ImageName"));

                        }

                    } catch (Exception e) {

                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("", "Error");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
       finally{
       Log.e("", "data response outside try catch" + list);
       return list;}
    }
    /*

     */


    public void Show_dialog(Context context) {
        dl = new Dialog(context);
        dl.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dl.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dl.setContentView(R.layout.loading_dialog);
        dl.show();

    }

    public void cancel_dialog() {
        dl.cancel();
    }

}
