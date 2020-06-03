package com.example.basicapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_signin.*

class Sigin : AppCompatActivity() {

    internal var dbHelper = SQLiteConfig(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

////        create database
//        val db: SQLiteDatabase = openOrCreateDatabase("coronadb", Context.MODE_PRIVATE, null)
//
////        create a tables inside the database
//        db.execSQL("CREATE TABLE IF NOT EXISTS users(names VARCHAR, profession VARCHAR, residence VARCHAR, password VARCHAR )")

//        grab data from user after clicking save button
        imgadd.setOnClickListener {
            val name = etname.text.trim().toString()
            val profession = etproffession.text.trim().toString()
            val password = etpassword.text.trim().toString()

//            check if users entered all field
            if (name.isEmpty() or profession.isEmpty() or password.isEmpty()) {
                show_message("Empty fields", "Please fill all the inputs")
            } else {
//                db.execSQL("INSERT INTO users VALUES ('"+name+"','"+profession+"','"+residence+"','"+password+"')")
                dbHelper.insertData(name, profession, password)
                show_message("Success", " Data by the name ${name} has been added successfully")
                clear()
                startActivity(Intent(this@Sigin, MainActivity::class.java))
            }



        }
        loginbutton.setOnClickListener {
            startActivity(Intent(this@Sigin,Login::class.java))
        }


    }


    //    message to diplay to the user
    private fun show_message(title: String, message: String) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton(
            "Ok",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        alertDialog.create().show()
    }

    //    A method that will clear input after a successfil request
    private fun clear() {
        etname.setText("")
        etproffession.setText("")
        etpassword.setText("")
    }
}



