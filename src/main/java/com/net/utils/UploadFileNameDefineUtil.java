package com.net.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @包名 com.net.util
 * @类名 UploadFileNameDefineUtil
 * @作者 snail
 * @创建时间 2015-8-12
 * @描述 TODO
 */
public class UploadFileNameDefineUtil {

    public static String DefineUploadFileName(String originalFilename) {
        if (originalFilename.equals("") | originalFilename.equals(null))
            return "";
        String fileName = "";
        //获得当前时间的最小精度
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //时间+获得三位随机数=文件名
        fileName = format.format(new Date()) + RandomUtil.getRandom(3);
        //获取原文件格式
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //返回新的文件名
        return fileName + suffix;
    }
}
