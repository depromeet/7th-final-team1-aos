package com.depromeet.finddepro.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.depromeet.finddepro.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigationMenu;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.mainTitle)
    TextView title;


    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0, true);
                    return true;
                case R.id.navigation_attendance:
                    viewPager.setCurrentItem(1, true);
                    return true;
                case R.id.navigation_vote:
                    viewPager.setCurrentItem(2, true);
                    return true;
                case R.id.navigation_feed:
                    viewPager.setCurrentItem(3, true);
                    return true;
                case R.id.navigation_setting:
                    viewPager.setCurrentItem(4, true);
                    return true;
            }

            return false;
        }
    };
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        navigationMenu.setItemIconTintList(null);

        // 탭 생성 및 적용
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigationMenu.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationMenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

}
