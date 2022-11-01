package com.luz.giphy.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.luz.giphy.R
import com.luz.giphy.api.model.Data
import com.luz.giphy.databinding.DetailsFragmentBinding
import com.luz.giphy.viewmodel.GiphyViewModel

class DetailGiftFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = DetailGiftFragment().apply {
            /*arguments = Bundle().apply {
                putParcelable("data", data)
            }*/
        }

        lateinit var data: Data
    }

    private val sharedViewModel: GiphyViewModel by activityViewModels()
    private var binding: DetailsFragmentBinding? = null

    lateinit var imgvGift: ImageView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getParcelable<Data>("data")?.let {
            data = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.details_fragment, container, false)
        val detailfragmentBiding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding = detailfragmentBiding
        return detailfragmentBiding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
        }
        imgvGift = requireActivity().findViewById(R.id.imgGifLarge)
        sharedViewModel.imgurl.observe(requireActivity(), Observer {
            Glide.with(requireActivity()).load(it).into(imgvGift)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}