package com.example.newsly

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class MainActivity : AppCompatActivity() {

    private val businessUrl: String = "https://saurav.tech/NewsAPI/top-headlines/category/business/in.json"
    private val entertainmentUrl: String = "https://saurav.tech/NewsAPI/top-headlines/category/entertainment/in.json"
    private val generalUrl: String = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
    private val healthUrl: String = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
    private val scienceUrl: String = "https://saurav.tech/NewsAPI/top-headlines/category/science/in.json"
    private val sportsUrl: String = "https://saurav.tech/NewsAPI/top-headlines/category/sports/in.json"
    private val technologyUrl: String = "https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json"
    private val CHANNEL_ID: String = "First Channel"
    private val CHANNEL_NAME: String = "App First Channel"

    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure?")
        builder.setMessage("Do you want to close the Newsly app?")
        builder.setPositiveButton("Yes" ) { dialogue, which ->

            val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel: NotificationChannel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH)
                channel.apply {
                    enableLights(true)
                    enableVibration(true)
                    description = "This is my created channel"
                }
                manager.createNotificationChannel(channel)
            }

            val intent: Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:pikucode15@gmail.com"))
            val pi: PendingIntent = PendingIntent.getActivity(this,123,intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val myNotification: Notification = NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Thanks for using newsly app")
                .setContentText("Click on this notification for sending feedback")
                .setSmallIcon(R.drawable.ic_article)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
                .setContentIntent(pi)
                .setOngoing(false)
                .setAutoCancel(true)
                .build()

            manager.notify(1,myNotification)

            finish()
        }
        builder.setNegativeButton("No") { dialogue, which -> null }
        builder.setCancelable(false)
        builder.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val root_layout: ConstraintLayout = findViewById<ConstraintLayout>(R.id.root_view)
        val catagort_text: TextView = findViewById<TextView>(R.id.catagoryTV)

        val animationDrawable: AnimationDrawable =  root_layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        YoYo.with(Techniques.Bounce).duration(1500).repeat(2).playOn(catagort_text)

        val businessBtn: Button = findViewById<Button>(R.id.businessBtn)
        val entertainmentBtn: Button = findViewById<Button>(R.id.entertainmentBtn)
        val generalBtn: Button = findViewById<Button>(R.id.generalBtn)
        val healthBtn: Button = findViewById<Button>(R.id.healthBtn)
        val scienceBtn: Button = findViewById<Button>(R.id.scienceBtn)
        val sportsBtn: Button = findViewById<Button>(R.id.sportsBtn)
        val technologyBtn: Button = findViewById<Button>(R.id.technologyBtn)

        val intent: Intent = Intent(applicationContext,NewsActivity::class.java)

        businessBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intent.putExtra("selectedUrl",businessUrl)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_left_out)
            }
        })

        entertainmentBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intent.putExtra("selectedUrl",entertainmentUrl)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out)
            }
        })

        generalBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intent.putExtra("selectedUrl",generalUrl)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_left_out)
            }
        })

        healthBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intent.putExtra("selectedUrl",healthUrl)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out)
            }
        })

        scienceBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intent.putExtra("selectedUrl",scienceUrl)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_left_out)
            }
        })

        sportsBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intent.putExtra("selectedUrl",sportsUrl)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out)
            }
        })

        technologyBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                intent.putExtra("selectedUrl",technologyUrl)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_left_out)
            }
        })
    }
}