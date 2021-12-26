package cn.edu.njau.webdemo.test.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Response;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class CsdnCourseUtil {


    public static void  saveCSDNCourseToEs(RestHighLevelClient restHighLevelClient,String cookie,String saveIndexName,int pageStartIndex,int pageEndIndex) throws IOException { ;
        for (int i = pageStartIndex; i <= pageEndIndex; i++) {
            saveOneListToEs(restHighLevelClient,cookie,saveIndexName, i);
        }
        System.out.println("全部数据保存结束");
    }

    public static void saveOneListToEs(RestHighLevelClient restHighLevelClient,String cookie,String saveIndexName,int pageNum) throws IOException {
        Response csdnCourseList = HttpUtil.getCsdnCourseList(cookie,pageNum);
        JSONObject jsonObject = JSONObject.parseObject(csdnCourseList.body().string());
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray dataList = data.getJSONArray("dataList");
        for(int i=0;i<dataList.size();i++)
        {
            JSONObject dataListJSONObject = dataList.getJSONObject(i);
//            System.out.println(dataListJSONObject);
            JSONObject ext = dataListJSONObject.getJSONObject("ext");
            Boolean isShowStudyVipLogo = ext.getBoolean("isShowStudyVipLogo");
            String goodsId = ext.getString("goodsId");
            int intPrice=0;
            try {
                String price = ext.getString("price");
                String[] split = price.split("\\.");
                intPrice=Integer.valueOf(split[0]);
            }
            catch (Exception e)
            {
                intPrice=0;
            }
            String lecturerName = ext.getString("lecturerName");
            int views = ext.getIntValue("views");
            dataListJSONObject.put("price",intPrice);
            dataListJSONObject.put("lecturerName",lecturerName);
            dataListJSONObject.put("views",views);
            int courseType = ext.getIntValue("courseType");
            System.out.println(goodsId+":"+isShowStudyVipLogo);
            if (isShowStudyVipLogo && courseType==0)
            {
                saveDataToEs(restHighLevelClient, dataListJSONObject,saveIndexName,goodsId);
            }
        }
    }

    public  static  void saveDataToEs(RestHighLevelClient restHighLevelClient, JSONObject jsonObject, String indexName, String esId) throws IOException {
        UpdateRequest updateRequest=new UpdateRequest();
        updateRequest.index(indexName).id(esId);
        String s = jsonObject.toJSONString();
        System.out.println(s);
        updateRequest.doc( s, XContentType.JSON);
        updateRequest.docAsUpsert(true);
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }
}
