package gofabby.com;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by vivek on 8/24/2016.
 */
public class Classifieds_listing extends Fragment {
    RecyclerView rv;
    Common_Methods cm;
    String Cat_id = "", Section_id = "";
    ArrayList<HashMap<String, String>> list;
    Recycler_View Recycler_view_adaper;
    int last_visible_item_count = 0;
    SwipeRefreshLayout swipe_refresh;
    int item_count = 0;
    ArrayList<String> id_list;
    String last_advt_id = "0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.classifieds_liting, null);
        rv = (RecyclerView) v.findViewById(R.id.rv);
        id_list = new ArrayList<>();
        swipe_refresh = (SwipeRefreshLayout) v.findViewById(R.id.swipe);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setStackFromEnd(true);
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        rv.setLayoutManager(layoutManager);
        cm = new Common_Methods();
        Cat_id = getArguments().getString("Cat_id");
        Section_id = getArguments().getString("Section_id");
        list = new ArrayList<>();
        Recycler_view_adaper = new Recycler_View();
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.column_width);
        rv.addItemDecoration(itemDecoration);
        Load_Data(cm.Serv_Address + "Pagination?", "0", false);
        swipe_refresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipe_refresh.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        swipe_refresh.setEnabled(true);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(getActivity(), "loading", Toast.LENGTH_SHORT).show();
//                Load_Data(cm.Serv_Address + "Pagination?", last_advt_id, true);
            }
        });
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                item_count = layoutManager.getItemCount();
                last_visible_item_count = layoutManager.findLastVisibleItemPosition();
                if (item_count == (last_visible_item_count + 1)) {
                    if (!swipe_refresh.isRefreshing()) {
                        swipe_refresh.post(new Runnable() {
                            @Override
                            public void run() {
                                swipe_refresh.setRefreshing(true);
                                Load_Data(cm.Serv_Address + "Pagination?", last_advt_id, true);
                            }
                        });
                    }


                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return v;
    }

    public Classifieds_listing datas(String Cat_id, String Section_id) {
        Classifieds_listing f = new Classifieds_listing();

        Bundle b = f.getArguments();
        if (b == null)
            b = new Bundle();


        b.putString("Cat_id", Cat_id);
        b.putString("Section_id", Section_id);
        Log.e("", "section id" + Section_id);
        f.setArguments(b);
        return f;
    }

    void Load_Data(String url, String id, final boolean refreshing) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("countryid", cm.Selected_country_id(getActivity()));//1 for uae uae http://mobileapi.gofabby.com/home/GetCountryDetails
            //  map.put("categoryid", cm.Cat_id(Integer.parseInt(Cat_id)));
//            map.put("adoption", "buy");
            map.put("adoption", "sell");
            map.put("categoryid", Section_id);
            map.put("Id", id);


//            StringRequest request = new StringRequest(Request.Method.GET, cm.Serv_Address + "ClassifiedFeaturedAdFill?" + cm.getPostDataString(map).toString(), new Response.Listener<String>() {
//            Log.e("", "url" + url + cm.getPostDataString(map));
            StringRequest request = new StringRequest(Request.Method.GET, url + cm.getPostDataString(map), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("", "data response" + response);
                    try {


                        JSONArray jarr = new JSONArray(response);
                        for (int i = 0; i < jarr.length(); i++) {
                            JSONObject job = jarr.getJSONObject(i);
//                            Log.d("", "map" + job.names());
                            HashMap<String, String> map = new HashMap<>();
                            JSONArray names = job.names();
                            for (int j = 0; j < names.length(); j++) {
                                map.put(names.getString(j), job.getString(names.getString(j)));
                            }
                            id_list.add(job.getString("advtid"));
//

//                            Log.e("", "map" + map);
                            list.add(map);
                        }
                        if (refreshing) {
                            Recycler_view_adaper.notifyDataSetChanged();
                        } else rv.setAdapter(Recycler_view_adaper);

                        Collections.sort(id_list);
//                        Collections.reverse(id_list);
                        Log.e("","id list"+id_list+"");


                        last_advt_id = id_list.get(0);
                        Log.e("","LAst id"+last_advt_id );
                        id_list.clear();
                        swipe_refresh.setRefreshing(false);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("", "Error" + error);
                }
            });
            RequestQueue queue = Volley.newRequestQueue(getActivity());

            if(!refreshing)
            cm.Show_dialog(getActivity());
            queue.add(request);
