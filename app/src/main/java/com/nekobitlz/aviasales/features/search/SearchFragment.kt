package com.nekobitlz.aviasales.features.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nekobitlz.aviasales.R
import com.nekobitlz.aviasales.di.injector
import com.nekobitlz.aviasales.features.search.di.SearchComponent
import com.nekobitlz.aviasales.router.Router
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), SearchComponent by injector.searchModule {
    // TODO() Item decoration for recycler
    // TODO() Network error handling

    private lateinit var viewModel: SearchViewModel

    private val adapter: SearchAdapter by lazy {
        SearchAdapter(viewModel::onItemClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initSearchMenu()
        initRecyclerView()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)

        viewModel.getCities().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.getRouter().observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.perform(this, Router)
        })
    }

    private fun initSearchMenu() {
        toolbar.inflateMenu(R.menu.menu_search)

        val menuItem = toolbar.menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView

        menuItem.expandActionView()
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean = true

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                viewModel.onBackPressed()
                return true
            }

        })

        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = resources.getString(R.string.departure)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean = true

            override fun onQueryTextChange(text: String): Boolean {
                viewModel.onTextChanged(text)
                return true
            }
        })
    }

    private fun initRecyclerView() {
        rv_cities.layoutManager = LinearLayoutManager(context)
        rv_cities.setHasFixedSize(true)
        rv_cities.adapter = adapter
    }
}