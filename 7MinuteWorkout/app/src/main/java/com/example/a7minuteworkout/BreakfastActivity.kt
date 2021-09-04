package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_breakfast.*

class BreakfastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakfast)
        val actionBar = supportActionBar

        actionBar !!.title = "Breakfast Plan"

        actionBar.setDisplayHomeAsUpEnabled(true)


        animation1.setAnimation("animation2.json")
        animation1.playAnimation()
        animation1.loop(true)


    }
}