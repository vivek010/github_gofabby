package gofabby.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vivek on 8/13/2016.
 */
public class Sub_Cat_Advt extends Fragment {
    ImageView iv_icon;
    ArrayList<HashMap<String, String>> image_list;
    TextView tv_title, continue_;
    int Cat_id = 0, Cat_pos = 0;
    String ad_section = "", sectionid = "";

    String title = "";

    Gofabby gofabby;
    Common_Methods cm;
    ViewPager vp;
    Fragment_adapter banner_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.classifieds_landing_with_add, null);
//        return super.onCreateView(inflater, container, savedInstanceState);
        iv_icon = (ImageView) v.findViewById(R.id.landing_page_cat_logo);
        cm = new Common_Methods();
        tv_title = (TextView) v.findViewById(R.id.landing_page_cat_name);
        image_list = new ArrayList<>();
        gofabby = (Gofabby) getActivity().getApplicationContext();
        Cat_id = Integer.parseInt(getArguments().getString("cat_id"));
        title = getArguments().getString("title");
        ad_section = getArguments().getString("ad_section");
        sectionid = getArguments().getString("sectionid");
        banner_adapter = new Fragment_adapter(getFragmentManager());
        vp = (ViewPager) v.findViewById(R.id.vp);
        continue_ = (TextView) v.findViewById(R.id.continue_button);

        Load_data();
        set_icon_and_name(Cat_id + "".trim(), title);
        continue_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f = new Classifieds_listing().datas(Cat_id + "", sectionid);
                getFragmentManager().beginTransaction().replace(R.id.fragment_lay, f).addToBackStack("").commit();
            }
        });

        return v;
    }

    class Fragment_adapter extends FragmentStatePagerAdapter {

        public Fragment_adapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            Fragment f = new Advt_lay_fragment().datas(image_list.get(position).get("ipath").toString(),
                    image_list.get(position).get("Title").toString(),
                    image_list.get(position).get("Description").toString(),
                    image_list.get(position).get("AdLink").toString(),
                    image_list.get(position).get("RightAdId").toString()
            );
            return f;
        }

        @Override
        public int getCount() {
            return image_list.size();
        }
    }

    //
    public Sub_Cat_Advt datas(String title, String cat_id, String ad_sction, String sectionid) {
        Sub_Cat_Advt Advt = new Sub_Cat_Advt();
        Bundle b = Advt.getArguments();
        if (b == null)
            b = new Bundle();
        b.putString("title", title);
        b.putString("cat_id", cat_id);
        b.putString("ad_section", ad_sction);
        b.putString("sectionid", sectionid);
        Log.e("", title + " " + cat_id + " " + ad_sction + " " + sectionid);
        Advt.setArguments(b);
        return Advt;
    }

    void set_icon_and_name(String Cat_id, String title) {
        tv_title.setText(title);
        Log.e("", "cat id" + Cat_id);
        switch (Integer.parseInt(Cat_id)) {
            case 1:
                iv_icon.setImageResource(R.drawable.home_classifieds);
                break;
            case 2:
                iv_icon.setImageResource(R.drawable.home_motor);
                break;
            case 3:
                iv_icon.setImageResource(R.drawable.home_property_for_);
                break;
            case 4:
                iv_icon.setImageResource(R.drawable.home_property_for_sale);
                break;
            case 5:
                iv_icon.setImageResource(R.drawable.hme_jobs);
                break;
            case 6:
                iv_icon.setImageResource(R.drawable.home_barter);
                break;
            case 7:
                iv_icon.setImageResource(R.drawable.home_directory);
                break;
            case 8:
                iv_icon.setImageResource(R.drawable.community);
                break;
            case 9:
                iv_icon.setImageResource(R.drawable.home_events);
                break;
            case 10:
                iv_icon.setImageResource(R.drawable.home_personnel_page);
                break;
            case 11:
                iv_icon.setImageResource(R.drawable.hme_business);
                break;
        }


    }

    void Load_data() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("adsection", ad_section);
            map.put("sectionid", sectionid);//1 for uae uae http://mobileapi.gofabby.com/home/GetCountryDetails
            map.put("categoryid", cm.Cat_id(Cat_id));


//            StringRequest request = new StringRequest(Request.Method.GET, cm.Serv_Address + "ClassifiedFeaturedAdFill?" + cm.getPostDataString(map).toString(), new Response.Listener<String>() {

            StringRequest request = new StringRequest(Request.Method.GET, cm.Serv_Address + "ClassifiedFeaturedAdFill?" + cm.getPostDataString(map).toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("", "data response" + response);
                    //{"Title":"Summer Offer 50%","Description":"Pamper Package","ipath":"/img/banner/urdreamspa 1.jpg","AdLink":"http://www.urdreamspa.com/","RightAdId":66}
                    try {
                        JSONArray jarr = new JSONArray(response);
                        for (int i = 0; i < jarr.length(); i++) {
                            JSONObject job = jarr.getJSONObject(i);
                            HashMap<String, String> map = new HashMap<>();
                            map.put("Title", job.getString("Title"));
                            map.put("Description", ("Description"));
                            map.put("ipath", "https://gofabby.com" + job.getString("ipath").replace(" ", "%20"));
                            map.put("AdLink", job.getString("AdLink"));
                            map.put("RightAdId", job.getString("RightAdId"));
                            image_list.add(map);
                        }
                        vp.setAdapter(banner_adapter);

                    } catch (Exception e) {
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("", "Error");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            cm.Show_dialog(getActivity());
            queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                @Override
                public void onRequestFinished(Request<String> request) {
                    cm.cancel_dialog();
                }
            });
            queue.add(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
