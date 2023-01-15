package com.example.myapplication.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.Adapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.CryptoModel
import com.example.myapplication.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , Adapter.Listener{

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels : ArrayList<CryptoModel>? = null
    private lateinit var adapter: Adapter
    private lateinit var binding: ActivityMainBinding
    private var compositeDisposable : CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Coin App"
        binding.toolbar.setBackgroundColor(Color.parseColor("#000000"))
        setSupportActionBar(binding.toolbar)

        compositeDisposable = CompositeDisposable()

        binding.rv.layoutManager = LinearLayoutManager(this)

        loadData()

    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))

    }

    private fun handleResponse(cryptoList: List<CryptoModel>){

        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            adapter = Adapter(it,this@MainActivity)
            binding.rv.adapter = adapter
        }


    }

    override fun onItemClickListener(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Coin name: ${cryptoModel.currency}",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}