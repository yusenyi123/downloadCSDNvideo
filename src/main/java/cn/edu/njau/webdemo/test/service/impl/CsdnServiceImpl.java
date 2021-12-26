package cn.edu.njau.webdemo.test.service.impl;

import cn.edu.njau.webdemo.test.service.CsdnService;
import cn.edu.njau.webdemo.test.util.CsdnVideoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CsdnServiceImpl  implements CsdnService {


    @Value("${ffmpeg.dir}")
    private String ffmpegDir;


    @Async
    @Override
    public String getOneVideoPlayUrl(HttpServletRequest request) throws Exception {
        String url=request.getParameter("url");
        String cookie=request.getParameter("cookie");
        String firstVideoMaterialId=request.getParameter("firstVideoMaterialId");//开始下载的第一个课程的媒体id,从浏览器上获得
        String videoNum=request.getParameter("videoNum"); //需要按顺序下载的该课程的媒体数
        String xCaKey=request.getParameter("xCaKey");
        String appSecretKey=request.getParameter("appSecretKey");


        String ffmpegPath=ffmpegDir;
        String oneVideoPlayUrl = CsdnVideoUtil.getOneVideoPlayUrl(ffmpegPath, cookie, xCaKey, appSecretKey, url, Integer.valueOf(firstVideoMaterialId));
        return oneVideoPlayUrl;
    }




    @Async
    @Override
    public void downloadCourseAllVideoByGetLessonListMap(HttpServletRequest request) throws Exception {
        String url=request.getParameter("url");
        String cookie=request.getParameter("cookie");
        String xCaKey=request.getParameter("xCaKey");
        String appSecretKey=request.getParameter("appSecretKey");
        String startIndex=request.getParameter("startIndex");
        String endIndex=request.getParameter("endIndex");

        String ffmpegPath=ffmpegDir;
        CsdnVideoUtil.downloadCourseAllVideoByGetLessonListMap(ffmpegPath,url,cookie,xCaKey,appSecretKey,Integer.valueOf(startIndex),Integer.valueOf(endIndex));
        System.out.println("————————————————————————————");
        System.out.println("下载课程全部视频完成");
    }
}
