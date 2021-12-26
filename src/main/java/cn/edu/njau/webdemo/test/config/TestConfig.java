package cn.edu.njau.webdemo.test.config;

import cn.edu.njau.webdemo.test.util.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Response;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Value("${ffmpeg.dir}")
    private String ffmpegDir;

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Override
    public void run(String... args) throws Exception {
//        saveAllCSDNCourse(restHighLevelClient);
        System.out.println(ffmpegDir);
    }

    public  void  saveAllCSDNCourse(RestHighLevelClient restHighLevelClient) throws IOException {
        int pageCount=668;
        for(int i=1;i<=pageCount;i++)
        {
            saveOneListToEs(restHighLevelClient,i);
        }
        System.out.println("全部数据保存结束");
    }

    public static void saveOneListToEs(RestHighLevelClient restHighLevelClient,int pageNum) throws IOException {
        Response csdnCourseList = HttpUtil.getCsdnCourseList(pageNum);
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
            dataListJSONObject.put("price",intPrice);
            int courseType = ext.getIntValue("courseType");
            System.out.println(goodsId+":"+isShowStudyVipLogo);
            if (isShowStudyVipLogo && courseType==0)
            {
                saveDataToEs(restHighLevelClient, dataListJSONObject,"csdncourse2",goodsId);
            }
        }
    }

    public  static  void saveDataToEs(RestHighLevelClient restHighLevelClient,JSONObject jsonObject,String indexName,String esId) throws IOException {
        UpdateRequest updateRequest=new UpdateRequest();
        updateRequest.index(indexName).id(esId);
        String s = jsonObject.toJSONString();
        System.out.println(s);
        updateRequest.doc( s, XContentType.JSON);
        updateRequest.docAsUpsert(true);
        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

}
