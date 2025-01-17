package teacherpkg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.loginform.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import administratorpkg.FragmentAdapter;
import administratorpkg.classroom_activity_adapter;

public class teacher_classroomclicked extends varchi_line{


    private static final int MENU_ITEM_ITEM1 = 1;
    ViewPager vpager;
    TabLayout tabLayout;
    teacher_classroom_adapter myFragmentAdapter1;
    String title;


    @Override
    int getLayoutresId() {
        return R.layout.activity_teacher_classroomclicked;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    String getactionbarTiile_in_varchi_line() {

        Intent intent=getIntent();
        this.title = intent.getStringExtra("name");
        return title;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        vpager=findViewById(R.id.vpager);
        myFragmentAdapter1=new teacher_classroom_adapter(getSupportFragmentManager());
        vpager.setAdapter(myFragmentAdapter1);
        tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vpager);
        SharedPreferences sharedPreferences = getSharedPreferences("subname", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("subname", title);

// Use commit instead of apply for synchronous write
        editor.commit();
        Intent intent=getIntent();

        boolean flag=intent.getBooleanExtra("flagfordocuments",false);

        boolean flagattend=intent.getBooleanExtra("flagforattendence",false);
        boolean atsameattend=intent.getBooleanExtra("atsameattend",false);
        boolean atsamedoc=intent.getBooleanExtra("atsamedoc",false);

        if(flag)
        {
            //document
            getSupportActionBar().setTitle("CLASS TEACHER'S");

            tabLayout.setScrollPosition(1, 0f, true);
            vpager.setCurrentItem(1);
        }   if(flagattend) {
            //attendence

            getSupportActionBar().setTitle("CLASS TEACHER'S");


        }if (atsameattend){

            tabLayout.setScrollPosition(0,0f,true);
            vpager.setCurrentItem(0);
        }if (atsamedoc){

            tabLayout.setScrollPosition(1,0f,true);
            vpager.setCurrentItem(1);
        }

    }


}