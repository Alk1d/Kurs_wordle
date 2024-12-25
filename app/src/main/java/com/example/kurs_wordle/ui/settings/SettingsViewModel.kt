package com.example.kurs_wordle.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    val settings: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
}