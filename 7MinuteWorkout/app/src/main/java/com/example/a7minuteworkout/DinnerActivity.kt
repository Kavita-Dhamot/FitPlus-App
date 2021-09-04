package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dinner.*

class DinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinner)

        val actionBar = supportActionBar

        actionBar !!.title = "Dinner Plan"

        actionBar.setDisplayHomeAsUpEnabled(true)

        animation3.setAnimation("gif1.json")
        animation3.playAnimation()
        animation3.loop(true)

    }
}