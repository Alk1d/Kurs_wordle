package com.example.kurs_wordle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kurs_wordle.databinding.ActivityMainBinding
import com.example.kurs_wordle.ui.game.GameFragment
import com.example.kurs_wordle.ui.settings.SettingsFragment
import com.example.kurs_wordle.ui.settings.SettingsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val settingsModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        loadFragment(GameFragment())
        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_game -> {
                    loadFragment(GameFragment())
                    true
                }

                R.id.navigation_settings -> {
                    createAlertDialog()
                    true
                }
                else -> {false}
            }
        }
        settingsModel.settings.observe(this, {

        })
    }

    private fun createAlertDialog()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Warning")
        builder.setMessage("Going into settings will reset your game!")
        builder.setPositiveButton("Settings") { dialogInterface, i ->
            loadFragment(SettingsFragment())
        }
        builder.setNegativeButton("Return") { dialogInterface, i ->
        binding.navView.selectedItemId = R.id.navigation_game
        }
        builder.show()
    }

    private  fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
        .replace(R.id.container,fragment)
        .commit()
    }
}