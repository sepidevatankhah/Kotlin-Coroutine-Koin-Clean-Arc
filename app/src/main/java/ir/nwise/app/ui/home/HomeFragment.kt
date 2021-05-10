package ir.nwise.app.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ir.nwise.app.R
import ir.nwise.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewState, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val photoAdapter: PhotoAdapter = PhotoAdapter { food ->
        Toast.makeText(context, food.userName, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun getLayout(): Int = R.layout.fragment_home

    override fun render(state: HomeViewState) {
        when (state) {
            is HomeViewState.Loading -> {
                //TODO: Add custom spinner
            }
            is HomeViewState.Loaded -> {
                photoAdapter.submitItems(state.photos)
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
        }
    }

    override fun onCreateCompleted() {
        viewModel = homeViewModel
        setHasOptionsMenu(true)
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = photoAdapter
        }

    }
}