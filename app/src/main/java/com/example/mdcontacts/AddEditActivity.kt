package com.example.mdcontacts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_edit.*
import java.io.File

class AddEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        button_save.setOnClickListener {
            saveContact()
        }
    }

    private fun saveContact() {
        val name = edit_text_name.text.toString().trim()
        val phone = edit_text_phone.text.toString().trim()
        val email = edit_text_email.text.toString().trim()

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val contact = Contact(name, phone, email)
        writeContactToFile(contact)

        Toast.makeText(this, "Contact saved!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun writeContactToFile(contact: Contact) {
        val fileName = contact.name.toLowerCase().replace(" ", "-") + ".md"
        val fileContent = "# ${contact.name}\n\n- Phone: ${contact.phone}\n- Email: ${contact.email}\n"

        try {
            val file = File(filesDir, fileName)
            file.writeText(fileContent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error saving contact", Toast.LENGTH_SHORT).show()
        }
    }
}
