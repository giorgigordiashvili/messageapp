package com.example.messageapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.messageapp.databinding.ActivitySendMessageBinding

class SendMessageActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySendMessageBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        sendButton.setOnClickListener {
            val message = messageEditText.text?.toString()
            val recipient = recipientEditText.text?.toString()
            if (messageNotEmpty(message) &&
                validNumber(recipient) &&
                checkMessageLength(message!!)
            ) {
                navigateToMessageActivity(recipient!!, message)
            } else {
                showError()
            }
        }
    }

    private fun messageNotEmpty(message: String?) = message.isNullOrBlank().not()

    private fun validNumber(number: String?): Boolean {
        if (number.isNullOrBlank()) return false
        number.forEach {
            if (it.isWhitespace()) return@forEach
            if (it.isDigit().not()) return false
        }
        return true
    }

    private fun checkMessageLength(message: String) = message.length < MESSAGE_MAX_LENGTH


    private fun navigateToMessageActivity(recipient: String, message: String) {
        startActivity(
            Intent(this, MessageActivity::class.java).apply {
                putExtra(NUMBER_EXTRA_KEY, recipient)
                putExtra(MESSAGE_EXTRA_KEY, message)
            }
        )
    }

    private fun showError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }

    companion object {
        const val NUMBER_EXTRA_KEY = "number_extra"
        const val MESSAGE_EXTRA_KEY = "message_extra"
        private const val MESSAGE_MAX_LENGTH = 250
    }
}