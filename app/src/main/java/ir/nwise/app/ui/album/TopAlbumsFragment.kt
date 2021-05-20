package ir.nwise.app.ui.album

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ir.nwise.app.R
import ir.nwise.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_top_albums.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel

class TopAlbumsFragment : BaseFragment<TopAlbumsViewState, TopAlbumsViewModel>() {
    private val albumViewModel: TopAlbumsViewModel by viewModel()
    private val args: TopAlbumsFragmentArgs by navArgs()

    private val topAlbumsAdapter: TopAlbumsAdapter =
        TopAlbumsAdapter(
            onItemClicked = { result ->
                Toast.makeText(context, result.name, Toast.LENGTH_LONG).show()
            },
            onSaveAlbumClicked = { album ->
                viewModel.saveAlbum(album)
            })

    override fun getLayout(): Int = R.layout.fragment_top_albums

    override fun render(state: TopAlbumsViewState) {
        when (state) {
            is TopAlbumsViewState.Loading -> {
                //TODO: Add custom spinner
            }
            is TopAlbumsViewState.Loaded -> {
                topAlbumsAdapter.submitItems(state.results ?: listOf())
                initRecyclerView()
            }
            is TopAlbumsViewState.Error -> {
                Log.e(
                    "TopAlbumsFragment",
                    state.throwable.message,
                    state.throwable
                )
                //TODO: Add custom error view
            }
            is TopAlbumsViewState.SavedAlbum -> {
                //TODO:
            }
            is TopAlbumsViewState.AlbumIsSaved -> {
                Toast.makeText(
                    context,
                    getString(R.string.this_album_is_already_saved),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = albumViewModel
        args.artist?.apply { viewModel.getTopAlbums(this) }
    }

    override fun onCreateCompleted() {}

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = topAlbumsAdapter
        }
    }
}