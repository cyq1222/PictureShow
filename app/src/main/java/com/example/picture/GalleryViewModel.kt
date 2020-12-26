package com.example.picture

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive : LiveData<List<PhotoItem>>
        //返回上面的MutableLiveData的版本
    get() = _photoListLive
    //
    fun fetchData(){
        val stringRequest = StringRequest(
            Request.Method.GET,
            getURL(),
            //正确响应后的监听器
            Response.Listener {
                _photoListLive.value = Gson().fromJson(it,Pixabay::class.java).hits.toList()
            },
            //错误监听
            Response.ErrorListener {
                Log.d("hello",it.toString())
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }
    //返回一个网址
    private  fun getURL():String{
        return "https://pixabay.com/api/?key=12472743-874dc01dadd26dc44e0801d61&q=${keyWords.random()}&per_page=100"
    }
    //每次下拉刷新都会形成新的请求
    private  val keyWords = arrayOf("cat","dog","car","beauty","phone","computer","flower","animal")
}