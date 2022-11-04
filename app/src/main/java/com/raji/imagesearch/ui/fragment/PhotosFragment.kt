package com.raji.imagesearch.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.raji.imagesearch.R
import com.raji.imagesearch.databinding.FragmentPhotosBinding
import com.raji.imagesearch.ui.PhotoLoadStateAdapter
import com.raji.imagesearch.ui.PhotosAdapter
import com.raji.imagesearch.ui.viewmodel.ImageViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private val viewModel by viewModels<ImageViewModel>()

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPhotosBinding.bind(view)

        val adapter = PhotosAdapter()
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter { adapter.retry() },
                footer = PhotoLoadStateAdapter { adapter.retry() }
            )
        }
        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}