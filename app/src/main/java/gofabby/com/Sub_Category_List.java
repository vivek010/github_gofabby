package gofabby.com;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vivek on 8/21/2016.
 */

public class Sub_Category_List extends Fragment {
    ArrayList<HashMap<String, String>> list;
    int Cat_id = 0, Cat_pos = 0;
    ImageView iv_icon;
    ArrayList<String> image_list;
    TextView tv_title;
    String title = "";
    Common_Methods cm;
    RecyclerView rv;
    Recycler_View adapter;
    ViewPager vp;
    slide_show_adapter Page_adapter;
    CirclePageIndicator tp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.sub_category, null);
        Cat_id = Integer.parseInt(getArguments().getString("cat_id"));
        title = getArguments().getString("title");
        list = new ArrayList<>();
        iv_icon = (ImageView) v.findViewById(R.id.sub_cat_icon);
        cm = new Common_Methods();
        tv_title = (TextView) v.findViewById(R.id.sub_cat_name);
        set_icon_and_name(Cat_id + "".trim(), title);
        rv = (RecyclerView) v.findViewById(R.id.rv);
        image_list = new ArrayList<>();
        vp = (ViewPager) v.findViewById(R.id.vp);
        Page_adapter = new slide_show_adapter();
        tp = (CirclePageIndicator) v.findViewById(R.id.titles);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setStackFromEnd(true);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        rv.setLayoutManager(layoutManager);
        adapter = new Recycler_View();
        rv.setAdapter(adapter);
        Load_Data(cm.Serv_Address + "GetSubCategoryList?");

//        cm.Load_banners(cm.Serv_Address_Banner + "GetBannerList",getActivity());

//        new load_banners().execute(cm.Serv_Address_Banner + "GetBannerList");
        Load_banners(cm.Serv_Address_Banner + "GetBannerList");

        return v;
//        View v=inflater.inflate()
    }

    public Sub_Category_List datas(String title, String country_id, String cat_id) {
        Sub_Category_List Advt = new Sub_Category_List();
        Bundle b = Advt.getArguments();
        if (b == null)
            b = new Bundle();
        b.putString("title", title);
        b.putString("country_id", country_id);
        b.putString("cat_id", cat_id);

        Advt.setArguments(b);
//        this.id=id;
        return Advt;
    }

    void set_icon_and_name(String Cat_id, String title) {
        tv_title.setText(title);
        Log.e("", "cat id" + Cat_id);
        Palette palette = null;
        switch (Integer.parseInt(Cat_id)) {
            case 1:
                iv_icon.setImageResource(R.drawable.home_classifieds);

                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_classifieds)).generate();
                break;
            case 2:
                iv_icon.setImageResource(R.drawable.home_motor);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_motor)).generate();
                break;
            case 3:
                iv_icon.setImageResource(R.drawable.home_property_for_);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_property_for_)).generate();
                break;
            case 4:
                iv_icon.setImageResource(R.drawable.home_property_for_sale);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_property_for_sale)).generate();
                break;
            case 5:
                iv_icon.setImageResource(R.drawable.hme_jobs);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.hme_jobs)).generate();
                break;
            case 6:
                iv_icon.setImageResource(R.drawable.home_barter);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_barter)).generate();
                break;
            case 7:
                iv_icon.setImageResource(R.drawable.home_directory);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_directory)).generate();
                break;
            case 8:
                iv_icon.setImageResource(R.drawable.home_forum);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_forum)).generate();
                break;
            case 9:
                iv_icon.setImageResource(R.drawable.community);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.community)).generate();
                break;
            case 10:
                iv_icon.setImageResource(R.drawable.home_events);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_events)).generate();
                break;
            case 11:
                iv_icon.setImageResource(R.drawable.home_personnel_page);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.home_personnel_page)).generate();
                break;
            case 12:
                iv_icon.setImageResource(R.drawable.hme_business);
                palette = palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.hme_business)).generate();
                break;

        }

//        Palette palette = Palette.from(((BitmapDrawable)iv_icon.getDrawable()).getBitmap()).generate();
        //[Swatch [RGB: #ff527300] [HSL: [77.21739, 0.99999994, 0.2254902]]
        // [Population: 7276] [Title Text: #96ffffff] [Body Text: #dbffffff],
        // Swatch [RGB: #ff527b00] [HSL: [79.99999, 0.99999994, 0.24117647]]
        // [Population: 135] [Title Text: #a3ffffff] [Body Text: #ebffffff]]
