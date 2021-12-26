package cn.edu.njau.webdemo.test.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;

public class HttpUtil {


    public static  Response getCsdnCourseList(String cookie,int pageNum) throws IOException {
        String urlTemplate="https://edu-core-api.csdn.net/cms/v2/getCourseList?channelType=1&page=%s";
        String url=String.format(urlTemplate,pageNum);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .addHeader("authority", "edu-core-api.csdn.net")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"")
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("origin", "https://edu.csdn.net")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://edu.csdn.net/course?channelType=1&page=668")
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .addHeader("cookie", cookie)
                .build();
        Response response = client.newCall(request).execute();
        return  response;
    }


    public static void main(String[] args) {
        changeFileName();
    }


    public static  void changeFileName()
    {
        File fileDir=new File("E:\\codespace\\javaspace\\testspace");

        File[] files = fileDir.listFiles();
        for(File file:files)
        {
            String fileName=file.getName();
            if(fileName.contains("mp4"))
            {
                String[] split = fileName.split("\\.");
//                System.out.println(Arrays.toString(split));

                int num=Integer.valueOf(split[0])-3;
                String rename=num+"."+split[1]+".mp4";
                System.out.println(rename);
                File renameFile=new File("C:\\Users\\sen\\Desktop\\csdn视频下载\\Downloads\\"+rename);
                file.renameTo(renameFile);
            }

        }

    }



}
