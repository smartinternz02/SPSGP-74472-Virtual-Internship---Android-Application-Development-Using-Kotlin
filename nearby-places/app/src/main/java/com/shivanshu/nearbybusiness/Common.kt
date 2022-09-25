package com.shivanshu.nearbybusiness

import com.shivanshu.nearbybusiness.Model.Results
import com.shivanshu.nearbybusiness.Remote.IGoogleAPIService
import com.shivanshu.nearbybusiness.Remote.RetrofitClient


object Common {
    var currentResult: Results? = null
    private val GOOGLE_API_URL = "https://maps.googleapis.com/"
    val googleApiService: IGoogleAPIService
        get() = RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService::class.java)
}