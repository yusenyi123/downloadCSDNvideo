package cn.edu.njau.webdemo.test.util;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class EsUtil {



    public static Map getEsPageList(RestHighLevelClient restHighLevelClient,String indexName, SearchSourceBuilder sourceBuilder, int pageNumber, int pageSize)
    {

        // 分页查询设置
        // 每页显示多少条 size
        // 当前页其实索引(第一条数据的顺序号) from
        Integer size=pageSize;
        Integer fromStart=(pageNumber-1)*size;
        Integer fromEnd=fromStart+size-1;

        if(fromEnd<=10000)
        {
            // 创建搜索请求对象
            SearchRequest searchRequest = new SearchRequest(indexName);
            sourceBuilder.trackTotalHits(true);
            sourceBuilder.from(fromStart);
            sourceBuilder.size(size);
            //请求对象设置请求体
            searchRequest.source(sourceBuilder);
            List<Map> dataList=new ArrayList<>();
            //es客户端发送请求
            try {
                SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                // 查询匹配
                SearchHits hits = response.getHits();
                for (SearchHit hit : hits) {
                    dataList.add(hit.getSourceAsMap());
                }
                // 获得数据总数
                long total=hits.getTotalHits().value;
                Map returnMap=new HashMap();
                returnMap.put("dataList",dataList);
                returnMap.put("total",total);
                returnMap.put("number",pageNumber);
                return returnMap;
            }catch (Exception e)
            {
                log.error(e.getMessage());
                Map returnMap=new HashMap();
                returnMap.put("error",e.getMessage());
                return returnMap;
            }
        }
        else
        {
            Integer scrollSize = 1000;
            //计算在scorllSize需要查询的轮数
            Integer searchCount = fromEnd / scrollSize+1;
            Integer dataListStart=searchCount==1?scrollSize*(searchCount-1):scrollSize*(searchCount-2);
            List<Map> dataList=new ArrayList<>();

            SearchRequest searchRequest = new SearchRequest(indexName);
            sourceBuilder.size(scrollSize);
            sourceBuilder.trackTotalHits(true);
            searchRequest.source(sourceBuilder);
            //使用scroll方式进行分页查询,scroll方式使用快照保存,设置scroll间隔(快照保存的时间),按分钟为单位
            Scroll scroll = new Scroll(TimeValue.timeValueMinutes(5));
            searchRequest.scroll(scroll);

            SearchResponse searchResponse = null;
            long total=0;
            //进行第一次分页查询
            try {
                searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
                // 查询匹配
                SearchHits hits = searchResponse.getHits();
                total=hits.getTotalHits().value;
                if(searchCount==1 || searchCount==2)
                {
                    for (SearchHit hit : hits) {
                        //输出每条查询的结果信息
                        dataList.add(hit.getSourceAsMap());
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                Map returnMap=new HashMap();
                returnMap.put("error",e.getMessage());
                return returnMap;
            }
            String scrollId = searchResponse.getScrollId();
            //因为已经查询过一次,所以滚动查询从2开始
            for (int i = 2; i <= searchCount; i++)
            {
                //读取返回的scroll id，该id指向保持活动状态的搜索上下文，在以下搜索scroll调用中将需要它
                scrollId = searchResponse.getScrollId();
                //创建一个新的搜索滚动请求，保存最后返回的滚动标识符和滚动间隔
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                //重新设置快照保存时间
                scrollRequest.scroll(scroll);
                //查询下一次结果
                try {
                    searchResponse = restHighLevelClient.scroll(scrollRequest, RequestOptions.DEFAULT);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    Map returnMap=new HashMap();
                    returnMap.put("error",e.getMessage());
                    return returnMap;
                }
                if(i==searchCount-1 || i==searchCount)
                {
                    SearchHits hits = searchResponse.getHits();
                    for (SearchHit hit : hits) {
                        dataList.add(hit.getSourceAsMap());
                    }
                }
            }
            //完成滚动后，清除保存的scroll快照
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.addScrollId(scrollId);
            try {
                ClearScrollResponse clearScrollResponse = restHighLevelClient.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
            } catch (Exception e) {
                log.error(e.getMessage());
                Map returnMap=new HashMap();
                returnMap.put("error",e.getMessage());
                return returnMap;
            }
            List<Map> scrollPageList = getScrollPageList(fromStart, fromEnd, dataListStart, dataList);
            Map returnMap=new HashMap();
            returnMap.put("dataList",scrollPageList);
            returnMap.put("total",total);
            returnMap.put("number",pageNumber);
            return returnMap;
        }
    }



    public static  List<Map> getScrollPageList(int fromStart,int fromEnd,int dataListStart,List<Map> dataList)
    {
        log.info("fromStart:{},fromEnd:{},dataListStart:{},dataListSize:{}",fromStart,fromEnd,dataListStart,dataList.size());
        List<Map> returnList=new ArrayList<>();
        for(Map map:dataList)
        {
            if(dataListStart>fromEnd)
            {
                break;
            }
            if(dataListStart>=fromStart && dataListStart<=fromEnd)
            {
                returnList.add(map);
            }
            dataListStart++;
        }
        log.info("returnList大小为:{}",returnList.size());
        return returnList;
    }

    public  static void testSearchRequest(RestHighLevelClient client) throws IOException {

        // 创建搜索请求对象
        SearchRequest searchRequest = new SearchRequest("pinecone_lc_message");
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置排序
//        sourceBuilder.sort("createTime", SortOrder.DESC);

        //设置多条件查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("messageType.keyword","NORMAL"));

        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);


        System.out.println(sourceBuilder);

        System.out.println("---------------------");
        System.out.println(searchRequest);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    }

}
