package lib_self;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import Model.FoodItem;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Max on 2016/1/7.
 */
public class JsonManager {
    //ProgressDialog mDialog;

    public JsonManager() {

    }

    public static FoodItem ConverJsonToObj(String jsonRole) {
        Gson gson = new Gson();

        //Json to Object
        FoodItem newFood = gson.fromJson(jsonRole, FoodItem.class);
        Log.i("ConverJsonToObj", "getName:" + newFood.getName());
        Log.i("ConverJsonToObj", "getDesc" + newFood.getDesc());
        Log.i("ConverJsonToObj", "getImgUrl" + newFood.getImgUrl());
        Log.i("ConverJsonToObj", "getLocation" + newFood.getLocation());
        Log.i("ConverJsonToObj", "getRating" + newFood.getRating());

        Log.i("gson.toJson", gson.toJson(newFood));

        return newFood;
    }

    public static ArrayList<FoodItem> ConverJsonToList(String jsonRole) {
        Gson gson = new Gson();

        //Json to ArrayList
        ArrayList<FoodItem> newFoodList;
        newFoodList = gson.fromJson(jsonRole, new TypeToken<ArrayList<FoodItem>>() {
        }.getType());

        Log.i("gson.toJson", gson.toJson(newFoodList));

        return newFoodList;
    }


    public void AsyncHttpClientConnectTest(final Context context, String urlString) {
        //mDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //mDialog.dismiss();
                Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show();
                //mSwipeLayout.setRefreshing(false); //結束更新動畫
                //Toast.makeText(getApplicationContext(),
                //        "Success!", Toast.LENGTH_LONG).show();
                //Log.d("Hot Text:", response.toString());

                //mainAdapter.updateData(response.optJSONArray("list"));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject error) {
                //mDialog.dismiss();
                //mSwipeLayout.setRefreshing(false); //結束更新動畫
                Toast.makeText(context, "Error:" + statusCode + " " + e.getMessage(), Toast.LENGTH_LONG).show();

                //Toast.makeText(getApplicationContext(),
                //        "Error: " + statusCode + " " + e.getMessage(),
                //        Toast.LENGTH_LONG).show();
                // Log error message
                //Log.e("Hot Text:", statusCode + " " + e.getMessage());
            }
        });
    }

}
