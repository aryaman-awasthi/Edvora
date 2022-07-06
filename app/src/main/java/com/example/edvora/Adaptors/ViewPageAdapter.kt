package com.example.edvora.Adaptors
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.edvora.Fragments.Nearest
import com.example.edvora.Fragments.Past
import com.example.edvora.Fragments.Upcoming

class ViewPageAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                Nearest()
            } 1 -> {
                Past()
            } 2 -> {
                Upcoming()
            } else -> {
                Fragment()
            }
        }
    }
}