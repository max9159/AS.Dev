package xuenn.lesson_4;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivitySimpleList2 extends AppCompatActivity {

    private Context context;
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_2);

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
                Toast.makeText(context, list.get(i).get("foodName"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListView() {
        list = new ArrayList<HashMap<String, String>>();
        String[] foodName = getResources().getStringArray(R.array.Food_Name);
        String[] foodCalorie = getResources().getStringArray(R.array.Food_Calorie);
        for (int i = 0; i < foodName.length; i++) {
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("foodName", foodName[i]);
            item.put("foodCalorie", foodCalorie[i]);
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2,
                new String[]{"foodName", "foodCalorie"},
                new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(simpleAdapter);
    }

}
