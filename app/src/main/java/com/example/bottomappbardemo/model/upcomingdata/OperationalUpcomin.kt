package com.example.bottomappbardemo.model.upcomingdata

interface OperationalUpcomin{

    fun onSuccessUp(upcomingResponse: UpcomingResponse)
    fun onFailureUp(message: String)
}