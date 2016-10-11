package com.example.anish.youtubechannel.Adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anish.youtubechannel.Model.Item;
import com.example.anish.youtubechannel.R;
import com.example.anish.youtubechannel.databinding.CustomYoutuberowBinding;

import java.util.List;

/**
 * Created by anish on 11-10-2016.
 */
public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> items;
    private int rowLayout;
    private Activity context;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    CustomYoutuberowBinding customYoutuberowBinding;

    public CustomRecyclerAdapter(List<Item> items, int rowLayout, Activity context) {
        this.items = items;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == TYPE_HEADER) {
           // customYoutuberowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.demomain, parent, false);
          //  View headerview = customYoutuberowBinding.getRoot();
            View headerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.demomain, parent, false);
            Log.d("root-->", "onCreateViewHolder: "+headerview.toString());
            return new VHHeader(headerview);
        } else if (viewType == TYPE_ITEM) {
          //  customYoutuberowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_youtuberow, parent, false);
          //  View itemview = customYoutuberowBinding.getRoot();
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_youtuberow, parent, false);
            return new VideoInfoHolder(itemview);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

        //return new VideoInfoHolder(itemView);
    }

  /*  private Item getItem(int position)
    {
        return items.get(position);
    }
*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof VHHeader) {
            VHHeader VHheader = (VHHeader) holder;
            VHheader.txtTitle.setText("Making You The Guru");
        } else if (holder instanceof VideoInfoHolder) {
            VideoInfoHolder videoInfoHolder = (VideoInfoHolder) holder;
            videoInfoHolder.title.setText(items.get(position).getSnippet().getTitle());
            Glide.with(context)
                    .load(items.get(position).getSnippet().getThumbnails().getDefault().getUrl())
                    .thumbnail(0.5f)
                    .into(((VideoInfoHolder) holder).thumbImg);

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class VHHeader extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public VHHeader(View headerView) {
            super(headerView);
            this.txtTitle = (TextView) headerView.findViewById(R.id.txtTitle);
        }
    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder {

        ImageView thumbImg;
        TextView title;

        public VideoInfoHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            thumbImg = (ImageView) itemView.findViewById(R.id.thumbnail);

        }


    }
}
