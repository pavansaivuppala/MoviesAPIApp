package com.example.bottomappbardemo.model.nowplayingdata
import com.example.bottomappbardemo.model.upcomingdata.UpcomingResponse

interface MVPUpcoming {
    interface UpcomingPresenter{
        fun fetchUpcomingMovieData()
    }
    interface UpcomingView{
        fun setResultUpcoming(upcomingMovieResponse: UpcomingResponse)
        fun onLoadUpcoming(isLoading: Boolean)
        fun showErrorUpcoming(message: String)
    }
}