package com.example.qr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class scanerQR : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var Scanner: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Scanner = ZBarScannerView(this)// инициализируем
        setContentView(Scanner)// показываем только камеру
        //setContentView(R.layout.activity_scaner_qr)
    }
    // функция для вывода результата
    override fun handleResult(p0: Result?) {
// Объявляем переменную и переходим на гл экран
        var intent=Intent(this, MainActivity::class.java)
        intent.putExtra("key"," ${p0?.contents}")
        intent.putExtra("kkey","true")
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        Scanner.setResultHandler(this)
        Scanner.startCamera()
    }

    override fun onPause() {
        super.onPause()
        Scanner.stopCamera()
    }




}