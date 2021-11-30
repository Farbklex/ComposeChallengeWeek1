package com.example.androiddevchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.androiddevchallenge.recyclerview.RecyclerViewFragment

class ViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_view_pager
        )
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = DogViewPager1Adapter(supportFragmentManager)
    }

    class DogViewPager1Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(
                DogListFragment(),
                RecyclerViewFragment(),
                EmptyTextFragment()
            )
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

    }
}