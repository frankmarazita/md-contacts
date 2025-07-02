package com.example.mdcontacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = ContactRepository(this)
        val contacts = repository.getContacts()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ContactAdapter(contacts)
        }

        fab.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }
    }
}
