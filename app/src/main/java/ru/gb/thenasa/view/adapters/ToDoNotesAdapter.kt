package ru.gb.thenasa.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.gb.thenasa.R
import ru.gb.thenasa.viewmodel.itemviewmodel.ItemNoteViewModel
import ru.gb.thenasa.viewmodel.itemviewmodel.ItemViewModel


class ToDoNotesAdapter: RecyclerView.Adapter<ToDoNotesAdapter.NotesViewHolder>() {
    private var itemViewModelData: List<ItemViewModel> = emptyList()
    private var onItemClickListener: OnItemClickListener? = null
    private val viewTypeToLayoutId: MutableMap<Int, Int> = mutableMapOf()

    fun setOnClickListener(_onItemClickListener: OnItemClickListener){
        onItemClickListener = _onItemClickListener
    }

    fun setData(items: List<ItemViewModel>){
        itemViewModelData = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoNotesAdapter.NotesViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val layoutId = viewTypeToLayoutId[viewType]
        return ToDoNotesAdapter.NotesViewHolder(inflater.inflate(layoutId ?: 0, parent, false))
    }

    override fun onBindViewHolder(holder: ToDoNotesAdapter.NotesViewHolder, position: Int) {
        holder.bind(itemViewModelData[position] as ItemNoteViewModel)
        var itemNoteViewModel = itemViewModelData[position] as ItemNoteViewModel
        holder.cardView.setOnClickListener { onItemClickListener?.let {it.onItemClick(itemNoteViewModel.dataModel.id!!) }}
    }

    override fun getItemCount(): Int = itemViewModelData.size

    override fun getItemViewType(position: Int): Int {
        val item = itemViewModelData[position]
        if (!viewTypeToLayoutId.containsKey(item.layoutId)){
            viewTypeToLayoutId[item.viewType] = item.layoutId
        }
        return item.viewType
    }

    class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cardView: CardView = itemView.findViewById(R.id.note_card_view)
        val noteTitle: TextView = itemView.findViewById(R.id.note_title)
        val noteDate: TextView = itemView.findViewById(R.id.note_date)

        fun bind (itemNoteViewModel: ItemNoteViewModel){
            noteDate.text = itemNoteViewModel.dataModel.date
            noteTitle.text = itemNoteViewModel.dataModel.title
        }

    }

    interface OnItemClickListener{
       fun onItemClick(_id: Int)
    }
}