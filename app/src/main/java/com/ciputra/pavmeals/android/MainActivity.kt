package com.ciputra.pavmeals.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.adapter.CategoryAdapter
import com.ciputra.pavmeals.android.fragment.ExploreFragment
import com.ciputra.pavmeals.android.fragment.HomeFragment
import com.ciputra.pavmeals.android.fragment.ProfileFragment
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.CategoryLayer2
import com.ciputra.pavmeals.api.MealsLayer1
import com.ciputra.pavmeals.api.MealsLayer2
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.ciputra.pavmeals.api.CategoryLayer1 as CategoryLayer1

class MainActivity : FragmentActivity(){
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager2)
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(HomeFragment())
        viewPagerAdapter.addFragment(ExploreFragment())
        viewPagerAdapter.addFragment(ProfileFragment())
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 0
        viewPager.isUserInputEnabled = false

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.ic_home -> viewPager.currentItem = 0
                R.id.ic_explore -> viewPager.currentItem = 1
                R.id.ic_profile -> viewPager.currentItem = 2
            }
            false
        }

//        val homeFragment = HomeFragment()
//        val exploreFragment = ExploreFragment()
//        val profileFragment = ProfileFragment()
//        makeCurrentFragment(homeFragment)
//        bottom_nav.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.ic_home -> makeCurrentFragment(homeFragment)
//                R.id.ic_explore ->makeCurrentFragment(exploreFragment)
//                R.id.ic_profile -> makeCurrentFragment(profileFragment)
//            }
//            true
//        }
    }

//    private fun makeCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply{
//            replace(R.id.fl_wrapper, fragment)
//            commit()
//        }
//    }
}