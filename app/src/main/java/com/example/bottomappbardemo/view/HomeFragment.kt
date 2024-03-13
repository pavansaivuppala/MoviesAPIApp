package com.example.bottomappbardemo.view

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomappbardemo.R
import com.example.bottomappbardemo.databinding.FragmentHomeBinding
import com.example.bottomappbardemo.model.Constant
import com.example.bottomappbardemo.model.MovieDetails
import com.example.bottomappbardemo.model.RecyclerAdapter
import com.example.bottomappbardemo.model.VolleyHandler
import com.example.bottomappbardemo.model.nowplayingdata.MVPNowplaying
import com.example.bottomappbardemo.model.nowplayingdata.NowPlayingPresenter
import com.example.bottomappbardemo.model.nowplayingdata.NowplayingResponse

class HomeFragment : Fragment(), MVPNowplaying.NowplayingView {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: NowPlayingPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        initPresenternow()
        initViewsnow()
        val recyclerView = binding.recyclenow
        val spanCount = 1
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount, LinearLayoutManager.VERTICAL, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initViewsnow() {
        presenter.fetchNowplayingMovieData()
    }

    private fun initPresenternow() {
        presenter = NowPlayingPresenter(VolleyHandler(this.context),this)
    }

    override fun onLoadNowplaying(isLoading: Boolean) {
        if(isLoading){
            binding.progressf.visibility = View.VISIBLE
        }else{
            binding.progressf.visibility = View.GONE
        }
    }

    override fun setResultNowplaying(nowPlayingResponse: NowplayingResponse) {
        val yourList=ArrayList<MovieDetails>()
        val list=nowPlayingResponse.results
        var count=0
        for(i in list.indices){
            if(count<10) {
                var url = "${Constant.BASE_URL_MOVIE.replace("{movie_id}", list[i].poster_path.toString())}"
                yourList.add(
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
        val adapter = RecyclerAdapter(requireContext(), yourList)
        binding.recyclenow.adapter = adapter
    }

    override fun showErrorNowplaying(message: String) {
    }

}