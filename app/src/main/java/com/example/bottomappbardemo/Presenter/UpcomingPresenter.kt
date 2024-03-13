package com.example.bottomappbardemo.model.nowplayingdata
import com.example.bottomappbardemo.model.VolleyHandler
import com.example.bottomappbardemo.model.upcomingdata.OperationalUpcomin
import com.example.bottomappbardemo.model.upcomingdata.UpcomingResponse


class UpcomingPresenter(
    private val volleyHandler: VolleyHandler,
    private val upcomingView:MVPUpcoming.UpcomingView
): MVPUpcoming.UpcomingPresenter {
    override fun fetchUpcomingMovieData() {
        upcomingView.onLoadUpcoming(true)
        volleyHandler.makeApiCallUp(object : OperationalUpcomin {
            override fun onSuccessUp(upcomingMovieResponse: UpcomingResponse) {
                upcomingView.onLoadUpcoming(false)
                upcomingView.setResultUpcoming(upcomingMovieResponse)
            }

            override fun onFailureUp(message: String) {
                upcomingView.onLoadUpcoming(false)
                upcomingView.showErrorUpcoming(message)
            }
        })
    }
}