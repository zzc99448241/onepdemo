package com.itheima95.gooleplay.http;

import android.os.Environment;

import com.itheima95.gooleplay.global.MyApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by 99448 on 2016/11/15.
 */

public class CacheManager {
    //定义缓存目录  /mnt/sdcard/包名/cache
    public static final String CACHE_DIR = Environment.getExternalStorageDirectory()
            + File.separator + MyApp.context.getPackageName() + File.separator + "cache";
    //缓存文件的有效期限
    public static final long CACHE_DURATION = 1000 * 60 * 60 * 2;
    private static CacheManager mInstance = new CacheManager();

    private CacheManager() {
        //文件目录
        File dir = new File(CACHE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static CacheManager create() {
        return mInstance;
    }

    //保存缓存数据
    public void saveCacheData(String url, String json) {

        try {

            File file = new File(CACHE_DIR, URLEncoder.encode(url));
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //根据url获取缓存数据
    public String getCacheData(String url) {

        StringBuilder builder = new StringBuilder();

        try {
            File file = new File(CACHE_DIR, URLEncoder.encode(url));
            if (!file.exists()) {
                if (isValid(file)) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        //拼接字符串
                        builder.append(line);
                    }
                    br.close();
                }else {
                    file.delete();
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return builder.toString();

    }


    //判断文件是否有效
    private boolean isValid(File file) {
        long existDuration = System.currentTimeMillis() - file.lastModified();
        return existDuration <= CACHE_DURATION;
    }


}
