package ru.gb.thenasa.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.transition.*
import com.squareup.picasso.Picasso
import ru.gb.thenasa.R
import ru.gb.thenasa.databinding.ItemViewPagerApodBinding
import ru.gb.thenasa.model.Const
import ru.gb.thenasa.model.appstates.PictureOfTheDayState
import ru.gb.thenasa.viewmodel.PictureOfTheDayViewModel

class ApodViewPagerFragment : Fragment() {
    private var isExpanded = true
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
        binding.apodTitle.apply {
            val span = SpannableString(text)
            span.setSpan(
                BulletSpan(40, Color.WHITE),
                0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            text = span
            Log.e("span", span.length.toString())
        }
        binding.apodTitle.setOnClickListener {
            if (isExpanded) showApodDescription() else
                hideApodDescription()
        }
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

    private fun showApodDescription() {
        isExpanded = isExpanded.not()
        val viewGroup = binding.vpNestedScrollView
        val set = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(Fade())
            duration = 2000
            interpolator = AnticipateOvershootInterpolator(1.0f)
        }
        TransitionManager.beginDelayedTransition(viewGroup, set)
        val constraintLayout: ConstraintLayout = binding.apodDescriptionConstraint
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(
                R.id.vp_text_view_apod_description, ConstraintSet.TOP,
                R.id.apod_title, ConstraintSet.BOTTOM
            )
            applyTo(constraintLayout)
        }
    }

    private fun hideApodDescription() {
        isExpanded = isExpanded.not()
        val viewGroup = binding.vpNestedScrollView
        val constraintLayout: ConstraintLayout = binding.apodDescriptionConstraint
        val set = TransitionSet().apply {
            addTransition(ChangeBounds())
            addTransition(Fade())
            duration = 2000
            interpolator = AnticipateOvershootInterpolator(1.0f)
        }
        TransitionManager.beginDelayedTransition(viewGroup, set)
        ConstraintSet().apply {
            clone(constraintLayout)
            connect(
                R.id.vp_text_view_apod_description, ConstraintSet.TOP,
                R.id.apod_description_constraint, ConstraintSet.BOTTOM
            )
            applyTo(constraintLayout)
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
        val tv = requireActivity().findViewById<TextView>(R.id.vp_text_view_apod_description)
        binding.vpTextViewApodDescription.text = explanation

    }

    private fun UiLoadingState() {
        binding.vpProgressBar.visibility = View.VISIBLE
    }

}