package com.example.chatrealtimetracking;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.chatrealtimetracking.adapter.TabPagerAdapter;
import com.example.chatrealtimetracking.fragment.report.PengaduanFragment;
import com.example.chatrealtimetracking.fragment.room.ChatFragment;
import com.example.chatrealtimetracking.utils.SessionManager;
import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {

    SessionManager manager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        manager = new SessionManager(this);
        initViewPager(viewPager);
    }

    private void initViewPager(ViewPager pager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.fragment(new ChatFragment(), "Chat");
        adapter.fragment(new PengaduanFragment(), "Pengaduan");
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }
}
