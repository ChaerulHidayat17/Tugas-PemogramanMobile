package id.co.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainFirstActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainFirstActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.hellotoast.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private EditText mMessageEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satu);
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button diklik");
        Intent tampil = new Intent(this, MainSecondActivity.class);
        String pesan = mMessageEditText.getText().toString();
        tampil.putExtra(EXTRA_MESSAGE, pesan);
        startActivityForResult(tampil, TEXT_REQUEST);
    }

    public void createAlarm(View view) {

        //This creates an ArrayList called alarmDays which is passed into the .EXTRA_DAYS method

        ArrayList<Integer> alarmDays = new ArrayList<>();

        //This sets Monday as one of the alarm days

        alarmDays.add(2);

        //This sets Tuesday as one of the alarm days

        alarmDays.add(3);

        //This sets Wednesday as one of the alarm days

        alarmDays.add(4);

        //How to set up a new Alarm intent

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)

                //This sets which days the alarm goes off on using the ArrayList above

                .putExtra(AlarmClock.EXTRA_DAYS, alarmDays)

                //This sets the name or message of the alarm

                .putExtra(AlarmClock.EXTRA_MESSAGE, "Wake up!")

                //This sets the hour of the alarm (24 hour) to 7am

                .putExtra(AlarmClock.EXTRA_HOUR, 7)

                // This sets the minutes of the alarm to 30

                .putExtra(AlarmClock.EXTRA_MINUTES, 30)

                // This sets the alarm to (false=not) vibrate

                .putExtra(AlarmClock.EXTRA_VIBRATE, false)

                // This is meant to make the alarm be silent but I don't think it works...

                .putExtra(AlarmClock.EXTRA_RINGTONE, "VALUE_RINGTONE_SILENT");

        // To show the Alarm app after adding an alarm, uncomment the line below:

        //intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);

        // This line first checks that your phone has an app that can handle the intent before

        // starting so the app doesn't crash if your phone doesn't an an appropriate app

        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(MainSecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}

