package com.depromeet.finddepro.main.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.depromeet.finddepro.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {
    @BindView(R.id.mainTabMenu)
    TabLayout mainTab;
    @BindView(R.id.mainTabPager)
    ViewPager mainTabPager;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, root);

        //tab init
        mainTab.addTab(mainTab.newTab().setText(R.string.noticeTitle));
        mainTab.addTab(mainTab.newTab().setText(R.string.scheduleTitle));
        mainTab.setTabGravity(TabLayout.GRAVITY_FILL);

        //view pager
        HomeFragmentTabAdapter pagerAdapter = new HomeFragmentTabAdapter(getChildFragmentManager(), 2);
        mainTabPager.setAdapter(pagerAdapter);
        mainTabPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTab));

        tabTextStyle(0, Typeface.BOLD);

        //tab listener
        mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabTextStyle(tab.getPosition(), Typeface.BOLD);
                mainTabPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabTextStyle(tab.getPosition(), Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;

    }

    // tab's text style(normal to bold / bold to normal)
    private void tabTextStyle(int index, int Type) {
        TabLayout.Tab tab = mainTab.getTabAt(index);
        assert tab != null;
        LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mainTab.getChildAt(0)).getChildAt(tab.getPosition());
        TextView tabTextView = (TextView) tabLayout.getChildAt(1);
        tabTextView.setTypeface(null, Type);

    }
}