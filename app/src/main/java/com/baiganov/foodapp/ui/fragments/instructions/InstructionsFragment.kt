package com.baiganov.foodapp.ui.fragments.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.baiganov.foodapp.R
import com.baiganov.foodapp.databinding.FragmentIngredientsBinding
import com.baiganov.foodapp.databinding.FragmentInstructionsBinding
import com.baiganov.foodapp.databinding.FragmentOverviewBinding
import com.baiganov.foodapp.models.Result


class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val args = arguments
        val model: Result? = args?.getParcelable("recipeBundle")

        binding.instructionsWebView.webViewClient = object : WebViewClient() {}
        val websiteUrl: String = model!!.sourceUrl
        binding.instructionsWebView.loadUrl(websiteUrl)
        return binding.root
    }
}