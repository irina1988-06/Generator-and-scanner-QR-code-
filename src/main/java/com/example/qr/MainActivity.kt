package com.example.qr

import android.Manifest
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.qr.databinding.ActivityMainBinding
import com.google.zxing.WriterException
import java.sql.DriverManager.println



class MainActivity : AppCompatActivity()
{
    lateinit  var vvodText: String
    var imag: ImageView? = null
    //var buttonn: Button? = null
    var buttonnScan: Button? = null
    lateinit var ttext: EditText
    lateinit  var scanerText: EditText
    var buttonShare: Button? = null
    var buttonSearch: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scanerText = findViewById(R.id.y2)
        ttext = findViewById(R.id.y)
        imag= findViewById(R.id.imageView)
       var buttonn = findViewById<Button>(R.id.button)


        buttonShare = findViewById(R.id.button4)
        buttonSearch = findViewById(R.id.button3)

        buttonnScan = findViewById(R.id.button2)

                //Получение данных с Activity2
        var intent:Intent
        intent= getIntent()
        var a:String
        a= intent.getStringExtra("key").toString()

        var button_Visibility:String
        button_Visibility= intent.getStringExtra("kkey").toString()

    if(button_Visibility=="true"){
    scanerText.setText(a)
    buttonShare?.setVisibility(View.VISIBLE)
    buttonSearch?.setVisibility(View.VISIBLE)
}





                buttonnScan?.setOnClickListener {

                    checkCamera()

                }



        buttonn?.setOnClickListener {
            vvodText= ttext.text.toString()

            generateQrCode(vvodText)

        }

        buttonSearch?.setOnClickListener()// komentariy poisk w internete
        {
         var luboeSearch = Intent(Intent.ACTION_WEB_SEARCH)
            luboeSearch.putExtra(SearchManager.QUERY,a)
            startActivity(luboeSearch)
        }

        buttonShare?.setOnClickListener()
        {
            var ShareText = Intent(Intent.ACTION_SEND)
            ShareText.type = "text/plain"
            ShareText.putExtra(Intent.EXTRA_TEXT,a)
            startActivity(ShareText)
        }

    }
        private fun generateQrCode(vvodText: String){

            val qrGenerator = QRGEncoder(vvodText, null,
                QRGContents.Type.TEXT, 400)
                val MapIm = qrGenerator.encodeAsBitmap()
                imag?.setImageBitmap(MapIm)
        }



    private fun checkCamera(){ // функция которая будет выполнять
                                         // проверку есть ли уже разрешение или нет
        if(ContextCompat.checkSelfPermission(this,  Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,

                arrayOf(Manifest.permission.CAMERA), 20)

        } else {
            startActivity(Intent(this, scanerQR::class.java))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 20){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this, scanerQR::class.java))
            }
        }
    }








}








