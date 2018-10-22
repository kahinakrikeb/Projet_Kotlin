package com.cam.tp_kotlin

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivityForResult

class MainActivity : AppCompatActivity() {
    companion object {
        const val STATE_MESSAGES = "MainActivity.soiree"
    }

    var soirees = arrayOf("", "", "", "", "", "", "", "", "", "") // 10 messages maxi
    var j=1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSoirees()
        remplire()

        btn_main_next.setOnClickListener {

            if(j<soirees.lastIndex)
                j++

            remplire()

        }
        btn_main_new.setOnClickListener {


            startActivityForResult<TypeSoireeActivity>(42)

        }
        btn_main_previous.setOnClickListener {

            if(j>0)
                j--

            remplire()

        }
    }
    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            42 -> {
                getSoirees()
                remplire()
            }
        }
    }
    private fun getSoirees() {
        val sharedPref = getSharedPreferences("MyPreferencesSoirees", Context.MODE_PRIVATE)
        //val msg = sharedPref.getString("messages","")
        for(i in 1..soirees.lastIndex){
            soirees[i]= sharedPref.getString("nom"+i.toString(),"")
        }
       // detail_soiree.text= soirees.joinToString("\n")
        Toast.makeText(this, "Récupération des messages sauvegardés", Toast.LENGTH_LONG).show()
    }

    private fun remplire(){

        detail_soiree.text=soirees[j].toString()
    }


}