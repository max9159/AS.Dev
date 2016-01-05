package xuenn.lesson_4;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by SsuChi on 2015/1/12.
 */
public class UtilScreen {
    //偵測是不是平板
    public static boolean isTablet(Context context) {
        try {
            return (context.getResources().getConfiguration().screenLayout
                    & Configuration.SCREENLAYOUT_SIZE_MASK)
                    >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        } catch (Exception e) {
            return false;
        }

    }

    //取得ActionBar高度
//    public static int getActionBarHeight(Context context){
//        int actionBarHeight = 0;
//        TypedValue tv = new TypedValue();
//        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
//            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
//        }
//        return actionBarHeight;
//    }

    //取得螢幕大的那一邊
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

    //取得螢幕小的那一邊
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

    //取得DPI
    private static Float density;
    public static float getDensity(Context context) {
        if(density==null){
            density=context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

   public static float dp2Pix(Context context,int dp){
       return dp*getDensity(context);
   }

    public static int getOrientation(Context context){
        return  context.getResources().getConfiguration().orientation;
    }

    private static Double inches;
    public static double getInches(Context context){
        if(inches!=null){
            return inches;
        }
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        window.getDefaultDisplay().getMetrics(displayMetrics);
        Display display = window.getDefaultDisplay();
        double x = Math.pow(display.getWidth()/displayMetrics.xdpi,2);
        double y = Math.pow(display.getHeight()/displayMetrics.ydpi,2);
        double screenInches = Math.sqrt(x + y);
        Log.d("debug", "Screen inches : " + screenInches);
        screenInches =  (double)Math.round(screenInches * 10) / 10;
        Log.d("debug", "Screen inches : " + screenInches);
        inches=screenInches;
        return inches;
    }


    public static String screen2File(View view,String Path){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_M_dd_HH_mm_ss");
        String now = formatter.format(+System.currentTimeMillis());

        view.setDrawingCacheEnabled(true);
        Bitmap bitmap=Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        String FilePath=Path+now+".jpg";

        File folder=new File(Path);
        if(!folder.exists()){
            folder.mkdirs();
        }

        File file=new File(FilePath);

        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e1) {
        }

        try {
            FileOutputStream output = new FileOutputStream(file);;
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, output);
            output.flush();
            output.close();
        } catch (Exception e) {
        }

        return "file://"+FilePath;
    }
}
