package xuenn.lesson_4;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityRecyclerView extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<CardItem> cardItems;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        init();
        initUI();
        initAction();
        initListView();
    }

    private void init() {
        this.context = this;
    }

    private void initUI() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initAction() {

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

        MyAdapter myAdapter = new MyAdapter(context);
        linearLayoutManager = new LinearLayoutManager(context);
        //設定滑動方向
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //RecyclerView 設定LayoutManager
        recyclerView.setLayoutManager(linearLayoutManager);
        //set margin
        recyclerView.addItemDecoration(new RecyclerViewSpacing(
                        (int) UtilScreen.dp2Pix(context, 8),
                        (int) UtilScreen.dp2Pix(context, 8),
                        (int) UtilScreen.dp2Pix(context, 8),
                        (int) UtilScreen.dp2Pix(context, 8))
                        .setTopSpacing(0) // First Item Margin
                        .setBottomSpacing(0)
//                        .setTopSpacing((int) UtilScreen.dp2Pix(context, 8) + (int) UtilScreen.dp2Pix(context, 48)) // First Item Margin
//                        .setBottomSpacing((int) UtilScreen.dp2Pix(context, 8))
        );

        recyclerView.addItemDecoration(new RecyclerViewSpacing(
                        (int) UtilScreen.dp2Pix(context, 8),//左Margin
                        (int) UtilScreen.dp2Pix(context, 8),//上Margin
                        (int) UtilScreen.dp2Pix(context, 8),//右Margin
                        (int) UtilScreen.dp2Pix(context, 8))//下Margin
                        .setTopSpacing((int) UtilScreen.dp2Pix(context, 8)) // 第一項的上Margin
                        .setBottomSpacing((int) UtilScreen.dp2Pix(context, 8)) // 最後一項的下Margin
        );


        recyclerView.setAdapter(myAdapter);
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private LayoutInflater layoutInflater;

        public MyAdapter(Context context) {
            this.layoutInflater = LayoutInflater.from(context);
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = layoutInflater.inflate(R.layout.custom_list_item, null);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            //當物件進入舞台時，刷新
            viewHolder.ivWallpaper.setImageDrawable(getResources().getDrawable(cardItems.get(position).getBackgroundID()));
            viewHolder.tvName.setText(cardItems.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return cardItems != null ? cardItems.size() : 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tvName;
            public ImageView ivWallpaper;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                tvName = (TextView) itemLayoutView.findViewById(R.id.tvName);
                ivWallpaper = (ImageView) itemLayoutView.findViewById(R.id.ivWallpaper);
            }

        }
    }


}
