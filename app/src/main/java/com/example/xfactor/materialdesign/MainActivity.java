package com.example.xfactor.materialdesign;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends AppCompatActivity implements MaterialTabListener {
    // implements MaterialTabListener for the new dependency
    android.support.v7.widget.Toolbar toolbar;

    MaterialTabHost materialTab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String person = getIntent().getExtras().getString("person");
//        String email = getIntent().getExtras().getString("email");
////        Log.d("intent","" + person + "" + email);
////        new Adapter(person,email);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        // setting the navigation drawer for swipe
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Adding fragment statically
        //when we add dynamically we call fragment manager and all..

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setup(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

//        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
//        Bundle arg = new Bundle();
//        arg.putString("Key",person);
//        fragment.setArguments(arg);
//        new NavigationDrawerFragment(person,"dsf");
        materialTab = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.pager);
        MypagerAdapter adapter = new MypagerAdapter(getSupportFragmentManager());

        //viewPager.setAdapter(new MypagerAdapter(getSupportFragmentManager()));
        // materialTab

        // viewPager.setAdapter(new MyAdapter(manager));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                materialTab.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            materialTab.addTab(
                    materialTab.newTab()
                            .setIcon(adapter.getPageIcon(i))
                            .setTabListener(this)
            );
        }








     /*   ImageView imageView = new ImageView(this); // Create an icon
        imageView.setImageResource(R.drawable.ic_launcher);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon = new ImageView(this);
        itemIcon.setImageResource(R.drawable.contact);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();

        SubActionButton.Builder itemBuilder2 = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon2 = new ImageView(this);
        itemIcon.setImageResource(R.drawable.pencil);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();

        SubActionButton.Builder itemBuilder3 = new SubActionButton.Builder(this);
        // repeat many times:
        ImageView itemIcon3 = new ImageView(this);
        itemIcon.setImageResource(R.drawable.calender);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                        // ...
                .attachTo(actionButton)
                .build();
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.navigate) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    public class MypagerAdapter extends FragmentStatePagerAdapter {

        public static final int Box_office = 0;
        public static final int Search = 1;
        public static final int Upcoming = 2;

        // String [] tabs;
        int[] icon = {R.drawable.pencil, R.drawable.contact,R. drawable.calender};


        public MypagerAdapter(FragmentManager fm) {

            super(fm);

            //  tabs=getResources().getStringArray(R.array.TITLE);

        }

        public android.graphics.drawable.Drawable getPageIcon(int position) {
            return ResourcesCompat.getDrawable(getResources(), icon[position], null);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            android.support.v4.app.Fragment fragment = null;
            switch (position) {
                case Box_office:
                    fragment = FragmentBoxOffice.newInstance("", "");
                    break;
                case Search:
                    fragment = FragmentSearch.newInstance("", "");
                    break;
                case Upcoming:
                    fragment = FragmentUpcoming.newInstance("", "");
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}


