package com.iamauttamai.volleyapi.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class libWebservice {
    private Context context;
    private VolleyController volley;

    public libWebservice(Context context) {
        super();
        this.context = context;
        volley = VolleyController.getInstance(context);
    }

    public void cancel() {
        volley.cancelRequest();
    }

    public interface VolleyCallback {
        void onSuccess(JSONObject result) throws JSONException, Exception;
        void onError(String result) throws Exception;
    }

    @SuppressLint("HardwareIds")
    public void RequestAPI(String url, int method, JSONObject jsonObject, final VolleyCallback callback) {

        JsonObjectRequest rq = new JsonObjectRequest(method, url, jsonObject, response -> {
            try {
                callback.onSuccess(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    callback.onError(error.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }}) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("header-key", "a7DwvemPterjt3MsdLwvhreheh21P8pFlvAC5OdPnRAgrheh");
                return headers;
            }

        };

        rq.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Request added to the RequestQueue
        VolleyController.getInstance(this.context).addToRequestQueue(rq);
    }

    public void cancelRequest(Object o){
        VolleyController.getInstance(this.context).cancelRequest(o);
    }

}
