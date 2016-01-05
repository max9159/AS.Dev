package xuenn.lesson_4;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;

public class ActivityGridView extends AppCompatActivity {

    private Context context;
    private GridView gridView;
    private MyAdapter myAdapter;
    private ArrayList<CardItem> cardItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        init();
        initUI();
        initAction();
        initListView();
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        this.gridView = (GridView) findViewById(R.id.gridView);
    }

    private void initAction() {
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, cardItems.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListView() {
        cardItems = new ArrayList<>();
        CardItem cardItem = null;
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
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(myAdapter);
//        SwingLeftInAnimationAdapter swingLeftInAnimationAdapter = new SwingLeftInAnimationAdapter(myAdapter);
//        SwingRightInAnimationAdapter swingRightInAnimationAdapter = new SwingRightInAnimationAdapter(myAdapter);
//        AlphaInAnimationAdapter alphaInAnimationAdapter=new AlphaInAnimationAdapter(myAdapter);
//        ScaleInAnimationAdapter scaleInAnimationAdapter=new ScaleInAnimationAdapter(myAdapter);


        swingBottomInAnimationAdapter.setAbsListView(gridView);
//        swingLeftInAnimationAdapter.setAbsListView(gridView);
//        swingRightInAnimationAdapter.setAbsListView(gridView);
//        alphaInAnimationAdapter.setAbsListView(gridView);
//        scaleInAnimationAdapter.setAbsListView(gridView);


        gridView.setAdapter(swingBottomInAnimationAdapter);
//        gridView.setAdapter(swingLeftInAnimationAdapter);
//        gridView.setAdapter(swingRightInAnimationAdapter);
//        gridView.setAdapter(alphaInAnimationAdapter);
//        gridView.setAdapter(scaleInAnimationAdapter);

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
