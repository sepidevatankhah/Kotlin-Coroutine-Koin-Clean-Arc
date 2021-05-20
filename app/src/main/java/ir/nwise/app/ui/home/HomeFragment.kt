package ir.nwise.app.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ir.nwise.app.R
import ir.nwise.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewState, HomeViewModel>() {
    private val homeViewModel: HomeViewModel by viewModel()
    private val cachedAlbumAdapter: CachedAlbumAdapter =
        CachedAlbumAdapter(
            onItemClicked = { album ->
                Toast.makeText(context, album.name, Toast.LENGTH_LONG).show()
            },
            onDeleteItemClicked = { album -> viewModel.deleteAlbum(album) }
        )

    override fun getLayout(): Int = R.layout.fragment_home

    override fun render(state: HomeViewState) {
        when (state) {
            is HomeViewState.Loading -> {
                //TODO: Add custom spinner
            }
            is HomeViewState.PreLoaded -> {
                Toast.makeText(context, state.session.session?.name, Toast.LENGTH_LONG).show()
            }
            is HomeViewState.Loaded -> {
                cachedAlbumAdapter.submitItems(state.photos)
                initRecyclerView()
            }
            is HomeViewState.Error -> {
                Log.e(
                    "HomeFragment",
                    state.throwable.message,
                    state.throwable
                )
                //TODO: Add custom error view
            }
            is HomeViewState.DeletedAlbum -> {
                viewModel.getCachedAlbums()
                Toast.makeText(context, getString(R.string.album_deleted), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onCreateCompleted() {
        setHasOptionsMenu(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = homeViewModel
        super.onCreate(savedInstanceState)
        viewModel.getCachedAlbums()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = cachedAlbumAdapter
        }

    }
}