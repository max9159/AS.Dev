package xuenn.lesson_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import xuenn.service.ServiceScreen;

public class ActivityMain extends AppCompatActivity {
    private Context context;
    private Button btnStartService;
    private Button btnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initUI();
        initAction();
    }

    private void init() {
        this.context=this;
    }

    private void initUI() {
        this.btnStartService= (Button) findViewById(R.id.btnStartService);
        this.btnStopService= (Button) findViewById(R.id.btnStopService);
    }

    private void initAction() {
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openService();
            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeService();
            }
        });
    }

    //start service
    private void openService(){
        Intent i=new Intent(context, ServiceScreen.class);
        startService(i);
    }

    //stop service
    private void closeService(){
        Intent i=new Intent(context, ServiceScreen.class);
        stopService(i);
    }

}
