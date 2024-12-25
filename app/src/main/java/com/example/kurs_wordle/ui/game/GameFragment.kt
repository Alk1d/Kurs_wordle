package com.example.kurs_wordle.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBindings
import com.example.kurs_wordle.MainActivity
import com.example.kurs_wordle.R
import com.example.kurs_wordle.databinding.FragmentGameBinding
import com.example.kurs_wordle.ui.settings.SettingsFragment
import com.example.kurs_wordle.ui.settings.SettingsViewModel


class GameFragment : Fragment() {

private var _binding: FragmentGameBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

    private lateinit var texts:  MutableList<MutableList<TextView>>
    private val rowCount = 7
    private val colCount = 5
    private var countGames = 0
    private var countWins = 0
    private var difficulty = 1
    private lateinit var gameCore: GameCore
    private val settingsModel: SettingsViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentGameBinding.inflate(inflater, container, false)
    val root: View = binding.root

    gameCore = GameCore(rowCount)

    return root
  }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    settingsModel.settings.observe(activity as LifecycleOwner, {
      difficulty = it
    })
    setEventListeners()
    initTexts()
    newRound(difficulty)
  }

  private fun setEventListeners() {
    for (c in 90 downTo 65) {

      val resID = resources.getIdentifier("button${c.toChar()}", "id", activity?.packageName)
      val btn = ViewBindings.findChildViewById<Button>(view, resID)

     /* val btn = when (c) {
        65 -> binding.buttonA
        66 -> binding.buttonB
        67 -> binding.buttonC
        68 -> binding.buttonD
        69 -> binding.buttonE
        70 -> binding.buttonF
        71 -> binding.buttonG
        72 -> binding.buttonH
        73 -> binding.buttonI
        74 -> binding.buttonJ
        75 -> binding.buttonK
        76 -> binding.buttonL
        77 -> binding.buttonM
        78 -> binding.buttonN
        79 -> binding.buttonO
        80 -> binding.buttonP
        81 -> binding.buttonQ
        82 -> binding.buttonR
        83 -> binding.buttonS
        84 -> binding.buttonT
        85 -> binding.buttonU
        86 -> binding.buttonV
        87 -> binding.buttonW
        88 -> binding.buttonX
        89 -> binding.buttonY
        90 -> binding.buttonZ
        else -> null
      }

      */

      btn?.setOnClickListener {
        if (gameCore.isPause()) {
          gameCore.startOver()
          newRound(difficulty)
        }
        val row = gameCore.getCurRow()
        val col = gameCore.getCurCol()
        if (gameCore.setNextChar(c.toChar())) {
          texts[row][col].text = c.toChar().toString()
        }
      }
    }

    val btnEnter = binding.buttonEnter
    btnEnter.setOnClickListener {
      if (gameCore.isPause()) {
        gameCore.startOver()
        newRound(difficulty)
      }
      val row = gameCore.getCurRow()
      if (gameCore.enter()) {
        for (col in 0 until colCount) {
          val id = when (gameCore.validateChar(row, col)) {
            gameCore.IN_WORD -> {
              R.drawable.letter_in_word
            }

            gameCore.IN_PLACE -> {
              R.drawable.letter_in_place
            }

            else -> {
              R.drawable.letter_not_in
            }
          }

          texts[row][col].background = ContextCompat.getDrawable(requireActivity(), id)
        }
        if (gameCore.getResult()) {
          countWins++
        }
      }
    }

    val btnErase = binding.buttonErase
    btnErase.setOnClickListener {
      if (gameCore.isPause()) {
        gameCore.startOver()
        newRound(difficulty)
      }
      gameCore.erase()
      val row = gameCore.getCurRow()
      val col = gameCore.getCurCol()
      texts[row][col].text = " "
    }
  }

  private fun initTexts() {
    texts = MutableList(rowCount) { mutableListOf() }
    for (row in 0 until rowCount) {
      for (col in 0 until colCount) {
        val resID = resources.getIdentifier("text${col + 1}col${row + 1}row", "id", activity?.packageName)
        val txtView = ViewBindings.findChildViewById<TextView>(view, resID)
        if (txtView != null) {
          texts[row].add(txtView)
        }
      }
    }
  }

  private fun newRound(difficulty: Int) {
    when(difficulty) {
      0 -> gameCore.setWordTest()
      1 -> gameCore.setWordNormal()
      2 -> gameCore.setWordHard()
      else -> gameCore.setWordNormal()
    }
    for (row in 0 until rowCount) {
      for (col in 0 until colCount) {
        texts[row][col].background =
          ContextCompat.getDrawable(requireActivity(), R.drawable.letter_border)
        texts[row][col].text = " "
      }
    }
    val textGames = binding.games
    val textWins = binding.wins

    textGames.text = "Games: $countGames"
    textWins.text = "Wins: $countWins"
    countGames++

    Log.e("Word", "=============---- ${gameCore.getFinalWord()}")
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
