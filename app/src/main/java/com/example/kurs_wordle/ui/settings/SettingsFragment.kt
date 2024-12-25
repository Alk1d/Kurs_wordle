package com.example.kurs_wordle.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kurs_wordle.databinding.FragmentSettingsBinding
import kotlin.jvm.internal.Intrinsics.Kotlin

class SettingsFragment : Fragment() {

private var _binding: FragmentSettingsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private val settingsModel: SettingsViewModel by activityViewModels()
    var checkedId = "rdradioBtn_normal"

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentSettingsBinding.inflate(inflater, container, false)
    val root: View = binding.root

    return root
  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.radioGroup.setOnCheckedChangeListener{ _, checkedId ->
            val selectedDifficulty = when (checkedId) {
                binding.radioBtnTest.id -> 0
                binding.radioBtnNormal.id -> 1
                binding.radioBtnHard.id -> 2
                else -> 1
            }
            settingsModel.settings.value = selectedDifficulty
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}