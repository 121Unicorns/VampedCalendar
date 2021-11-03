package com.unicorn.vampedcalendar.utils;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.unicorn.vampedcalendar.VampedCalendar;
import com.unicorn.vampedcalendar.R;

public class GraphicUtils {

    public static void setDayColor(TextView tv, int color, int textColor, @NonNull VampedCalendar.EventShape eventShape) {
        int drawable;
        if (eventShape == VampedCalendar.EventShape.ROUNDED_SQUARE) {
            drawable = R.drawable.background_square_rounded;
        } else if (eventShape == VampedCalendar.EventShape.SQUARE) {
            drawable = R.drawable.background_square;
        } else if (eventShape == VampedCalendar.EventShape.DOT) {
            drawable = R.drawable.background_dots;
        } else if (eventShape == VampedCalendar.EventShape.HEART) {
            drawable = R.drawable.background_heart;
        } else {
            drawable = R.drawable.background_circle;
        }

        setDayColors(tv, textColor, Typeface.NORMAL, drawable);
        com.unicorn.vampedcalendar.utils.MyDrawableCompat.setColorFilter(tv.getBackground(), color);

    }

    private static void setDayColors(TextView textView, int textColor, int typeface, int background) {
        if (textView == null) {
            return;
        }

        textView.setTypeface(null, typeface);
        textView.setTextColor(textColor);
        textView.setBackgroundResource(background);
    }

    public static void setDayDrawable(TextView textView, Drawable drawable, int textColor) {
        if (textView == null) {
            return;
        }

        textView.setTypeface(null, Typeface.NORMAL);
        textView.setTextColor(textColor);
        textView.setBackground(drawable);
    }

}



