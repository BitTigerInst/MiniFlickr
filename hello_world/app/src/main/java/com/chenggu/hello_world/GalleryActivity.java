package com.chenggu.hello_world;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = GalleryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
    }

    /*
    * 当其它Activity或某个intent发起要重新调用这个activity的时候，如果这个activity的启动模式不是default的
    * 不会重新从onCreate建立这个Activity的Instance，而是重用之前的Instance
    * lifecycle会变化，会具有较好的performance
    * */
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    /*
    * 建立一个intent的filter，从intent中提取出我们搜索的关键词
    * 把关键词存入到Shared Prefrence中，Shared Prefrence可以简单认为是在一个app之内，用key-value的形式存一些共享数据
    * */
    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d(TAG, "Received a new search query: " + query);

            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putString(UrlManager.PREF_SEARCH_QUERY, query) // 把query存入以UrlManger的某个字符串为key的utility class中
                    .commit();
            // 通过FragmentManager去refresh我们的UI
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.gallery_fragment);
            if(fragment != null) {
                ((GalleryFragment) fragment).refresh();
            }
        }
    }
}
