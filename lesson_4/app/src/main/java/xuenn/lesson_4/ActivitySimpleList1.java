package xuenn.lesson_4;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivitySimpleList1 extends AppCompatActivity {

    private Context context;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private String[] taiwanArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_1);

        init();
        initUI();
        initAction();
        initListView();
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        this.listView = (ListView) findViewById(R.id.listView);
    }

    private void initAction() {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, taiwanArea[i], Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListView() {
        taiwanArea = getResources().getStringArray(R.array.TaiwanArea);
        arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, taiwanArea);
        listView.setAdapter(arrayAdapter);
    }

}
