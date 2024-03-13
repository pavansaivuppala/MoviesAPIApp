package com.example.bottomappbardemo.model.nowplayingdata
import com.example.bottomappbardemo.model.poulardata.PopularResponse

interface MVPPopular {
    interface PopularPresenter{
        fun fetchPopularMovieData()
    }
    interface PopularView{
        fun setResultPopular(popularResponse: PopularResponse)
        fun onLoadPopular(isLoading: Boolean)
        fun showErrorPopular(message: String)
    }
}