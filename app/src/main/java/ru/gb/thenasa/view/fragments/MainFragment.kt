package ru.gb.thenasa.view.fragments

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.gb.thenasa.R
import ru.gb.thenasa.databinding.FragmentApodBinding
import ru.gb.thenasa.view.adapters.ViewPagerApodAdapter
import ru.gb.thenasa.viewmodel.PictureOfTheDayViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList

class MainFragment: Fragment() {
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(requireActivity())[PictureOfTheDayViewModel::class.java]
    }
    private var binding: FragmentApodBinding? = null
    private val _binding: FragmentApodBinding get() = binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApodBinding.inflate(inflater, container, false)
        return _binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val items = ArrayList<String>()
        for (i in 0..100) {
            items.add(formatter.format(date.minusDays(i.toLong())))
        }
        val adapter = ViewPagerApodAdapter(this).apply {
            setItems(items)
            _binding.viewPagerApod.adapter = this
        }
    }
}