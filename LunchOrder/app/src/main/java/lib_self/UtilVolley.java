package lib_self;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony1 on 1/12/2016.
 */
public class UtilVolley {
    private Context context;
    private static RequestQueue requestQueue;
    private StringRequest stringRequest;

    private int timeout = 60000;
    private int retryTimes = 3;
    private boolean autoCancel = true;

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILED = 1;

    private OnEvent onEvent;

    public interface OnEvent {
        public void onLoaded(String result, int status, int ErrorCode);
    }

    public void setOnEvent(OnEvent onEvent) {
        this.onEvent = onEvent;
    }

    public UtilVolley(Context context) {
        this.context = context;
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public void load(String url) {
        if (stringRequest != null) {
            if (autoCancel) {
                if (!stringRequest.isCanceled()) {
                    stringRequest.cancel();
                }
                stringRequest = null;
            }
        }

        //取得Volley Request的佇列
        requestQueue = Volley.newRequestQueue(context);
        //定義一個新的Request
        StringRequest stringRequest = new UTF8StringRequest(
                url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //成功載入時
                if (onEvent != null) {
                    onEvent.onLoaded(s, STATUS_SUCCESS, 0);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //載入錯誤時
                if (onEvent != null) {
                    onEvent.onLoaded(null, STATUS_SUCCESS, volleyError.networkResponse.statusCode);
                }
                Log.i("result", "error code : " + volleyError.networkResponse.statusCode);
            }
        }
        );

        //設定Request的重試規則
        RetryPolicy retryPolicy = new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return timeout;
            }

            @Override
            public int getCurrentRetryCount() {
                return retryTimes;
            }

            @Override
            public void retry(VolleyError volleyError) throws VolleyError {
                //重試時 進入retry
            }
        };
        //設定規則
        stringRequest.setRetryPolicy(retryPolicy);
        //新增到佇列並執行
        requestQueue.add(stringRequest);
    }

    public void load(String url,final HashMap<String, String> params) {
        if (requestQueue != null) {
            if (autoCancel) {
                if (!stringRequest.isCanceled()) {
                    stringRequest.cancel();
                }
                stringRequest = null;
            }
        }

        //取得Volley Request的佇列
        requestQueue = Volley.newRequestQueue(context);
        //定義一個新的Request
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST
                ,url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //成功載入時
                if (onEvent != null) {
                    onEvent.onLoaded(s, STATUS_SUCCESS, 0);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //載入錯誤時
                if (onEvent != null) {
                    onEvent.onLoaded(null, STATUS_SUCCESS, volleyError.networkResponse.statusCode);
                }
                Log.i("result", "error code : " + volleyError.networkResponse.statusCode);
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        //設定Request的重試規則
        RetryPolicy retryPolicy = new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return timeout;
            }

            @Override
            public int getCurrentRetryCount() {
                return retryTimes;
            }

            @Override
            public void retry(VolleyError volleyError) throws VolleyError {
                //重試時 進入retry
            }
        };
        //設定規則
        stringRequest.setRetryPolicy(retryPolicy);
        //新增到佇列並執行
        requestQueue.add(stringRequest);
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public boolean isAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
    }
}
