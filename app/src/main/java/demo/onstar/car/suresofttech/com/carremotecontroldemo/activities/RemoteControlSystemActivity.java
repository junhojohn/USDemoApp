package demo.onstar.car.suresofttech.com.carremotecontroldemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import demo.onstar.car.suresofttech.com.carremotecontroldemo.R;

public class RemoteControlSystemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control_system);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createToolbarIcon();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ImageButton btnTempUp = (ImageButton)findViewById(R.id.btn_temp_up);
        ImageButton btnTempDown = (ImageButton)findViewById(R.id.btn_temp_down);
        ImageButton btnLock = (ImageButton)findViewById(R.id.btn_lock);
        ImageButton btnUnlock = (ImageButton)findViewById(R.id.btn_unlock);
        ImageButton btnTurnByTurn = (ImageButton)findViewById(R.id.btn_turn_by_turn);
        final TextView txtLog = (TextView)findViewById(R.id.txt_view_log_message);

        btnTempUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLog.setText("AIRCON_TEMP_UP:");
            }
        });
        btnTempDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLog.setText("AIRCON_TEMP_DOWN:");
            }
        });
        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLog.setText("LOCK:");
            }
        });
        btnUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLog.setText("UNLOCK:");
            }
        });
        btnTurnByTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLog.setText("SEND_DESTINATION:");
                Intent intent = new Intent(RemoteControlSystemActivity.this, OnStarMapActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_remote_control_system, menu);
//        return true;
          return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_remote_control) {

        } else if (id == R.id.nav_navigation_map) {
            Intent intent = new Intent(RemoteControlSystemActivity.this, OnStarMapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_vehicle_status) {
            Intent intent = new Intent(RemoteControlSystemActivity.this, VehicleStatusActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_system_configuration) {
            Intent intent = new Intent(RemoteControlSystemActivity.this, ConfigurationActivity.class);
            startActivity(intent);
        } else if(id == R.id.nav_logout){
            Intent intent = new Intent(RemoteControlSystemActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createToolbarIcon(){
        ActionBar ab = getSupportActionBar() ;

        ab.setIcon(R.drawable.img_remote_control);
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;
    }
}
