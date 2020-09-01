package tw.y_studio.ptt.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.Date;
import java.util.List;
import java.util.Map;

import tw.y_studio.ptt.R;
import tw.y_studio.ptt.UI.ImageLoadingDrawable;


import tw.y_studio.ptt.UI.StickyHeader.StickyAdapter;
import tw.y_studio.ptt.Utils.StringUtils;

public class HotArticleFilterAdapter extends StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder> implements View.OnClickListener,View.OnLongClickListener  {

    private List<Map<String, Object>> data;
    private int RingColor = 0;
    private int RingBackgroundColor = 0;

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        return 0;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int headerPosition) {

        onBindViewHolder(holder,headerPosition);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return onCreateViewHolder(parent,1);
    }

    public class ViewHolderTitleSubitem extends RecyclerView.ViewHolder {
        private TextView mTextView_title;
        private LinearLayout main;
        public ViewHolderTitleSubitem(View v) {
            super(v);
            mTextView_title=v.findViewById(R.id.article_list_item_textView_title);
            main=v.findViewById(R.id.article_list_item_main);
        }
    }
    public class ViewHolderTitle extends RecyclerView.ViewHolder {
        private TextView mTextView_title;
        private LinearLayout main;
        private ImageButton more;
        public ViewHolderTitle(View v) {
            super(v);
            mTextView_title=v.findViewById(R.id.article_list_item_textView_title);
            main=v.findViewById(R.id.article_list_item_main);
            more= v.findViewById(R.id.article_list_item_imageButton_more);
        }
    }
    public class ViewHolderMore extends RecyclerView.ViewHolder {
        //private TextView mTextView_title;
        private LinearLayout main;
        public ViewHolderMore(View v) {
            super(v);
            //mTextView_title=v.findViewById(R.id.article_list_item_textView_title);
            main=v.findViewById(R.id.article_list_item_main);
        }
    }
    private Context context;
    public HotArticleFilterAdapter(Context context, List<Map<String, Object>> items) {
        this.data = items;
        this.context=context;
        inflater = LayoutInflater.from(context);
        if(true){
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.slateGrey, typedValue, true);
            @ColorInt int color = typedValue.data;
            RingColor = color;
        }
        if(true){
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.paleGrey, typedValue, true);
            @ColorInt int color = typedValue.data;
            RingBackgroundColor = color;
        }
    }

    private View.OnClickListener moreClickListen;
    public void setMoreClickListen(View.OnClickListener moreClickListen){
        this.moreClickListen=moreClickListen;
    }
    private String highLightUrl = "";
    public void setHighLightUrl(String url){
        highLightUrl=url;
    }
    private LayoutInflater inflater;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case 0:
                View view1 = inflater.inflate(R.layout.hot_article_list_item_title_subitem, parent, false);
                //view1.setOnClickListener(this);
                //view1.setOnLongClickListener(this);
                ViewHolderTitleSubitem holder1 = new ViewHolderTitleSubitem(view1);
                return holder1;
            case 1:
                View view2 = inflater.inflate(R.layout.hot_article_list_item_title_top, parent, false);
                //view2.setOnClickListener(this);
                //view2.setOnLongClickListener(this);
                ViewHolderTitle holder2 = new ViewHolderTitle(view2);
                return holder2;
            case 2:
                View view3 = inflater.inflate(R.layout.hot_article_list_item_more, parent, false);
                //view1.setOnClickListener(this);
                //view1.setOnLongClickListener(this);
                ViewHolderMore holder3 = new ViewHolderMore(view3);
                return holder3;
            default:

        }

        return null;
    }




    @Override
    public int getItemViewType(int position) {
        int pos=0;
        if(StringUtils.notNullString(data.get(position).get("type")).equalsIgnoreCase("title")){
            pos=1;
        }else if(StringUtils.notNullString(data.get(position).get("type")).equalsIgnoreCase("more")){
            pos=2;
        }

        return pos;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holderO, final int position) {
        holderO.itemView.setTag(position);
        if(holderO instanceof ViewHolderTitleSubitem){
            ViewHolderTitleSubitem holder=(ViewHolderTitleSubitem) holderO;

            holder.mTextView_title.setText(StringUtils.notNullString(data.get(position).get("title")));
            StringUtils.TextViewAutoSplitFix(holder.mTextView_title);


            if((boolean)(data.get(position).get("select"))){
                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = context.getTheme();
                theme.resolveAttribute(R.attr.tangerine, typedValue, true);
                @ColorInt int color = typedValue.data;
                holder.mTextView_title.setTextColor(color);
            } else {

                TypedValue typedValue = new TypedValue();
                Resources.Theme theme = context.getTheme();
                theme.resolveAttribute(R.attr.paleGrey, typedValue, true);
                @ColorInt int color = typedValue.data;
                holder.mTextView_title.setTextColor(color);


            }






        }else if(holderO instanceof ViewHolderTitle){
            ViewHolderTitle holder=(ViewHolderTitle) holderO;

            holder.mTextView_title.setText(StringUtils.notNullString(data.get(position).get("title")));
            holder.more.setOnClickListener(moreClickListen);
            /*holder.main.getRootView().setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });*/






        }else if(holderO instanceof ViewHolderMore){
            ViewHolderMore holder=(ViewHolderMore) holderO;

            //holder.mTextView_title.setText(StringUtils.notNullString(data.get(position).get("title")));






        }



    }
    private void setNumberColor(TextView tv, StringUtils.SortDecimal sd){
        if(sd.isOverDecimal()){
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.tangerine, typedValue, true);
            @ColorInt int color = typedValue.data;
            tv.setTextColor(color);
        }else {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(R.attr.lightBlueGrey, typedValue, true);
            @ColorInt int color = typedValue.data;
            tv.setTextColor(color);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    //define interface
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemClick(v,(int)v.getTag());
            return true;
        }
        return false;
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
                    new GenericDraweeHierarchyBuilder(context.getResources());

            //RoundingParams roundingParams = RoundingParams.fromCornersRadius (10f);

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
                    //.setRoundingParams(roundingParams)
                    .build();

            draweeView.setController(controller);
            draweeView.setHierarchy(hierarchy);

        }catch (Exception e){


        }





    }


    /*public int getTopIndex(){
        return top;
    }
    private int top=0;
    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        top=itemPosition;
        int headerPosition = 0;
        return headerPosition;
    }

    @Override
    public int getHeaderLayout(int headerPosition) {
        return R.layout.hot_article_list_item_title_top;

    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        header.getRootView().setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        TextView title = header.findViewById(R.id.article_list_item_textView_title);
        title.setText(StringUtils.notNullString(data.get(headerPosition).get("title")));
        ImageButton more = header.findViewById(R.id.article_list_item_imageButton_more);
        more.setOnClickListener(moreClickListen);
    }

    @Override
    public boolean isHeader(int itemPosition) {
        if(itemPosition==0){
            return true;
        }
        return false;
    }*/


}
