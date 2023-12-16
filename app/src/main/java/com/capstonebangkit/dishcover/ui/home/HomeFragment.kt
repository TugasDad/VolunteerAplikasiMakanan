package com.capstonebangkit.dishcover.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstonebangkit.dishcover.databinding.FragmentHomeBinding
import com.google.android.material.search.SearchBar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


//        val textView: TextView = binding.textHome
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
//                    Toast.makeText(this@HomeFragment, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
            homeViewModel.text.observe(viewLifecycleOwner) {
//                textView.text = it
            }
            return root
        }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
    }
}