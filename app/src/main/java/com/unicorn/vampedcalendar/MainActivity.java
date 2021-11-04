package com.unicorn.vampedcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.unicorn.vampedcalendar.listener.OnDateClickedListener;
import com.unicorn.vampedcalendar.model.Event;
import com.unicorn.vampedcalendar.model.EventDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    VampedCalendar vampedCalendar;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vampedCalendar = findViewById(R.id.vCalendar);
        linearLayout = findViewById(R.id.mainLayout);

        //vampedCalendar.setNoEventDayShape(VampedCalendar.EventShape.DOT);
        //vampedCalendar.setNoEventDayBackgroundColor(getResources().getColor(R.color.purple_700));

        List<Event> events = new ArrayList<>();

        //calendar for creating event
        Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.DAY_OF_MONTH, 1);

        EventDate eventDate = new EventDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));//calendar starts from 0 for january
        events.add(new Event(eventDate, getResources().getColor(R.color.primary_red), VampedCalendar.EventShape.CIRCLE));
        vampedCalendar.setEvents(events);

        /*//event with dots at the bottom shape
        for (int i = 0; i < 5; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            EventDate eventDate = new EventDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));//calendar starts from 0 for january
            events.add(new Event(eventDate, getResources().getColor(R.color.secondary_light_red), VampedCalendar.EventShape.DOT));
        }

        //Set events to calendar
        vampedCalendar.setEvents(events);

         */

        //onDateClickListener
        vampedCalendar.setOnDateClickedListener(new OnDateClickedListener() {
            @Override
            public void onDateClicked(Event event) {
                Snackbar.make(linearLayout, "Selected date is: " + event.getEventDate().getDay() + "/" + event.getEventDate().getMonth() + "/" + event.getEventDate().getYear(), BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });

    }
}