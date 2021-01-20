package com.ciputra.pavmeals.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.ciputra.pavmeals.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()

        tv_login.setSafeOnClickListener{
                val moveIntent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(moveIntent)
            }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    private fun register(){
        bt_signup.setSafeOnClickListener{
            if (TextUtils.isEmpty(et_email.text.toString())){
                et_email.setError("Please enter email")
                return@setSafeOnClickListener
            }else if (TextUtils.isEmpty(et_pwd.text.toString())){
                et_pwd.setError("Please enter password")
                return@setSafeOnClickListener
            }else if (TextUtils.isEmpty(et_name.text.toString())){
                et_name.setError("Please enter name")
                return@setSafeOnClickListener
            }
        auth.createUserWithEmailAndPassword(et_email.text.toString(), et_pwd.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val currentUser = auth.currentUser
                            val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                            currentUserDb?.child("fullname")?.setValue(et_name.text.toString())
                            currentUserDb?.child("email")?.setValue(et_email.text.toString())
                            startActivity(Intent(this@SignupActivity, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@SignupActivity, "Registration failed, please try again.", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }

    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }
}