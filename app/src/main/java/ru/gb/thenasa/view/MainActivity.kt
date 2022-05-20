package ru.gb.thenasa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import ru.gb.thenasa.R
import ru.gb.thenasa.databinding.ActivityMainBinding
import ru.gb.thenasa.model.Const
import ru.gb.thenasa.view.callbacks.ChangeThemeCallback
import ru.gb.thenasa.view.fragments.MainFragment
import ru.gb.thenasa.view.fragments.PictureFromTheMarsFragment
import ru.gb.thenasa.view.fragments.SettingsFragment

class MainActivity : AppCompatActivity(),
    NavigationBarView.OnItemSelectedListener, ChangeThemeCallback {
    private val fragmentsMap: HashMap<String, Fragment> = HashMap()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCustomTheme()
        setContentView(binding.root)
        init()
        setMainFragment()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_main -> setMainFragment()
            R.id.action_settings -> setSettingsFragment()
            R.id.action_favorites -> setFavoritesFragment()
            R.id.action_mars -> setPictureFromTheMarsFragment()
        }
        return true
    }

    private fun init() {
        binding.bnv.setOnItemSelectedListener(this)
        fragmentsMap.apply {
            put(Const.NasaAppFragmentsNames.MAIN_FRAGMENT, MainFragment())
            put(Const.NasaAppFragmentsNames.SETTINGS_FRAGMENT, SettingsFragment())
            put(Const.NasaAppFragmentsNames.MARTIAN_PICTURES_FRAGMENT, PictureFromTheMarsFragment())
        }
    }

    private fun setCustomTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themeId = sharedPreferences.getInt(Const.THEME_ID, Const.DEFAULT_THEME)
        when (themeId) {
            Const.DEFAULT_THEME -> setTheme(R.style.Theme_TheNasa)
            Const.MOON_THEME -> setTheme(R.style.Theme_TheNasa_Moon)
            Const.MARTIAN_THEME -> setTheme(R.style.Theme_TheNasa_Martian)
        }

    }

    private fun setFavoritesFragment() {

    }


    private fun setSettingsFragment() {
        fragmentsMap[Const.NasaAppFragmentsNames.SETTINGS_FRAGMENT]?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, it, Const.NasaAppFragmentsNames.SETTINGS_FRAGMENT)
                .addToBackStack(Const.NasaAppFragmentsNames.SETTINGS_FRAGMENT)
                .commit()
        }
    }

    private fun setMainFragment() {
        fragmentsMap[Const.NasaAppFragmentsNames.MAIN_FRAGMENT]?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, it, Const.NasaAppFragmentsNames.MAIN_FRAGMENT)
                .addToBackStack(Const.NasaAppFragmentsNames.MAIN_FRAGMENT)
                .commit()
        }
    }

    private fun setPictureFromTheMarsFragment() {
        fragmentsMap[Const.NasaAppFragmentsNames.MARTIAN_PICTURES_FRAGMENT]?.let {
            if(!it.isVisible){
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_holder,
                        it,
                        Const.NasaAppFragmentsNames.MARTIAN_PICTURES_FRAGMENT
                    )
                    .addToBackStack(Const.NasaAppFragmentsNames.MARTIAN_PICTURES_FRAGMENT)
                    .commit()
            }
        }
    }


    override fun changeTheme() {
        recreate()
    }




}
