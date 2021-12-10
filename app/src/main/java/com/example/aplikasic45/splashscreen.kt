package com.example.aplikasic45

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.aplikasic45.R.id.*
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val textView1 = findViewById<TextView>(anim_text1)
        val textView2 = findViewById<TextView>(anim_text2)
        val progbaranim = findViewById<CircularProgressBar>(progbar_check)
        val textView3 = findViewById<TextView>(anim_text3)
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        val timer = object: CountDownTimer(4500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
//                textView3.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            override fun onFinish() {
                progbaranim.visibility = View.VISIBLE
                textView3.visibility = View.VISIBLE

                progbaranim.startAnimation(animation)
                textView3.startAnimation(animation)

                progbaranim.apply {
                    progressMax = 100f
                    setProgressWithAnimation(100f, 5000)
                }

                val timerlapse = object: CountDownTimer(6000, 1000) {
                    override fun onTick(timermilis: Long) {
//                        textView3.setText("seconds remaining: " + timermilis / 1000);
                    }

                    override fun onFinish() {
                        if (isNetworkAvailable() == true) {
                            textView3.setText("Connected");
                        } else {
                            textView3.setText("Connection Error");
                        }
                    }
                }
                timerlapse.start()
            }
        }
        timer.start()


        textView1.visibility = View.VISIBLE
        textView2.visibility = View.VISIBLE
        progbaranim.visibility = View.INVISIBLE
        textView3.visibility = View.INVISIBLE

        //starting the animation
        textView1.startAnimation(animation)
        textView2.startAnimation(animation)

    }

    private fun isNetworkAvailable():Boolean{
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo =conManager.activeNetworkInfo
        return internetInfo!=null && internetInfo.isConnected
    }
}