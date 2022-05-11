package ru.gb.thenasa.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import coil.api.load
import com.squareup.picasso.Picasso
import ru.gb.thenasa.R
import ru.gb.thenasa.databinding.FragmentApodBinding
import ru.gb.thenasa.model.PictureOfTheDayState
import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay
import ru.gb.thenasa.viewmodel.PictureOfTheDayViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.getPictureOfTheDay()
            viewModel.uiState.collect{uiState ->
                when(uiState){
                    is PictureOfTheDayState.Loading -> UiLoadingState()

                    is PictureOfTheDayState.Success -> UiSuccessState(uiState.resultUrl, uiState.explanation)

                    is PictureOfTheDayState.Error -> UiErrorState()
                }
            }
        }
    }

    private fun UiLoadingState() {
        _binding.progressBar.visibility = View.VISIBLE
    }

    private fun UiSuccessState(resultUrl: String?, explanation: String?) {

        _binding.progressBar.visibility = View.GONE
        Picasso.get().load(resultUrl)
            .placeholder(R.drawable.ic_download_placeholder)
            .error(R.drawable.ic_error_placeholder)
            .into(_binding.imageViewAPOD)
        _binding.textViewApodDescription.text = explanation
    }

    private fun UiErrorState() {
        Log.e("error", "error")
    }
}