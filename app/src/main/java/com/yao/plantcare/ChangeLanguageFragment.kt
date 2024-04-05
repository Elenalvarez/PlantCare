package com.yao.plantcare

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yao.plantcare.databinding.FragmentChangeLanguageBinding
import java.util.Locale

class ChangeLanguageFragment : Fragment() {
    private var _binding : FragmentChangeLanguageBinding? = null
    private val binding get() = _binding!!

    var preferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangeLanguageBinding.inflate(inflater, container, false)
        val root = binding.root

        ChangeLanguage(requireContext()).setLanguage()

        preferences = context?.getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE)
        editor= preferences!!.edit()

        val currentLanguage=preferences!!.getString("language", "Spanish")
        if (currentLanguage == "Spanish")
            binding.radioSpanish.isChecked = true
        else if(currentLanguage=="English")
            binding.radioEnglish.isChecked = true
        else
            binding.radioFrench.isChecked = true

        binding.btnChangeLang.setOnClickListener {
            val selectedId = binding.groupRadio.checkedRadioButtonId
            when(selectedId){
                R.id.radio_spanish -> {
                    setLanguage("Spanish", "es")
                }
                R.id.radio_english -> {
                    setLanguage("English", "en")
                }
                R.id.radio_french -> {
                    setLanguage("French", "fr")
                }
            }

        }

        return root
    }

    private fun setLanguage(language: String, languageCode: String) {
        editor?.putString("language", language)
        editor?.commit()
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}