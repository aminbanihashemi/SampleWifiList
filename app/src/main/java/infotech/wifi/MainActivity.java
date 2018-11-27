package infotech.wifi;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuPresenter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Switch switchwifi;
    ListView listView;
    CustomDialog customDialog;
    TextView textStatus;
    List<String> wifi;
    WifiAdapter adapter;
    int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        switchwifi = findViewById(R.id.switchwifi);
        listView = findViewById(R.id.wifi_list);
        listView.setVisibility(View.GONE);
        textStatus = findViewById(R.id.textStatus);
        customDialog = new CustomDialog(this);
        wifi = new ArrayList<>();
        wifi.add("Mobinnet");
        wifi.add("Irancell");
        wifi.add("Infotech Famoco");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                index=position;
                                                customDialog.setTitle(wifi.get(position));
                                                customDialog.show();
                                            }
                                        });

                switchwifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            listView.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "Turn on wifi", Toast.LENGTH_SHORT).show();
                        } else {
                            listView.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Turn offfff wifi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        adapter = new WifiAdapter(this,wifi);
        listView.setAdapter(adapter);


    }

    public class CustomDialog extends Dialog implements View.OnClickListener {
        EditText etPassword;
        Button btnEnterPass, btnCancel;

        CustomDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.custom_dialog);
            bind();
            btnEnterPass.setOnClickListener(this);
            btnCancel.setOnClickListener(this);
        }

        private void bind() {

            etPassword = findViewById(R.id.etPassword);
            btnEnterPass = findViewById(R.id.btnEnterPass);
            btnCancel = findViewById(R.id.btnCancel);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnEnterPass:
                    if (checkValidatePassword(etPassword.getText().toString())) {
                        getWifiPassword(index,etPassword.getText().toString());
                        customDialog.dismiss();
                        etPassword.setText("");
                    }else {
                        etPassword.setText("");
                        etPassword.setError("Please Enter a valid Password");
                    }
                    break;
                case R.id.btnCancel:
                    customDialog.dismiss();
                    break;
            }

        }
        private boolean checkValidatePassword(String password) {
            if(!password.isEmpty())
                return true;
            else {
                return false;
            }

        }
    }



    private void getWifiPassword(int index,String pass) {
        String valuePass = pass;
        String wifiSelected = wifi.get(index);
        Toast.makeText(this, valuePass+" "+wifiSelected, Toast.LENGTH_SHORT).show();
    }
}

