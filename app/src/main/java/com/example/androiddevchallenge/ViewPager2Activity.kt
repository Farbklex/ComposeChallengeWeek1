package com.example.androiddevchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.androiddevchallenge.recyclerview.RecyclerViewFragment

class ViewPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_view_pager2
        )

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
            .apply {
            }
        viewPager.adapter = DogViewPagerStateAdapter(supportFragmentManager, lifecycle)
    }

    class DogViewPagerStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(DogListFragment(),
                RecyclerViewFragment(),
                EmptyTextFragment())
        }

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}