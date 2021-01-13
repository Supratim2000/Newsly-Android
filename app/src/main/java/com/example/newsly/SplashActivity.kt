package com.example.newsly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import org.w3c.dom.Text

class SplashActivity : AppCompatActivity() {

    private val SPLASH_SCREEN_TIME: Long = 6000
    private val SPLASH_WELCOME_ANIMATION_DURATION: Long = 2000
    private val SPLASH_WELCOME_ANIMATION_REPEAT: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val welcomeTV: TextView = findViewById<TextView>(R.id.welcome_text)

        YoYo.with(Techniques.DropOut).duration(SPLASH_WELCOME_ANIMATION_DURATION).repeat(SPLASH_WELCOME_ANIMATION_REPEAT).playOn(welcomeTV)


        Handler().postDelayed(object: Runnable{
            override fun run() {
                val splashIntent = Intent(applicationContext,MainActivity::class.java)
                startActivity(splashIntent)
                finish()
            }
        },SPLASH_SCREEN_TIME)
    }
}