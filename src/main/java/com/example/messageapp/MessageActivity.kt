package com.example.messageapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.messageapp.databinding.ActivityMessageBinding

class MessageActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMessageBinding.inflate(layoutInflater) }
    private lateinit var recipientNumber: String
    private lateinit var message: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recipientNumber = intent.getStringExtra(SendMessageActivity.NUMBER_EXTRA_KEY)!!
        message = intent.getStringExtra(SendMessageActivity.MESSAGE_EXTRA_KEY)!!
        init()
    }

    private fun init() = with(binding) {
        messageEditText.setText(message)
        recipientTextView.text = recipientNumber
        clearButton.setOnClickListener{
            clearFields()
            navigateUp()
        }
    }

    private fun clearFields() = with(binding){
        messageEditText.setText(EMPTY_TEXT)
        recipientTextView.text = EMPTY_TEXT
    }

    private fun navigateUp(){
        navigateUpTo(Intent(this, SendMessageActivity::class.java))
    }

    companion object{
        private const val EMPTY_TEXT = ""
    }
}