//            Log.e("", "url" + url + cm.getPostDataString(map));
            queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                @Override
                public void onRequestFinished(Request<String> request) {
                    cm.cancel_dialog();
                    swipe_refresh.setRefreshing(false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Recycler_View extends RecyclerView.Adapter<Recycler_View.Viewholder> {
        @Override
        public Viewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.classifieds_list_item, viewGroup, false);
            Viewholder v = new Viewholder(itemLayoutView);

            return v;
        }

        @Override
        public void onBindViewHolder(Viewholder viewholder, int position) {
            final HashMap<String, String> map = list.get(position);
            viewholder.getTv_price().setText(map.get("price") + " " + map.get("currencysymbol"));
            viewholder.getTv_age().setText(map.get("age"));
            viewholder.getTv_condition().setText(map.get("condition"));
            viewholder.getTv_p_date().setText(map.get("pdate"));
            viewholder.getTv_provision().setText(map.get("StateProvName"));
            viewholder.getTv_title().setText(map.get("title"));
            viewholder.getTv_type().setText(map.get("SubcategoryName"));
            viewholder.getTv_usage().setText(map.get("usage"));
//            Log.e("", "https://in.gofabby.com/img/" + cm.Image_Folder_location(Integer.parseInt(Cat_id)) + "/" + map.get("memberid") + "/" + map.get("Imgfolder") + "/" + map.get("ImgPath").replace(" ", "%20"));
            Picasso.with(getActivity()).load("https://in.gofabby.com/img/" + cm.Image_Folder_location(Integer.parseInt(Cat_id)) + "/" + map.get("memberid") + "/" + map.get("Imgfolder") + "/" + map.get("ImgPath").replace(" ", "%20")).placeholder(getResources().getDrawable(R.drawable.temp_banner_)).into(viewholder.getiv());


        }

        @Override
        public int getItemCount() {

            return list.size();
        }

        class Viewholder extends RecyclerView.ViewHolder {
            TextView tv_price;
            TextView tv_age;
            TextView tv_usage;
            TextView tv_condition;
            TextView tv_type;
            TextView tv_provision;
            TextView tv_title;
            ImageView iv;

            public TextView getTv_price() {
                return tv_price;
            }

            public ImageView getiv() {
                return iv;
            }

            public TextView getTv_age() {
                return tv_age;
            }

            public TextView getTv_usage() {
                return tv_usage;
            }

            public TextView getTv_condition() {
                return tv_condition;
            }

            public TextView getTv_title() {
                return tv_title;
            }

            public TextView getTv_type() {
                return tv_type;
            }

            public TextView getTv_provision() {
                return tv_provision;
            }

            public TextView getTv_p_date() {
                return tv_p_date;
            }

            public LinearLayout getView() {
                return view;
            }

            TextView tv_p_date;
            LinearLayout view;

            public Viewholder(View v) {

                super(v);
                tv_price = (TextView) v.findViewById(R.id.classifieds_price);
                tv_age = (TextView) v.findViewById(R.id.classifieds_age);
                tv_usage = (TextView) v.findViewById(R.id.classifieds_usage);
                tv_condition = (TextView) v.findViewById(R.id.classifieds_condition);
                tv_type = (TextView) v.findViewById(R.id.classifieds_type);
                tv_provision = (TextView) v.findViewById(R.id.classifieds_location);
                tv_title = (TextView) v.findViewById(R.id.classifieds_item_title);
                tv_p_date = (TextView) v.findViewById(R.id.classifieds_p_date);
                view = (LinearLayout) v.findViewById(R.id.view_);
                iv = (ImageView) v.findViewById(R.id.classifieds_image);

            }


        }
    }

    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
//            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
            outRect.set(0, 0, 0, (mItemOffset * 2));
        }
    }
}
