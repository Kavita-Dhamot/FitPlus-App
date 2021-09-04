package com.example.a7minuteworkout
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_custom_dialog_confirmation.*
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener {

    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var exerciseTimer:CountDownTimer?=null
    private var exerciseProgress=0

    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition=-1

    private var tts:TextToSpeech?=null
    private var player:MediaPlayer?=null

    private var exerciseAdapter:ExerciseStatusAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar=supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        toolbar_exercise_activity.setNavigationOnClickListener {
        customDialogForBackButton()
        }

        skip.setOnClickListener {

            currentExercisePosition++
            if(currentExercisePosition< exerciseList?.size!! - 1)
            {
                setupRestView()
            }
            else
            {
                finish()
                val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                startActivity(intent)
            }

        }

        tts= TextToSpeech(this,this)

        exerciseList=Constants.defaultExerciseList()
        setupRestView()
        setUpExerciseStatusRecyclerView()
    }

    override fun onInit(status: Int) {

        if(status==TextToSpeech.SUCCESS)
        {
            val result=tts!!.setLanguage(Locale.US)
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED)
                Log.e("TTS","The specified language is not supported!")
        }
        else
        {
            Log.e("TTS","Initialization failed!")
        }
    }

    private fun speakOut(text:String)
    {
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

    }

    public override fun onDestroy() {
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0
        }
        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }

        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player!=null)
        {
            player!!.stop()
        }
        super.onDestroy()

    }

    private fun setRestProgressBar()
    {
        progressBar.progress=restProgress
        restTimer=object:CountDownTimer(10000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress=10-restProgress
                tvTimer.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()               //Toast.makeText(this@ExerciseActivity,"Here now we will start exercise",Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun setExerciseProgressBar()
    {
        progressBarExercise.progress=exerciseProgress
        exerciseTimer=object:CountDownTimer(30000,1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBarExercise.progress=30-exerciseProgress
                tvExerciseTimer.text=(30-exerciseProgress).toString()

            }

            override fun onFinish() {
                if(currentExercisePosition< exerciseList?.size!! - 1)
                {
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }
                else
                {
                        finish()
                    val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setupRestView()
    {

        try {

            player = MediaPlayer.create(applicationContext, R.raw.wow_sound)
            player!!.isLooping = false
            player!!.start()
        }catch (e:Exception){
            e.printStackTrace()
        }
        llRestView.visibility= View.VISIBLE
        llExerciseView.visibility=View.GONE
        if(restTimer!=null)
        {
            restTimer!!.cancel()
            restProgress=0
        }
        tvUpcomingExerciseName.text=exerciseList!![currentExercisePosition+1].getName()

        setRestProgressBar()
    }

    private fun setupExerciseView()
    {
        llRestView.visibility= View.GONE
        llExerciseView.visibility=View.VISIBLE
        if(exerciseTimer!=null)
        {
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }

        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text=exerciseList!![currentExercisePosition].getName()

        speakOut(exerciseList!![currentExercisePosition].getName())

        setExerciseProgressBar()

    }

    private fun setUpExerciseStatusRecyclerView(){

        rvExerciseStatus.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        exerciseAdapter= ExerciseStatusAdapter(exerciseList!!,this)
        rvExerciseStatus.adapter=exerciseAdapter

    }

    private fun customDialogForBackButton()
    {
        val customDialog=Dialog(this)
        customDialog.setContentView(R.layout.dialog_custom_dialog_confirmation)
        customDialog.tvYes.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener {

            customDialog.dismiss()
        }

        customDialog.show()

    }

}