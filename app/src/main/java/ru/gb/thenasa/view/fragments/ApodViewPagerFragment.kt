package ru.gb.thenasa.view.fragments

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
import ru.gb.thenasa.databinding.ItemViewPagerApodBinding
import ru.gb.thenasa.model.Const
import ru.gb.thenasa.model.appstates.PictureOfTheDayState
import ru.gb.thenasa.viewmodel.PictureOfTheDayViewModel

class ApodViewPagerFragment : Fragment() {
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }
    private var _binding: ItemViewPagerApodBinding? = null
    private val binding: ItemViewPagerApodBinding get() = _binding!!

    companion object {
        fun getInstance(date: String): ApodViewPagerFragment {
            val bundle = Bundle().apply { putString(Const.DATE, date) }
            return ApodViewPagerFragment().apply { this.arguments = bundle }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemViewPagerApodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            arguments?.let { arg ->
                    viewModel.getPictureOfTheDayWithDate(arg.getString(Const.DATE)!!)
            }
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is PictureOfTheDayState.Loading -> UiLoadingState()

                    is PictureOfTheDayState.Success -> UiSuccessState(
                        uiState.resultUrl,
                        uiState.explanation
                    )

                    is PictureOfTheDayState.Error -> UiErrorState()
                }
            }
        }
    }

    private fun UiErrorState() {
        Log.e("error", "error")
    }

    private fun UiSuccessState(resultUrl: String?, explanation: String?) {
        binding.vpProgressBar.visibility = View.GONE
        Picasso.get().load(resultUrl)
            .placeholder(R.drawable.ic_download_placeholder)
            .error(R.drawable.ic_error_placeholder)
            .into(binding.vpImageViewAPOD)
        binding.vpTextViewApodDescription.text = explanation

    }

    private fun UiLoadingState() {
        binding.vpProgressBar.visibility = View.VISIBLE
    }

}