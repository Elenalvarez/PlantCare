package com.yao.plantcare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yao.plantcare.databinding.ActivityMainBinding
import com.yao.plantcare.homework.HomeworkFragment
import com.yao.plantcare.my_plants.MyPlantsFragment
import com.yao.plantcare.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeworkFragment())

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
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment, fragment)
        fragmentTransaction.commit()
    }


}