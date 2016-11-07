package com.cmsc434.adi.doodle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

/**
 * Created by Adi on 11/7/16.
 */

public class Preferences {
    public static Preferences sharedInstance;
    private Resources res;
    int brushSize;
    int paintColor;

    public Preferences(Context context) {
        sharedInstance = this;
        res = context.getResources();
        brushSize = res.getInteger(R.integer.initial_size);
        paintColor = Color.argb(res.getInteger(R.integer.initial_opacity),
                res.getInteger(R.integer.initial_red),
                res.getInteger(R.integer.initial_green),
                res.getInteger(R.integer.initial_blue));
    }
}
