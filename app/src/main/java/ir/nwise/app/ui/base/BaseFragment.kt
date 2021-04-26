package ir.nwise.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<VS, VM : BaseViewModel<VS>> : Fragment() {

    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateCompleted()
        startObserving()
    }

    @LayoutRes
    abstract fun getLayout(): Int

    private fun startObserving() {
        viewModel.liveData.observe(viewLifecycleOwner, Observer { state ->
            render(state)
        })
    }

    abstract fun render(state: VS)

    /**
     * add your code here every thing injected and view
     */
    protected abstract fun onCreateCompleted()
}