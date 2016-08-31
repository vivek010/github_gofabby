package gofabby.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by vivek on 8/23/2016.
 */
public class Advt_lay_fragment extends Fragment {
    ImageView iv_image;
    TextView tv_title;
    String image = "", title = "", link = "", id = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.advt_lay, null);
        iv_image = (ImageView) v.findViewById(R.id.advt_image);
        tv_title = (TextView) v.findViewById(R.id.advt_title);
        image = getArguments().getString("image");
        title = getArguments().getString("title");
        link = getArguments().getString("link");
        id = getArguments().getString("id");
        try {
//                aq.id(R.id.slideshow).image(image.replace(" ", "%20"), true, true);
            Log.e("","image"+image);
           // Picasso.with(getActivity()).load(image).placeholder(getResources().getDrawable(R.drawable.temp_banner)).into(iv_image);
            tv_title.setText(title);

        } catch (Exception e) {
        }

        return v;
    }

    Advt_lay_fragment datas(String image, String title,String desc, String link, String id) {
        Advt_lay_fragment advt = new Advt_lay_fragment();
        Bundle b = advt.getArguments();
        if (b == null)
            b = new Bundle();
        b.putString("image", image);
        b.putString("title", title);
        b.putString("desc", desc);
        b.putString("link", link);
        b.putString("id", id);
        advt.setArguments(b);
        return advt;
    }
}
