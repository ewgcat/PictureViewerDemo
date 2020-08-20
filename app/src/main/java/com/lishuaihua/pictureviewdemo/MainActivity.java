package com.lishuaihua.pictureviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.lishuaihua.pictureviewer.ImagePagerActivity;
import com.lishuaihua.pictureviewer.PictureConfig;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>   list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<String>();
        //网络图片
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597899369139&di=f3d6489bdee0cff0ee3b077b4e1465bf&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F56%2F12%2F01300000164151121576126282411.jpg");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3984473917,238095211&fm=26&gp=0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597899369137&di=8e3a7679d853baed96c5e62afd5e9f6d&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F52%2F52%2F01200000169026136208529565374.jpg");


        findViewById(R.id.btn_picture_viewer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureConfig config = new PictureConfig.Builder()
                        .setListData((ArrayList<String>) list)//图片数据List<String> list
                        .setPosition(0)//图片下标（从第position张图片开始浏览）
                        .setDownloadPath("pictureviewer")//图片下载文件夹地址
                        .setIsShowNumber(true)//是否显示数字下标
                        .needDownload(true)//是否支持图片下载
                        .setPlaceholder(R.mipmap.ic_launcher_round)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                        .build();
                ImagePagerActivity.startActivity(MainActivity.this, config);
            }
        });
    }
}