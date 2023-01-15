package com.example.myapplication.service

import com.example.myapplication.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CryptoAPI {

    // https://raw.githubusercontent.com/hincim/dataset/master/Yeni%20Metin%20Belgesi.json

    // https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("hincim/dataset/master/Yeni%20Metin%20Belgesi.json")
    fun getData(): Observable<List<CryptoModel>>
    // gözlemlenebilir -> RxJava

    //fun getData() : retrofit2.Call<List<CryptoModel>>
    // Call metodu aseknron bir şekilde işlem yapar.


}