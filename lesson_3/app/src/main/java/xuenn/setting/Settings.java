package xuenn.setting;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SsuChi on 1/4/2016.
 */
public class Settings {
    private static Settings settings;

    private Context context;
    private SharedPreferences sharedPreferences;

    public static final String SETTING_NAME="setting";
    public static final String NAME="name";
    public static final String AGE="age";
    public static final String GENDER="gender";

    public final String DEFAULT_NAME="name";
    public final int DEFAULT_AGE=0;
    public final boolean DEFAULT_GENDER=false;

    private Settings(Context context){
        this.context=context;
        this.sharedPreferences=context.getSharedPreferences(SETTING_NAME,Context.MODE_PRIVATE);
    }

    //get settings
    public static Settings getIntance(Context context){
        if(settings==null){
            settings=new Settings(context);
            settings.loadDefault();
        }
        return settings;
    }

    //if this app do not have any setting then write default setting
    public void loadDefault(){
        if(!sharedPreferences.contains(NAME)){
            sharedPreferences.edit().putString(NAME,DEFAULT_NAME).commit();
        }
        if(!sharedPreferences.contains(AGE)){
            sharedPreferences.edit().putInt(AGE,DEFAULT_AGE).commit();
        }
        if(!sharedPreferences.contains(GENDER)){
            sharedPreferences.edit().putBoolean(GENDER, DEFAULT_GENDER).commit();
        }
    }

    public void setName(String name){
        sharedPreferences.edit().putString(NAME,name).commit();
    }

    public void setAge(int age){
        sharedPreferences.edit().putInt(AGE, age).commit();
    }

    public void setGender(boolean gender){
        sharedPreferences.edit().putBoolean(GENDER, gender).commit();
    }

    public String getName(){
        return sharedPreferences.getString(NAME, DEFAULT_NAME);
    }

    public int getAge(){
        return sharedPreferences.getInt(AGE,DEFAULT_AGE);
    }

    public boolean getGender(){
        return sharedPreferences.getBoolean(GENDER, DEFAULT_GENDER);
    }


}
