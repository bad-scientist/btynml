package com.example.beautymnl

import android.app.Application
import com.example.beautymnl.utils.Network

class App : Application() {

    private val BASE_URL = "https://beautymnl.herokuapp.com"
    lateinit var network: Network

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        network = Network(BASE_URL)
    }
}