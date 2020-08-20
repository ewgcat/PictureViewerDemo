package com.lishuaihua.pictureviewer;


import androidx.annotation.DrawableRes;
import java.io.Serializable;
import java.util.ArrayList;

public class PictureConfig {
    public static boolean mIsShowNumber = true;
    public static boolean needDownload = false;
    public static String path = "pictureviewer";
    public static int resId = 0;
    public static int position = 0;
    public static ArrayList<String> list;

    public PictureConfig(PictureConfig.Builder builder) {
        mIsShowNumber = builder.mIsShowNumber;
        needDownload = builder.needDownload;
        path = builder.path;
        resId = builder.resId;
        position = builder.position;
        list = builder.list;
    }

    public static class Builder implements Serializable {
        private boolean mIsShowNumber = true;
        private boolean needDownload = false;
        private int resId = 0;
        private String path = "pictureviewer";
        private int position = 0;
        private ArrayList<String> list;

        public Builder() {
        }

        public PictureConfig.Builder setListData(ArrayList<String> list) {
            this.list = list;
            return this;
        }

        public PictureConfig.Builder setPosition(int position) {
            this.position = position;
            return this;
        }

        public PictureConfig.Builder setIsShowNumber(boolean mIsShowNumber) {
            this.mIsShowNumber = mIsShowNumber;
            return this;
        }

        public PictureConfig.Builder needDownload(boolean needDownload) {
            this.needDownload = needDownload;
            return this;
        }

        public PictureConfig.Builder setDownloadPath(String path) {
            this.path = path;
            return this;
        }

        public PictureConfig.Builder setPlaceholder(@DrawableRes int resId) {
            this.resId = resId;
            return this;
        }

        public PictureConfig build() {
            return new PictureConfig(this);
        }
    }
}
