package lib_self;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.yee.launch.lunchorder.R;

import java.util.ArrayList;

import Model.FoodItem;

/**
 * Created by Max on 2016/1/13.
 */
public class MyBaseAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private ArrayList<FoodItem> foodItems;
    private ImageLoader imageLoader;

    public MyBaseAdapter(Context context, ArrayList<FoodItem> foods) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageLoader = UniversalImageLoaderHelper.getInstance(context);
        this.foodItems = foods;
    }

    public void updateData(ArrayList<FoodItem> jsonArray) {
        this.foodItems = jsonArray;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        public ImageView ivWallpaper;
        public TextView tvName;
    }

    @Override
    public int getCount() {
        return foodItems == null ? 0 : foodItems.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

        if (foodItems.get(position).getImgUrl() != "") {
            Picasso.with(mContext).load(foodItems.get(position).getImgUrl()).placeholder(R.drawable.slotmachine).into(holder.ivWallpaper);
//            holder.ivWallpaper.setImageResource(R.drawable.slotmachine);
            //imageLoader.displayImage(foodItems.get(position).getImgUrl(), holder.ivWallpaper);
            //holder.ivWallpaper.setImageDrawable(getResources().getDrawable(cardItems.get(position).getBackgroundID()));
        }

        holder.tvName.setText(foodItems.get(position).getName());

        return convertView;
    }

    public ArrayList<FoodItem> getFoodItems(){
        return foodItems;
    }
}
