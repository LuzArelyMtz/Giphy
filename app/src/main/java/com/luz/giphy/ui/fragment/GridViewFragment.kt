package com.luz.giphy.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.luz.giphy.R
import com.luz.giphy.api.model.Data
import com.luz.giphy.databinding.GridviewFragmentBinding
import com.luz.giphy.ui.adapter.GiphyGridAdapter
import com.luz.giphy.ui.adapter.OnItemClickListener
import com.luz.giphy.viewmodel.GiphyViewModel

class GridViewFragment : Fragment() {
    companion object {
        fun newInstance() = GridViewFragment()
    }

    private val sharedViewModel: GiphyViewModel by activityViewModels()
    lateinit var gifAdapter: GiphyGridAdapter

    lateinit var recyclerv: RecyclerView
    private lateinit var coordinatorLayout: CoordinatorLayout

    private var binding: GridviewFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gridfragmentBiding = GridviewFragmentBinding.inflate(inflater, container, false)
        binding = gridfragmentBiding
        return gridfragmentBiding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
        }
        recyclerv = requireActivity().findViewById(R.id.recyclerv)
        coordinatorLayout = requireActivity().findViewById(R.id.coordinatorLayout)
        initGridAdapter()



        sharedViewModel.getGiphyGift()
        sharedViewModel.livedataGiphy.observe(requireActivity(), Observer {
            handleResults(it)
        })
    }

    private fun initGridAdapter() {
        recyclerv.layoutManager = GridLayoutManager(requireActivity(), 2)
        gifAdapter = GiphyGridAdapter(object : OnItemClickListener {
            override fun onClick(v: View?, data: Data) {
                sharedViewModel.setData(data)
                val action =
                    GridViewFragmentDirections.actionGridViewFragmentToDetailGiftFragment()
                findNavController().navigate(action)
            }
        })
        recyclerv.adapter = gifAdapter
    }

    private fun handleResults(giftList: List<Data>) {
        gifAdapter.submitList(giftList)
    }

    private fun handleError(t: Throwable) {
        val snackbar = Snackbar
            .make(
                coordinatorLayout,
                "ERROR IN FETCHING API RESPONSE. Try again",
                Snackbar.LENGTH_LONG
            )
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}