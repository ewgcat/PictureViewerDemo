package com.lishuaihua.pictureviewer;


import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lishuaihua.pictureviewer.PhotoViewAttacher.OnPhotoTapListener;
import com.lishuaihua.pictureviewer.R.id;
import com.lishuaihua.pictureviewer.R.layout;

public class ImageDetailFragment extends Fragment {
    public static int mImageLoading;
    public static boolean mNeedDownload = false;
    private String mImageUrl;
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;
    private Bitmap mBitmap;

    public ImageDetailFragment() {
    }

    public static ImageDetailFragment newInstance(String imageUrl) {
        ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putString("url", imageUrl);
        imageDetailFragment.setArguments(args);
        return imageDetailFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mImageUrl = this.getArguments() != null ? this.getArguments().getString("url") : null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(layout.fragment_image_detail, container, false);
        this.mImageView = (ImageView)v.findViewById(id.image);
        this.mAttacher = new PhotoViewAttacher(this.mImageView);
        this.mAttacher.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                if (ImageDetailFragment.mNeedDownload) {
                    Builder builder = new Builder(ImageDetailFragment.this.getActivity());
                    builder.setMessage("保存图片");
                    builder.setNegativeButton("取消", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.setPositiveButton("确定", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ImageUtil.saveImage(ImageDetailFragment.this.getActivity(), ImageDetailFragment.this.mImageUrl, ImageDetailFragment.this.mBitmap);
                        }
                    });
                    builder.create().show();
                }

                return false;
            }
        });
        this.mAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                ImageDetailFragment.this.getActivity().finish();
            }
        });
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(this.mImageUrl)) {
            ((RequestBuilder)((RequestBuilder)Glide.with(this.getActivity()).asBitmap().load(this.mImageUrl).placeholder(mImageLoading)).error(mImageLoading)).into(new SimpleTarget<Bitmap>() {
                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                    ImageDetailFragment.this.mBitmap = bitmap;
                    ImageDetailFragment.this.mImageView.setImageBitmap(ImageDetailFragment.this.mBitmap);
                    ImageDetailFragment.this.mAttacher.update();
                }
            });
        } else {
            this.mImageView.setImageResource(mImageLoading);
        }

    }
}

