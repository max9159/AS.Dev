package Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.yee.launch.lunchorder.R;

import java.util.ArrayList;
import java.util.Collections;

import Model.FoodItem;
import lib_self.JsonManager;
import lib_self.MyBaseAdapter;
import lib_self.UtilVolley;

/**
 * Created by Max on 2016/1/13.
 */
public class FoodItemControl {
    private MyBaseAdapter myAdapter;
    private Context context;
    public static ArrayList<FoodItem> FoodListFromJson;
    public String targetFileSourceURL = "http://jdata.azurewebsites.net/API/Files/FoodItemList.json";
    ProgressDialog mDialog;

    public FoodItemControl(Context context) {
        this.context = context;
    }

    private void initListView() {
        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        ListView listView2 = (ListView) rootView.findViewById(R.id.listView2);
        ListView listView3 = (ListView) rootView.findViewById(R.id.listView3);

        myAdapter = new MyBaseAdapter(context, FoodListFromJson);
        listView.setAdapter(myAdapter);

        //random List
        ArrayList<FoodItem> randomListForLV2 = new ArrayList<>(FoodListFromJson);
        Collections.shuffle(randomListForLV2);
        myAdapter = new MyBaseAdapter(context, randomListForLV2);
        listView2.setAdapter(myAdapter);

        //random List
        ArrayList<FoodItem> randomListForLV3 = new ArrayList<>(FoodListFromJson);
        Collections.shuffle(randomListForLV3);
        myAdapter = new MyBaseAdapter(context, randomListForLV3);
        listView3.setAdapter(myAdapter);

    }

    public void LoadFoodListByVolley() {

        mDialog = new ProgressDialog(context);
        mDialog.setMessage("Loading Data...");
        mDialog.setCancelable(false);
        mDialog.show();

        UtilVolley utilVolley = new UtilVolley(context);
        utilVolley.setRetryTimes(3);
        utilVolley.setTimeout(60000);
        utilVolley.setAutoCancel(true);
        utilVolley.setOnEvent(new UtilVolley.OnEvent() {
            @Override
            public void onLoaded(String result, int status, int ErrorCode) {
                if (status == UtilVolley.STATUS_SUCCESS) {
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                    Log.i("loadByVolleyObject()", "Success:" + result.toString());
                    FoodListFromJson = JsonManager.ConverJsonToList(result);
                    initListView();
                    //Toast.makeText(context, "conver getName"+getfoodFromJson.getName(), Toast.LENGTH_LONG).show();
                } else {
                    Log.i("loadByVolleyObject()", "failed");
                    Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
                }
                mDialog.dismiss();
            }
        });
        //Post
//        Map<String, String> map=new HashMap<>();
//        map.put("name", "tony");
//        map.put("password","1234");
//        utilVolley.load("http://finance.yahoo.com/webservice/v1/symbols/allcurrencies/quote?format=json", map);
        //GET
        //utilVolley.load("http://jdata.azurewebsites.net/FoodItem.json");
        utilVolley.load(targetFileSourceURL);

    }
}
