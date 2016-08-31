package gofabby.com.custom_layouts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import gofabby.com.Common_Methods;
import gofabby.com.R;
import gofabby.com.Sub_Category_List;

/**
 * Created by vivek on 8/13/2016.
 */
public class Landing_page extends Fragment {
    RelativeLayout _1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12;
    Common_Methods cm;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.home_page, null);
        cm = new Common_Methods();
        _1 = (RelativeLayout) v.findViewById(R.id._1);
        _2 = (RelativeLayout) v.findViewById(R.id._2);
        _3 = (RelativeLayout) v.findViewById(R.id._3);
        _4 = (RelativeLayout) v.findViewById(R.id._4);
        _5 = (RelativeLayout) v.findViewById(R.id._5);
        _6 = (RelativeLayout) v.findViewById(R.id._6);
        _7 = (RelativeLayout) v.findViewById(R.id._7);
        _8 = (RelativeLayout) v.findViewById(R.id._8);
        _9 = (RelativeLayout) v.findViewById(R.id._9);
        _10 = (RelativeLayout) v.findViewById(R.id._10);
        _11 = (RelativeLayout) v.findViewById(R.id._11);
        _12 = (RelativeLayout) v.findViewById(R.id._12);


        _1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(1);
            }
        });
        _2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(2);
            }
        });
        _3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(3);
            }
        });

        _4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(4);
            }
        });

        _5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(5);
            }
        });
        _6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(6);
            }
        });
        _7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(7);
            }
        });

        _8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(8);
            }
        });

        _9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(9);
            }
        });

        _10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(10);
            }
        });

        _11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(11);
            }
        });

        _12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select_category(12);
            }
        });


        return v;
    }

    void select_category(int id) {
//        Fragment f = new Sub_Cat_Advt().datas(cm.Cat_name(id), cm.Selected_country_name(getActivity()), id + "".trim());
        Fragment f = new Sub_Category_List().datas(cm.Cat_name(id), cm.Selected_country_name(getActivity()), id + "".trim());

        getActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_lay, f
        ).addToBackStack("").commit();
    }
}
