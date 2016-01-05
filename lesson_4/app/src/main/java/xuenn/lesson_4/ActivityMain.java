package xuenn.lesson_4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityMain extends AppCompatActivity {
    private Context context;
    private Button btnSimpleList1;
    private Button btnSimpleList2;
    private Button btnCustomList;
    private Button btnCustomListGoogleAnim;
    private Button btnGridView;
    private Button btnRecyclerView;
    private Button btnRecyclerViewRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initUI();
        initAction();
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        btnSimpleList1 = (Button) findViewById(R.id.btnSimpleList1);
        btnSimpleList2 = (Button) findViewById(R.id.btnSimpleList2);
        btnCustomList = (Button) findViewById(R.id.btnCustomList);
        btnCustomListGoogleAnim = (Button) findViewById(R.id.btnCustomListGoogleAnim);
        btnGridView = (Button) findViewById(R.id.btnGridView);
        btnRecyclerView = (Button) findViewById(R.id.btnRecyclerView);
        btnRecyclerViewRefresh = (Button) findViewById(R.id.btnRecyclerViewRefresh);
    }

    private void initAction() {
        btnSimpleList1.setOnClickListener(onClick);
        btnSimpleList2.setOnClickListener(onClick);
        btnCustomList.setOnClickListener(onClick);
        btnCustomListGoogleAnim.setOnClickListener(onClick);
        btnGridView.setOnClickListener(onClick);
        btnRecyclerView.setOnClickListener(onClick);
        btnRecyclerViewRefresh.setOnClickListener(onClick);
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = null;
            switch (v.getId()) {
                case R.id.btnSimpleList1:
                    i = new Intent(context, ActivitySimpleList1.class);
                    break;
                case R.id.btnSimpleList2:
                    i = new Intent(context, ActivitySimpleList2.class);
                    break;
                case R.id.btnCustomList:
                    i = new Intent(context, ActivityCustomList.class);
                    break;
                case R.id.btnCustomListGoogleAnim:
                    i = new Intent(context, ActivityCustomListGoogleAnimation.class);
                    break;
                case R.id.btnGridView:
                    i = new Intent(context, ActivityGridView.class);
                    break;
                case R.id.btnRecyclerView:
                    i = new Intent(context, ActivityRecyclerView.class);
                    break;
                case R.id.btnRecyclerViewRefresh:
                    i = new Intent(context, ActivityRecyclerViewRefresh.class);
                    break;
            }

            if (i != null) {
                startActivity(i);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        }
    };

}
