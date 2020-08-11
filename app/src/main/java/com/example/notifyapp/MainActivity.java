package com.example.notifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int notificationID =1;
    EditText task;
    TimePicker timePicker;
    Button setbtn,cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setbtn = findViewById(R.id.set_id);
        setbtn.setOnClickListener(this);
        cancelbtn = findViewById(R.id.cancel_id);
        cancelbtn.setOnClickListener(this);
    }

    public void onClick(View view){
        task = findViewById(R.id.task_id);
        timePicker = findViewById(R.id.timepicker_id);

        // set text & notificationId

        Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
        intent.putExtra("notificationID",notificationID);
        intent.putExtra("todo",task.getText().toString());

        PendingIntent alarmintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        switch (view.getId()){
            case R.id.set_id:
                 int hour = timePicker.getHour();
                 int minute = timePicker.getMinute();

                Calendar starttime = Calendar.getInstance();
                starttime.set(Calendar.HOUR_OF_DAY,hour);
                starttime.set(Calendar.MINUTE,minute);
                starttime.set(Calendar.SECOND,0);
                long alarmStarttime = starttime.getTimeInMillis();

                // set alarm
                // set Type

                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStarttime,alarmintent);

                Toast.makeText(getApplicationContext(),"Done ",Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancel_id :
                alarmintent.cancel();
                Uri ring = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),ring);
                r.stop();

                Toast.makeText(getApplicationContext(),"Canceled ",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
