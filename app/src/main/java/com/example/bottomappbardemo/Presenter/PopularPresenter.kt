package com.example.bottomappbardemo.model.nowplayingdata
import com.example.bottomappbardemo.model.VolleyHandler
import com.example.bottomappbardemo.model.poulardata.OperationalPopular
import com.example.bottomappbardemo.model.poulardata.PopularResponse


class PopularPresenter(
    private val volleyHandler: VolleyHandler,
    private val popularView: MVPPopular.PopularView
): MVPPopular.PopularPresenter {
    override fun fetchPopularMovieData() {
        popularView.onLoadPopular(true)
        volleyHandler.makeApiCallPop(object : OperationalPopular {
            override fun onSuccessPop(popularResponse: PopularResponse) {
                popularView.onLoadPopular(false)
                popularView.setResultPopular(popularResponse)
            }

            override fun onFailurePop(message: String) {
                popularView.onLoadPopular(false)
                popularView.showErrorPopular(message)
            }
        })
    }
}