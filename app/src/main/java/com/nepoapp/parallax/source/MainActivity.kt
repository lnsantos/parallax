package com.nepoapp.parallax.source

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.nepoapp.parallax.R
import com.nepoapp.parallax.core.Film
import com.nepoapp.parallax.databinding.ActivityMainBinding
import com.nepoapp.parallax.source.RecyclerViews.adapters.FilmAdapter
import com.nepoapp.parallax.source.RecyclerViews.viewHolder.FilmViewHolder

/**
 * I learned this article, see below
 * https://medium.com/@patrick_iv/add-extra-depth-to-your-list-using-parallax-eddb27b369de
 * **/
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var filmAdapter : FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        adapterConfig()
        recyclerViewConfig()
    }

    private fun recyclerViewConfig(){
        binding.recycler.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

            /**
            * At the third line, we attach a PagerSnapHelper to the RecyclerView.
            * The PagerSnapHelper is part of the RecyclerView library and makes the
            * list behave like a ViewPager, where one view is always snapped to the center of the screen.
            * So if you scroll halfway between two views and let go, the list will automatically center the closest view.
            **/
            PagerSnapHelper().attachToRecyclerView(this)

            setupParallaxScrollListener()
        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return

            val measuredWidth = recyclerView.measuredWidth
            val scrollOffSet = recyclerView.computeHorizontalScrollOffset()
            val offsetFactor = (scrollOffSet % measuredWidth) / measuredWidth.toFloat()

            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            recyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition)?.let {
                (it as? FilmViewHolder)?.offset = -offsetFactor
            }

            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            if (firstVisibleItemPosition != lastVisibleItemPosition){
                recyclerView.findViewHolderForAdapterPosition(lastVisibleItemPosition)?.let {
                    (it as? FilmViewHolder)?.offset = 1 - offsetFactor
                }
            }
        }
    }

    private fun RecyclerView.setupParallaxScrollListener(){
        addOnScrollListener(onScrollListener)
    }

    private fun adapterConfig(){
        val list = ArrayList<Film>()

        list.add(Film(R.string.didi,R.string.description_didi,R.drawable.didi))
        list.add(Film(R.string.xuxa,R.string.description_xuxa,R.drawable.xuxa))
        list.add(Film(R.string.nepo,R.string.description_nepo,R.drawable.fdi))

        filmAdapter = FilmAdapter()
        filmAdapter.setFilmsList(list)
    }
}