//        Log.e("", "Color" + palette.getSwatches().get(0).getRgb());

        try {
            tv_title.setTextColor(palette.getSwatches().get(0).getRgb());
        } catch (Exception e) {
        }


    }


    class Recycler_View extends RecyclerView.Adapter<Recycler_View.Viewholder> {
        @Override
        public Viewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.sub_cat_list, viewGroup, false);
            Viewholder v = new Viewholder(itemLayoutView);

            return v;
        }

        @Override
        public void onBindViewHolder(Viewholder viewholder, int position) {
            final HashMap<String, String> map = list.get(position);
            viewholder.tv_name_id().setText(map.get("CategoryName"));
            viewholder.tv_count().setText(map.get("itemcount"));
            viewholder.view().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//String title, String cat_id, String ad_sction, String sectionid

                    Log.e("", "" + title + Cat_id + "".trim() + "    " + title + "    " + map.get("CategoryId"));
                    Fragment f = new Sub_Cat_Advt().datas(map.get("CategoryName"), Cat_id + "".trim(), title, map.get("CategoryId"));

                    getFragmentManager().beginTransaction().replace(
                            R.id.fragment_lay, f
                    ).addToBackStack("").commit();


                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Viewholder extends RecyclerView.ViewHolder {
            TextView tv_name;
            TextView tv_count;
            LinearLayout view;

            public Viewholder(View v) {

                super(v);
                tv_name = (TextView) v.findViewById(R.id.sub_cat_name);
                tv_count = (TextView) v.findViewById(R.id.sub_cat_count);
                view = (LinearLayout) v.findViewById(R.id.relative_lay);
            }

            TextView tv_name_id() {
                return tv_name;
            }

            TextView tv_count() {
                return tv_count;
            }

            LinearLayout view() {
                return view;
            }

        }
    }

    void Load_Data(String url) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("countryid", cm.Selected_country_id(getActivity()));//1 for uae uae http://mobileapi.gofabby.com/home/GetCountryDetails
            map.put("sectionId", cm.Cat_id(Cat_id));


//            StringRequest request = new StringRequest(Request.Method.GET, cm.Serv_Address + "ClassifiedFeaturedAdFill?" + cm.getPostDataString(map).toString(), new Response.Listener<String>() {
            StringRequest request = new StringRequest(Request.Method.GET, url + cm.getPostDataString(map), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("", "data response" + response);
                    //{"countryid":1,"SectionID":"998","CategoryId":1,"CategoryName":"Accessories","itemcount":"1523"}
                    try {
                        JSONArray jarr = new JSONArray(response);
                        for (int i = 0; i < jarr.length(); i++) {
                            JSONObject job = jarr.getJSONObject(i);
                            HashMap<String, String> map = new HashMap<>();
                            map.put("countryid", job.getString("countryid"));
                            map.put("CategoryId", job.getString("CategoryId"));
                            map.put("CategoryName", job.getString("CategoryName"));
                            map.put("itemcount", job.getString("itemcount"));
                            list.add(map);

                        }
                        rv.setAdapter(adapter);
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

    void Load_banners(String url) {
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

                            image_list.add(job.getString("ImageName"));

                        }
                        vp.setAdapter(Page_adapter);
                        tp.setViewPager(vp);

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
            queue.add(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class slide_show_adapter extends PagerAdapter {

        Context c_;
        ArrayList<String> list_;

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
//			NUM_PAGES=5;
            return image_list.size();
//            if (images.size() > 1)
//                return Integer.MAX_VALUE;
//            else return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
//			view == ((RelativeLayout) object);
            return arg0 == ((View) arg1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            // TODO Auto-generated method stub
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.view_pager_image_for_prodcu_images, container, false);
            final ImageView iv = (ImageView) v.findViewById(R.id.slideshow);

//            final String image = images.get((position % images.size()));
            final String image = image_list.get((position % image_list.size()));
            Log.e("", "" + image);
            try {
//                aq.id(R.id.slideshow).image(image.replace(" ", "%20"), true, true);
                Picasso.with(getActivity()).load("https://mobileapi.gofabby.com//Datas/images/banners/" + image).placeholder(getResources().getDrawable(R.drawable.temp_banner)).into(iv);
            } catch (Exception e) {
            }
            ((ViewPager) container).addView(v);
//    		return v;
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                }
            });
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            //super.destroyItem(container, position, object);
            ((ViewPager) container).removeView((RelativeLayout) object);
            //int virtualPosition = position % images.size();
//	        super.destroyItem(container, virtualPosition, object);
        }

    }


}
