package xuenn.lesson_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain2 extends AppCompatActivity {
    private Context context;
    private TextView tvName;


    public static final String  INTENT_NAME="name";
    public static final String  INTENT_AGE="age";
    public static final String  INTENT_GNENDER="gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
        initUI();
        initAction();
        initIntent();

    }

    private void init() {
        this.context=this;
    }

    private void initUI() {
        this.tvName= (TextView) findViewById(R.id.tvName);
    }

    private void initAction() {

    }

    //get intent from previous activity
    private void initIntent() {
        //get intent extra
        Intent i=getIntent();
        String name=i.getStringExtra(INTENT_NAME);
        int age=i.getIntExtra(INTENT_AGE,0);
        boolean gender=i.getBooleanExtra(INTENT_GNENDER,false);

        //show on Toast
        String output="name : "+name+"\nage : "+age+"\n gender : "+gender;
        Toast.makeText(context,output, Toast.LENGTH_LONG).show();

        //set tvName text
        tvName.setText(output);
    }

}
