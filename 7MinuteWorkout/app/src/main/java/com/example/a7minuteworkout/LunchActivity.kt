package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lunch.*

class LunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)
        val actionBar = supportActionBar
        actionBar !!.title = "Lunch Plan"
        actionBar.setDisplayHomeAsUpEnabled(true)

        animation2.setAnimation("gif4.json")
        animation2.playAnimation()
        animation2.loop(true)

    }
}