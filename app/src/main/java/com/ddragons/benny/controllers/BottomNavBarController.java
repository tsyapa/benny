package com.ddragons.benny.controllers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Vitaliy Tsyapa on 11/10/2017.
 */

public class BottomNavBarController {

    private ArrayList<Stack<Fragment>> fragmentStacks;
    private int tabsAmount;
    private FragmentManager fragmentManager;
    private RootFragmentListener rootFragmentListener;

    public BottomNavBarController(Activity activity, int tabsAmount){
        this.tabsAmount=tabsAmount;
        fragmentStacks=new ArrayList<>();
        for(int i=0;i<tabsAmount;i++)
            fragmentStacks.add(new Stack<Fragment>());
        fragmentManager=activity.getFragmentManager();
    }

    public static void openTabFromScratch(int position){

    }

    public static void openTabWithSavedState(int position){

    }

    public interface RootFragmentListener {

        android.support.v4.app.Fragment getRootFragment(int index);

    }

}
