package com.example.mdcontacts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mdcontacts.databinding.ActivityAddEditBinding
import java.io.File

class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            saveContact()
        }
    }

    private fun saveContact() {
        val name = binding.editTextName.text.toString().trim()
        val phone = binding.editTextPhone.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()

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
