package demo.onstar.car.suresofttech.com.carremotecontroldemo.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import demo.onstar.car.suresofttech.com.carremotecontroldemo.consts.Const;
import demo.onstar.car.suresofttech.com.carremotecontroldemo.R;
import demo.onstar.car.suresofttech.com.carremotecontroldemo.utils.ProjectManager;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private EditText edit_txt_ip_address = null;
    private EditText edit_txt_ip_port = null;
    private EditText edit_txt_vin_num = null;

    private Button btnConnect = null;
    private Button btnClearAll = null;
    private ProjectManager projectManager = null;

    @Override
    public void beforeTextChanged(CharSequence characters, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence characters, int start, int before, int count) {
        boolean isBtnsOk = updateComplete(characters.toString());
        switchButtonState(isBtnsOk);
    }

    @Override
    public void afterTextChanged(Editable characters) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbarIcon();

        edit_txt_ip_address = (EditText)findViewById(R.id.input_ip_address);
        edit_txt_ip_port = (EditText)findViewById(R.id.input_ip_port);
        edit_txt_vin_num = (EditText)findViewById(R.id.input_vin_number);

        edit_txt_ip_address.addTextChangedListener(this);
        edit_txt_ip_port.addTextChangedListener(this);
        edit_txt_vin_num.addTextChangedListener(this);


        btnConnect = (Button)findViewById(R.id.btn_connect);
        btnClearAll = (Button)findViewById(R.id.btn_clear_all);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = edit_txt_ip_address.getText().toString();
                String ipPort = edit_txt_ip_port.getText().toString();
                String vinNumber = edit_txt_vin_num.getText().toString();
                String errorMessage = checkValidation(ipAddress, ipPort, vinNumber);

                if(errorMessage != null && !errorMessage.isEmpty()){
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                }else{
                    ProjectManager.getDefault().createModel(vinNumber);
                    ProjectManager.getDefault().updateModelBy(vinNumber, ipAddress, ipPort);
                    Toast.makeText(MainActivity.this, "Log-in success!" + Const.NEXT_LINE + ProjectManager.getDefault().toString(vinNumber), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, RemoteControlSystemActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_txt_ip_address.setText(getString(R.string.empty_string));
                edit_txt_ip_port.setText(getString(R.string.empty_string));
                edit_txt_vin_num.setText(getString(R.string.empty_string));
            }
        });
        switchButtonState(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean updateComplete(String stringToBeCheck){
        if(stringToBeCheck == null || stringToBeCheck.length() <= 0){
            return false;
        }
        return true;
    }

    private void switchButtonState(boolean enabled){
        btnClearAll.setEnabled(enabled);
        btnConnect.setEnabled(enabled);
    }

    private String checkValidation(String ipAddress, String ipPort, String vinNumber){
        String errorMessage = getString(R.string.empty_string);;
        String ipAddrRegex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        String portRegex = "[0-65535]";

        if(ipAddress == null || ipAddress.length() == 0){
            errorMessage = getString(R.string.error_msg_4);
        }
        if(ipPort == null || ipPort.length() == 0){
            errorMessage = getString(R.string.error_msg_5);
        }
        if(vinNumber == null || vinNumber.length() == 0){
            errorMessage = getString(R.string.error_msg_6);
        }

        if(ipAddress.matches(ipAddrRegex) == false){
            errorMessage = getString(R.string.error_msg_1) + Const.BLANK +
                            "Valid IP Address format:" + Const.BLANK + "xxx.xxx.xxx.xxx";
        }
//        if(ipPort.matches(protRegex) == false){
//            errorMessage = getString(R.string.error_msg_2);
//        }
        if(vinNumber.length() < 17){
            errorMessage = getString(R.string.error_msg_3) + Const.BLANK +
                            "Current input vin number length:" + vinNumber.length();
        }
        return errorMessage;
    }


    private void createToolbarIcon(){
        ActionBar ab = getSupportActionBar() ;

        ab.setIcon(R.drawable.img_remote_control);
        ab.setDisplayUseLogoEnabled(true) ;
        ab.setDisplayShowHomeEnabled(true) ;
    }
}