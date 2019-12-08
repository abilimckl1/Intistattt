package com.example.intistattt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.intistattt.ui.timetable.TimetableFragment;
import com.example.intistattt.ui.home.HomeFragment;
import com.example.intistattt.ui.attendancestats.AttendanceStatsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(navListener);

        String valueProgramme = getIntent().getStringExtra("STUDY_PROGRAMME");
        ArrayList<String> valueAttended = getIntent().getStringArrayListExtra("CLASS_ATTENDED");
        ArrayList<String> keys = getIntent().getStringArrayListExtra("KEY_CLASS");

        bundle = new Bundle();
        bundle.putString("STUDY_PROGRAMME", valueProgramme);
        bundle.putStringArrayList("CLASS_ATTENDED", valueAttended);
        bundle.putStringArrayList("KEY_CLASS", keys);

        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.navigation_home:
                            loadFragment(new HomeFragment());
                            return true;
                        case R.id.navigation_dashboard:
                            loadFragment(new TimetableFragment());
                            return true;
                        case R.id.navigation_notifications:
                            loadFragment(new AttendanceStatsFragment());
                            return true;
                    }
                    return false;
                }
            };

    private void loadFragment(Fragment fragment)
    {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
