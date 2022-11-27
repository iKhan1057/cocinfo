package com.cocdetails.network

import com.cocdetails.ui.nodels.home.HomeParents
import com.cocdetails.ui.nodels.landing.LandingParent
import retrofit2.http.GET

interface CocDetailsApi {
    @GET("landing.json")
    suspend fun getLandingDataFromServ(): LandingParent

    @GET("/main.json")
    suspend fun getHomeData(): HomeParents
}