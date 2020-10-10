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

public class PhimBoAdapter extends RecyclerView.Adapter<PhimBoAdapter.ViewHolder> {
    List<ImageBanner> list;
    Context context;
    IOnclickVideo iOnclickVideo;

    public void setiOnclickVideo(IOnclickVideo iOnclickVideo) {
        this.iOnclickVideo = iOnclickVideo;
    }

    public PhimBoAdapter(List<ImageBanner> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.item_layout_phimbo, parent, false );
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        {
            holder.tv5.setText( list.get( position ).getName() );
            holder.tv11.setVisibility( View.GONE );
            Glide.with( context ).load( list.get( position ).getLink() ).into( holder.tv23 );
            holder.tv23.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnclickVideo.onClickBanner(list.get( position ));
                }
            } );
            holder.tv11.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnclickVideo.onClickName(list.get( position ));
                }
            } );
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv5, tv11;
        ImageView tv23;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            {
                tv5 = itemView.findViewById( R.id.tvName );
                tv11 = itemView.findViewById( R.id.tv11 );
                tv23 = itemView.findViewById( R.id.img_avt );
            }
        }
    }
}
