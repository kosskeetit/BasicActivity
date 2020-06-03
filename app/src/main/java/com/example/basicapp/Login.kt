package com.example.basicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signin.*

class Login : AppCompatActivity() {

    internal var dbHelper = SQLiteConfig(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginbtn.setOnClickListener {
            val name = etnamelg.text.trim().toString()
            val profession = etproffessionlg.text.trim().toString()
            val password = etpasswordlg.text.trim().toString()

//            check if users entered all field
            if (name.isEmpty() or profession.isEmpty() or password.isEmpty()) {
                Toast.makeText(this@Login, "Please fill in all the fields",  Toast.LENGTH_LONG).show()
            } else {
//                db.execSQL("INSERT INTO users VALUES ('"+name+"','"+profession+"','"+residence+"','"+password+"')")
//                dbHelper.insertData(name, profession, password)
                Toast.makeText(this@Login,"You've been successfully logged in", Toast.LENGTH_LONG).show()
//                clear()
                startActivity(Intent(this@Login, MainActivity::class.java))


            }
            startActivity(Intent(this@Login, MainActivity::class.java))

        }
        signinbtn.setOnClickListener {
            startActivity(Intent(this@Login, Sigin::class.java))
        }

    }
//    private fun clear() {
//        etname.setText("")
//        etproffession.setText("")
//        etpassword.setText("")
//    }

}
