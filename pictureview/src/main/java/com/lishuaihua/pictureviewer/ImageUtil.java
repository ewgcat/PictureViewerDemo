package com.lishuaihua.pictureviewer;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
    public static String path = "pictureviewer";
    private static final int DOWNLOAD_SUCCESS = 17;
    private static final int DOWNLOAD_FAILD = 18;
    private static Context mContext;
    private static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 17) {
                Toast.makeText(ImageUtil.mContext, (String)msg.obj, 0).show();
            } else if (msg.what == 18) {
                Toast.makeText(ImageUtil.mContext, "保存失败", 0).show();
            }

        }
    };

    public ImageUtil() {
    }

    public static void saveImage(Context context, String url, final Bitmap bitmap) {
        mContext = context;
        String imgDir = "";
        if (checkSDCard()) {
            imgDir = Environment.getExternalStorageDirectory().getPath() + "/" + path;
        } else {
            imgDir = Environment.getDataDirectory().getPath() + "/" + path;
        }

        String[] fileNameArr = url.substring(url.lastIndexOf("/") + 1).split("\\.");
        final String fileName = fileNameArr[0] + ".png";
        File fileDir = new File(imgDir);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        final File imageFile = new File(fileDir, fileName);
        (new Thread(new Runnable() {
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(imageFile);
                    boolean compress = bitmap.compress(CompressFormat.PNG, 100, fos);
                    if (compress) {
                        Message message = new Message();
                        message.what = 17;
                        message.obj = "保存成功，路径/sd卡/" + ImageUtil.path + "/" + fileName;
                        ImageUtil.mHandler.sendMessage(message);
                    } else {
                        ImageUtil.mHandler.sendEmptyMessage(18);
                    }

                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException var4) {
                    var4.printStackTrace();
                    ImageUtil.mHandler.sendEmptyMessage(18);
                } catch (IOException var5) {
                    var5.printStackTrace();
                    ImageUtil.mHandler.sendEmptyMessage(18);
                } catch (Exception var6) {
                    var6.printStackTrace();
                    ImageUtil.mHandler.sendEmptyMessage(18);
                }

            }
        })).start();
        Uri uri = Uri.fromFile(imageFile);
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uri));
    }

    private static boolean checkSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}

