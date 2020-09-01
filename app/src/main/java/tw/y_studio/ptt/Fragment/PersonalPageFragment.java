package tw.y_studio.ptt.Fragment;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import tw.y_studio.ptt.Adapter.GeneralFragmentStatePagerAdapter;
import tw.y_studio.ptt.R;
import tw.y_studio.ptt.UI.ImageLoadingDrawable;
import tw.y_studio.ptt.Utils.DebugUtils;

import static android.content.Context.MODE_PRIVATE;

public class PersonalPageFragment extends Fragment {
    private View Mainview=null;
    public static PersonalPageFragment newInstance() {
        Bundle args = new Bundle();
        PersonalPageFragment fragment = new PersonalPageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public static PersonalPageFragment newInstance(Bundle args) {
        PersonalPageFragment fragment = new PersonalPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TabLayout mTabs;
    private com.facebook.drawee.view.SimpleDraweeView persionPicture;
    private TextView personIDTextView;
    private TextView personNickTextView;
    private TextView personLikeTextView;
    private AppBarLayout mAppBar;

    private com.facebook.drawee.view.SimpleDraweeView persionPictureMini;
    private TextView personIDTextViewMini;

    private RelativeLayout headerRelativeLayout;
    private RelativeLayout headerRelativeLayoutMini;
    private RelativeLayout likeBarRelativeLayout;

    private ViewPager2 mViewPager;
    private GeneralFragmentStatePagerAdapter fragmentStatePagerAdapter;
    private ArrayList<Fragment> fragmentArrayList;
    //private ScrollView scrollView_personal_page;

    private ArrayList<String> TabTitles;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.persional_page_fragment_layout, container, false);



        Mainview=view;

        Bundle bundle = getArguments();//取得Bundle

        persionPicture = Mainview.findViewById(R.id.person_page_picture);

        persionPictureMini = Mainview.findViewById(R.id.person_page_picture_mini);

        mTabs = Mainview.findViewById(R.id.page_tabs);

        mTabs.addTab(mTabs.newTab().setText(R.string.persion_page_tabs_info));
        mTabs.addTab(mTabs.newTab().setText(R.string.persion_page_tabs_articles));
        mTabs.addTab(mTabs.newTab().setText(R.string.persion_page_tabs_comments));

        TabTitles = new ArrayList<>();
        TabTitles.add(getString(R.string.persion_page_tabs_info));
        TabTitles.add(getString(R.string.persion_page_tabs_articles));
        TabTitles.add(getString(R.string.persion_page_tabs_comments));

        personIDTextView = Mainview.findViewById(R.id.textView_persion_page_id);
        personNickTextView = Mainview.findViewById(R.id.textView_persion_page_nick);
        personLikeTextView = Mainview.findViewById(R.id.textView_persional_like);

        personIDTextViewMini = Mainview.findViewById(R.id.textView_person_page_id_mini);

        headerRelativeLayout = Mainview.findViewById(R.id.relativeLayout_person_page_header);
        headerRelativeLayoutMini = Mainview.findViewById(R.id.relativeLayout_person_page_header_mini);
        likeBarRelativeLayout = Mainview.findViewById(R.id.relativeLayout_person_page_like_bar);

        //scrollView_personal_page = Mainview.findViewById(R.id.scrollView_personal_page);

        //headerRelativeLayoutMini.setVisibility(View.GONE);
        //likeBarRelativeLayout.setVisibility(View.VISIBLE);

        mAppBar = Mainview.findViewById(R.id.appBarLayout_person_page);

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if(isAlreadyReady){

                    int percent =  (int) ((double)Math.abs(verticalOffset) / (double)Math.abs(headerRelativeLayout.getHeight()) * 100d);

                    //Log.d("tag_scroll", "percent =  "+percent);
                    int hight = headerRelativeLayoutMini.getHeight();
                    headerRelativeLayoutMini.setY((float) (hight*-1+(hight*(percent/100d))));


                }
                isAlreadyReady = true;

            }
        });

        mViewPager = Mainview.findViewById(R.id.viewPager_person_page);
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(PersonInfoFragment.newInstance());
        fragmentArrayList.add(EmptyFragment.newInstance());
        fragmentArrayList.add(EmptyFragment.newInstance());

        fragmentStatePagerAdapter = new GeneralFragmentStatePagerAdapter(getActivity(),fragmentArrayList);
        mViewPager.setAdapter(fragmentStatePagerAdapter);

        new TabLayoutMediator(mTabs, mViewPager,
                (tab, position) -> tab.setText(this.TabTitles.get(position))
        ).attach();

        loadData();


        return view;
    }

    private boolean isAlreadyReady = false;
    public void loadData(){
        setImageView(persionPicture,"asset:///List-Of-Android-R-Features.jpeg");
        setImageView(persionPictureMini,"asset:///List-Of-Android-R-Features.jpeg");

        String id = getActivity().getSharedPreferences(
                "MainSetting", MODE_PRIVATE).getString("APIPTTID","Guest");
        personIDTextView.setText(id);
        personIDTextViewMini.setText(id);
        personNickTextView.setText("匿名訪客");
    }



    private void setImageView(SimpleDraweeView draweeView, final String Url){


        if(draweeView.getTag()!=null){
            if(draweeView.getTag().toString().equals(Url)){
                return;
            }
        }
        draweeView.setTag(Url);

        try{
            final Uri uri = Uri.parse(Url);


            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .setProgressiveRenderingEnabled(false)
                    .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                    .setResizeOptions(new ResizeOptions(1024,1024))

                    .build();

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setAutoPlayAnimations(true)

                    .setOldController(draweeView.getController())
                    .build();

            //Fresco.getImagePipeline().prefetchToDiskCache(request,null);

            GenericDraweeHierarchyBuilder builder =
                    new GenericDraweeHierarchyBuilder(this.getResources());

            RoundingParams roundingParams = RoundingParams.fromCornersRadius (200f);

            //roundingParams.setOverlayColor(Color.GRAY);
            //roundingParams.setBorderColor(Color.GRAY);
            //roundingParams.setBorderWidth(2f);
            PointF pf=new PointF(0.5f,0.5f);
            GenericDraweeHierarchy hierarchy = null;
            hierarchy = builder
                    .setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                    .setActualImageFocusPoint(pf)

                    .setFadeDuration(0)
                    .setProgressBarImage(new ImageLoadingDrawable())
                    .setRoundingParams(roundingParams)
                    .build();

            draweeView.setController(controller);
            draweeView.setHierarchy(hierarchy);

        }catch (Exception e){


        }





    }



    private void initView() throws Exception{

    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }
}
