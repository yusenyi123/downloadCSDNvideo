package cn.edu.njau.webdemo.test.service;

import javax.servlet.http.HttpServletRequest;

public interface CsdnService {


    String getOneVideoPlayUrl(HttpServletRequest request) throws Exception;

   void downloadCourseAllVideoByGetLessonListMap(HttpServletRequest request)  throws Exception;
}
