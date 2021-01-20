package com.ciputra.pavmeals.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.MealsLayer1
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_pwd
import kotlinx.android.synthetic.main.activity_login.et_email
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        tv_signup.setSafeOnClickListener{
                val moveIntent: Intent = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(moveIntent)
        }

        auth = FirebaseAuth.getInstance()
        login()
    }

    private fun login(){
        bt_login.setSafeOnClickListener{
            if(TextUtils.isEmpty(et_email.text.toString())){
                et_email.setError("Please enter email")
                return@setSafeOnClickListener
            }else if(TextUtils.isEmpty(et_pwd.text.toString())){
                et_pwd.setError("Please enter password")
                return@setSafeOnClickListener
            }
            auth.signInWithEmailAndPassword(et_email.text.toString(), et_pwd.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity, "Login failed, please try again/register.", Toast.LENGTH_LONG).show()
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