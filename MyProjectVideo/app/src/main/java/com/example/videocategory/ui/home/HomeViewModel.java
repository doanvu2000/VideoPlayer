package com.example.videocategory.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.videocategory.IOnclickVideo;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private IOnclickVideo.RecyclerViewFragment x;
    public HomeViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue( "This is home fragment" );
        x = IOnclickVideo.RecyclerViewFragment.newInstance();
//        FragmentManager fragmentManager = getClass().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace( R.id.nav_host_fragment, x);
//        fragmentTransaction.commit();
    }

    public LiveData<String> getText() {
        return mText;
    }
}