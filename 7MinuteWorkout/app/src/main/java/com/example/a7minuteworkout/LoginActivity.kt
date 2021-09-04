package com.example.a7minuteworkout
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sawolabs.androidsdk.LOGIN_SUCCESS_MESSAGE

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val message = intent.getStringExtra(LOGIN_SUCCESS_MESSAGE)
        findViewById<TextView>(R.id.tv).apply {
            text = message!!
        }
// continue with your implementation
    }
}

