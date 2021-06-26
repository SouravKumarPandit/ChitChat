package com.sourav.chitchat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.sourav.chitchat.databinding.ActivityChitchatBinding

class ChitChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChitchatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)

        binding = ActivityChitchatBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}
