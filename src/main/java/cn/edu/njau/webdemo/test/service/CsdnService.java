package cn.edu.njau.webdemo.test.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface CsdnService {


    String getOneVideoPlayUrl(HttpServletRequest request) throws Exception;

    void downloadCourseAllVideoByGetLessonListMap(HttpServletRequest request)  throws Exception;

    void saveCSDNCourseToEs(HttpServletRequest request) throws Exception;

     Map getCoursePage(HttpServletRequest request) throws Exception;

}
