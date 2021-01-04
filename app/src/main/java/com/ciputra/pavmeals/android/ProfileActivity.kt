package com.ciputra.pavmeals.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ciputra.pavmeals.R
import kotlinx.android.synthetic.main.activity_main.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> {
                    val moveHome = Intent(this@ProfileActivity, MainActivity::class.java)
                    startActivity(moveHome)
                }
                R.id.ic_explore -> {
                    val moveExplore = Intent(this@ProfileActivity, ExploreActivity::class.java)
                    startActivity(moveExplore)
                }
            }
            true
        }
    }
}