package com.route.contact

import adapter.ContactAdapter
import android.content.Intent
import dataClass.ContactData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.route.contact.databinding.ActivityMainBinding
import dataClass.Constant

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var name: String
    lateinit var phone: String
    lateinit var description : String
    lateinit var adapter: ContactAdapter
    val contactsList = mutableListOf<ContactData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactRecyclerView()
        saveButton()
        adapter.onContactItemClickListener = ContactAdapter.OnContactItemClickListener {
            var intent = Intent(this@MainActivity, ContactDetailsActivity::class.java)
            intent.putExtra(Constant.CONTANT, it)
            startActivity(intent)
        }
    }

    private fun saveButton() {
        binding.btnSave.setOnClickListener {
            if (!validateTextFields()) {
                return@setOnClickListener
            }
            name = binding.nameEditText.text?.trim().toString()
            phone = binding.phoneEditText.text?.trim().toString()
            description = binding.descriptionEditText.text?.trim().toString()
            val contact = ContactData(name, phone, description)
            adapter.addContact(contact)
            binding.nameEditText.text = null
            binding.phoneEditText.text = null
            binding.descriptionEditText.text = null
        }
    }
    private fun contactRecyclerView(){
        adapter = ContactAdapter(contactsList)
        binding.contactRv.adapter = adapter
    }
    fun validateTextFields():Boolean{
        val name = binding.nameEditText.text?.trim().toString()
        val phone = binding.phoneEditText.text?.trim().toString()
        binding.nameLayout.error = nameValidation(name)
        binding.phoneLayout.error = phoneValidation(phone)
        return binding.nameLayout.error == null && binding.phoneLayout.error == null
    }

    private fun nameValidation(name:String) : String? {
        if(name.isEmpty()) return "Required"
        if(name.trim().length<3) return "Name can't be less than 3 characters"
        return null
    }
    private fun phoneValidation(phone: String): String? {
        if (phone.isEmpty()) return "Required"
        if (!phone.matches(Regex("\\d+"))) return "Phone number must contain only numbers"
        if (phone.length != 11) return "Phone number must be 11 numbers"
        return null
    }

}