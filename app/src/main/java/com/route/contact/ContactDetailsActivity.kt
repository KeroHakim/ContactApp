package com.route.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.IntentCompat
import com.route.contact.databinding.ActivityContactDetailsBinding
import dataClass.Constant
import dataClass.ContactData
//01287201027 01287201027 //01287201027
class ContactDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contact: ContactData? = intent.getParcelableExtra(Constant.CONTANT)
        binding.contactNameDetails.text = contact?.name
        binding.phoneNumberDetails.text = contact?.phone
        binding.descriptionDetails.text = contact?.description
    }
}