package com.rainingkitkat.countdown;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView dayTV, hourTV, minuteTV, secondTV;
    private ImageView background;
    private String cyberpunk2077Release = "10-12-2020";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayTV = findViewById(R.id.days_textView);
        hourTV = findViewById(R.id.hours_textView);
        minuteTV = findViewById(R.id.minutes_textView);
        secondTV = findViewById(R.id.seconds_textView);
        background = findViewById(R.id.background_imageView);

        Glide.with(this)
                .load("https://mfiles.alphacoders.com/741/741943.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(background);

        startCountdown();
    }

    public void startCountdown() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date futureDate = dateFormat.parse(cyberpunk2077Release);
                    Date currentDate = new Date();
                    if(!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);

                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);

                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);

                        long seconds = diff / 1000;

                        // Setting date to text field.
                        dayTV.setText(String.format("%02d", days) + "");
                        hourTV.setText(String.format("%02d", hours) + "");
                        minuteTV.setText(String.format("%02d", minutes) + "");
                        secondTV.setText(String.format("%02d", seconds) + "");
                    } else {
                        dayTV.setText("Cyberpunk 2077 is out");
                        hourTV.setText("");
                        minuteTV.setText("");
                        secondTV.setText("");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }
}
