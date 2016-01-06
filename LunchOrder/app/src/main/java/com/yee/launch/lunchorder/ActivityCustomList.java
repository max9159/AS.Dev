package com.yee.launch.lunchorder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityCustomList extends AppCompatActivity {

    private Context context;
    private ListView listView;
    private ListView listView2;
    private ListView listView3;
    private MyAdapter myAdapter;
    private ArrayList<CardItem> cardItems;
    private Button btnLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        init();
        initUI();
        initAction();
        initListView(listView);
        initListView(listView2);
        initListView(listView3);
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        this.listView = (ListView) findViewById(R.id.listView);
        this.listView2 = (ListView) findViewById(R.id.listView2);
        this.listView3 = (ListView) findViewById(R.id.listView3);
        this.btnLaunch = (Button) findViewById(R.id.btnLaunch);
    }

    private void initAction() {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, cardItems.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        this.btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        int listViewSize = listView.getAdapter().getCount();

                        for (int index = 0; index < listViewSize ; index++) {
                            listView.smoothScrollToPositionFromTop(listView.getLastVisiblePosition() + 100, 5, 1000);
                            listView2.smoothScrollToPositionFromTop(listView2.getLastVisiblePosition() + 100, 5, 1000);
                            listView3.smoothScrollToPositionFromTop(listView3.getLastVisiblePosition() + 100, 5, 1000);
                            try {
                                // it helps scrolling to stay smooth as possible (by experiment)
                                Thread.sleep(60);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }).start();

            }
        });
    }

    private void initListView(ListView lv) {
        cardItems = new ArrayList<>();
        CardItem cardItem = null;
        //add KS
        for (int i = 0; i < 10; i++) {
            cardItem = (new CardItem())
                    .setBackgroundID(R.mipmap.taiwan_changhua)
                    .setName(getString(R.string.Changhua));
            cardItems.add(cardItem);

            cardItem = (new CardItem())
                    .setBackgroundID(R.mipmap.taiwan_chiayi)
                    .setName(getString(R.string.Chiayi));
            cardItems.add(cardItem);

            cardItem = (new CardItem())
                    .setBackgroundID(R.mipmap.taiwan_kaohsiung)
                    .setName(getString(R.string.Kaohsiung));
            cardItems.add(cardItem);

            cardItem = (new CardItem())
                    .setBackgroundID(R.mipmap.taiwan_miaoli)
                    .setName(getString(R.string.Miaoli));
            cardItems.add(cardItem);

            cardItem = (new CardItem())
                    .setBackgroundID(R.mipmap.taiwan_taichung)
                    .setName(getString(R.string.Taichung));
            cardItems.add(cardItem);

            cardItem = (new CardItem())
                    .setBackgroundID(R.mipmap.taiwan_tainan)
                    .setName(getString(R.string.Tainan));
            cardItems.add(cardItem);
        }

        myAdapter = new MyAdapter(context);
        lv.setAdapter(myAdapter);

    }


    static class ViewHolder {
        public ImageView ivWallpaper;
        public TextView tvName;

    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;

        public MyAdapter(Context context) {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return cardItems == null ? 0 : cardItems.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.custom_list_item, null);
                holder.ivWallpaper = (ImageView) convertView.findViewById(R.id.ivWallpaper);
                holder.tvName = (TextView) convertView.findViewById(R.id.tvName);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivWallpaper.setImageDrawable(getResources().getDrawable(cardItems.get(position).getBackgroundID()));
            holder.tvName.setText(cardItems.get(position).getName());

            return convertView;
        }
    }

}
