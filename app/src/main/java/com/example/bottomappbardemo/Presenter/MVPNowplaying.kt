package com.example.bottomappbardemo.model.nowplayingdata

import com.example.bottomappbardemo.model.nowplayingdata.NowplayingResponse

interface MVPNowplaying {
    interface NowplayingPresenter{
        fun fetchNowplayingMovieData()
    }
    interface NowplayingView{
        fun setResultNowplaying(nowPlayingResponse: NowplayingResponse)
        fun onLoadNowplaying(isLoading: Boolean)
        fun showErrorNowplaying(message: String)
    }
}