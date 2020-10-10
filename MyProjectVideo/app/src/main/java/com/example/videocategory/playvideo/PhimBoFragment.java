package com.example.videocategory.playvideo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.videocategory.IOnclickVideo;
import com.example.videocategory.ImageBanner;
import com.example.videocategory.R;
import com.example.videocategory.VideoObject;
import com.example.videocategory.databinding.PhimboFragmentBinding;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhimBoFragment extends Fragment {
    PhimboFragmentBinding binding;
    String url = "http://demo5898233.mockable.io/phimbo";

    public static PhimBoFragment newInstance() {
        Bundle args = new Bundle();

        PhimBoFragment fragment = new PhimBoFragment();
        fragment.setArguments( args );
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate( inflater, R.layout.phimbo_fragment, container, false );
        //Call API
        RequestQueue queue = Volley.newRequestQueue( getContext() );
        JsonArrayRequest request = new JsonArrayRequest( Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<ImageBanner> bannerList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject( i );
                                String ID = jsonObject.getString( "id" );
                                String name = jsonObject.getString( "name" );
                                String link = jsonObject.getString( "link" );
                                String source = jsonObject.getString( "source" );
                                ImageBanner imageBanner = new ImageBanner( Integer.parseInt( ID ), name, link, source );
                                bannerList.add( imageBanner );
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getContext(), RecyclerView.HORIZONTAL, false );
                        binding.listVideoBo.setLayoutManager( layoutManager );
                        PhimBoAdapter phimBoAdapter = new PhimBoAdapter( bannerList, getContext() );
                        binding.listVideoBo.setAdapter( phimBoAdapter );
                        //------------------------------------------------------OnClickItem---------------------------------------------------------
                        phimBoAdapter.setiOnclickVideo( new IOnclickVideo() {
                            @Override
                            public void onClickName(ImageBanner imageBanner) {
//                                getFragment( imageBanner );
                                MKPlayerActivity.configPlayer( getActivity() ).play( imageBanner.getSource() );
//                                MKPlayerActivity.configPlayer( getActivity() ).play( "android.resource://" +
//                                        getActivity().getPackageName() + "/" + getContext().getResources()
//                                        .openRawResource( R.raw.aot ) );
                            }

                            @Override
                            public void onClickBanner(ImageBanner imageBanner) {
//                                getFragment( imageBanner );
                                MKPlayerActivity.configPlayer( getActivity() ).play( imageBanner.getSource() );

//                                MKPlayerActivity.configPlayer( getActivity() ).play( "android.resource://" +
//                                        getActivity().getPackageName() + "/" + getContext().getResources()
//                                        .openRawResource( R.raw.aot ) );
                            }

                            @Override
                            public void onClickName(VideoObject videoObject) {
                                //
                            }

                            @Override
                            public void onClickBanner(VideoObject VideoObject) {
                                //
                            }
                            public void getFragment(ImageBanner imageBanner) {
                                Fragment fragment = Play_Video_Exo_Fragment.newInstance( imageBanner.getSource() );
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace( R.id.nav_host_fragment, fragment );
                                fragmentTransaction.commit();
                            }
                        } );

                        //------------------------------------------------------End-OnClickItem---------------------------------------------------------

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        } );
        queue.add( request );
        return binding.getRoot();
    }
}
