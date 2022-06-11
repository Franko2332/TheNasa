package ru.gb.thenasa.view.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.gb.thenasa.R
import ru.gb.thenasa.databinding.FragmentSettingsBinding
import ru.gb.thenasa.model.Const
import ru.gb.thenasa.view.MainActivity
import ru.gb.thenasa.view.callbacks.ChangeThemeCallback

class SettingsFragment : Fragment() {
    var binding: FragmentSettingsBinding? = null
    val _binding: FragmentSettingsBinding get() = binding!!
    lateinit var changeThemeCallback: ChangeThemeCallback


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        changeThemeCallback = requireActivity() as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab).apply {
            setOnClickListener(object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    Log.e("animate", "anim")
                    ObjectAnimator.ofFloat(_binding.imgSettings, "rotation", 90f).start()
                }

            })
        }
        when (PreferenceManager.getDefaultSharedPreferences(requireActivity())
            .getInt(Const.THEME_ID, Const.DEFAULT_THEME)) {
            Const.DEFAULT_THEME -> _binding.themesRadioGroup.check(R.id.radio_button_default_theme)
            Const.MOON_THEME -> _binding.themesRadioGroup.check(R.id.radio_button_moon_theme)
            Const.MARTIAN_THEME -> _binding.themesRadioGroup.check(R.id.radio_button_martian_theme)
        }
        _binding.radioButtonDefaultTheme.setOnClickListener {
            setDefualtTheme()
        }
        _binding.radioButtonMoonTheme.setOnClickListener {
            setMoonTheme()
        }
        _binding.radioButtonMartianTheme.setOnClickListener {
            setMartianTheme()
        }
        _binding.motionContainer.transitionToEnd()
    }

    private fun setDefualtTheme() {
        requireActivity().let {
            PreferenceManager.getDefaultSharedPreferences(it).edit()
                .putInt(Const.THEME_ID, Const.DEFAULT_THEME).apply()
        }
        changeThemeCallback.changeTheme()

    }

    private fun setMoonTheme() {
        requireActivity().let {
            PreferenceManager.getDefaultSharedPreferences(it).edit()
                .putInt(Const.THEME_ID, Const.MOON_THEME).apply()
        }
        changeThemeCallback.changeTheme()
    }

    private fun setMartianTheme() {
        requireActivity().let {
            PreferenceManager.getDefaultSharedPreferences(it).edit()
                .putInt(Const.THEME_ID, Const.MARTIAN_THEME).apply()
        }
        changeThemeCallback.changeTheme()
    }

}