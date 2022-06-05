package ru.gb.thenasa.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.squareup.picasso.Picasso
import ru.gb.thenasa.R
import ru.gb.thenasa.databinding.FragmentMartianPictureBinding
import ru.gb.thenasa.model.appstates.PictureFromTheMarsState
import ru.gb.thenasa.model.pojo.ResultPicturesFromTheMars
import ru.gb.thenasa.viewmodel.PictureFromTheMarsViewModel
import kotlin.random.Random

class PictureFromTheMarsFragment : Fragment() {
    private val PIC_DATE = "Picture date"
    private val viewModel: PictureFromTheMarsViewModel by lazy {
        ViewModelProvider(this)[PictureFromTheMarsViewModel::class.java]
    }
    var _binding: FragmentMartianPictureBinding? = null
    val binding: FragmentMartianPictureBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMartianPictureBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.getPicturesFromTheMars()
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is PictureFromTheMarsState.Loading -> uiLoadingState()
                    is PictureFromTheMarsState.Success -> uiSuccessState(uiState.result)
                    is PictureFromTheMarsState.Error -> errorState(uiState.error)
                }
            }
        }
    }

    private fun errorState(error: Throwable) {
        error.printStackTrace()
    }

    @SuppressLint("SetTextI18n")
    private fun uiSuccessState(resultPictures: ResultPicturesFromTheMars) {
        binding.martianFragmentProgressBar.visibility = View.GONE
        resultPictures.result!!.get(Random.nextInt(0, 835)).apply {
            binding.martianFragmentDateTextView.text = PIC_DATE+date
            Picasso.get().load(imageUrl)
                .error(R.drawable.ic_error_placeholder)
                .into(binding.imgMartianPic)
        }

    }

    private fun uiLoadingState() {
        binding.martianFragmentProgressBar.visibility = View.VISIBLE
    }
}