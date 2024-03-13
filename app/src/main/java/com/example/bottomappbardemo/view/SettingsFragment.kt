package com.example.bottomappbardemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomappbardemo.R
import com.example.bottomappbardemo.databinding.FragmentHomeBinding
import com.example.bottomappbardemo.databinding.FragmentSettingsBinding
import com.example.bottomappbardemo.model.Constant
import com.example.bottomappbardemo.model.MovieDetails
import com.example.bottomappbardemo.model.RecyclerAdapter
import com.example.bottomappbardemo.model.VolleyHandler
import com.example.bottomappbardemo.model.nowplayingdata.MVPUpcoming
import com.example.bottomappbardemo.model.nowplayingdata.UpcomingPresenter
import com.example.bottomappbardemo.model.upcomingdata.UpcomingResponse

class SettingsFragment : Fragment(),MVPUpcoming.UpcomingView {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var presenter: MVPUpcoming.UpcomingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSettingsBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this
        initPresenterup()
        initViewsup()
        val recyclerView = binding.recycleupcoming
        val spanCount = 1
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount, LinearLayoutManager.VERTICAL, false)
        // Inflate the layout for this fragment
        return binding.root
        return binding.root
    }

    private fun initViewsup() {
        presenter.fetchUpcomingMovieData()
    }

    private fun initPresenterup(){
        presenter=UpcomingPresenter(VolleyHandler(this.context),this)
    }

    override fun onLoadUpcoming(isLoading: Boolean) {
        if(isLoading){
            binding.progressf.visibility = View.VISIBLE
        }else{
            binding.progressf.visibility = View.GONE
        }

    }

    override fun setResultUpcoming(upcomingMovieResponse: UpcomingResponse) {
        val yourList1=ArrayList<MovieDetails>()
        val list=upcomingMovieResponse.results
        var count=0
        for(i in list.indices){
            if(count<10) {
                var url = "${Constant.BASE_URL_MOVIE.replace("{movie_id}", list[i].poster_path.toString())}"
                yourList1.add(
                    MovieDetails(
                        list[i].original_title.toString(),
                        url.toString(),
                        list[i].vote_count.toString()
                    )
                )
                count = count + 1
            }
            else{
                break
            }
        }
        val adapter = RecyclerAdapter(requireContext(), yourList1)
        binding.recycleupcoming.adapter = adapter
    }

    override fun showErrorUpcoming(message: String) {

    }
}