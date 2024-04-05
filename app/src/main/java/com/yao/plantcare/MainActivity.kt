package com.yao.plantcare

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.room.Room
import com.yao.plantcare.AlarmNotification.Companion.NOTIFICATION_ID
import com.yao.plantcare.database.AllDatabase
import com.yao.plantcare.database.AllRepository
import com.yao.plantcare.database.AllViewModel
import com.yao.plantcare.databinding.ActivityMainBinding
import com.yao.plantcare.homework.HomeworkFragment
import com.yao.plantcare.my_plants.MyPlantsFragment
import com.yao.plantcare.search.SearchFragment
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeworkFragment())
        createChannel()

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_homework -> replaceFragment(HomeworkFragment())
                R.id.nav_my_plants -> replaceFragment(MyPlantsFragment())
                R.id.nav_search -> replaceFragment(SearchFragment())

                else ->{

                }
            }
            true
        }

        val db = Room.databaseBuilder(this, AllDatabase::class.java, "all_db").build()
        val allDao = db.AllDao()
        val repository = AllRepository(allDao)
        val viewModel = AllViewModel(repository)

        viewModel.readIrrigationMyPlant().observe(this, Observer { plant ->
            if (!plant.isEmpty()) scheduleNotification()
        })

        binding.changeLanguage.setOnClickListener {
            replaceFragment(ChangeLanguageFragment())
        }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment, fragment)
        fragmentTransaction.commit()
    }


    private fun scheduleNotification() {
        val intent = Intent(applicationContext, AlarmNotification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //si pongo setExact no funciona
        alarmManager.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().timeInMillis + 10000, pendingIntent)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MyChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "notification"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object{
        const val MY_CHANNEL_ID = "myChannel"
    }

}