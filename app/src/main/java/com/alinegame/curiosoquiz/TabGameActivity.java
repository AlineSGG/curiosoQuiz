package com.alinegame.curiosoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class TabGameActivity extends AppCompatActivity {

    public static TabLayout mtabLayout;
    public static ViewPager mViewPager;
    public static MyFragmentPagerAdapter customPagerAdapter;
    private Context context = this;
    private Activity activity = this;
    private String idStatus, vitima;
    CoordinatorLayout idCoordinator;
    private int[] imageResId = {
            R.drawable.pikachu,
            R.drawable.hearth,
            R.drawable.heart };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager = new ViewPager(activity);


        setTitle("Quiz");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mtabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        idCoordinator = (CoordinatorLayout) findViewById(R.id.idCoordinator);



            customPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(customPagerAdapter);
            mtabLayout.setupWithViewPager(mViewPager);

            for (int i = 0; i < imageResId.length; i++) {
                mtabLayout.getTabAt(i).setIcon(imageResId[i]);
            }

        mViewPager.setOffscreenPageLimit(3);

        mtabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    int tabIconColor = ContextCompat.getColor(context, R.color.colorWhite);
                    tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                } catch (Exception e) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                try {
                    int tabIconColor = ContextCompat.getColor(context, R.color.colorRed);
                    tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                } catch (Exception e) {

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configuracao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case android.R.id.home:

                back();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    public void back() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();

    }
}
