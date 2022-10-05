package com.eve.testichigo.view.search.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.annotation.CheckResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.testichigo.R
import com.eve.testichigo.core.decoration.GridSpacingItemDecoration
import com.eve.testichigo.core.decoration.VerticalItemDecoration
import com.eve.testichigo.databinding.ActivitySearchBinding
import com.eve.testichigo.view.photos.presentation.PhotoActivity
import com.eve.testichigo.view.search.data.remote.request.SearchRequest
import com.eve.testichigo.view.search.data.remote.response.SearchResponse
import com.eve.testichigo.view.search.presentation.adapter.SearchAdapter
import com.eve.testichigo.view.search.presentation.viewmodel.SearchActivityState
import com.eve.testichigo.view.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        observe()
    }

    private fun initView() {
        initRecyclerView()

        binding.etSearch.textChanges().debounce(500)
            .onEach {
                Log.e(TAG, "initView: ${it.toString()}")
                val searchRequest = SearchRequest(
                    "1",
                    "20",
                    it.toString()
                )
                searchViewModel.search(searchRequest)
            }
            .launchIn(lifecycleScope)
    }

    private fun initRecyclerView() {
        searchAdapter = SearchAdapter(this, listOf(), object : SearchAdapter.ItemClickListener {
            override fun onItemClicked(position: Int, photoId: Int) {

            }
        })
        val itemDecoration = VerticalItemDecoration(10, false)
        binding.rvPhotos.addItemDecoration(itemDecoration)
        binding.rvPhotos.adapter = searchAdapter
    }

    @ExperimentalCoroutinesApi
    @CheckResult
    fun EditText.textChanges(): Flow<CharSequence?> {
        return callbackFlow {
            val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
            awaitClose { removeTextChangedListener(listener) }
        }.onStart { emit(text) }
    }

    private fun observe() {
        observeState()
        observeData()
    }

    private fun observeState() {
        searchViewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: SearchActivityState) {
        when(state){
            is SearchActivityState.Error -> {

            }
            is SearchActivityState.Init -> {

            }
            is SearchActivityState.IsLoading -> {

            }
            is SearchActivityState.ShowToast -> {

            }
            is SearchActivityState.Success -> {

            }
            is SearchActivityState.ViewListGrid -> {
                when(state.isList){
                    true -> {
                        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        val itemDecoration = VerticalItemDecoration(10, false)
                        binding.rvPhotos.layoutManager = layoutManager
                        if(binding.rvPhotos.itemDecorationCount == 0){
                            binding.rvPhotos.addItemDecoration(itemDecoration)
                        }
                    }
                    false -> {
                        val layoutManager = GridLayoutManager(this, 2)
                        val itemDecoration = GridSpacingItemDecoration(2, 10, false, 0)
                        binding.rvPhotos.layoutManager = layoutManager
                        if(binding.rvPhotos.itemDecorationCount == 0){
                            binding.rvPhotos.addItemDecoration(itemDecoration)
                        }
                    }
                }
            }
        }
    }

    private fun observeData() {
        searchViewModel.search.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { detail ->
                detail?.let { handleSearch(it) }
            }
            .launchIn(lifecycleScope)
    }

    private fun handleSearch(data: SearchResponse) {
        searchAdapter.items = data.results ?: listOf()
        searchAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option, menu)
        val searchItem = menu!!.findItem(R.id.search)
        searchItem.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item!!.itemId) {
            R.id.list -> {
                searchViewModel.setView(PhotoActivity.VIEW_LIST)
                true
            }
            R.id.grid -> {
                searchViewModel.setView(PhotoActivity.VIEW_GRID)
                true
            }
            else -> super.onOptionsItemSelected(item!!)
        }
    }

    companion object {
        private const val TAG = "SearchActivity"
        const val VIEW_LIST = 1
        const val VIEW_GRID = 2
    }
}