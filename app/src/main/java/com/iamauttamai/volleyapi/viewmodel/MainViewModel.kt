package com.iamauttamai.volleyapi.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.iamauttamai.volleyapi.api.libWebservice
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by AuttaphonL. on 14,June,2023
 */

class MainViewModel : ViewModel() {

    fun callAPI(activity: Activity, url: String){
        val params = HashMap<String?, Any?>()
        params["param"] = "value"
        val ws = libWebservice(activity)
        val objects = JSONObject(params)
        ws.RequestAPI(
            url,
            Request.Method.GET,
            objects,
            object : libWebservice.VolleyCallback {
                @Throws(JSONException::class)
                override fun onSuccess(result: JSONObject) {
                    Log.i("VolleyAPI", "success")
                }

                @Throws(Exception::class)
                override fun onError(result: String) {
                    Log.i("VolleyAPI", "error")
                }
            })
    }

}