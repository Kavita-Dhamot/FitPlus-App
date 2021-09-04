package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_diet.*

class DietActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        break1.setOnClickListener{
            val intent = Intent(this, BreakfastActivity::class.java)
            startActivity(intent)
        }

        break2.setOnClickListener{
            val intent = Intent(this, LunchActivity::class.java)
            startActivity(intent)
        }


        break3.setOnClickListener{
            val intent = Intent(this, DinnerActivity::class.java)
            startActivity(intent)
        }


    }
}