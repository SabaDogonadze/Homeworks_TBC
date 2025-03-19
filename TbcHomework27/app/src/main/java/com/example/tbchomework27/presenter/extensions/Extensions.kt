package com.example.tbchomework27.presenter.extensions

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

fun Snackbar.customize(backgroundColor: Int, textColor: Int): Snackbar {
    view.setBackgroundColor(backgroundColor)
    view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.setTextColor(textColor)
    return this
}

fun Fragment.launchObserver(collect: suspend() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            collect.invoke()
        }
    }
}
