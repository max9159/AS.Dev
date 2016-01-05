package xuenn.lesson_3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xuenn.setting.Settings;

public class ActivityMain extends AppCompatActivity {
    private Context context;
//    private SharedPreferences settings;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initSetting();
        writeSetting();
        readSetting();
    }

    private void init() {
        this.context=this;
    }

    private void initSetting() {
//        settings=getSharedPreferences("setting",MODE_PRIVATE);
        this.settings=Settings.getIntance(context);
    }

    private void writeSetting() {
//        settings.edit().putString("name", "tony").commit();
//        settings.edit().putInt("age",18).commit();
//        settings.edit().putBoolean("gender",true).commit();
        settings.setName("tony");
        settings.setAge(18);
        settings.setGender(true);
    }

    private void readSetting() {
//        String name=settings.getString("name","none");
//        int age=settings.getInt("age",0);
//        boolean gender=settings.getBoolean("gender",false);
        String name=settings.getName();
        int age=settings.getAge();
        boolean gender=settings.getGender();
    }


}
