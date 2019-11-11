package com.android.rahul.movies.components

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter


class ActivityUIExt( val context : Context){
    // Build Error dialog.
    fun buildDialog(error:String , onReload:() -> Unit,goOffline:()-> Unit){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(error)
        builder.setPositiveButton("Reload"){dialog, which ->
        onReload.invoke()
            dialog.dismiss()
        }

        builder.setNegativeButton("No"){dialog,which ->
            dialog.dismiss()
        }

        builder.setNeutralButton("Go Offline"){dialog, which ->
            goOffline.invoke()
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

@BindingAdapter("visible")
fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOnText")
fun View.visibleOnText(text: String) {
    this.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
}