package com.ddragons.benny.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ddragons.benny.R;
import com.ddragons.benny.controllers.BottomNavBarController;
import com.ddragons.benny.fragments.TabFragment;
import com.ddragons.benny.fragments.SearchBusinessFragment;

public class MainActivity extends BaseActivity implements SearchBusinessFragment.OnFragmentInteractionListener, BottomNavBarController.RootFragmentListener{

    private int[] tabIcons = {
            R.mipmap.tab_favourites,
            R.mipmap.tab_calendar,
            R.mipmap.tab_messages,
            R.mipmap.tab_search,
            R.mipmap.tab_profile};
    private TabLayout bottomTabLayout;
    private Toolbar toolbar;
    private TabFragment[] rootFragments={
            new SearchBusinessFragment(),
            new SearchBusinessFragment(),
            new SearchBusinessFragment(),
            new SearchBusinessFragment(),
            new SearchBusinessFragment(),
    };
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(prefsManager.getRegId().isEmpty()){
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            init();
            //SearchBusinessFragment searchBusinessFragment=new SearchBusinessFragment();
            //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, searchBusinessFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        SearchBusinessFragment tabFragment=(SearchBusinessFragment) pagerAdapter.getItem(viewPager.getCurrentItem());
        if (tabFragment.hasChildFragments()) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            tabFragment.setFragment(tabFragment.getLastFragment());
        } else {
            // Otherwise, select the previous step.
            super.onBackPressed();
        }
    }

    private void init(){
        bottomTabLayout=(TabLayout) findViewById(R.id.bottom_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter=new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        bottomTabLayout.setupWithViewPager(viewPager);
        //toolbar=(Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        if (bottomTabLayout != null) {
            for (int i = 0; i < tabIcons.length; i++) {
                //bottomTabLayout.addTab(bottomTabLayout.newTab());
                TabLayout.Tab tab = bottomTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setIcon(tabIcons[i]);
            }
        }

        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#FF0000"),PorterDuff.Mode.MULTIPLY);
                /*
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content_frame, rootFragments[tab.getPosition()].getLastFragment());
                transaction.addToBackStack(null);
                transaction.commit();
                */
                //fragmentHistory.push(tab.getPosition());
                //switchTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#FFFFFF"),PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#FF0000"),PorterDuff.Mode.MULTIPLY);
                /*
                setRootFragment(tab.getPosition(), new SearchBusinessFragment());
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content_frame, rootFragments[tab.getPosition()]);
                transaction.addToBackStack(null);
                transaction.commit();
                */
                //mNavController.clearStack();
                //switchTab(tab.getPosition());
            }
        });
    }

    public void openTabFromScratch(int position){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.content_frame, getRootFragment(position));
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public static void openTabWithSavedState(int position){

    }

    private void logOut(){
        prefsManager.saveRegId("");
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void onFragmentInteraction(Uri uri){}

    public Fragment getRootFragment(int index){
        return rootFragments[index];
    }

    public void setRootFragment(int index, TabFragment fragment){
        rootFragments[index]=fragment;
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new SearchBusinessFragment();
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
