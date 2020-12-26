package com.example.picture

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
//创建完一个单例
class VolleySingleton private constructor(context:Context){
    companion object {
        private var INSTANCE : VolleySingleton?=null
        fun getInstance(context: Context) =
            INSTANCE?: synchronized(this) {
                VolleySingleton(context).also { INSTANCE = it }
            }

    }
//创建一个成员
    val requestQueue:RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
}