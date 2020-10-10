package com.example.videocategory.playvideo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.videocategory.IOnclickVideo;
import com.example.videocategory.ImageBanner;
import com.example.videocategory.R;

import java.util.List;

public class RecyclerView_2Adapter extends RecyclerView.Adapter<RecyclerView_2Adapter.ViewHolder> {
    List<ImageBanner> videoObjectList;
    Context context;
    IOnclickVideo iOnclickVideo;

    public void setiOnclickVideo(IOnclickVideo iOnclickVideo) {
        this.iOnclickVideo = iOnclickVideo;
    }

    public RecyclerView_2Adapter(List<ImageBanner> videoObjectList, Context context) {
        this.videoObjectList = videoObjectList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.item_layout, parent, false );
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        {
            holder.tv5.setText( videoObjectList.get( position ).getName() );
            holder.tv11.setVisibility( View.GONE );
            Glide.with( context ).load( videoObjectList.get( position ).getLink() ).into( holder.tv23 );
            holder.tv23.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnclickVideo.onClickBanner(videoObjectList.get( position ));
                }
            } );
            holder.tv11.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnclickVideo.onClickName(videoObjectList.get( position ));
                }
            } );
        }
    }

    @Override
    public int getItemCount() {
        return videoObjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv5, tv11;
        ImageView tv23;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            {
//                tv1 = itemView.findViewById( R.id.tvID );
                tv5 = itemView.findViewById( R.id.tvTitle );
                tv11 = itemView.findViewById( R.id.tv11 );
                tv23 = itemView.findViewById( R.id.img_video );
            }
        }
    }
}
