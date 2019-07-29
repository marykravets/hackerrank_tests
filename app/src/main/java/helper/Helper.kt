package helper

import android.app.Activity
import android.view.View

fun <ViewT : View> Activity.bindView(idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ViewT>(idRes)
    }
}