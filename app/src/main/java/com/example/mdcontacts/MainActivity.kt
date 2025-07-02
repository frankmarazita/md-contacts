package com.example.mdcontacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdcontacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = ContactRepository(this)
        val contacts = repository.getContacts()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ContactAdapter(contacts)
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh contacts when returning to MainActivity
        val repository = ContactRepository(this)
        val contacts = repository.getContacts()
        (binding.recyclerView.adapter as? ContactAdapter)?.let {
            // Assuming you have a way to update the adapter's data
            // For simplicity, re-initializing the adapter here. A more efficient way would be to update the list and call notifyDataSetChanged()
            binding.recyclerView.adapter = ContactAdapter(contacts)
        }
    }
}
