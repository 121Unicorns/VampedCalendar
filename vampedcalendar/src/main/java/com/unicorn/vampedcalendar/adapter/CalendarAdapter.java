package com.unicorn.vampedcalendar.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.unicorn.vampedcalendar.VampedCalendar;
import com.unicorn.vampedcalendar.R;
import com.unicorn.vampedcalendar.listener.OnDateClickedListener;
import com.unicorn.vampedcalendar.model.Event;
import com.unicorn.vampedcalendar.model.EventDate;
import com.unicorn.vampedcalendar.utils.CalendarUtils;
import com.unicorn.vampedcalendar.utils.GraphicUtils;

import java.util.ArrayList;
import java.util.HashMap;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.vh> {
    private ArrayList<EventDate> dates;
    private HashMap<String, Event> eventMap;
    private int currentMonth;
    private OnDateClickedListener listener;
    private final Context context;
    private final int colorTrans, colorBlack, colorWhite;
    private int noEventBackgroundColor, noEventTextColor;
    private VampedCalendar.EventShape defaultEventShape, noEventShape;
    private Drawable noEventBackground;

    public CalendarAdapter(Context context, ArrayList<EventDate> dates, int currentMonth, OnDateClickedListener listener) {
        this.dates = dates;
        this.currentMonth = currentMonth;
        this.context = context;
        this.eventMap = new HashMap<>();
        this.listener = listener;
        colorTrans = ContextCompat.getColor(context, android.R.color.transparent);
        colorBlack = ContextCompat.getColor(context, android.R.color.black);
        colorWhite = ContextCompat.getColor(context, android.R.color.white);
        defaultEventShape = VampedCalendar.EventShape.CIRCLE;
        noEventShape = defaultEventShape;
        noEventTextColor = colorBlack;
        noEventBackgroundColor = colorTrans;
        noEventBackground = null;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public CalendarAdapter.vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.day_layout, viewGroup, false);
        return new vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.vh vh, int i) {
        EventDate eventDate = dates.get(i);
        vh.tv.setText(String.valueOf(eventDate.getDay()));
        vh.tv.setAlpha(isCurrentMonth(eventDate) ? 1.0f : 0.12f);

        Event event = eventMap.get(CalendarUtils.getCalendarKey(context, eventDate));
        handleEvent(event, vh);
    }

    private void handleEvent(Event event, vh vh) {
        //if no event, just set normal text color
        if (event == null) {
            if (noEventBackground == null) {
                GraphicUtils.setDayColor(vh.tv, noEventBackgroundColor, noEventTextColor, noEventShape);
            } else {
                GraphicUtils.setDayDrawable(vh.tv, noEventBackground, noEventTextColor);
            }
            return;
        }

        if (event.getColor() != null) {
            GraphicUtils.setDayColor(vh.tv, event.getColor(), event.getTextColor() == null ? colorWhite : event.getTextColor(), event.getEventShape() == null ? defaultEventShape : event.getEventShape());
        } else if (event.getDrawable() != null) {
            GraphicUtils.setDayDrawable(vh.tv, event.getDrawable(), event.getTextColor() == null ? colorWhite : event.getTextColor());
        } else {
            vh.tv.setTextColor(event.getTextColor() == null ? colorBlack : event.getTextColor());
        }
    }

    private boolean isCurrentMonth(EventDate eventDate) {
        return eventDate.getMonth() == currentMonth + 1;
    }


    @Override
    public int getItemCount() {
        return dates.size();
    }


    public void updateDate(ArrayList<EventDate> dates) {
        this.dates = dates;
        notifyDataSetChanged();
    }

    public void updateMonth(int currentMonth) {
        this.currentMonth = currentMonth;
        notifyDataSetChanged();
    }

    public void updateEvent(HashMap<String, Event> eventMap) {
        this.eventMap = eventMap;
        notifyDataSetChanged();
    }

    public void setListener(OnDateClickedListener listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    class vh extends RecyclerView.ViewHolder {
        View view;
        TextView tv;

        vh(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tv = view.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position == RecyclerView.NO_POSITION) return;

                    notifyListener(position);
                }
            });
        }
    }

    private void notifyListener(int position) {
        EventDate date = dates.get(position);

        Event event = eventMap.get(CalendarUtils.getCalendarKey(context, date));

        if (listener != null) {
            if (event != null) {
                listener.onDateClicked(event);
            } else {
                listener.onDateClicked(new Event(date, 0));
            }
        }
    }

    public void setDefaultEventShape(VampedCalendar.EventShape eventShape) {
        this.defaultEventShape = eventShape;
        notifyDataSetChanged();
    }

    public void setNoEventDayBackground(Drawable drawable) {
        noEventBackground = drawable;
        notifyDataSetChanged();
    }

    public void setNoEventDayBackgroundColor(@ColorInt int color) {
        noEventBackgroundColor = color;
        notifyDataSetChanged();
    }

    public void setNoEventDayTextColor(@ColorInt int color) {
        noEventTextColor = color;
        notifyDataSetChanged();
    }

    public void setNoEventDayShape(VampedCalendar.EventShape shape) {
        noEventShape = shape;
        notifyDataSetChanged();
    }
}
