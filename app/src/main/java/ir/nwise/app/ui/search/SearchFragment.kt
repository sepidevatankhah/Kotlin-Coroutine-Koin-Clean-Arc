package ir.nwise.app.ui.search

import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ir.nwise.app.R
import ir.nwise.app.ui.base.BaseFragment
import ir.nwise.app.ui.utils.InfiniteScrollListener
import ir.nwise.app.ui.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.recyclerView
import kotlinx.android.synthetic.main.view_search_result_toolbar.search_bar_edit_text
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchViewState, SearchViewModel>() {
    private val searchViewModel: SearchViewModel by viewModel()

    private val searchAdapter: SearchResultAdapter = SearchResultAdapter { result ->
        Toast.makeText(context, result.name, Toast.LENGTH_LONG).show()
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToTopAlbumsFragment(
                result.name
            )
        )
    }

    override fun getLayout(): Int = R.layout.fragment_search

    override fun render(state: SearchViewState) {
        when (state) {
            is SearchViewState.Loading -> {
                //TODO: Add custom spinner
            }
            is SearchViewState.Loaded -> {
                searchAdapter.submitItems(state.results)
                initRecyclerView()
            }
            is SearchViewState.Error -> {
                search_bar_edit_text?.hideKeyboard()

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
        viewModel = searchViewModel

        search_bar_edit_text.doOnTextChanged { text, _, _, _ ->
            viewModel.search(text.toString())
        }
    }

    override fun onStop() {
        super.onStop()
        with(search_bar_edit_text) {
            clearFocus()
            hideKeyboard()
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
            addOnScrollListener(InfiniteScrollListener(layoutManager as LinearLayoutManager) {
                hideKeyboard()
            })

            viewModel.listState?.let {
                layoutManager?.onRestoreInstanceState(it)
                viewModel.listState = null
            }

        }

    }

    override fun onDestroyView() {
        viewModel.listState = recyclerView?.layoutManager?.onSaveInstanceState()
        super.onDestroyView()
    }
}