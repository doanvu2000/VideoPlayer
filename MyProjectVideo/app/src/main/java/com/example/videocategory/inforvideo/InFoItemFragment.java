package com.example.videocategory.inforvideo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.videocategory.IOnclickVideo;
import com.example.videocategory.ImageBanner;
import com.example.videocategory.R;
import com.example.videocategory.databinding.InforItemBinding;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

public class InFoItemFragment extends Fragment {
    InforItemBinding binding;
    static ImageBanner videoObject;
    static Infor infor;
    static int flag;

    public static InFoItemFragment newInstance(ImageBanner k, Infor m, int check) {
        videoObject = k;
        infor = m;
        flag = check;
        Bundle args = new Bundle();

        InFoItemFragment fragment = new InFoItemFragment();
        fragment.setArguments( args );
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate( inflater, R.layout.infor_item, container, false );
        Glide.with( getContext() ).load( videoObject.getLink() ).into( binding.imgInfor );
        binding.tvVideoName.setText( infor.getName());
        int time = infor.getTime();
        if (flag == 1)
            binding.tvTime.setText( String.valueOf( time ) + " phút" );
        else if (flag == 2) binding.tvTime.setText( time + "/" + time + " tập" );
        binding.tvDescription.setText( "Nội dung phim: " + infor.getDescription() );
        binding.btnPlay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MKPlayerActivity.configPlayer( getActivity() ).play( videoObject.getSource() );
            }
        } );
        binding.btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {//Home
                    Fragment fragment = IOnclickVideo.RecyclerViewFragment.newInstance();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace( R.id.nav_host_fragment, fragment );
                    fragmentTransaction.commit();
                } else if (flag == 2) {//Phim Bo
                    Fragment fragment = IOnclickVideo.PhimBoFragment.newInstance();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace( R.id.nav_host_fragment, fragment );
                    fragmentTransaction.commit();
                }
            }
        } );
        return binding.getRoot();
    }
}
