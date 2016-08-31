package gofabby.com;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;

import gofabby.com.custom_layouts.Landing_page;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar tb;
    TextView tv;
    BottomBar bottomBar;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.fragment_lay,new Landing_page()).addToBackStack("").commit();
//        tb=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(tb);
//        tv=(TextView)findViewById(R.id.text_);
//        String str="0";
//        for(int i=0;i<1000;i++)
//        str="one "+str+"/n";
//        tv.setText(str);

//        bottomBar=BottomBar.attachShy((CoordinatorLayout)findViewById(R.id.coordinator_lay),savedInstanceState);
//
//        bottomBar.useFixedMode();
//        bottomBar.noTopOffset();
//        View v; // Creating an instance for View Object
//
//        bottomBar.setItems(
//                new BottomBarTab(R.drawable.ic_home,""),
//                new BottomBarTab(R.drawable.ic_profile, ""),
//                new BottomBarTab(R.drawable.ic_comment, ""),
//                new BottomBarTab(R.drawable.ic_search, "")
//        );

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else
        getSupportFragmentManager().popBackStack();

    }
}
