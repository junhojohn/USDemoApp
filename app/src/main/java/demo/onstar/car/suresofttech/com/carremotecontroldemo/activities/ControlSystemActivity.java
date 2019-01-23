package demo.onstar.car.suresofttech.com.carremotecontroldemo.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.onstar.car.suresofttech.com.carremotecontroldemo.R;

public class ControlSystemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_system);
        createToolbarIcon();

    }

    private void createToolbarIcon(){
        ActionBar ab = getSupportActionBar() ;

        ab.setIcon(R.drawable.img_remote_control);
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;
    }
}
