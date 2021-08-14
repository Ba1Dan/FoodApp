package com.baiganov.foodapp.ui.fragments.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baiganov.foodapp.databinding.FragmentOverviewBinding
import com.baiganov.foodapp.models.Result


class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val args = arguments
        val model: Result? = args?.getParcelable("recipeBundle")

        if (model != null) {
            Log.d("OverviewFragment", model.toString())
            binding.result = model
        }

        return binding.root
    }
}