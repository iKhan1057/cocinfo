package com.cocdetails.network

import com.cocdetails.ui.nodels.home.HomeParents
import com.cocdetails.ui.nodels.landing.LandingParent

interface CocDetailsApi {
    @GET("/landing.json")
    suspend fun getLandingData(): LandingParent

    @GET("/main.json")
    suspend fun getHomeData(): HomeParents
}