package cn.edu.njau.webdemo.test.controller;

import cn.edu.njau.webdemo.test.service.CsdnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/csdn")
public class CsdnController  {

    @Autowired
    private  CsdnService csdnService;


    @RequestMapping("/test")
    public String test()
    {
        return "OK";
    }


    @RequestMapping("/getOneVideoPlayUrl")
    public String getOneVideoPlayUrl(HttpServletRequest request) throws Exception {
        return  csdnService.getOneVideoPlayUrl(request);
    }


    @RequestMapping("/downloadCourseAllVideoByGetLessonListMap")
    public String downloadCourseAllVideoByGetLessonListMap(HttpServletRequest request) throws Exception {
        csdnService.downloadCourseAllVideoByGetLessonListMap(request);
        return "OK";
    }
}
