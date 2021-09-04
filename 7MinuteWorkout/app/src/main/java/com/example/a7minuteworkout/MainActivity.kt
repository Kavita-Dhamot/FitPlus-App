package com.example.a7minuteworkout
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sawolabs.androidsdk.Sawo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llStart.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        llBMI.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        llHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

//        login.setOnClickListener {
//            Sawo(
//                this,
//                "08644ca7-b17a-43c2-aa9a-8c32272ae8a7", // your api key
//                "613289082f5bde7b000739bfWwrys0eKRGUwb8bSHpjMHebD"  // your api key secret
//            ).login(
//                "email", // can be one of 'email' or 'phone_number_sms'
//                LoginActivity::class.java.name // Callback class name
//            )
//        }

        diet.setOnClickListener {
            val intent = Intent(this, DietActivity::class.java)
            startActivity(intent)
        }

        age.setOnClickListener {
            val intent = Intent(this, AgeActivity::class.java)
            startActivity(intent)
        }
        ex.setOnClickListener {
            val intent = Intent(this, ExerciseDashboard::class.java)
            startActivity(intent)
        }

        stepc.setOnClickListener {
            val intent = Intent(this, StepCounter::class.java)
            startActivity(intent)
        }

    }

}