package com.example.anish.youtubechannel.Adapters;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.VideoInfoHolder> {
    private List<Item> items;
    private int rowLayout;
    private Activity context;

    CustomYoutuberowBinding customYoutuberowBinding;

    public CustomRecyclerAdapter(List<Item> items, int rowLayout, Activity context) {
        this.items = items;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public VideoInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        customYoutuberowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_youtuberow, parent, false);
        View view = customYoutuberowBinding.getRoot();
        return new VideoInfoHolder(view);

        //return new VideoInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoInfoHolder holder, final int position) {

        holder.title.setText(items.get(position).getSnippet().getTitle());
        //holder.imageView.setImageURI(Uri.parse(items.get(position).getSnippet().getThumbnails().getDefault().getUrl()));
        Glide.with(context).load(items.get(position).getSnippet().getThumbnails().getDefault().getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class VideoInfoHolder extends RecyclerView.ViewHolder {

        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        ImageView imageView;
        //protected CardView playButton;
        TextView title;

        public VideoInfoHolder(View itemView) {
            super(itemView);
           /* playButton = (CardView) itemView.findViewById(R.id.play_onthis);
            title = (TextView) itemView.findViewById(R.id.video_title);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail);
*/
          //  playButton = customYoutuberowBinding.play;
            title = customYoutuberowBinding.title;
            imageView = customYoutuberowBinding.thumbnail;
        }


    }
}
