package com.example.bottomappbardemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomappbardemo.R
import com.example.bottomappbardemo.databinding.FragmentWalletBinding
import com.example.bottomappbardemo.model.Constant
import com.example.bottomappbardemo.model.MovieDetails
import com.example.bottomappbardemo.model.RecyclerAdapter
import com.example.bottomappbardemo.model.VolleyHandler
import com.example.bottomappbardemo.model.nowplayingdata.MVPPopular
import com.example.bottomappbardemo.model.nowplayingdata.PopularPresenter
import com.example.bottomappbardemo.model.poulardata.PopularResponse

class WalletFragment : Fragment(), MVPPopular.PopularView {
    private lateinit var binding:FragmentWalletBinding
    private lateinit var presenter: PopularPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentWalletBinding.inflate(layoutInflater,container,false)
        initPresenterpop()
        initViewpop()
        val recyclerView = binding.recyclepop
        val spanCount = 1
        recyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount, LinearLayoutManager.VERTICAL, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    private fun initViewpop() {
        presenter.fetchPopularMovieData()
    }

    private fun initPresenterpop() {
        presenter= PopularPresenter(VolleyHandler(this.context),this)
    }

    override fun onLoadPopular(isLoading: Boolean) {
        if(isLoading){
            binding.progressf.visibility = View.VISIBLE
        }else{
            binding.progressf.visibility = View.GONE
        }

    }

    override fun setResultPopular(popularResponse: PopularResponse) {
        val yourList=ArrayList<MovieDetails>()
        val list=popularResponse.results
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
        binding.recyclepop.adapter = adapter
    }

    override fun showErrorPopular(message: String) {

    }


}