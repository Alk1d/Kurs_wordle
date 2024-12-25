package com.example.kurs_wordle.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    //private val _text = MutableLiveData<String>().apply {  value = "This is game Fragment" }
    private val _countWins = MutableLiveData<Int>().apply { value = 1 }

    //val text: LiveData<String> = _text
    val countGames: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val countWins: LiveData<Int> = _countWins
}