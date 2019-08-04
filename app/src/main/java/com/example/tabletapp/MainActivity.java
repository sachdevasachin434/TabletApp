package com.example.tabletapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.example.tabletapp.R.layout.activity_main;


public class MainActivity extends FragmentActivity {
    TextView tvdate, tvtemp1, tvtemp2, tvday, tvtimecount, tvtemp3, tvOrganizer, date;
    SimpleDateFormat dateformat;
    SimpleDateFormat dayformat;
    SimpleDateFormat  tformat;
    String  sTime;
    int minutes,seconds;
    CountDownTimer countDownTimer;
    Button bstart;
    String time1, time2;
    private long timeCountInMilliSeconds;

    TextView start, end, start_text, end_text;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;
    private TextView textViewTime,tvName;
    private ProgressBar progressBarCircle;
    Calendar calendar,calendar2;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            setContentView(activity_main);

            tvdate = (TextView) findViewById(R.id.date);
            tvday = (TextView) findViewById(R.id.tvday);
            tvtimecount = findViewById(R.id.textViewTime);
            bstart = findViewById(R.id.bstart);

            tvtemp1 = findViewById(R.id.tvtemp1);
            tvtemp2 = findViewById(R.id.tvtemp2);
            tvtemp3=findViewById(R.id.tvtemp3);
            progressBarCircle = findViewById(R.id.progressBarCircle);
            textViewTime = (TextView) findViewById(R.id.textViewTime);
            tvName=(TextView)findViewById(R.id.textView4);
            tvOrganizer=findViewById(R.id.textView3);
            tvName.setVisibility(View.GONE);
            tvOrganizer.setVisibility(View.GONE);

            tvtimecount.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
            bstart.setVisibility(View.VISIBLE);

            Date now = new Date();

            calendar = Calendar.getInstance();
            dateformat = new SimpleDateFormat("dd MMMM yyyy");
            String strDate = dateformat.format(calendar.getTime());
            tvdate.setText(strDate);

            tvdate.setText(strDate);
            dayformat = new SimpleDateFormat("EEEE");
            String strDay = dayformat.format(now);

            tvday.setText(strDay.substring(0,3));

            start = findViewById(R.id.start);
            end = findViewById(R.id.end);
            start_text = findViewById(R.id.start_text);
            end_text = findViewById(R.id.end_text);

            start.setVisibility(View.GONE);
            start_text.setVisibility(View.GONE);
            end.setVisibility(View.GONE);
            end_text.setVisibility(View.GONE);

        }
    }

    public void text_view(View view) {
        calendar2 = Calendar.getInstance();
        time1 = tvtemp1.getText().toString();
        time2 = tvtemp2.getText().toString();
        tformat = new SimpleDateFormat("HH:mm:ss");
        sTime = tformat.format(calendar2.getTime());
        if (sTime.substring(0,5).compareTo(time1) >= 0 && sTime.substring(0,5).compareTo(time2) < 0) {
            tvName.setText(tvtemp3.getText().toString());
            tvName.setVisibility(View.VISIBLE);
            tvOrganizer.setVisibility(View.VISIBLE);
            LayoutInflater li = getLayoutInflater();
            View layout = li.inflate(R.layout.customtoast, (ViewGroup) findViewById(R.id.custom_toast_layout));
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setView(layout);
//            alertDialog = builder.create();
//            alertDialog.show();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//            layoutParams.copyFrom(alertDialog.getWindow().getAttributes());

            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

            final PopupWindow popupWindow = new PopupWindow(layout, layoutParams.width, layoutParams.height);
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.showAtLocation(view, Gravity.CENTER, 0,0);
            TextView textView = layout.findViewById(R.id.textView);
            String fontPath1 = "font/aclonica.xml";

            Typeface tf1 = Typeface.createFromAsset(getAssets(), fontPath1);
            textView.setTypeface(tf1);

            start_text.setText(time1.substring(0,5));
            end_text.setText(time2.substring(0,5));

            start.setVisibility(View.VISIBLE);
            start_text.setVisibility(View.VISIBLE);
            end.setVisibility(View.VISIBLE);
            end_text.setVisibility(View.VISIBLE);



//            alertDialog.getWindow().setAttributes(layoutParams);

            final Handler handler  = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();

                    }
                }
            };

//            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//                    handler.removeCallbacks(runnable);
//                }
//            });

            handler.postDelayed(runnable, 5000);
            String m = sTime.substring(3,5);
            minutes = Integer.parseInt(m);

            String s=sTime.substring(6);
            seconds=Integer.parseInt(s);
            // call to initialize the timer values
            setTimerValues();
            // call to initialize the progress bar values
            setProgressBarValues();
            // showing the reset icon
            textViewTime.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
            bstart.setVisibility(View.GONE);
            // changing the timer status to started
            timerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();
        }
        else
        {
            Toast.makeText(this, "No booking at this time slot", Toast.LENGTH_SHORT).show();
        }
}
    private void setTimerValues() {
        if(minutes<=30){
        timeCountInMilliSeconds = ((30-minutes-1) * 60*1000) + (60-seconds)*1000 ;
        }
        else if (minutes==30 || minutes==00)
        {

            timeCountInMilliSeconds = 29*60*1000 + (60-seconds)*1000 ;
        }
        else
        {
            timeCountInMilliSeconds = (60-minutes-1) *60*1000 + (60-seconds)*1000;
        }
    }
    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));

                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {

                textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues();
                // hiding the reset icon
                bstart.setVisibility(View.VISIBLE);
                textViewTime.setVisibility(View.GONE);
                // changing the timer status to stopped
                timerStatus = TimerStatus.STOPPED;
//
                start.setVisibility(View.GONE);
                start_text.setVisibility(View.GONE);
                end.setVisibility(View.GONE);
                end_text.setVisibility(View.GONE);

            }

        }.start();
        countDownTimer.start();
    }
    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    /**
     * method to set circular progress bar values
     */
    private void setProgressBarValues() {

        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }
    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return hms;

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
