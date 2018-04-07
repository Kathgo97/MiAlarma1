package com.katherine.mialarma;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText EtHour;
    private EditText EtMinut;
    private Button BtnProg;

    private int minut_var = 0;
    private int hour_var = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllViews();

        BtnProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!EtHour.getText().toString().matches("")){

                    if(Integer.parseInt(EtHour.getText().toString()) <0 || Integer.parseInt(EtHour.getText().toString())> 23){
                        hour_var = 0;
                    }else{
                        hour_var = Integer.parseInt(EtHour.getText().toString());
                    }
                }

                if(!EtMinut.getText().toString().matches("")){

                    if(Integer.parseInt(EtMinut.getText().toString()) < 0 || Integer.parseInt(EtMinut.getText().toString()) > 60){
                        minut_var = 0;
                    }else{
                        minut_var = Integer.parseInt(EtMinut.getText().toString());
                    }
                }


                setupAlarm(hour_var, minut_var);
            }
        });

    }

    private void getAllViews(){
        EtHour = findViewById(R.id.EtHours);
        EtMinut = findViewById(R.id.EtMinutes);
        BtnProg = findViewById(R.id.Btn_Prog);
    }

    private void setupAlarm(int hours, int minutes){

        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);

        alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hours);
        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);

        if(alarmIntent.resolveActivity(getPackageManager()) != null){
            startActivity(alarmIntent);
            EtHour.setText("");
            EtMinut.setText("");
        }
    }
}
