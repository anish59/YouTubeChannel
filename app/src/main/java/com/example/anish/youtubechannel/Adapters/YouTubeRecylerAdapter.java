package com.example.anish.youtubechannel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anish.youtubechannel.KeyClass;
import com.example.anish.youtubechannel.Model.Id;
import com.example.anish.youtubechannel.Model.Item;
import com.example.anish.youtubechannel.Model.Snippet;
import com.example.anish.youtubechannel.R;
import com.example.anish.youtubechannel.databinding.YoutuberowBinding;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

/**
 * Created by ANISH on 09-10-2016.
 */
public class YouTubeRecylerAdapter extends RecyclerView.Adapter<YouTubeRecylerAdapter.VideoInfoHolder> {
    private List<Item> items;
    private int rowLayout;
    private Activity context;


    YoutuberowBinding youtuberowBinding;

    public YouTubeRecylerAdapter(List<Item> items, int rowLayout, Activity context) {
        this.items = items;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public VideoInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtuberow, parent, false);
        return new VideoInfoHolder(itemView);*/
        youtuberowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.youtuberow, parent, false);
        View view = youtuberowBinding.getRoot();
        return new VideoInfoHolder(view);
        // return new VideoInfoHolder(youtuberowBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(final VideoInfoHolder holder, final int position) {

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                // Toast.makeText(YouTubeRecylerAdapter.this, "Parsing error", Toast.LENGTH_SHORT).show();
                Log.e("---2---", "onThumbnailError: " + errorReason);
            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.title.setText(items.get(position).getSnippet().getTitle());
                // holder.youTubeThumbnailView.setImageIcon("https://i.ytimg.com/vi/DnWltU9baPg/default.jpg");
            }
        };

        holder.youTubeThumbnailView.initialize(KeyClass.KEY, new YouTubeThumbnailView.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(items.get(position).getId().getVideoId());
                Log.e("--1--", "onInitializationSuccess: " + items.get(position).getId().getVideoId());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                holder.youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.title.setText(items.get(position).getSnippet().getTitle());
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class VideoInfoHolder extends RecyclerView.ViewHolder {

        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected CardView playButton;
        TextView title;

        public VideoInfoHolder(View itemView) {
            super(itemView);
           /* playButton = (CardView) itemView.findViewById(R.id.play_onthis);
            title = (TextView) itemView.findViewById(R.id.video_title);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
*/
            playButton = youtuberowBinding.playOnthis;
            title = youtuberowBinding.videoTitle;
            youTubeThumbnailView = youtuberowBinding.youtubeThumbnail;
        }


    }
}
