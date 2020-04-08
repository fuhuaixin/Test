package com.example.test0.utlis

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class NoScrollerGridLayoutManager(context: Context?, spanCount: Int) :
    GridLayoutManager(context, spanCount) {

    override fun canScrollVertically(): Boolean {
        return false
    }

}