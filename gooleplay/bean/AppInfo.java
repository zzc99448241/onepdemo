package com.itheima95.gooleplay.bean;

import java.util.ArrayList;

/**
 * Created by 99448 on 2016/11/15.
 */

public class AppInfo {

    public String des;
    public String downloadUrl;
    public String iconUrl;
    public int id;
    public String name;
    public String packageName;
    public long size;
    public float stars;

    public String author;//app的作者，一般是公司名字
    public String date;//上传日期
    public String downloadNum;//下载数量
    public String version;
    public ArrayList<String> screen;//截图的url
    public ArrayList<SafeInfo> safe;//安全信息

}
