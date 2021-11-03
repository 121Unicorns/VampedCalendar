package com.unicorn.vampedcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.unicorn.vampedcalendar.listener.OnDateClickedListener;
import com.unicorn.vampedcalendar.model.Event;
import com.unicorn.vampedcalendar.model.EventDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    VampedCalendar vampedCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vampedCalendar = findViewById(R.id.vCalendar);

        vampedCalendar.setNoEventDayShape(VampedCalendar.EventShape.DOT);
        vampedCalendar.setNoEventDayBackgroundColor(getResources().getColor(R.color.colorDefault));


    }
}