package com.example.bottomappbardemo.model.nowplayingdata

interface OperationalNowplaying {
    fun onSuccessNow(nowplayingResponse: NowplayingResponse)
    fun onFailureNow(message: String)
}