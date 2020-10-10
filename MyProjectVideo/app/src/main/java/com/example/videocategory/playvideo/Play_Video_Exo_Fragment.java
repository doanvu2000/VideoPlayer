package com.example.videocategory.playvideo;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.videocategory.R;
import com.example.videocategory.databinding.PlayVideoExoFragmentBinding;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

public class Play_Video_Exo_Fragment extends Fragment {
    static String x;
    static boolean flag = false;
    PlayVideoExoFragmentBinding binding;
    public static Play_Video_Exo_Fragment newInstance(String imageBanner) {

        Bundle args = new Bundle();
        x = imageBanner;
        Play_Video_Exo_Fragment fragment = new Play_Video_Exo_Fragment();
        fragment.setArguments( args );
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.play_video__exo_fragment,container,false);
        String url = x;
        {
//        Uri uri = Uri.parse( url );
//        getActivity().getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
//        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        TrackSelector trackSelector = new DefaultTrackSelector( new AdaptiveTrackSelection.Factory( bandwidthMeter ) );
//        SimpleExoPlayer simpleExoPlayer = ExoPlayerFactory.newSimpleInstance( getContext(), trackSelector );
//        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory( "exoplayer_video" );
//        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//        MediaSource mediaSource = new ExtractorMediaSource( uri,factory,extractorsFactory,null,null );
//        simpleExoPlayer.prepare( mediaSource );
//        binding.playerView.setPlayer( simpleExoPlayer );
//        binding.playerView.setKeepScreenOn( true );
//        //Play Video when ready
//        simpleExoPlayer.setPlayWhenReady( true );
//        simpleExoPlayer.addListener( new Player.EventListener() {
//            @Override
//            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                //Check condition
//                if(playbackState == Player.STATE_BUFFERING){
//                    //when buffering
//                    //show progess bar
//                    binding.progessBar.setVisibility( View.VISIBLE );
//                }else if (playbackState == Player.STATE_READY){
//                    //when ready
//                    //Hide Progess bar
//                    binding.progessBar.setVisibility( View.GONE );
//                }
//            }
//        } );
//
//        binding.btnFullscreen.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Check condition
//                if (flag){
//                    //When flag is true
//                    //Set enter full screen image
//                    binding.btnFullscreen.setImageDrawable( getResources().getDrawable( R.drawable.ic_baseline_fullscreen_24 ) );
//                    //Set portrait orientation
//                    getActivity().setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
//                    //Set flag value is false
//                    flag = false;
//                }else {
//                    //when flag is false
//                    //Set exit full screen image
//                    binding.btnFullscreen.setImageDrawable( getResources().getDrawable( R.drawable.ic_baseline_fullscreen_exit_24 ) );
//                    //Set Landscape orientation
//                    getActivity().setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
//                    //set flag value is true
//                    flag = true;
//                }
//            }
//        } );
        }
        MKPlayerActivity.configPlayer( getActivity() ).play( url );

        return binding.getRoot();
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        binding.playerView.onPause();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        binding.playerView.onPause();
//    }
}
