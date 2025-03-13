package com.study.sender

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.study.sender.ui.theme.CaseStudySenderTheme
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact (val name:String, val phone:String, val email:String):Parcelable

class MainActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var nameField : EditText
    private lateinit var emailField : EditText
    private lateinit var phoneField : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        nameField = findViewById<EditText>(R.id.nameInput);
        emailField = findViewById<EditText>(R.id.emailInput);
        phoneField = findViewById<EditText>(R.id.phoneInput);

        val sendButton = findViewById<Button>(R.id.sendButton);
        val shareTextButton = findViewById<Button>(R.id.shareTextButton);

        sendButton.setOnClickListener(this);
        shareTextButton.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.sendButton -> {
                val name = nameField.text.toString().trim()
                val email = emailField.text.toString().trim()
                val phone = phoneField.text.toString().trim()

                if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                    val contact = Contact(name, phone, email)
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("Contact", contact)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.shareTextButton -> {
                val name = nameField.text.toString().trim()
                val email = emailField.text.toString().trim()
                val phone = phoneField.text.toString().trim()

                if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                    val contactInfo = "Name: $name\nPhone: $phone\nEmail: $email"
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, contactInfo)
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(sendIntent, "Share Contact via"))
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

