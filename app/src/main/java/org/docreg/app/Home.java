package org.docreg.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.home);

        Toolbar toolbar = findViewById(R.id.toolbar_main) ;

        setSupportActionBar(toolbar) ;
        toolbar.setTitle("Heal Up");

        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer , toolbar , R.string. navigation_drawer_open ,
                R.string. navigation_drawer_close ) ;
        drawer.addDrawerListener(toggle) ;
        toggle.syncState() ;
        NavigationView navigationView = findViewById(R.id.nav_view) ;
        navigationView.setNavigationItemSelectedListener(this) ;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_container,new fronthome());
        fragmentTransaction.commit();

    }
    @Override
    public void onBackPressed () {
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        if (drawer.isDrawerOpen(GravityCompat. START )) {
            drawer.closeDrawer(GravityCompat. START ) ;
        } else {
            super .onBackPressed() ;
        }
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_menu, menu) ;
        return true;
    }

    @SuppressWarnings ( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected ( @NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId() ;
        if (id == R.id. nav_home ) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container,new fronthome());
            fragmentTransaction.commit();

        } else if (id == R.id. nav_docregister ) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container,new docregister());
            fragmentTransaction.commit();
        } else if (id == R.id. nav_setting ) {
        }
        else if(id == R.id.nav_logout)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Auth auth = new Auth(getApplicationContext());
                            auth.removeUser();
                            Intent intent=new Intent(Home.this,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
        else if(id == R.id.nav_about)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frag_container,new about_frag());
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        drawer.closeDrawer(GravityCompat. START ) ;
        return true;
    }
}




