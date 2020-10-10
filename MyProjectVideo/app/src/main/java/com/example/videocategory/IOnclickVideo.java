package com.example.videocategory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.videocategory.databinding.PhimboFragmentBinding;
import com.example.videocategory.databinding.RecyclerviewFragmentBinding;
import com.example.videocategory.inforvideo.InFoItemFragment;
import com.example.videocategory.inforvideo.Infor;
import com.example.videocategory.playvideo.RecyclerViewAdapter;
import com.example.videocategory.playvideo.RecyclerView_2Adapter;
import com.example.videocategory.playvideo.Play_Video_Exo_Fragment;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public interface IOnclickVideo {
    void onClickName(ImageBanner imageBanner);

    void onClickBanner(ImageBanner imageBanner);

    void onClickName(VideoObject videoObject);

    void onClickBanner(VideoObject VideoObject);

    class RecyclerViewFragment extends Fragment {
        List<Infor> inforList = new ArrayList<>();
        RecyclerviewFragmentBinding binding;
        String urlAPI = "http://demo1913415.mockable.io/GetCategory";
        String url_banner = "https://demo5898233.mockable.io/banner";

        public static RecyclerViewFragment newInstance() {

            Bundle args = new Bundle();

            RecyclerViewFragment fragment = new RecyclerViewFragment();
            fragment.setArguments( args );
            return fragment;
        }

        public int indexOfInforVideo(int id) {
            for (int i = 0; i < inforList.size(); i++) {
                if (id == inforList.get( i ).getId_video()) {
                    return i;
                }
            }
            return -1;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate( inflater, R.layout.recyclerview_fragment, container, false );
            new DoGetData().execute();
            inforList.add( new Infor( "The Amazing Spider Man", 153, "Amazing Spider-Man hay Siêu Nhện " +
                    "Tái Xuất có khá nhiều khác biệt so với các phần trước tập trung ở sự khác biệt ở tính cách của các nhân vật, " +
                    "Peter không còn là cậu bé nhút nhát mà khá nghịch ngợm, bên cạnh đó đạo diễn Marc Webb cũng mạnh dạn đưa ra thêm " +
                    "nhiều thay đổi về cốt truyện để Amazing Spider-Man thực sự mới lạ so với các phiên bản cũ. Cha mẹ Peter biến mất " +
                    "bí ấn và để cậu cho cô chú nuôi nấng.Sự tò mò về thân phận của bản thân lẫn những điều chưa biết " +
                    "về cha mẹ luôn thôi thúc cậu. Peter tìm thấy tập tài liệu mà cha để lại.Từ đây, những manh mối " +
                    "dẫn cậu tới tập đoàn Oscorp và người cộng sự của ông.Một tai nạn tại Oscorp khiến Peter có những siêu năng lực " +
                    "của loài nhện, Người nhện xuất hiện bí ẩn trong lòng New York. Cùng lúc, giáo sư Curt Connors cũng tìm" +
                    " cách \"hấp thu\" những năng lực của loài bò sát để cải biến cơ thể.Nhưng ông không biết năng lực này cũng " +
                    "có thể quay lại chiếm hữu mình.  Phim đánh dấu sự trở lại của Người nhện với diện mạo và câu chuyện hoàn toàn" +
                    " mới so với 3phần phim đã công chiếu.Các nhà làm phim cũng đổi bạn gái cho Người nhện, thay nhân vật Gwen Stacy " +
                    "cho Mary - Jane", 1 ) );
            inforList.add( new Infor( "Blac Panther", 135, "Black Panther", 2 ) );
            inforList.add( new Infor( "Iron Man", 126, "Iron Man", 3 ) );
            inforList.add( new Infor( "Spiderman Home Coming", 129, "Spiderman Home Coming", 4 ) );
            inforList.add( new Infor( "Thor Ranagrok", 112, "Thor Ranagrok", 5 ) );
            inforList.add( new Infor( "Your Name", 112, "Bộ phim là câu chuyện hoán đổi cơ thể của 2 cô cậu Mitsuha - nữ sinh trung học sống ở một thị trấn nhỏ của vùng Itomori và Taki – nam sinh tại thủ đô Tokyo đầy sôi động.\n" +
                    "\n" +
                    "Mitsuha luôn chán chường với cuộc sống tẻ nhạt của mình và mơ ước được làm anh chàng đẹp trai sống tại thủ đô. Trong khi đó, Taki hằng đêm lại nhìn thấy mình trong hình hài cô gái vùng miền quê. Ước mơ của cả 2 đã thành sự thật khi sao chổi nghìn năm xuất hiện trên trái đất và rồi cứ cách ngày lại được hoán đổi cơ thể cho nhau.\n" +
                    "\n" +
                    "Anime ăn khách nhất mọi thời đại.", 6 ) );
            ArrayList<String> videoList = new ArrayList<>();
            videoList.add( "The Amazing Spiderman" );
            videoList.add( "The Amazing Spiderman 2" );
            videoList.add( "The Amazing Spiderman 3" );
            videoList.add( "The Amazing Spiderman 4" );
            videoList.add( "Thor Ragarok " );
            ArrayAdapter<String> x = new ArrayAdapter<>( getContext(), R.layout.support_simple_spinner_dropdown_item, videoList );
            binding.searchView.setThreshold( 2 );
            binding.searchView.setAdapter( x );
            // ---------------- Call API with Volley-----------------------------------------
            {
                RequestQueue queue = Volley.newRequestQueue( getContext() );
                JsonArrayRequest request = new JsonArrayRequest( Request.Method.GET, url_banner, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                List<ImageBanner> bannerList = new ArrayList<>();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject( i );
                                        int ID = jsonObject.getInt( "id" );
                                        String name = jsonObject.getString( "name" );
                                        String link = jsonObject.getString( "link" );
                                        String source = jsonObject.getString( "source" );
                                        ImageBanner imageBanner = new ImageBanner( ID, name, link, source );
                                        bannerList.add( imageBanner );
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                RecyclerView.LayoutManager layoutManager = new GridLayoutManager( getContext(), 2, RecyclerView.VERTICAL, false );
                                binding.listVideo1.setLayoutManager( layoutManager );
                                RecyclerView_2Adapter recyclerViewAdapter = new RecyclerView_2Adapter( bannerList, getContext() );
                                binding.listVideo1.setAdapter( recyclerViewAdapter );
                                //------------------------------------------------------OnClickItem---------------------------------------------------------
                                recyclerViewAdapter.setiOnclickVideo( new IOnclickVideo() {
                                    @Override
                                    public void onClickName(ImageBanner imageBanner) {
                                        int index = indexOfInforVideo( imageBanner.getID() );
                                        if (index != -1)
                                            getFragment( imageBanner, inforList.get( index ) );
                                        else getFragment( imageBanner, inforList.get( 0 ) );
//                                        MKPlayerActivity.configPlayer( getActivity() ).play( imageBanner.getSource() );
                                    }

                                    @Override
                                    public void onClickBanner(ImageBanner imageBanner) {
                                        int index = indexOfInforVideo( imageBanner.getID() );
                                        if (index != -1)
                                            getFragment( imageBanner, inforList.get( index ) );
                                        else getFragment( imageBanner, inforList.get( 0 ) );
//                                        MKPlayerActivity.configPlayer( getActivity() ).play( imageBanner.getSource() );
                                    }

                                    @Override
                                    public void onClickName(VideoObject videoObject) {
                                        //
                                    }

                                    @Override
                                    public void onClickBanner(VideoObject VideoObject) {
                                        //
                                    }

                                    public void getFragment(ImageBanner imageBanner, Infor x) {
                                        Fragment fragment = InFoItemFragment.newInstance( imageBanner, x, 1 );
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
            }
            // ----------------End Call API with Volley--------------------------------------
            int image[] = {R.drawable.black_panther, R.drawable.iron_man, R.drawable.thor, R.drawable.zutto, R.drawable.your_name_min};
            for (int i = 0; i < image.length; i++) {
                FlipperImage( image[i] );
            }
            return binding.getRoot();
        }

        public void FlipperImage(int image) {
            ImageView imageView = new ImageView( getContext() );
            imageView.setBackgroundResource( image );
            imageView.setScaleType( ImageView.ScaleType.FIT_XY );
            binding.viewFlipper.addView( imageView );
            binding.viewFlipper.setFlipInterval( 4000 );
            binding.viewFlipper.setAutoStart( true );
            binding.viewFlipper.setInAnimation( getContext(), android.R.anim.slide_in_left );
            binding.viewFlipper.setOutAnimation( getContext(), android.R.anim.slide_out_right );
        }

        public class DoGetData extends AsyncTask<Void, Void, Void> {
            List<VideoObject> videoObjectList;
            String result = "";

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                binding.loading.setVisibility( View.VISIBLE );
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URL url = new URL( urlAPI );
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    int byteCharacter;
                    while ((byteCharacter = inputStream.read()) != -1) {
                        result += (char) byteCharacter;
                    }
                    //co chuoi json => add vao list
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                binding.loading.setVisibility( View.GONE );
                videoObjectList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray( result );
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject( i );

                        int id = jsonObject.getInt( "id" );
                        String title = jsonObject.getString( "title" );
                        String avatar = jsonObject.getString( "avatar" );
                        String date_created = jsonObject.getString( "date_created" );
                        String date_modified = jsonObject.getString( "date_modified" );
                        String thumb = jsonObject.getString( "thumb" );
                        VideoObject videoObject = new VideoObject( id, title, avatar, date_created, date_modified, thumb,
                                "https://firebasestorage.googleapis.com/v0/b/demovideoview-a5d42.appspot.com/o/G%C3%81C%20L%E1%BA%A0" +
                                        "I%20%C3%82U%20LO%20-%20DA%20LAB%20X%20MIU%20L%C3%8A%20(%20ZANG%20REMIX%20)%20N" +
                                        "h%E1%BB%9B%20%C4%90eo%20Tai%20Nghe.mp4?alt=media&token=765db932-29c7-4191-a339-cd780d501972" );
                        videoObjectList.add( videoObject );
                    }
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager( getContext(), 2, RecyclerView.HORIZONTAL, false );
                    binding.listVideo.setLayoutManager( layoutManager );
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter( videoObjectList, getContext() );
                    binding.listVideo.setAdapter( recyclerViewAdapter );
                    recyclerViewAdapter.setiOnclickVideo( new IOnclickVideo() {
                        @Override
                        public void onClickName(ImageBanner imageBanner) {

                        }

                        @Override
                        public void onClickBanner(ImageBanner imageBanner) {

                        }

                        @Override
                        public void onClickName(VideoObject videoObject) {
                            getFragment( videoObject );
                        }

                        @Override
                        public void onClickBanner(VideoObject VideoObject) {
                            getFragment( VideoObject );
                        }

                        public void getFragment(VideoObject videoObject) {
                            Fragment fragment = Play_Video_Exo_Fragment.newInstance( videoObject.getUrl() );
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace( R.id.nav_host_fragment, fragment );
                            fragmentTransaction.commit();
                        }
                    } );

                } catch (Exception e) {
                    e.printStackTrace();
                }

                super.onPostExecute( aVoid );
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    }

    class PhimBoFragment extends Fragment {
        List<Infor> inforList = new ArrayList<>();
        PhimboFragmentBinding binding;
        String url = "http://demo5898233.mockable.io/phimbo";

        public static PhimBoFragment newInstance() {

            Bundle args = new Bundle();

            PhimBoFragment fragment = new PhimBoFragment();
            fragment.setArguments( args );
            return fragment;
        }

        public int indexOfInforVideo(int id) {
            for (int i = 0; i < inforList.size(); i++) {
                if (id == inforList.get( i ).getId_video()) {
                    return i;
                }
            }
            return -1;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate( inflater, R.layout.phimbo_fragment, container, false );
            RequestQueue queue = Volley.newRequestQueue( getContext() );
            inforList.add( new Infor( "The God Of High School", 12, "The god of high school", 1 ) );
            inforList.add( new Infor( "Attack On TiTan", 36, "Phim được chuyển thể từ bộ manga ăn " +
                    "khách Attack on Titan của họa sĩ Hajime Isayama. Mùa đầu tiên được phát sóng vào năm 2013," +
                    " mùa thứ hai được phát sóng vào tháng 4 năm 2017 và mùa thứ ba phần một được phát sóng từ tháng 7 " +
                    "năm ngoái, phần thứ hai được phát sóng từ tháng 4 năm 2019.", 2 ) );
            inforList.add( new Infor( "Dr Stone", 24, "Dr Stone", 3 ) );
            inforList.add( new Infor( "Darling In The Franxx", 25, "Darling In The Franxx", 4 ) );
            inforList.add( new Infor( "Dragon Ball Super", 163, "Một thời gian sau khi Majin Buu bị tiêu diệt, hòa bình đã trở lại với Trái Đất. Goku đã ổn định cuộc sống và làm nông dân để hỗ trợ gia đình. Gia đình và bạn bè của Goku đều sống một cuộc sống yên bình. Tuy nhiên, một mối đe dọa mới xuất hiện với sự tỉnh giấc của Thần hủy diệt có tên là Beerus, người được coi là đáng sợ nhất và mạnh thứ hai trong Vũ trụ 7. Thức dậy sau nhiều thập kỷ chìm trong giấc ngủ, Beerus nói với trợ lý (kiêm giáo viên) của mình là Thiên sứ Whis rằng mình rất muốn chiến đấu với chiến binh huyền thoại mà Beerus đã thấy trong một lời tiên tri được gọi là Super Saiyan God (サイヤ人ゴッド, Sūpā Saiya-jin Goddo). Để bảo vệ Trái Đất, Goku biến thành Super Saiyan God để chiến đấu với Beerus bằng cách sử dụng sức mạnh của năm Super Saiyan, bao gồm Vegeta, Gohan, Goten, Trunks và em bé tên Pan trong bụng của Videl. Goku cho thấy một sức mạnh to lớn đủ để gây ấn tượng với Beerus. Mặc dù thua cuộc, những nỗ lực của Goku đã xoa dịu Thần hủy diệt đủ để Beerus quyết định tha cho hành tinh này.\n" +
                    "\n" +
                    "Trong khi Goku và Vegeta rời đi cùng với Beerus và Whis để luyện tập trong một năm, những tàn dư còn sót lại của quân đội Frieza đã đến Trái Đất để tìm kiếm Ngọc Rồng nhằm hồi sinh Frieza. Vài tháng sau khi được hồi sinh trở lại và bắt đầu luyện tập, Frieza tập hợp lực lượng của mình và quay trở lại Trái Đất để thực hiện việc trả thù Goku. Mặc dù đạt được một phép biến đổi mới có tên Golden Frieza, hắn ta vẫn bị đánh bại bởi Goku và Vegeta, cả hai đều thành thạo phép biến hình mới tên là Super Saiyan God Super Saiyan (sau này được gọi là Super Saiyan Blue). Frieza quyết định làm nổ tung Trái Đất để tiêu diệt tất cả. Tuy nhiên, với khả năng thao túng thời gian của Whis, Goku đã tiêu diệt Frieza một lần nữa và cứu được Trái Đất.\n" +
                    "\n" +
                    "Champa, anh trai của Beerus và là Thần hủy diệt Vũ trụ 6, thuyết phục Beerus tổ chức một giải đấu giữa các chiến binh giỏi nhất từ \u200B\u200Bvũ trụ của họ. Whis tiết lộ rằng có mười hai vũ trụ tồn tại. Vũ trụ mà Goku và những người bạn của anh đang sống được gọi là Vũ trụ 7. Champa cũng tiết lộ phần thưởng cho giải đấu của họ sẽ là Ngọc Rồng Siêu Cấp, là những viên Ngọc Rồng có kích cỡ hành tinh với khả năng thực hiện được bất kì điều ước nào. Champa dự định hoán đổi Trái Đất không còn sự sống của Vũ trụ 6 với Trái Đất của Vũ trụ 7 để lấy thức ăn. Goku và bạn bè của anh ấy quyết định tham gia giải đấu. Mỗi trận đấu trong giải đấu cho thấy những bất ngờ và khả năng khác nhau của mỗi chiến binh. Giải đấu đạt đến đỉnh điểm từ trận đấu thứ hai đến trận đấu giữa Goku và Hit. Sau khi thất bại trong việc nâng cao sức mạnh hơn nữa để đối mặt với Hit đã dùng toàn bộ sức mạnh của mình, Goku chọn từ bỏ trận đấu. Tuy nhiên, Hit cũng từ bỏ trận đấu cuối cùng sau khi anh được Goku truyền cảm hứng để làm điều tương tự. Giải đấu kết thúc với chiến thắng thuộc về Vũ trụ 7. Beerus đã ước với Rồng Thần Siêu Cấp là khôi phục cho Trái Đất của Vũ trụ 6.\n" +
                    "\n" +
                    "Goku sau đó gặp gỡ và kết bạn với Đấng Tối cao Zenō, Vua của mọi vũ trụ. Goku hứa sẽ mang tới cho ngài một người bạn để chơi cùng. Sau đó, Trunks tương lai xuất hiện trở lại, mang đến tin xấu về một kẻ thù mới và mạnh mẽ là đối thủ của Goku, người được biết đến với cái tên Goku Black. Goku và những người khác cuối cùng phát hiện ra rằng Goku Black thực chất là một người học việc của Kaioshin đến từ Vũ trụ 10 tên là Zamasu, người đã chiếm đoạt cơ thể của Goku từ dòng thời gian khác như một phần trong kế hoạch của anh ta. Black Goku còn hợp tác và giúp cho Zamasu ở dòng thời gian của Trunks tương lai đạt được sự bất tử, sau đó cả hai cùng nhau quét sạch tất cả con người lẫn các vị thần. Cuối cùng sau một trận chiến ác liệt kéo dài, Zamasu đã bị xóa sổ hoàn toàn cùng với toàn bộ dòng thời gian trong tương lai bởi Zenō tương lai, người sau đó đồng hành cùng Goku trở lại dòng thời gian hiện tại. Goku thực hiện lời hứa của mình với Zenō hiện tại bằng cách giới thiệu ngài với Zenō tương lai. Whis du hành tới tương lai để ngăn chặn sự trỗi dậy của Zamasu và khôi phục lại thế giới tương lai.\n" +
                    "\n" +
                    "Một thời gian sau khi Trunks tương lai trở về thế giới tương lai đã được phục hồi của mình, cả hai Đấng Tối cao Zenō tổ chức một giải đấu được gọi là Giải đấu Sức mạnh (力の大会, Chikara no Taikai). Theo đề nghị của Goku, giải đấu đa vũ trụ sẽ có sự tham gia của một nhóm gồm 10 chiến binh đến từ tám trong số mười hai vũ trụ. Tuy nhiên, nó trở thành một trận chiến sinh tồn khi cả hai Zenō tuyên bố rằng các vũ trụ thua cuộc tại giải đấu sẽ bị xóa sổ. Goku, Vegeta, Gohan, Piccolo, Android 17, Android 18, Krillin, Tien, Master Roshi và Frieza (tạm thời hồi sinh) tham gia giải đấu với tư cách là đại diện cho Vũ trụ 7. Nhóm chiến binh Vũ trụ 7 phải chiến đấu chống lại nhiều chiến binh đáng gờm đến từ các vũ trụ khác, đặc biệt là chiến binh tên Jiren thuộc lực lượng Pride Troopers của Vũ trụ 11. Trong giải đấu, Goku nhanh chóng đạt được một trạng thái mới của thần gọi là Bản năng vô cực (身勝手の極意, Migatte no Gokui), giúp tăng sự nhanh nhẹn và sức mạnh của anh. Bản năng vô cực cho phép Goku đồng thời tấn công và né tránh mà không cần phải suy nghĩ đến việc chuyển động. Giải đấu kết thúc với việc Goku và Frieza đẩy Jiren rơi khỏi võ đài cùng với chính họ, khiến Android 17 trở thành chiến binh cuối cùng còn sót lại trên võ đài và là người chiến thắng Giải đấu Sức mạnh. Android 17 được trao một điều ước từ Ngọc Rồng Siêu Cấp và anh ta sử dụng điều ước để khôi phục lại tất cả các vũ trụ bị xóa sổ. Sau đó mọi thứ được tiết lộ rằng cả hai Zenō dự kiến sẽ phục hồi \u200B\u200Bcác vũ trụ thua cuộc. Nếu điều ước được sử dụng cho mục đích ích kỷ thì cả hai sẽ xóa bỏ hết sự tồn tại của các vũ trụ đó lẫn người thực hiện điều ước. Với những nỗ lực của mình trong Giải đấu Sức mạnh, Frieza được hồi sinh hoàn toàn và xây dựng lại đội quân của mình. Goku và bạn bè anh trở về với cuộc sống hàng ngày của họ trên Trái Đất. Trong khi cãi nhau, Goku tiết lộ với Vegeta rằng anh vẫn chưa thể kích hoạt Bản năng vô cực một lần nữa. Goku và Vegeta cùng quyết tâm sẽ trở nên mạnh mẽ hơn.\n" +
                    "\n" +
                    "Một thời gian sau sự kiện Giải đấu Sức mạnh, Goku, Vegeta và Majin Buu đã gặp gỡ và nói chuyện với Đội tuần tra Thiên hà, những người đang cần họ giúp đỡ để bắt giữ một kẻ trốn tù nguy hiểm tên là Moro, kẻ đã bị giam giữ trong 10 triệu năm sau khi sử dụng sức mạnh của mình để rút cạn sinh lực của vô số hành tinh. Việc tìm kiếm Moro của họ dẫn họ đến Hành tinh Namek mới, nhưng họ đã không ngăn được kế hoạch của Moro là sử dụng Ngọc Rồng của người Namek để khôi phục lại hoàn toàn sức mạnh đã mất của mình và thả tất cả tội phạm bị giam giữ trong nhà tù của Đội tuần tra Thiên hà. Kể từ đó, Moro và những tên tội phạm trốn thoát đã cùng nhau tấn công nhiều hành tinh trong khi Goku và Vegeta chuẩn bị cho trận tái đấu sắp xảy ra với họ. Goku tìm cách làm chủ hoàn toàn Bản năng vô cực, còn Vegeta tới hành tinh Yardrat, nơi Goku học được kỹ năng dịch chuyển tức thời, để học một kỹ năng có thể giúp đánh bại Moro.", 5 ) );
            inforList.add( new Infor( "Doraemon", 225, "Doraemon", 6 ) );
            inforList.add( new Infor( "Sword Art Online", 48, "Sword Art Online", 7 ) );
            inforList.add( new Infor( "GleiPnir", 13, "GleiPnir", 8 ) );
            inforList.add( new Infor( "Boku no Hero", 50, "Boku no Hero", 9 ) );
            inforList.add( new Infor( "OverLord", 48, "OverLord", 10 ) );
            inforList.add( new Infor( "Fate Zero", 25, "Fate Zero", 11 ) );
            inforList.add( new Infor( "Kimetsu no Yaiba", 25, "Kimetsu no Yaiba", 12 ) );
            inforList.add( new Infor( "One Punch Man", 24, "One Punch Man", 13 ) );
            inforList.add( new Infor( "Code Geass", 50, "Code Geass", 14 ) );

            JsonArrayRequest request = new JsonArrayRequest( Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            List<ImageBanner> bannerList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject( i );
                                    int ID = jsonObject.getInt( "id" );
                                    String name = jsonObject.getString( "name" );
                                    String link = jsonObject.getString( "link" );
                                    String source = jsonObject.getString( "source" );
                                    ImageBanner imageBanner = new ImageBanner( ID, name, link, source );
                                    bannerList.add( imageBanner );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager( getContext(), 2, RecyclerView.VERTICAL, false );
                            binding.listVideoBo.setLayoutManager( layoutManager );
                            RecyclerView_2Adapter recyclerViewAdapter = new RecyclerView_2Adapter( bannerList, getContext() );
                            binding.listVideoBo.setAdapter( recyclerViewAdapter );
                            //------------------------------------------------------OnClickItem---------------------------------------------------------
                            recyclerViewAdapter.setiOnclickVideo( new IOnclickVideo() {
                                @Override
                                public void onClickName(ImageBanner imageBanner) {
                                    int index = indexOfInforVideo( imageBanner.getID() );
                                    if (index != -1)
                                        getFragment( imageBanner, inforList.get( index ) );
                                    else getFragment( imageBanner, inforList.get( 0 ) );

//                                    MKPlayerActivity.configPlayer( getActivity() ).play( imageBanner.getSource() );
                                }

                                @Override
                                public void onClickBanner(ImageBanner imageBanner) {
                                    int index = indexOfInforVideo( imageBanner.getID() );
                                    if (index != -1)
                                        getFragment( imageBanner, inforList.get( index ) );
                                    else getFragment( imageBanner, inforList.get( 0 ) );

//                                    MKPlayerActivity.configPlayer( getActivity() ).play( imageBanner.getSource() );

//                                        getFragment( imageBanner );
                                }

                                @Override
                                public void onClickName(VideoObject videoObject) {
                                    //
                                }

                                @Override
                                public void onClickBanner(VideoObject VideoObject) {
                                    //
                                }

                                public void getFragment(ImageBanner imageBanner, Infor x) {
                                    Fragment fragment = InFoItemFragment.newInstance( imageBanner, x, 2 );
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
}
