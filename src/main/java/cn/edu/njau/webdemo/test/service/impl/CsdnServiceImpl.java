package cn.edu.njau.webdemo.test.service.impl;

import cn.edu.njau.webdemo.test.service.CsdnService;
import cn.edu.njau.webdemo.test.util.CsdnCourseUtil;
import cn.edu.njau.webdemo.test.util.CsdnVideoUtil;
import cn.edu.njau.webdemo.test.util.EsUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class CsdnServiceImpl  implements CsdnService {


    @Value("${ffmpeg.dir}")
    private String ffmpegDir;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

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

    @Async
    @Override
    public void saveCSDNCourseToEs(HttpServletRequest request) throws Exception {
        String cookie=request.getParameter("cookie");
        String saveIndexName=request.getParameter("saveIndexName");
        String pageStartIndex=request.getParameter("pageStartIndex");
        String pageEndIndex=request.getParameter("pageEndIndex");
        CsdnCourseUtil.saveCSDNCourseToEs(restHighLevelClient,cookie,saveIndexName,Integer.valueOf(pageStartIndex),Integer.valueOf(pageEndIndex));
    }

    @Override
    public Map getCoursePage(HttpServletRequest request) throws Exception {

        String indexName="csdncourse3";
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");

        //设置多条件查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        String title = request.getParameter("title");
        String priceStart = request.getParameter("priceStart");
        String priceEnd = request.getParameter("priceEnd");
        String description = request.getParameter("description");
        if(!StringUtils.isEmpty(title))
        {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("title.keyword","*"+title+"*"));
        }
        if(!StringUtils.isEmpty(priceStart))
        {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(Integer.valueOf(priceStart)));
        }

        if(!StringUtils.isEmpty(priceEnd))
        {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(Integer.valueOf(priceEnd)));
        }
        if(!StringUtils.isEmpty(description))
        {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("description.keyword","*"+description+"*"));
        }
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.sort("price", SortOrder.DESC);
        sourceBuilder.sort("ext.views", SortOrder.DESC);
        Map esPageList = EsUtil.getEsPageList(restHighLevelClient, indexName, sourceBuilder, Integer.valueOf(page), Integer.valueOf(rows));

        HashMap hashMap=new HashMap();
        hashMap.put("total",esPageList.get("total"));
        hashMap.put("rows",esPageList.get("dataList"));
        return hashMap;
    }
}
