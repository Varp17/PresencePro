package teacherpkg;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.loginform.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import administratorpkg.ManageStudent_classTeacher;
import administratorpkg.administrotor_panel;
import administratorpkg.administrtor_profile;
import administratorpkg.all_college_notiFication_from_Administrator;
import administratorpkg.classroomClicked_activity;
import administratorpkg.feedback_activity_administrator;

public abstract class varchi_line extends AppCompatActivity  {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
//    ImageButton notification;



    Menu menu;
    MenuItem menu_home;
    MenuItem menu_classroom,menu_documents,menu_attendence,menu_notification,menu_managestudent,menu_feedback;

    abstract int getLayoutresId() ;
    abstract String getactionbarTiile_in_varchi_line();


    private static final int MENU_ITEM_ITEM1 = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutresId());
//        notification=findViewById(R.id.notificationbtn);
        drawerLayout=findViewById(R.id.Drawer_lay);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getactionbarTiile_in_varchi_line());

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();





        menu=navigationView.getMenu();
        menu_home=menu.findItem(R.id.nav_home);
        menu_classroom=menu.findItem(R.id.nav_classroom);
        menu_documents=menu.findItem(R.id.nav_document);
        menu_attendence=menu.findItem(R.id.nav_attendance);
        menu_notification=menu.findItem(R.id.nav_notification);
        menu_attendence=menu.findItem(R.id.nav_attendance);
        menu_feedback = menu.findItem(R.id.nav_feedback);

        menu_feedback.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                Intent intent = new Intent(getApplicationContext(), feedback_activity_administrator.class);
                startActivity(intent);
                return false;
            }
        });


        ImageView profileImg = navigationView.getHeaderView(0).findViewById(R.id.profileimg);
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toolbar.getTitle()!="PROFIlE") {

                    Intent intent = new Intent(getApplicationContext(), administrtor_profile.class);
                    startActivity(intent);
                    finish();
                    finishActivity(R.id.managestaff);
                }

            }
        });
        menu_home.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent=new Intent(getApplicationContext(), administrotor_panel.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
        menu_classroom.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {


                Intent intent=new Intent(getApplicationContext(), administrotor_panel.class);
                startActivity(intent);
                finish();
                drawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });
        menu_documents.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override

            public boolean onMenuItemClick(@NonNull MenuItem item) {

                drawerLayout.closeDrawer(GravityCompat.START);
                if(!"CLASS TEACHER'S".equals(toolbar.getTitle())) {

                    Intent intent = new Intent(getApplicationContext(), classroomClicked_activity.class);
                    intent.putExtra("flagfordocuments", true);

                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });

        menu_attendence.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override

            public boolean onMenuItemClick(@NonNull MenuItem item) {

                drawerLayout.closeDrawer(GravityCompat.START);
                if(!"CLASS TEACHER'S".equals(toolbar.getTitle())) {

                    Intent intent = new Intent(getApplicationContext(), classroomClicked_activity.class);
                    intent.putExtra("flagforattendence", true);

                    startActivity(intent);
                    finish();
                }

                return false;
            }
        });

        menu_notification.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                if(getactionbarTiile_in_varchi_line()!="NOTIFICATIONS") {
                    Intent intent1 = new Intent(getApplicationContext(), all_college_notiFication_from_Administrator.class);
                    startActivity(intent1);
                    finish();
                }
                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubaradmin, menu);
        updateMenuVisibility(menu.findItem(R.id.nav_managestu));



        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle your menu items here
        if (id == R.id.nav_notification && getactionbarTiile_in_varchi_line()!= "NOTIFICATIONS") {

            Intent intent=new Intent(getApplicationContext(), all_college_notiFication_from_Administrator.class);
            startActivity(intent);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    private void updateMenuVisibility(MenuItem menuItem) {
        // Check if the action bar title is "CLASS TEACHER'S"
        if (getSupportActionBar() != null && getSupportActionBar().getTitle() != null &&
                getSupportActionBar().getTitle().toString().equals("CLASS TEACHER'S")) {
            menuItem.setVisible(true); // Show the menu item
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(@NonNull MenuItem item) {
                    Intent intent=new Intent(getApplicationContext(), ManageStudent_classTeacher.class);
                    startActivity(intent);
                    return false;
                }
            });
        } else {
            menuItem.setVisible(false); // Hide the menu item
            menuItem.setOnMenuItemClickListener(null); // Clear the listener
            menu.removeItem(R.id.nav_managestu);
        }
    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }
}





