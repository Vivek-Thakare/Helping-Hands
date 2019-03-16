package com.example.studentapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import static com.example.studentapp.Profile.int_items;

/**
 * Created by Admin on 3/1/2017.
 */

public class MyAdapter  extends FragmentPagerAdapter {


    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PersonalFragment();
            case 1:
                return new EducationFragment();
            case 2:
                return new FamilyFragment();
            case 3:
                return new FragmentTechnical();
            case 4:
                return new FragmentOther();


        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Personal";
            case 1:
                return "Education";
            case 2:
                return "Family";
            case 3:
                return "Technical";
            case 4:
                return "Other Info";


        }

        return null;
    }
}
