package com.example.mdcontacts

import android.content.Context
import org.commonmark.parser.Parser
import org.commonmark.node.*
import java.io.InputStream

class ContactRepository(private val context: Context) {

    fun getContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val parser = Parser.builder().build()

        try {
            val contactFiles = context.assets.list("contacts")
            if (contactFiles != null) {
                for (fileName in contactFiles) {
                    val inputStream: InputStream = context.assets.open("contacts/$fileName")
                    val document = parser.parse(inputStream.bufferedReader().use { it.readText() })
                    val contact = parseContact(document)
                    if (contact != null) {
                        contacts.add(contact)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return contacts
    }

    private fun parseContact(document: Node): Contact? {
        var name: String? = null
        var phone: String? = null
        var email: String? = null

        document.accept(object : AbstractVisitor() {
            override fun visit(heading: Heading) {
                if (heading.level == 1 && heading.firstChild is Text) {
                    name = (heading.firstChild as Text).literal
                }
            }

            override fun visit(listItem: ListItem) {
                val text = (listItem.firstChild?.firstChild as? Text)?.literal ?: ""
                when {
                    text.startsWith("Phone: ") -> phone = text.substringAfter("Phone: ")
                    text.startsWith("Email: ") -> email = text.substringAfter("Email: ")
                }
            }
        })

        return if (name != null && phone != null && email != null) {
            Contact(name!!, phone!!, email!!)
        } else {
            null
        }
    }
}
