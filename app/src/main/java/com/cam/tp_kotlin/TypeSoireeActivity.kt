package com.cam.tp_kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_type_soiree.*
import org.jetbrains.anko.startActivityForResult

class TypeSoireeActivity : AppCompatActivity() {

    var soirees = arrayOf("", "", "", "", "", "", "", "", "", "") // 10 messages maxi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_soiree)

        getSoirees()
        updateSoireesView()
        btn_submit.setOnClickListener {

            // On ajoute le nouveau message à la fin
            for(i in 1..soirees.lastIndex){
                soirees[i-1] = soirees[i]
            }
            soirees[soirees.lastIndex] = name_soiree.text.toString()

            putSoirees()
            updateSoireesView()



           // startActivityForResult<ConfirmationActivity>(42, ConfirmationActivity.EXTRA_MESSAGE to new_message.text.toString())
        }


    }


    private fun updateSoireesView() {

        liste_soirees.text = soirees.joinToString("\n")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArray(MainActivity.STATE_MESSAGES, soirees)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        soirees = savedInstanceState.getStringArray(MainActivity.STATE_MESSAGES)
        updateSoireesView()
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

    private fun putSoirees() {
        val sharedPref = getSharedPreferences("MyPreferencesSoirees", Context.MODE_PRIVATE)

        val editor = sharedPref.edit()
        for(i in 1..soirees.lastIndex){
            editor.putString("nom"+i.toString(), soirees[i])
        }
        // editor.putString("messages", txt_messages.text.toString())
        editor.apply()
        Toast.makeText(this, "Messages sauvegardés", Toast.LENGTH_LONG).show()

    }

}
