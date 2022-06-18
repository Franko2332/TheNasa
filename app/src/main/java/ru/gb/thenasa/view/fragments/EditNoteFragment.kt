package ru.gb.thenasa.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.gb.thenasa.databinding.FragmentEditNoteBinding
import ru.gb.thenasa.model.room.ToDoNoteEntity
import ru.gb.thenasa.view.callbacks.CloseEditNoteCallback
import ru.gb.thenasa.viewmodel.ToDoNotesViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditNoteFragment : Fragment() {
    lateinit var closeFragmentCallback: CloseEditNoteCallback

    val viewModel: ToDoNotesViewModel by lazy {
        ViewModelProvider(requireActivity())[ToDoNotesViewModel::class.java]
    }
    val observer: Observer<ToDoNoteEntity> by lazy { Observer<ToDoNoteEntity> { x -> render(x) } }
    var binding: FragmentEditNoteBinding? = null
    val _binding: FragmentEditNoteBinding get() = binding!!

    companion object {
        private const val NOTE_ENTITY_ID = "NOTE_ENTITY_ID"
        fun getInstance(noteID: Int): EditNoteFragment {
            return EditNoteFragment().apply {
                val bundle = Bundle().apply { putInt(NOTE_ENTITY_ID, noteID) }
                arguments = bundle
            }
        }
    }

    private fun render(data: ToDoNoteEntity?) {
        data?.let {
            _binding.editNoteFragmentTitle.setText(data.title, TextView.BufferType.EDITABLE)
            _binding.editNoteFragmentDescription.setText(
                data.description,
                TextView.BufferType.EDITABLE
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        closeFragmentCallback = requireActivity() as CloseEditNoteCallback
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return _binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            viewModel.getNoteById(it.getInt(NOTE_ENTITY_ID)).observe(viewLifecycleOwner, observer)
        }

        _binding.noteSaveImageView.setOnClickListener {
            if (arguments == null) {
                val sdf = SimpleDateFormat("dd/M/yyyy")
                val currentDate = sdf.format(Date())
                val noteEntity = ToDoNoteEntity(
                    null, _binding.editNoteFragmentTitle.editableText.toString(),
                    _binding.editNoteFragmentDescription.editableText.toString(), currentDate
                )
                viewModel.saveNote(noteEntity)
            } else arguments?.getInt(NOTE_ENTITY_ID)?.let {
                val sdf = SimpleDateFormat("dd/M/yyyy")
                val currentDate = sdf.format(Date())
                val noteEntity = ToDoNoteEntity(
                    it, _binding.editNoteFragmentTitle.editableText.toString(),
                    _binding.editNoteFragmentDescription.editableText.toString(), currentDate
                )
                viewModel.updateNote(noteEntity)
            }
            closeFragmentCallback.closeEditNoteFragment()
        }
    }
}