package com.cocdetails.network

import com.cocdetails.ui.nodels.home.HomeParents
import com.cocdetails.ui.nodels.landing.LandingParent

interface CocDetailsApi {

   suspend fun getLandingData():LandingParent

   suspend fun getHomeData():HomeParents
}