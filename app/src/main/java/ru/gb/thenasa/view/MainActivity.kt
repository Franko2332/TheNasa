package ru.gb.thenasa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.gb.thenasa.R
import ru.gb.thenasa.databinding.ActivityMainBinding
import ru.gb.thenasa.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setMainFragment()
    }

    private fun setMainFragment() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_holder, MainFragment(), null)
            .addToBackStack(null).commit()
    }
}