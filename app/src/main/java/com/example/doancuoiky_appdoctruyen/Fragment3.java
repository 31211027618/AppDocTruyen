package com.example.doancuoiky_appdoctruyen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.doancuoiky_appdoctruyen.LichSuTruyen.favBook.FavFolderFragment;
import com.example.doancuoiky_appdoctruyen.LichSuTruyen.history.History_bookFragment;
import com.google.android.material.tabs.TabLayout;

public class Fragment3 extends Fragment {

    private TabLayout tabLayout2;
    private ViewPager2 viewPager2_frag3;
    History_bookFragment history_bookFragment = new History_bookFragment();
    FavFolderFragment favFolderFragment = new FavFolderFragment();
    public Fragment3() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        tabLayout2 = view.findViewById(R.id.tabLayout_frag3);
        viewPager2_frag3 = view.findViewById(R.id.tabViewPager2);
        initTabLayoutData();
        return view;
    }

    public void initTabLayoutData(){
        viewPager2_frag3.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position){
                    case 0:
                        return history_bookFragment;
                    case 1:
                        return favFolderFragment;
                    default:
                        return history_bookFragment;
                }
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });

        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2_frag3.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2_frag3.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout2.getTabAt(position).select();
            }
        });

        viewPager2_frag3.setSaveEnabled(false);
    }
}