package com.yao.plantcare

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

class ChangeLanguage(private val context: Context) {
    var preferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    fun setLanguage(){
        preferences = context.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        editor= preferences!!.edit()

        var language = "Spanish"
        var languageCode= "es"
        var currentLanguage= "Spanish"

        if (preferences!!.contains("language")){
            currentLanguage = preferences!!.getString("language", "Spanish")!!
            if (currentLanguage=="Spanish"){}
            else if (currentLanguage == "English"){
                language = "English"
                languageCode= "en"
            }else{
                language = "French"
                languageCode= "fr"
            }
        }

        editor?.putString("language", language)
        editor?.commit()

        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)

    }


    fun getLanguage():String{
        val preferences: SharedPreferences = context.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        var language= "spanish"
        if (preferences.contains("language"))
            language = preferences.getString("language", "Spanish")!!.toLowerCase()

        return language
    }
}