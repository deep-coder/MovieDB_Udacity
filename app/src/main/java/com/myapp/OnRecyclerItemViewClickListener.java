package com.myapp;

import android.view.View;

/**
 * Created by jdeepak on 1/1/2016.
 */
public interface OnRecyclerItemViewClickListener<Model> {
    public void onItemClick(View view,Model model);
}
