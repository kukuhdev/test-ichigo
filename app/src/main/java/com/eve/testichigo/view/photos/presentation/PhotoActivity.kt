package com.eve.testichigo.view.photos.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.testichigo.R
import com.eve.testichigo.core.decoration.GridSpacingItemDecoration
import com.eve.testichigo.core.decoration.VerticalItemDecoration
import com.eve.testichigo.databinding.ActivityPhotosBinding
import com.eve.testichigo.view.photos.data.remote.request.PhotosRequest
import com.eve.testichigo.view.photos.data.remote.response.PhotosResponse
import com.eve.testichigo.view.photos.presentation.adapter.PhotosAdapter
import com.eve.testichigo.view.search.presentation.adapter.SearchAdapter
import com.eve.testichigo.view.photos.presentation.viewmodel.PhotosActivityState
import com.eve.testichigo.view.photos.presentation.viewmodel.PhotosViewModel
import com.eve.testichigo.view.search.presentation.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotosBinding

    private val photosViewModel: PhotosViewModel by viewModels()

    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        observe()
    }

    private fun initView() {
        initRecyclerView()

        val photosRequest = PhotosRequest(
            "1",
            "20",
            null
        )
        photosViewModel.photos(photosRequest)
    }

    private fun initRecyclerView() {
        photosAdapter = PhotosAdapter(this, listOf(), object : PhotosAdapter.ItemClickListener {
            override fun onItemClicked(position: Int, photoId: Int) {

            }
        })
        val itemDecoration = VerticalItemDecoration(10, false)
        binding.rvPhotos.addItemDecoration(itemDecoration)
        binding.rvPhotos.adapter = photosAdapter
    }

    private fun observe() {
        observeState()
        observeData()
    }

    private fun observeState() {
        photosViewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: PhotosActivityState) {
        when(state){
            is PhotosActivityState.Error -> {
            }
            is PhotosActivityState.Init -> {
            }
            is PhotosActivityState.IsLoading -> {
            }
            is PhotosActivityState.ShowToast -> {
            }
            is PhotosActivityState.Success -> {
            }
            is PhotosActivityState.ViewListGrid -> {
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
        photosViewModel.photos.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { detail ->
                detail?.let { handlePhotos(it) }
            }
            .launchIn(lifecycleScope)
    }

    private fun handlePhotos(data: List<PhotosResponse.PhotosResponseItem>) {
        photosAdapter.items = data
        photosAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item!!.itemId) {
            R.id.search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }
            R.id.list -> {
                photosViewModel.setView(VIEW_LIST)
                true
            }
            R.id.grid -> {
                photosViewModel.setView(VIEW_GRID)
                true
            }
            else -> super.onOptionsItemSelected(item!!)
        }
    }

    companion object {
        private const val TAG = "PhotoActivity"
        const val VIEW_LIST = 1
        const val VIEW_GRID = 2
    }
}