package com.example.videocategory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.videocategory.playvideo.PhimBoFragment;
import com.example.videocategory.ui.gallery.GalleryFragment;
import com.example.videocategory.ui.slideshow.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import net.alhazmy13.mediapicker.Video.VideoPicker;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        toolbar.setBackgroundColor( getResources().getColor( R.color.yeallow ) );
        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
        final DrawerLayout drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view );
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow )
                .setDrawerLayout( drawer )
                .build();
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        NavigationUI.setupActionBarWithNavController( this, navController, mAppBarConfiguration );
        NavigationUI.setupWithNavController( navigationView, navController );
        getFragment( IOnclickVideo.RecyclerViewFragment.newInstance() );
        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        getFragment( IOnclickVideo.RecyclerViewFragment.newInstance() );
                        toolbar.setTitle( "Trang chủ" );
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_gallery:
                        getFragment( IOnclickVideo.PhimBoFragment.newInstance(  ) );
                        toolbar.setTitle( "Phim bộ" );
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_slideshow:
                        getFragment( new SlideshowFragment() );
                        toolbar.setTitle( "Play Video In Devices" );
                        drawer.closeDrawers();
                        new VideoPicker.Builder(MainActivity.this)
                                .mode(VideoPicker.Mode.CAMERA_AND_GALLERY)
                                .directory(VideoPicker.Directory.DEFAULT)
                                .extension(VideoPicker.Extension.MP4)
                                .enableDebuggingMode(true)
                                .build();
                        break;
                    default: break;
                }
                return false;
            }
        } );
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths = data.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH);
            //Your Code

            MKPlayerActivity.configPlayer(this).play( mPaths.get( 0 ));
//            String url = "https://firebasestorage.googleapis.com/v0/b/demovideoview-a5d42.appspot.com/o/The%20Amazing%20Spider-Man%20-%20Trailer.mp4?alt=media&token=7c672835-43db-4019-b51c-86293f6a2837";
//            MKPlayerActivity.configPlayer( this ).play(url);
        }
    }
    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }

    //Menu_Settings
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate( R.menu.main, menu );
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController( this, R.id.nav_host_fragment );
        return NavigationUI.navigateUp( navController, mAppBarConfiguration )
                || super.onSupportNavigateUp();
    }
}