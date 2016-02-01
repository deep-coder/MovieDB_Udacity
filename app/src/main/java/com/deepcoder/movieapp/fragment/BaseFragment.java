package com.deepcoder.movieapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.deepcoder.movieapp.activity.MovieDetailsActivity;
import com.deepcoder.movieapp.adapter.MovieAdapter;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.utils.Constants;
import com.deepcoder.movieapp.utils.URIBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 1/24/2016.
 */
public abstract class BaseFragment extends Fragment implements AdapterView.OnItemClickListener {
    int pageCount = 1;
    @Bind(R.id.movie_grid_list)
    GridView movieGridList;
    List<MovieDetails> movieDetailsList = null;
    private MovieAdapter movieAdapter;
    private int itemClicked=0;
    private int changeFrag=-1;
    boolean tabletSize;
    //public final static String PARCELABLE_KEY = "com.myapp.parcelable";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tabletSize = getResources().getBoolean(R.bool.isTablet);
        ButterKnife.bind(this, rootView);
        movieGridList.setOnItemClickListener(this);
        movieGridList.setOnScrollListener(new GridViewScrollListener());
        return rootView;
    }

    protected abstract void onScrollCompleted(int pageCount);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieDetailsList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getContext(), movieDetailsList);
        movieGridList.setAdapter(movieAdapter);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void jsonRequest(String sortType, String pageNumber) {
        String URL = new URIBuilder().buildURI(sortType, pageNumber);
        //final ProgressDialog progressDialog = new ProgressDialog(getContext());
        //progressDialog.show();
        FragmentController fragmentController = new FragmentController();
        fragmentController.jsonRequest(URL, getContext(), new onTaskCompleted() {
            @Override
            public void onSuccess(Object object) {
                if (object != null) {
                    movieDetailsList.addAll((List<MovieDetails>) object);
                    movieAdapter.notifyDataSetChanged();
                    //progressDialog.hide();
                   /* if(tabletSize &&(itemClicked!=changeFrag)){
                        changeFrag=itemClicked;
                        MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(movieDetailsList.get(itemClicked));
                        getFragmentManager().beginTransaction()
                                .replace(R.id.movie_detail_container, fragment)
                                .commit();
                    }*/
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        itemClicked=position;
        movieGridList.setItemChecked(position, true);
        if (!tabletSize) {
            Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(Constants.PARCELABLE_KEY, movieDetailsList.get(position));
            intent.putExtras(mBundle);
            startActivity(intent);
        } else {
            MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(movieDetailsList.get(position));
            getFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }

    public final class GridViewScrollListener implements AbsListView.OnScrollListener {
        public int currentScrollState;
        public int currentFirstVisibleItem;
        public int currentVisibleItemCount;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            this.currentScrollState = scrollState;
            this.isScrollCompleted();
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
            }

        }

        private void isScrollCompleted() {
            if (this.currentVisibleItemCount > 0 && this.currentScrollState == SCROLL_STATE_IDLE) {
                pageCount++;
                onScrollCompleted(pageCount);

            }
        }
    }

}
