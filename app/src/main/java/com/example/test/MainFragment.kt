package com.example.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.test.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var searchView: SearchView

    override fun onResume() {
        super.onResume()

        searchView.clearFocus()
        binding.focusDummyView.requestFocus()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)

        }
    }

    private fun initToolbar() {
        binding.databaseToolbar.inflateMenu(R.menu.toolbar)

        binding.databaseToolbar.menu.findItem(R.id.action_search).apply {
            (actionView.findViewById(R.id.search_view) as SearchView).apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        binding.focusDummyView.requestFocus()

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        return true
                    }
                })

                setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                    override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                        return true
                    }

                    override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                        return true
                    }
                })

                searchView = this
            }
        }
    }
}