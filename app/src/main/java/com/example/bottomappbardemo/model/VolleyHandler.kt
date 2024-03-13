package com.example.bottomappbardemo.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bottomappbardemo.model.Constant.BASE_URL_NOW
import com.example.bottomappbardemo.model.Constant.BASE_URL_POP
import com.example.bottomappbardemo.model.Constant.BASE_URL_UP
import com.example.bottomappbardemo.model.nowplayingdata.NowplayingResponse
import com.example.bottomappbardemo.model.nowplayingdata.OperationalNowplaying
import com.example.bottomappbardemo.model.poulardata.OperationalPopular
import com.example.bottomappbardemo.model.poulardata.PopularResponse
import com.example.bottomappbardemo.model.upcomingdata.OperationalUpcomin
import com.example.bottomappbardemo.model.upcomingdata.UpcomingResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class VolleyHandler(private val context: Context?) {
    private val requestQueue by lazy { Volley.newRequestQueue(context) }

    fun makeApiCallUp(callBack: OperationalUpcomin) {

        val stringRequest = StringRequest(
            Request.Method.GET,
            BASE_URL_UP, {
                val typeToken = object : TypeToken<UpcomingResponse>() {}
                val response = Gson().fromJson(it, typeToken)

                if (response.total_pages!= null) {
                    Log.i("tag", "${response.total_pages}")
                    callBack.onSuccessUp(response)
                } else {
                    Log.i("tag", "${it.toString()}")
                    callBack.onFailureUp("Server Error")
                }
            }, {
                Log.i("tag", "{$it.toString()}")
                callBack.onFailureUp(it.message.toString())
            }
        )
        requestQueue.add(stringRequest)
    }
    fun makeApiCallPop(callBack: OperationalPopular) {

        val stringRequest1 = StringRequest(
            Request.Method.GET,
            BASE_URL_POP, {
                val typeToken = object : TypeToken<PopularResponse>() {}
                val response = Gson().fromJson(it, typeToken)

                if (response.total_pages!= null) {
                    Log.i("tag", "${response.total_pages}")
                    callBack.onSuccessPop(response)
                } else {
                    Log.i("tag", "${it.toString()}")
                    callBack.onFailurePop("Server Error")
                }
            }, {
                Log.i("tag", "{$it.toString()}")
                callBack.onFailurePop(it.message.toString())
            }
        )
        requestQueue.add(stringRequest1)
    }


    fun makeApiCallNow(callBack: OperationalNowplaying) {

        val stringRequest2 = StringRequest(
            Request.Method.GET,
            BASE_URL_NOW, {
                val typeToken = object : TypeToken<NowplayingResponse>() {}
                val response = Gson().fromJson(it, typeToken)

                if (response.total_pages!= null) {
                    Log.i("tag", "${response.total_pages}")
                    callBack.onSuccessNow(response)
                } else {
                    Log.i("tag", "${it.toString()}")
                    callBack.onFailureNow("Server Error")
                }
            }, {
                Log.i("tag", "{$it.toString()}")
                callBack.onFailureNow(it.message.toString())
            }
        )
        requestQueue.add(stringRequest2)
    }
}