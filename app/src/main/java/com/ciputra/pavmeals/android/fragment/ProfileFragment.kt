package com.ciputra.pavmeals.android.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.LoginActivity
import com.ciputra.pavmeals.android.SafeClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        loadProfile()
    }

    private fun loadProfile(){
        val user = auth.currentUser
        val userReference = databaseReference?.child(user?.uid!!)

        tv_email.text = "Email: "+user?.email
        userReference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                tv_name.text = snapshot.child("fullname").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Database error, please try again.", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        bt_logout.setSafeOnClickListener{
            auth.signOut()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.requireActivity().supportFragmentManager.popBackStack()
            startActivity(intent)

        }
    }

    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }
}