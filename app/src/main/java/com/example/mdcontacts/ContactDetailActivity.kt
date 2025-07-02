package com.example.mdcontacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mdcontacts.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.getParcelableExtra<Contact>("contact")

        contact?.let {
            binding.detailNameTextView.text = it.name
            binding.detailPhoneTextView.text = it.phone
            binding.detailEmailTextView.text = it.email
        }
    }
}
