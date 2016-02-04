package com.deepcoder.movieapp.fragment;

import android.view.View;

/**
 * Created by jdeepak on 1/1/2016.
 */
public interface OnRecyclerItemViewClickListener<Model> {
    void onItemClick(View view,Model model);
}
