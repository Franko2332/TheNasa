package ru.gb.thenasa.view.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.gb.thenasa.view.fragments.ApodViewPagerFragment

class ViewPagerApodAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private var _items = ArrayList<String>()

    fun setItems(items: ArrayList<String>) {
        _items = items
    }

    override fun getItemCount(): Int = _items.size

    override fun createFragment(position: Int): Fragment {
        Log.e("item_position", _items[position])
        return ApodViewPagerFragment.getInstance(_items[position])
    }
}