package com.deepcoder.movieapp.activity;

import android.os.Bundle;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.deepcoder.movieapp.fragment.ConnectivityFragment;
import com.deepcoder.movieapp.fragment.MainFragment;
import com.deepcoder.movieapp.fragment.R;
import com.deepcoder.movieapp.utils.CheckConnectivity;

public class MainActivity extends BaseActivity {

    public final static String PARCELABLE_KEY = "com.myapp.parcelable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (new CheckConnectivity(getApplicationContext()).isConnectedToInternet()) {

            ft.replace(R.id.fragment, new MainFragment());

        }
        else{
            ft.replace(R.id.fragment, new ConnectivityFragment());
        }
        ft.commit();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
