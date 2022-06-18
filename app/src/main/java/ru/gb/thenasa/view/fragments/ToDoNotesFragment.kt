package ru.gb.thenasa.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gb.thenasa.databinding.FragmentToDoNotesBinding
import ru.gb.thenasa.view.adapters.ItemTouchHelperCallback
import ru.gb.thenasa.view.adapters.ToDoNotesAdapter
import ru.gb.thenasa.view.callbacks.SetEditNoteFragmentCallback
import ru.gb.thenasa.viewmodel.ToDoNotesViewModel
import ru.gb.thenasa.viewmodel.itemviewmodel.ItemViewModel

class ToDoNotesFragment : Fragment(), ToDoNotesAdapter.OnItemClickListener {
    var _adapter = ToDoNotesAdapter()
    var binding: FragmentToDoNotesBinding? = null
    val _binding: FragmentToDoNotesBinding get() = binding!!
    lateinit var callback: SetEditNoteFragmentCallback
    val observer: Observer<MutableList<ItemViewModel>> by lazy {
        Observer<MutableList<ItemViewModel>> { x -> render(x) }
    }
    val viewModel: ToDoNotesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ToDoNotesViewModel::class.java)
    }

    private fun render(x: MutableList<ItemViewModel>) {
        _adapter.setOnClickListener(this)
        _adapter.setData(x)
        binding!!.toDoNotesRecyclerView.adapter = _adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoNotesBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        callback = requireActivity() as SetEditNoteFragmentCallback
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding.fab.setOnClickListener {
            callback.setEditNoteFragment()
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        _binding.toDoNotesRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            adapter = _adapter
        }
        ItemTouchHelper(ItemTouchHelperCallback({_adapter.itemRemoved(it)}))
            .attachToRecyclerView(_binding.toDoNotesRecyclerView)
    }

    override fun onItemClick(_id: Int) {
        Log.e("card_view", _id.toString())
        callback.setEditNoteFragment(_id)
    }
}