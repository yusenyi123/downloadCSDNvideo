package cn.edu.njau.webdemo.test.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CsdnVideoUtil {

    public static void main(String[] args) throws Exception {
//        testGetOneCourseAllVideo();
//        testGetCourseCatalogues();
        testGetLessonList();
    }

    public  static void testGetLessonList() throws Exception {
        String url="https://bizapi.csdn.net/edu-academy-web/v1/material/info?materialId=465988&courseId=31165&cId=31165&playerVersion=2&isFree=2&isMember=2";
        String cookie="uuid_tt_dd=10_6140429900-1639969770257-216531; UserName=qq_42532156; UserInfo=9cad350a2666468ea469b4454e234e8b; UserToken=9cad350a2666468ea469b4454e234e8b; UserNick=qq_42532156; AU=4B0; UN=qq_42532156; BT=1639969752850; p_uid=U010000; ssxmod_itna=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7UDBwiPiNDnD8x7YDvmINYpKK50Bmwa4cChuXFvKc+G4QLWAr23Q=qtG+eDHxY=DUErkKoD4SKGwD0eG+DD4DWUx03DoxGYMIx0RMSg6ykExiOD7eDXxGCDQKhXxGWDiPD7ZIKpQRkLxi7DD5DnhAvq4DWDWckBgp+eRYA8GvxXxG1DQ5Ds2DU8SOUMt+sBPLxuQGqDBbxYwDR7Qi4DmR3EhY+DCKDjZA1G2YRRxAzIP6N5j4nbQ+NT0id8le55Q+K7CGe1GG4tGxoFj0xnGq/hZUvPF54dPxKhP00PeD==; ssxmod_itna2=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7D8T1xqGXqIqGaKnf1UNzx8h7e6zQAr0hNq45We9Npn7QRvKhcQ+WYcLxM6CLi4QsP9oxt2fFm52R52idBaL+HfZBb5PXHQlc/2k8otekY8KlfzWGIyiWrLtfvylbxsWndKfetZi+fR8+D9jqoRSqbPZnvl+qRyF=a0YeY=rWc=MOUQlOoHM8FYZ8uB3+qE4dUxzr7iTUYqcGg0/nikc4i1d+Hlj=QL4mI5ht78epL/0GDIMPuR16CuT5x2VE1hRdHUlSAR5tCqAQQMZQ+Df==AxmX2imx0Y7QG8AxP1P5ttXn0lGw==r=WfHQGGjrSW5ebDfB2=ONLrmYze+Q0ZApK9jXn53EP2lIx4wx6RHQYMxq+Y3wOwwQnueWcQIG7XVP6+GoPIrs=IEtW=ljGeeQAN7xeUFKmhXmRP74Ngru06affpcOw+ADaDG200FdFiB0xKmK32+fM74qzcdsgDRmczswKQoA0iT5yYijoairqRqKeK3D=YxIbben3IXQDFqD+=4xD; log_Id_view=53; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1640264126,1640313461; dc_session_id=10_1640444998648.840449; c_first_ref=default; c_first_page=https%3A//edu.csdn.net/course; c_segment=3; c_page_id=default; dc_sid=006dd5804308d17ebec58a83565df902; log_Id_click=48; dc_tos=r4ofgx; log_Id_pv=264";
        String xCaKey="203866374";
        String appSecretKey="Et9j9OntJ0zNRQvFxZoL4N9Y2uPDsoe4";

        getLessonListMap(url,cookie,xCaKey,appSecretKey);
    }


    public  static  void testGetCourseCatalogues() throws Exception {
        String url="https://bizapi.csdn.net/edu-academy-web/v1/material/info?materialId=80712&courseId=4528&cId=4528&playerVersion=2&isFree=2&isMember=2";
        String cookie="uuid_tt_dd=10_6140429900-1639014396621-857336; c_dl_um=-; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%7D; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_6140429900-1639014396621-857336; __gads=ID=993a08b80d36c8de-22f8016f63cf00fb:T=1639116414:RT=1639116414:S=ALNI_MaIyRdP9Y6CDJHh_cyQJCbLFA6zxA; c_dl_prid=1639365048609_565759; c_dl_rid=1639365677275_792860; c_dl_fref=https://www.baidu.com/link; c_dl_fpage=/download/colin3dmax/11390738; UserName=qq_42532156; UserInfo=21902f430a8e438b980f31d5166f61de; UserToken=21902f430a8e438b980f31d5166f61de; UserNick=qq_42532156; AU=4B0; UN=qq_42532156; BT=1639962466243; p_uid=U010000; ssxmod_itna=eqIxniDQqmq4BDlRQ+qG=eKM4m2CxCqFpxwnDDsqDcexA5D8D6DQeGTruKNor1oS032wiEKb04qGLYI7a55En0YphveKKooGLDmKDyYn2RpYD4SKGwD0eG+DD4DWUx03DoxGAMvx04CMnwNBWqGRD0YDzqDgD7j4hqDEDG3D04tP4oxB7qG0DDtDAd2NoeDADA3BRuLPdj2ioYXxKDtDjqGgDBdFn1uNy6ye/dNOE1It4xDHS7Dp50DpZ0GtDYpWWd2exBQD7qRMDLrpvd0ZtLSsQYpN+0vtmGpaY7G5CYx4U0G67DS+UA5p7D1B4j0zd25aYhF0Wq7xV044D===; ssxmod_itna2=eqIxniDQqmq4BDlRQ+qG=eKM4m2CxCqFpxuqikAq1Dcl7D0y0d03K1c7Oe2D6QAF+86/PC7NsgK3qA08oolQgb+Vm+nBuI7hfpgc6VRgjOcI56SLLO23LgmfMnAh5Wt7vOcMcOgXyD71O=mTP2Hbno7QPzy+=KY+DK4Go/nPDz7873osWSowTcxqulW5enrAVE78Zenf+2f1x/fwRIa6nKMW2iFiRmd0M4F0ZtL0GgkG3wja6DHtPEAWV2FQKD1GFTZXHdKyx+=77V5g1kZuXou955TSxamB8ytBP1FQWvuSu31MyvLZl5V3phQ9iS5jtY3D07q7ee7Du7YqYxYgOtjDrCDxh+FjKNAqD7=DYF4eD===; firstDie=1; log_Id_view=374; dc_session_id=10_1640257752180.651477; log_Id_click=31; c_utm_source=studyvip_pcxd; utm_source=studyvip_pcxd; dc_sid=a9d73c0c19af04273c2a2f1620aad30d; c_first_ref=default; c_first_page=https%3A//edu.csdn.net/learn/4545; c_segment=8; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1639116414,1639120422,1639125106,1640257978; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1640258565; c_page_id=default; dc_tos=r4kfni; log_Id_pv=216";
        String xCaKey="203866374";
        String appSecretKey="Et9j9OntJ0zNRQvFxZoL4N9Y2uPDsoe4";
        getCourseCataloguesMap(url,cookie,xCaKey,appSecretKey);
    }


    public  static Map<String,Object> getCourseCataloguesMap(String url,String cookie, String xCaKey, String appSecretKey) throws Exception {
        String urlTemplate="https://bizapi.csdn.net/edu-academy-web/v1/common/course/catalogues?courseId=%s";
        Map<String, String> paramMap = getParamMapFrolUrl(url);
        String courseId=paramMap.get("courseId");
        String sendUrl=String.format(urlTemplate,courseId);
        String xCaUrl="/edu-academy-web/v1/common/course/catalogues?courseId="+courseId;
        String xCaNonce=getXCaNonce();
        String xCaSignature=getXCaSignature(xCaKey,xCaNonce,xCaUrl,appSecretKey);
        String  myCookie=getCookie(cookie,0);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(sendUrl)
                .method("GET", null)
                .addHeader("authority", "bizapi.csdn.net")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"")
                .addHeader("x-ca-signature-headers", "x-ca-key,x-ca-nonce")
                .addHeader("x-ca-signature", xCaSignature)
                .addHeader("x-ca-nonce", xCaNonce)
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36")
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader("x-ca-key", xCaKey)
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("origin", "https://edu.csdn.net")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://edu.csdn.net/learn/4545")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("cookie",myCookie)
                .build();
        Response response = client.newCall(request).execute();
        String bodyString = response.body().string();

        JSONObject bodyJsonObject = JSONObject.parseObject(bodyString);
        JSONObject data = bodyJsonObject.getJSONObject("data");
        JSONArray catalogues = data.getJSONArray("catalogues");

        List<String> returnList=new ArrayList<>();
        for (int i=0;i<catalogues.size();i++)
        {
            JSONObject cataloguesJSONObject = catalogues.getJSONObject(i);
            returnList.add(cataloguesJSONObject.getString("materialId"));
        }

        JSONObject baseInfo = data.getJSONObject("baseInfo");
        String title = baseInfo.getString("title");
        String lecturerRealName = baseInfo.getString("lecturerRealName");
        title=title+"("+lecturerRealName+")";
        Map<String,Object> returnMap=new HashMap();
        returnMap.put("list",returnList);
        returnMap.put("title",title);
        return  returnMap;
    }

    public static  void downloadCourseAllVideo(String ffmpegPath,String url,String cookie, String xCaKey, String appSecretKey,int startIndex,int endIndex) throws Exception {
        Map<String, Object> courseCataloguesMap = getCourseCataloguesMap(url, cookie, xCaKey, appSecretKey);
        List<String> courseCatalogues = (List<String>) courseCataloguesMap.get("list");
        String title = (String) courseCataloguesMap.get("title");
        title=title.replaceAll(" ","");
        System.out.println("当前课程视频所有媒体号");
        System.out.println(courseCatalogues);
        for(int i=startIndex;i<=endIndex;i++)
        {
            String urlPrefix=getUrlPrefix(url);
            String nowVideoMaterialId=courseCatalogues.get(i-1);
            String courseXcaUrl=getCourseXcaUrl(url,Integer.valueOf(nowVideoMaterialId));
            Map<String, String> map = getPlayUrl(cookie, xCaKey, appSecretKey, courseXcaUrl, urlPrefix, i);
            String playUrl = map.get("playUrl");
            String saveVideoName = map.get("saveVideoName");
            saveVideoName=saveVideoName.replaceAll(" ","");
            downloadVideoByN_m3u8DL(ffmpegPath,playUrl,saveVideoName,title);
        }
    }


    public static void   downloadCourseAllVideoByGetLessonListMap(String ffmpegPath,String url,String cookie, String xCaKey, String appSecretKey,int startIndex,int endIndex) throws Exception {
        Map<String, Object> lessonListMap = getLessonListMap(url, cookie, xCaKey, appSecretKey);
        JSONArray lessonList = (JSONArray) lessonListMap.get("list");
        //title作为视频所在的文件夹目录名
        Map<String,String>syllabusMap = ( Map<String,String>) lessonListMap.get("syllabusMap");
        System.out.println("章节信息");
        System.out.println(syllabusMap);
        System.out.println("全部课程信息");
        System.out.println(lessonList);
        int lessonListSize = lessonList.size();
        System.out.println("课程总共"+lessonListSize+"节");
        for(int i=startIndex;i<=endIndex;i++)
        {
            if(i>lessonListSize-1)
            {
                break;
            }
            JSONObject jsonObject = lessonList.getJSONObject(i-1);
            int is_upload_video = jsonObject.getIntValue("is_upload_video");
            String lesson_id = jsonObject.getString("lesson_id");

            String urlPrefix=getUrlPrefix(url);
            String nowVideoMaterialId=lesson_id;
            String courseXcaUrl=getCourseXcaUrl(url,Integer.valueOf(nowVideoMaterialId));
            Map<String, String> map = getPlayUrl(cookie, xCaKey, appSecretKey, courseXcaUrl, urlPrefix, i);
            String playUrl = map.get("playUrl");
            String saveVideoName = map.get("saveVideoName");
            saveVideoName=saveVideoName.replaceAll(" ","");
            String title=syllabusMap.get(String.valueOf(i-1));
            title=title.replaceAll(" ","");

            if(is_upload_video==0)
            {
                System.out.println("视频id为"+lesson_id+"的视频没有上传");
                processNotUploadedVideo(ffmpegPath,saveVideoName,title);
                continue;
            }
            downloadVideoByN_m3u8DL(ffmpegPath,playUrl,saveVideoName,title);
        }

    }


    public static  void processNotUploadedVideo(String ffmpegPath,String saveVideoName,String videoDirName) throws IOException {
        String filePath=ffmpegPath+"\\download\\"+videoDirName+"\\"+saveVideoName+"(未上传).txt";
        File file=new File(filePath);
        File parentFile = file.getParentFile();
        if(parentFile.exists()==false)
        {
            parentFile.mkdirs();
        }
        file.createNewFile();
    }



    public static  Map<String,Object>  getLessonListMap(String url,String cookie, String xCaKey, String appSecretKey) throws Exception {
        String urlTemplate="https://edu-core-api.csdn.net/web/lesson/playInfo?courseId=%s";
        Map<String, String> paramMap = getParamMapFrolUrl(url);
        String courseId=paramMap.get("courseId");
        String sendUrl=String.format(urlTemplate,courseId);
        String xCaUrl="/edu-academy-web/v1/common/course/catalogues?courseId="+courseId;
        String xCaNonce=getXCaNonce();
        String xCaSignature=getXCaSignature(xCaKey,xCaNonce,xCaUrl,appSecretKey);
        String  myCookie=getCookie(cookie,0);

        String refererTemplate="https://edu.csdn.net/learn/%s";
        String referer=String.format(refererTemplate,courseId);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(sendUrl)
                .method("GET", null)
                .addHeader("authority", "edu-core-api.csdn.net")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"")
                .addHeader("x-ca-signature-headers", "x-ca-key,x-ca-nonce")
                .addHeader("x-ca-signature", xCaSignature)
                .addHeader("x-ca-nonce", xCaNonce)
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62")
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader("x-ca-key", xCaKey)
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("origin", "https://edu.csdn.net")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", referer)
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .addHeader("cookie", myCookie)
                .build();
        Response response = client.newCall(request).execute();
        String bodyString = response.body().string();
        JSONObject bodyJsonObject = JSONObject.parseObject(bodyString);
        JSONObject data = bodyJsonObject.getJSONObject("data");
        JSONObject directory = data.getJSONObject("directory");
        JSONObject syllabus_list = directory.getJSONObject("syllabus_list");
        JSONObject lesson_list = directory.getJSONObject("lesson_list");



        JSONArray returnJSONArray=new JSONArray();
        JSONObject course_info = data.getJSONObject("course_info");
        String course_name = course_info.getString("course_name");
        Map<String,String>  syllabusMap=new HashMap<>();
        int i=0;
        if(syllabus_list.isEmpty())
        {
            JSONArray s0 = lesson_list.getJSONArray("s0");
            returnJSONArray=s0;
            for(int j=0;j<s0.size();j++)
            {
                String title=course_name;
                syllabusMap.put(String.valueOf(i),title);
                i++;
            }
        }
        else
        {
            Set<String> keySet = syllabus_list.keySet();
            Set<String> sortSet = new TreeSet<String>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);//升序排序
                }
            });
            sortSet.addAll(keySet);
            for(String key:sortSet)
            {
                System.out.println(key);
                String chapterName = syllabus_list.getString(key);
                JSONArray jsonArray = lesson_list.getJSONArray(key);
                returnJSONArray.addAll(jsonArray);
                for(int j=0;j<jsonArray.size();j++)
                {
                    String title=course_name+"\\"+chapterName;
                    syllabusMap.put(String.valueOf(i),title);
                    i++;
                }
            }
        }

        Map<String,Object> returnMap=new HashMap();
        returnMap.put("list",returnJSONArray);
        returnMap.put("syllabusMap",syllabusMap);
        return returnMap;

    }




    public  static  Map<String,String> getParamMapFrolUrl(String url)
    {
        String[] split = url.split("\\?");
//        System.out.println(Arrays.toString(split));
        String params=split[1];
        String[] split1 = params.split("&");
        Map<String,String > paramMap=new HashMap<>();
        for(String param:split1)
        {
            String[] split2 = param.split("=");
            paramMap.put(split2[0],split2[1]);
        }
        return paramMap;
    }




    public  static  void testGetOneCourseAllVideo() throws Exception {
        String url="https://bizapi.csdn.net/edu-academy-web/v1/material/info?materialId=80712&courseId=4528&cId=4528&playerVersion=2&isFree=2&isMember=2";
        String cookie="uuid_tt_dd=10_6140429900-1639969770257-216531; UserName=qq_42532156; UserInfo=9cad350a2666468ea469b4454e234e8b; UserToken=9cad350a2666468ea469b4454e234e8b; UserNick=qq_42532156; AU=4B0; UN=qq_42532156; BT=1639969752850; p_uid=U010000; ssxmod_itna=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7UDBwiPiNDnD8x7YDvmINYpKK50Bmwa4cChuXFvKc+G4QLWAr23Q=qtG+eDHxY=DUErkKoD4SKGwD0eG+DD4DWUx03DoxGYMIx0RMSg6ykExiOD7eDXxGCDQKhXxGWDiPD7ZIKpQRkLxi7DD5DnhAvq4DWDWckBgp+eRYA8GvxXxG1DQ5Ds2DU8SOUMt+sBPLxuQGqDBbxYwDR7Qi4DmR3EhY+DCKDjZA1G2YRRxAzIP6N5j4nbQ+NT0id8le55Q+K7CGe1GG4tGxoFj0xnGq/hZUvPF54dPxKhP00PeD==; ssxmod_itna2=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7D8T1xqGXqIqGaKnf1UNzx8h7e6zQAr0hNq45We9Npn7QRvKhcQ+WYcLxM6CLi4QsP9oxt2fFm52R52idBaL+HfZBb5PXHQlc/2k8otekY8KlfzWGIyiWrLtfvylbxsWndKfetZi+fR8+D9jqoRSqbPZnvl+qRyF=a0YeY=rWc=MOUQlOoHM8FYZ8uB3+qE4dUxzr7iTUYqcGg0/nikc4i1d+Hlj=QL4mI5ht78epL/0GDIMPuR16CuT5x2VE1hRdHUlSAR5tCqAQQMZQ+Df==AxmX2imx0Y7QG8AxP1P5ttXn0lGw==r=WfHQGGjrSW5ebDfB2=ONLrmYze+Q0ZApK9jXn53EP2lIx4wx6RHQYMxq+Y3wOwwQnueWcQIG7XVP6+GoPIrs=IEtW=ljGeeQAN7xeUFKmhXmRP74Ngru06affpcOw+ADaDG200FdFiB0xKmK32+fM74qzcdsgDRmczswKQoA0iT5yYijoairqRqKeK3D=YxIbben3IXQDFqD+=4xD; log_Id_view=51; c_first_ref=default; c_first_page=https%3A//edu.csdn.net/learn/4545; c_segment=3; dc_sid=ad291265f1f63b0c935e6a5d2e36784e; dc_session_id=10_1640229295022.115747; c_pref=https%3A//edu.csdn.net/learn/4545; c_ref=https%3A//edu.csdn.net/learn/10443; log_Id_click=20; c_page_id=default; dc_tos=r4jt3y; log_Id_pv=145";
        int firstVideoMaterialId=80712;  //开始下载的第一个课程的媒体id,从浏览器上获得
        int videoNum=15; //需要按顺序下载的该课程的媒体数


        String xCaKey="203866374";
        String appSecretKey="Et9j9OntJ0zNRQvFxZoL4N9Y2uPDsoe4";
        String ffmpegPath="C:\\Users\\sen\\Desktop\\csdn视频下载\\bin\\ffmpeg.exe";
        CsdnVideoUtil.getOneCourseAllVideo(ffmpegPath,cookie,xCaKey,appSecretKey,url,firstVideoMaterialId,1,videoNum);
    }


    public static  String getOneVideoPlayUrl(String ffmpegPath,String cookie,String xCaKey,String appSecretKey,String url,int nowVideoMaterialId) throws Exception {
        String urlPrefix=getUrlPrefix(url);
        String courseXcaUrl=getCourseXcaUrl(url,nowVideoMaterialId);
        int downloadNum=0;
        Map<String, String> map = getPlayUrl(cookie, xCaKey, appSecretKey, courseXcaUrl, urlPrefix, downloadNum+1);
        String playUrl = map.get("playUrl");
        return playUrl;
    }

    public  static  void getOneCourseAllVideo(String ffmpegPath,String cookie,String xCaKey,String appSecretKey,String url,int firstVideoMaterialId,int videoIndex,int videoNum) throws Exception {
        int downloadNum=0;
        String beforeVideoId=null;
        int i=0;
        while(true)
        {

            try {
                int nowVideoMaterialId=firstVideoMaterialId+i;
                i++;
                String urlPrefix=getUrlPrefix(url);
                String nowUrl=getCourseXcaUrl(url,nowVideoMaterialId);
                System.out.println(nowUrl);
                Map<String, String> map = getPlayUrl(cookie, xCaKey, appSecretKey, nowUrl, urlPrefix, videoIndex+downloadNum);
                String playUrl = map.get("playUrl");
                String saveVideoName = map.get("saveVideoName");
                String nowVideoId = map.get("videoId");
                System.out.println(playUrl);
                System.out.println(saveVideoName);
                boolean downloadFlag=false;
                if(beforeVideoId==null)
                {
                    beforeVideoId=nowVideoId;
                    downloadFlag=true;
                }
                else
                {
                    int frontVideoId=Integer.valueOf(beforeVideoId);
                    int currentVideoId=Integer.valueOf(nowVideoId);
                    if(currentVideoId-frontVideoId==1)
                    {
                        downloadFlag=true;
                        beforeVideoId=nowVideoId;
                    }
                }
                if(downloadFlag)
                {
                    downloadVideoByN_m3u8DL(ffmpegPath,playUrl,saveVideoName,"video");
                    downloadNum++;
                }
                if(videoIndex+downloadNum-1==videoNum)
                {
                    break;
                }
            }catch (Exception e)
            {
                String message = e.getMessage();
                if(message.equals("filename Invalid argument"))
                {
                    int nowVideoMaterialId=firstVideoMaterialId+i-1;
                    String urlPrefix=getUrlPrefix(url);
                    String nowUrl=getCourseXcaUrl(url,nowVideoMaterialId);
                    System.out.println(nowUrl);
                    Map<String, String> map = getPlayUrl(cookie, xCaKey, appSecretKey, nowUrl, urlPrefix, downloadNum+1);
                    String playUrl = map.get("playUrl");
                    String saveVideoName =""+(downloadNum+1)+".ts";
                    System.out.println(playUrl);
                    System.out.println(saveVideoName);
                    downloadVideo(ffmpegPath,playUrl,saveVideoName);
                    downloadNum++;
                }
            }
        }
    }


    public  static Map<String,String> getPlayUrl(String cookie, String xCaKey, String appSecretKey, String courseXcaUrl, String urlPrefix, int videoIndex) throws Exception {
        String xCaNonce=getXCaNonce();
        String xCaSignature=getXCaSignature(xCaKey,xCaNonce,courseXcaUrl,appSecretKey);
        String  myCookie=getCookie(cookie,videoIndex);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(urlPrefix+courseXcaUrl)
                .method("GET", null)
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader("accept-encoding", "deflate, br")
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .addHeader("cookie", myCookie)
                .addHeader("origin", "https://edu.csdn.net")
                .addHeader("referer", "https://edu.csdn.net/learn/4545")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62")
                .addHeader("x-ca-key", xCaKey)
                .addHeader("x-ca-nonce", xCaNonce)
                .addHeader("x-ca-signature", xCaSignature)
                .addHeader("x-ca-signature-headers", "x-ca-key,x-ca-nonce")
                .build();
//        System.out.println(request.toString());
//        System.out.println(request.headers());
        Response response = client.newCall(request).execute();
//        System.out.println(response.toString());

        String bodyStr=response.body().string();
        System.out.println(bodyStr);
        JSONObject jsonObject = JSONObject.parseObject(bodyStr);
        JSONObject data = jsonObject.getJSONObject("data");
        String title=data.getString("title");
        String saveVideoName=""+videoIndex+"-"+title;
        JSONObject info = data.getJSONObject("info");
        String playUrl=info.getString("playUrl");
        Integer videoId = data.getInteger("id");
        Map<String,String> returnMap=new HashMap<>();
        returnMap.put("playUrl",playUrl);
        returnMap.put("saveVideoName",saveVideoName);
        returnMap.put("videoId",String.valueOf(videoId));
        return  returnMap;
    }


    public  static  void  downloadVideo(String ffmpegPath,String playUrl,String saveVideoName) throws Exception {
        ffmpegPath=ffmpegPath+"\\ffmpeg.exe";
        String cmdStr="cmd.exe /C  %s  -i  %s  %s";
        String formatCmdStr=String.format(cmdStr,ffmpegPath,playUrl,saveVideoName);
        System.out.println("下载命令");
        System.out.println(formatCmdStr);
        Process ps = Runtime.getRuntime().exec(formatCmdStr);
        //获取进程的标准输入流
        InputStream is1 = ps.getInputStream();
        List<String> inputStrList = new ArrayList<String>();
        //获取进程的错误流
        InputStream is2 = ps.getErrorStream();
        List<String> errorStrList = new ArrayList<String>();
        //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
        new ProcessStreamHandle(is1,inputStrList).start();
        new ProcessStreamHandle(is2,errorStrList).start();
        ps.waitFor();//等待线程结束
        System.out.println("下载结束,打印ffmpeg程序的输出");
        System.out.println("ffmpeg程序的info输出");
        printList(inputStrList);
        System.out.println("ffmpeg程序的err输出");
        printList(errorStrList);
        if(errorStrList.size()>0)
        {
            String s = errorStrList.get(errorStrList.size() - 1);
            if(s.contains("Invalid argument"))
            {
                throw  new Exception("filename Invalid argument");
            }
        }
    }


    public  static  void  downloadVideoByN_m3u8DL(String ffmpegPath,String playUrl,String saveVideoName,String videoDirName) throws Exception {
        String N_m3u8DLPath=ffmpegPath+"\\N_m3u8DL\\N_m3u8DL-CLI_v2.9.9.exe";
        String workPath=ffmpegPath+"\\download\\"+videoDirName;
        String cmdStr="cmd.exe /C  start /min \"title\"  %s  %s  --saveName  %s   --workDir %s  --enableDelAfterDone ";
//        String cmdStr="cmd.exe /C %s %s  --saveName  %s";
        String formatCmdStr=String.format(cmdStr,N_m3u8DLPath,playUrl,saveVideoName,workPath);

        String mp4FilePath=workPath+"\\"+saveVideoName+".mp4";
        File mp4File=new File(mp4FilePath);
        if(mp4File.exists())
        {
            System.out.println(mp4FilePath+"  已经存在");
            return;
        }

        System.out.println("下载命令");
        System.out.println(formatCmdStr);
        Process ps = Runtime.getRuntime().exec(formatCmdStr);
        //获取进程的标准输入流
        InputStream is1 = ps.getInputStream();
        List<String> inputStrList = new ArrayList<String>();
        //获取进程的错误流
        InputStream is2 = ps.getErrorStream();
        List<String> errorStrList = new ArrayList<String>();
        //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
        new ProcessStreamHandle(is1,inputStrList).start();
        new ProcessStreamHandle(is2,errorStrList).start();
        ps.waitFor();//等待线程结束
        System.out.println("下载结束,打印ffmpeg程序的输出");
        System.out.println("ffmpeg程序的info输出");
        printList(inputStrList);
        System.out.println("ffmpeg程序的err输出");
        printList(errorStrList);
        String videoFilePath=workPath+"\\"+saveVideoName+".mp4";
        System.out.println("下载的视频路径:"+videoFilePath);
        File videoFile=new File(videoFilePath);
        while(true)
        {
            if(videoFile.exists())
            {
                System.out.println("文件下载完成");
                break;
            }
            else
            {
                System.out.println("文件还没有下载完成");
                Thread.sleep(2000);
            }

        }
    }


    public static void printList(List<String> list){
        for (String string : list) {
            System.out.println(string);
        }
    }

    public static void getM3U8File(String  playUrl) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(playUrl)
                .method("GET", null)
                .addHeader("authority", "vedu.csdnimg.cn")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("accept", "*/*")
                .addHeader("origin", "https://edu.csdn.net")
                .addHeader("sec-fetch-site", "cross-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://edu.csdn.net/learn/4545")
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .build();
        Response response = client.newCall(request).execute();
        String m3u8File=response.body().string();
        String[] split = m3u8File.split("\\r?\\n");
        System.out.println(Arrays.toString(split));
        String decryptUrlStr=split[5];
        String decryptUrl = decryptUrlStr.split(",")[1].substring(4);
        decryptUrl=decryptUrl.substring(1,decryptUrl.length()-1);
        System.out.println(decryptUrl);
        getDecryptStr(decryptUrl);
    }


    public static void getDecryptStr(String decryptUrl) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(decryptUrl)
                .method("GET", null)
                .addHeader("authority", "hls.csdn.net")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("accept", "*/*")
                .addHeader("origin", "https://edu.csdn.net")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://edu.csdn.net/learn/4545")
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }


    public static  String getUrlPrefix(String url)
    {
        int index = StringUtils.ordinalIndexOf(url,"/",3);
        String substring = url.substring(0, index);
        return substring;
    }
    public  static String getCourseXcaUrl(String url,int materialId)
    {

//        int index = StringUtils.ordinalIndexOf(url,"/",3);
//        String substring = url.substring(0, index);
        String urlTemplate="/edu-academy-web/v1/material/info?cId=%s&courseId=%s&isFree=%s&isMember=%s&materialId=%s&playerVersion=%s";


        String[] split = url.split("\\?");
        String params=split[1];
        String[] split1 = params.split("&");
        Map<String,String > paramMap=new HashMap<>();
        for(String param:split1)
        {
            String[] split2 = param.split("=");
            paramMap.put(split2[0],split2[1]);
        }
        String cId=paramMap.get("cId");
        String courseId=paramMap.get("courseId");
        String isMember=paramMap.get("isMember");
        String isFree=paramMap.get("isFree");
        String playerVersion=paramMap.get("playerVersion");
        String returnUrl=String.format(urlTemplate,cId,courseId,isFree,isMember,materialId,playerVersion);
        return returnUrl;
    }

    public  static  String getCookie(String originalCookie,int videoIndex)
    {
        Map<String,String> cookieMap=new HashMap<>();
        String[] split = originalCookie.split(";");
        for (String  s:split)
        {
            s=s.trim();
            int i = s.indexOf("=");
            String key=s.substring(0,i);
            String value=s.substring(i+1);
            cookieMap.put(key,value);
        }

        System.out.println(cookieMap);
        Set<String> keySet = cookieMap.keySet();
        String returnCookie="";
        for(String cookieKey:keySet)
        {
            String cookieValue = cookieMap.get(cookieKey);
            if(cookieKey.equals("dc_tos"))
            {
                cookieValue=getLoc();
            }
            if(cookieKey.equals("log_Id_pv"))
            {
                int a=Integer.valueOf(cookieValue);
                cookieValue=String.valueOf(a+videoIndex);
            }
            returnCookie=returnCookie+cookieKey+"="+cookieValue+"; ";
        }
        returnCookie=returnCookie.substring(0,returnCookie.length()-2);
        System.out.println("自己创造的cookie:");
        System.out.println(returnCookie);
        return returnCookie;
    }

    public  static  String getLoc() {
        long a = new Date().getTime();
        a=a/1000;
        String a36 = Long.toString(a, 36);
        return a36;
    }



    public static String getXCaNonce() throws Exception{
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("javascript");
        String str = "function nonceFunc() {\n" +
                "    return \"xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx\".replace(/[xy]/g, (function(e) {\n" +
                "            var n = 16 * Math.random() | 0\n" +
                "                , t = \"x\" === e ? n : 3 & n | 8;\n" +
                "            return t.toString(16)\n" +
                "        }\n" +
                "    ));\n" +
                "}";
        //执行js脚本定义函数
        engine.eval(str);
        Invocable invocable = (Invocable) engine;
        Object res = invocable.invokeFunction("nonceFunc");
        return (String) res;
    }


    public static  String  getXCaSignature(String xCaKey,String xCaNonce,String url,String appSecretKey) throws Exception {
        String dataStr="GET\napplication/json, text/plain, */*\n\n\n\nx-ca-key:%s\nx-ca-nonce:%s\n%s";
        String formatDataStr=String.format(dataStr,xCaKey,xCaNonce,url);
//        System.out.println(formatDataStr);
        String base64Encode = base64Encode(HMACSHA256(formatDataStr, appSecretKey));
        return base64Encode;

    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static byte[] HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        return  array;
    }

    /**
     * 将字节数组经过base64编码获得字符串
     * @param bytes
     * @return
     */
    public  static String  base64Encode(byte[] bytes)
    {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedText = encoder.encodeToString(bytes);
        return encodedText;
    }

}
