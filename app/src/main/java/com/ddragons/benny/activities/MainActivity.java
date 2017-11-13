package com.ddragons.benny.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ddragons.benny.R;
import com.ddragons.benny.controllers.BottomNavBarController;
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

    private void init(){
        bottomTabLayout=(TabLayout) findViewById(R.id.bottom_tab_layout);
        //toolbar=(Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        if (bottomTabLayout != null) {
            for (int i = 0; i < tabIcons.length; i++) {
                bottomTabLayout.addTab(bottomTabLayout.newTab());
                TabLayout.Tab tab = bottomTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setIcon(tabIcons[i]);
            }
        }
        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#FF0000"),PorterDuff.Mode.MULTIPLY);
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
                //mNavController.clearStack();
                //switchTab(tab.getPosition());
            }
        });
    }

    public void openTabFromScratch(int position){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.content_frame, getRootFragment(position));
        transaction.addToBackStack(null);
// Commit the transaction
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

    public android.support.v4.app.Fragment getRootFragment(int index){
        return new SearchBusinessFragment();
    }
}
