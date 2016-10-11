package com.example.anish.youtubechannel;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.anish.youtubechannel.Adapters.CustomRecyclerAdapter;
import com.example.anish.youtubechannel.Adapters.YouTubeRecylerAdapter;

import com.example.anish.youtubechannel.Model.Item;
import com.example.anish.youtubechannel.Model.YouTubeResponse;
import com.example.anish.youtubechannel.Rest.ApiClient;
import com.example.anish.youtubechannel.Rest.ApiInterface;
import com.example.anish.youtubechannel.Rest.RecyclerTouchListener;
import com.example.anish.youtubechannel.databinding.ActivityMainBinding;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerViewOnScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = KeyClass.KEY;
    private final static String channelId = KeyClass.KEY;
    private final static String part = "snippet,id";
    private final static String order = "date";
    private final static String maxresult = "50";
    private List<Item> items;
    public YouTubeResponse youTubeResponse;

    ActivityMainBinding activityMainBinding;
    ApiInterface apiService;
    FamiliarRecyclerView recyclerView;
    String res;
    View v;
   // private YouTubeRecylerAdapter youTubeRecylerAdapter;
   private CustomRecyclerAdapter customRecyclerAdapter ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);



/*       *//* final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setAdapter(adapter);
       *//* //to use RecycleView, you need a layout manager. default is LinearLayoutManager

       *//* YouTubeRecylerAdapter adapter=new YouTubeRecylerAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);*//*
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        //ApiInterface apiService =ApiClient.getClient().create(ApiInterface.class);
        Call<YouTubeResponse> call = apiService.getChannelResult();//API_KEY,channelId,part,order,maxresult

        call.enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {
                List<Item> items = response.body().getItems();

                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new YouTubeRecylerAdapter(items, R.layout.youtuberow, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable t) {

            }
        });
    }
}*/
// 801917163578-ilc6rs51376ppv8vte10g392qqmbfirt.apps.googleusercontent.com

        recyclerView =activityMainBinding.list;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        customRecyclerAdapter = new CustomRecyclerAdapter(items, R.layout.custom_youtuberow, MainActivity.this);
        recyclerView.setAdapter(customRecyclerAdapter);

        prepareData(null);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(MainActivity.this, KeyClass.KEY, items.get(position).getId().getVideoId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        v = getLayoutInflater().inflate(R.layout.loadmore, null);
        v.setVisibility(View.GONE);
        recyclerView.addFooterView(v);

        recyclerView.addOnScrollListener(new FamiliarRecyclerViewOnScrollListener(recyclerView.getLayoutManager()) {

            @Override
            public void onScrolledToTop() {
                // top
            }

            @Override
            public void onScrolledToBottom() {
                if (res != null) {
                    v.setVisibility(View.VISIBLE);
                    prepareData(res);
                }
                else
                {
                    v.setVisibility(View.GONE);
                }

            }
        });
    }

    public void prepareData(String nextToken) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        //ApiInterface apiService =ApiClient.getClient().create(ApiInterface.class);
        Call<YouTubeResponse> call;
        if (nextToken != null) {
            call = apiService.getNextPage("UC6ZkHcW5QQubZ-Q6XYINE3Q", "snippet,id", "date", 10, nextToken, API_KEY);
        } else {
            call = apiService.getChannelResult1("UC6ZkHcW5QQubZ-Q6XYINE3Q", "snippet,id", "date", 10, API_KEY);
        }
        //
        call.enqueue(new Callback<YouTubeResponse>() {
            @Override
            public void onResponse(Call<YouTubeResponse> call, Response<YouTubeResponse> response) {


                Log.e("## Items:", response.code() + "");
                Log.d(TAG, "onResponse: "+response.body().getNextPageToken());
                res = response.body().getNextPageToken();
                Log.e("Res-->", "onResponse: "+res);
                for (int i = 0; i < response.body().getItems().size(); i++) {
                    items.add(response.body().getItems().get(i));

                }
                //res = response.body().nextPageToken;
                customRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<YouTubeResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG + ">>>", "ERRor :" + t.getMessage());
            }
        });

    }


}
 /* //int statusCode = response.code();
                List<Item> items =response.body().getItems();*/
// Log.e(TAG, "onResponse: " + items.get(3).getId().getVideoId());
// recyclerView.setAdapter(new YouTubeRecylerAdapter(items, R.layout.youtuberow, MainActivity.this));