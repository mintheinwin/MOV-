package com.mtw.movie_poc_screen.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.mtw.movie_poc_screen.R;
import com.mtw.movie_poc_screen.fragments.MovieListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Aspire-V5 on 12/6/2017.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.fl_movies)
    FrameLayout flMovies;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Movie Shelf");
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        TabLayout.Tab upcomingTab = tabLayout.newTab();
        upcomingTab.setText("UPCOMING");

        TabLayout.Tab mostpopularTab = tabLayout.newTab();
        mostpopularTab.setText("MOST POPULAR");

        tabLayout.addTab(tabLayout.newTab().setText("NOW ON CINEMA"));
        tabLayout.addTab(upcomingTab);
        tabLayout.addTab(mostpopularTab);

        if(tabLayout.getSelectedTabPosition() == 0) {
            MovieListFragment movieListFragment = MovieListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_movies, movieListFragment)
                    .commit();

        }

        //noinspection deprecation
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment = MovieListFragment.newInstance();
                        break;
                    case 1:
                        fragment = MovieListFragment.newInstance();
                        break;
                    case 2:
                        fragment = MovieListFragment.newInstance();
                        break;
                    default:
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_movies, fragment)
                        .commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
              //  drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
    }
}
