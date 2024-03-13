package com.example.bottomappbardemo.model.nowplayingdata

import com.example.bottomappbardemo.model.VolleyHandler

class NowPlayingPresenter(
    private val volleyHandler: VolleyHandler,
    private val nowplayingView: MVPNowplaying.NowplayingView
): MVPNowplaying.NowplayingPresenter {
    override fun fetchNowplayingMovieData(){
        nowplayingView.onLoadNowplaying(true)
        volleyHandler.makeApiCallNow(object : OperationalNowplaying {
            override fun onSuccessNow(nowPlayingResponse: NowplayingResponse) {
                nowplayingView.onLoadNowplaying(false)
                nowplayingView.setResultNowplaying(nowPlayingResponse)
            }

            override fun onFailureNow(message: String) {
                nowplayingView.onLoadNowplaying(false)
               nowplayingView.showErrorNowplaying(message)
            }
        })
    }
}