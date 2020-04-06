package io.aikosoft.estore.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.aikosoft.estore.utils.MyLogger
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), MyLogger {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    protected lateinit var commonViewModel: CommonViewModel
    private var errorListener: ((String) -> Unit)? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected open fun onInitUI(firstInit: Boolean) {}
    protected open fun onInitViewModel() {}
    protected open fun onObserveViewModel() {}
    protected open fun onSetOnClickListeners() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        observeCommonViewModel()
        onInitViewModel()
        onInitUI(savedInstanceState == null)
        onObserveViewModel()
        onSetOnClickListeners()
    }

    protected fun setErrorListener(block: ((String) -> Unit)?) {
        errorListener = block
    }

    private fun observeCommonViewModel() {
        commonViewModel = ViewModelProvider(this, viewModelFactory)[CommonViewModel::class.java]

        commonViewModel.error.observe(this, Observer {
            it?.let {
                val listener = errorListener
                if (listener == null) {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                } else {
                    listener.invoke(it)
                }
            }
        })
    }

    protected inline fun <reified VM : BaseViewModel> getViewModel(): VM =
        ViewModelProvider(this, viewModelFactory)[VM::class.java].also {
            it.setOnErrorListener(commonViewModel)
        }
}