package com.example.siddheshsave.parkeazy;

import android.app.ActionBar;
import android.app.DownloadManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.onesignal.OneSignal;

import java.util.Queue;

public class Main4Activity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private NavigationView navigationView;
    private FrameLayout MainFrame;
    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private ParkAssistFragment parkAssistFragment;
    private SettingsFragment settingsFragment;
    private FeedbackFragment feedbackFragment;
    SearchView searchView;
    static String LoggedIn_User_Email;
    boolean doubleBackToExitPressedOnce = false;
    //FirebaseAuth.AuthStateListener mAuthListener;
    //aterialSearchView materialSearchView;
    //String[] list;
    /*@Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        LoggedIn_User_Email=firebaseUser.getEmail();
        OneSignal.sendTag("User_ID",LoggedIn_User_Email);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeFragment=new HomeFragment();
        historyFragment=new HistoryFragment();
        parkAssistFragment=new ParkAssistFragment();
        settingsFragment=new SettingsFragment();
        feedbackFragment=new FeedbackFragment();
        setFragment(homeFragment);
        /*mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Intent i=new Intent(Main4Activity.this,Main3Activity.class);
                    startActivity(i);
                }
            }
        };*/
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                switch(menuItem.getItemId()){
                    case R.id.home :
                        setFragment(homeFragment);
                        return true;
                    case R.id.park_assist :
                        setFragment(parkAssistFragment);
                        return true;
                    case R.id.history :
                        setFragment(historyFragment);
                        return true;
                    case R.id.settings :
                        setFragment(settingsFragment);
                        return true;
                    case R.id.feedback :
                        setFragment(feedbackFragment);
                        return true;
                    case R.id.logout :
                        firebaseAuth=FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                        Intent intent=new Intent(Main4Activity.this,Main3Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        return true;
                    default :
                        return false;
                }
            }
        });
        /*list=new String[]{"City Mall Andheri","PVR Juhu","Infinity Mall Andheri","Phoenix Mall Kurla"};
        materialSearchView=(MaterialSearchView)findViewById(R.id.mysearch);
        materialSearchView.closeSearch();
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            ActivityCompat.finishAffinity(this);
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(Main4Activity.this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
