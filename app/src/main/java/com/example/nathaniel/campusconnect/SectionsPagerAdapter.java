package com.example.nathaniel.campusconnect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {

            case 0:

                ChatFragments chatFragments = new ChatFragments();
                return chatFragments;

            case 1:

                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;

            case 2:

                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){

        switch (position){

            case 0:
                return "Chats";

            case 1:
                return  "Friends";

            case 2:
                return "Profile";

            default:
                return null;

        }

    }
}
