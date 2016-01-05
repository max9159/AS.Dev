package xuenn.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by SsuChi on 12/30/2015.
 */
public class UtilScreen {
    public static boolean isTablet(Context context) {
        try {
            return (context.getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK)
                    >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        } catch (Exception e) {
            return false;
        }

    }

    //¨ú±o¿Ã¹õ¤jªº¨º¤@Ãä
    public static int getScreenLargerSide(Context context) {
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = window.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        if(height>width){
            return height;
        }else{
            return width;
        }
    }

    public static int getScreenSmallerSide(Context context) {
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = window.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        if(width>height){
            return height;
        }else{
            return width;
        }
    }


    public static float getDensity(Context context) {
        float dpi=context.getResources().getDisplayMetrics().density;
        return dpi;
    }

    public static float dp2Pix(Context context,int dp){
        return dp*getDensity(context);
    }

    public static int getOrientation(Context context){
        return  context.getResources().getConfiguration().orientation;
    }

}

