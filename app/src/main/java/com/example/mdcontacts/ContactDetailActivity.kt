package com.example.mdcontacts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ContactDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        val contact = intent.getParcelableExtra<Contact>("contact")

        contact?.let {
            detailNameTextView.text = it.name
            detailPhoneTextView.text = it.phone
            detailEmailTextView.text = it.email
        }
    }
}
