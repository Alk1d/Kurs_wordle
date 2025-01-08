package com.example.kurs_wordle.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val _countWins: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val _countGames: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    }