package com.example.bottomappbardemo.model.poulardata

interface OperationalPopular {

    fun onSuccessPop(popular: PopularResponse)
    fun onFailurePop(message: String)

}