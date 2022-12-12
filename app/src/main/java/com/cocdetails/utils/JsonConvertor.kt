package com.cocdetails.utils

import com.google.gson.Gson

object JsonConvertor {
//    Json To Object
    fun <A> String.fromJson(type: Class<A>): A {
        return Gson().fromJson(this, type)
    }

//    Object To Json String
    fun <A> A.toJson(): String? {
        return Gson().toJson(this)
    }

}