package cn.edu.njau.webdemo.test.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;

public class HttpUtil {


    public static  Response getCsdnCourseList(int pageNum) throws IOException {
        String urlTemplate="https://edu-core-api.csdn.net/cms/v2/getCourseList?channelType=1&page=%s";
         String url=String.format(urlTemplate,pageNum);
         String cookie="uuid_tt_dd=10_6140429900-1639014396621-857336; c_dl_um=-; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%7D; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_6140429900-1639014396621-857336; __gads=ID=993a08b80d36c8de-22f8016f63cf00fb:T=1639116414:RT=1639116414:S=ALNI_MaIyRdP9Y6CDJHh_cyQJCbLFA6zxA; c_dl_prid=1639365048609_565759; c_dl_rid=1639365677275_792860; c_dl_fref=https://www.baidu.com/link; c_dl_fpage=/download/colin3dmax/11390738; UserName=qq_42532156; UserInfo=21902f430a8e438b980f31d5166f61de; UserToken=21902f430a8e438b980f31d5166f61de; UserNick=qq_42532156; AU=4B0; UN=qq_42532156; BT=1639962466243; p_uid=U010000; ssxmod_itna=eqIxniDQqmq4BDlRQ+qG=eKM4m2CxCqFpxwnDDsqDcexA5D8D6DQeGTruKNor1oS032wiEKb04qGLYI7a55En0YphveKKooGLDmKDyYn2RpYD4SKGwD0eG+DD4DWUx03DoxGAMvx04CMnwNBWqGRD0YDzqDgD7j4hqDEDG3D04tP4oxB7qG0DDtDAd2NoeDADA3BRuLPdj2ioYXxKDtDjqGgDBdFn1uNy6ye/dNOE1It4xDHS7Dp50DpZ0GtDYpWWd2exBQD7qRMDLrpvd0ZtLSsQYpN+0vtmGpaY7G5CYx4U0G67DS+UA5p7D1B4j0zd25aYhF0Wq7xV044D===; ssxmod_itna2=eqIxniDQqmq4BDlRQ+qG=eKM4m2CxCqFpxuqikAq1Dcl7D0y0d03K1c7Oe2D6QAF+86/PC7NsgK3qA08oolQgb+Vm+nBuI7hfpgc6VRgjOcI56SLLO23LgmfMnAh5Wt7vOcMcOgXyD71O=mTP2Hbno7QPzy+=KY+DK4Go/nPDz7873osWSowTcxqulW5enrAVE78Zenf+2f1x/fwRIa6nKMW2iFiRmd0M4F0ZtL0GgkG3wja6DHtPEAWV2FQKD1GFTZXHdKyx+=77V5g1kZuXou955TSxamB8ytBP1FQWvuSu31MyvLZl5V3phQ9iS5jtY3D07q7ee7Du7YqYxYgOtjDrCDxh+FjKNAqD7=DYF4eD===; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1639125106,1640257978,1640264258,1640265245; dc_session_id=10_1640342366359.798787; c_first_ref=default; c_first_page=https%3A//edu.csdn.net/learn/4545; c_segment=8; dc_sid=f6869a25246971070a3d0ff415d94b86; log_Id_click=46; log_Id_view=446; c_pref=https%3A//edu.csdn.net/learn/1997/31048; c_ref=https%3A//edu.csdn.net/course%3Fkey%3D%25E5%25B5%258C%25E5%2585%25A5%25E5%25BC%258F%25E8%25BD%25AF%25E4%25BB%25B6%25E5%25B7%25A5%25E7%25A8%258B%25E5%25B8%2588%25E5%25AE%258C%25E5%2585%25A8; c_page_id=default; dc_tos=r4mavw; log_Id_pv=325";
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



    public static  void test1() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://bizapi.csdn.net/edu-academy-web/v1/material/info?materialId=214363&courseId=4545&cId=4545&playerVersion=2&isFree=2&isMember=2")
                .method("GET", null)
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader("accept-encoding", "gzip, deflate, br")
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .addHeader("cookie", "uuid_tt_dd=10_6140429900-1639969770257-216531; c_first_ref=www.baidu.com; c_first_page=https%3A//edu.csdn.net/help; c_segment=3; dc_sid=85e0e3407d43ceca9538ba13d9269e88; SESSION=6494c9d7-67ea-4203-9864-04ca60e5fba4; UserName=qq_42532156; UserInfo=9cad350a2666468ea469b4454e234e8b; UserToken=9cad350a2666468ea469b4454e234e8b; UserNick=qq_42532156; AU=4B0; UN=qq_42532156; BT=1639969752850; p_uid=U010000; ssxmod_itna=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7UDBwiPiNDnD8x7YDvmINYpKK50Bmwa4cChuXFvKc+G4QLWAr23Q=qtG+eDHxY=DUErkKoD4SKGwD0eG+DD4DWUx03DoxGYMIx0RMSg6ykExiOD7eDXxGCDQKhXxGWDiPD7ZIKpQRkLxi7DD5DnhAvq4DWDWckBgp+eRYA8GvxXxG1DQ5Ds2DU8SOUMt+sBPLxuQGqDBbxYwDR7Qi4DmR3EhY+DCKDjZA1G2YRRxAzIP6N5j4nbQ+NT0id8le55Q+K7CGe1GG4tGxoFj0xnGq/hZUvPF54dPxKhP00PeD==; ssxmod_itna2=Yq+hGIx0xfxUhEDz1Yb40K0QGCB7t5PD7CKvf7D8T1xqGXqIqGaKnf1UNzx8h7e6zQAr0hNq45We9Npn7QRvKhcQ+WYcLxM6CLi4QsP9oxt2fFm52R52idBaL+HfZBb5PXHQlc/2k8otekY8KlfzWGIyiWrLtfvylbxsWndKfetZi+fR8+D9jqoRSqbPZnvl+qRyF=a0YeY=rWc=MOUQlOoHM8FYZ8uB3+qE4dUxzr7iTUYqcGg0/nikc4i1d+Hlj=QL4mI5ht78epL/0GDIMPuR16CuT5x2VE1hRdHUlSAR5tCqAQQMZQ+Df==AxmX2imx0Y7QG8AxP1P5ttXn0lGw==r=WfHQGGjrSW5ebDfB2=ONLrmYze+Q0ZApK9jXn53EP2lIx4wx6RHQYMxq+Y3wOwwQnueWcQIG7XVP6+GoPIrs=IEtW=ljGeeQAN7xeUFKmhXmRP74Ngru06affpcOw+ADaDG200FdFiB0xKmK32+fM74qzcdsgDRmczswKQoA0iT5yYijoairqRqKeK3D=YxIbben3IXQDFqD+=4xD; log_Id_click=2; dc_session_id=10_1639997360319.237197; c_utm_source=studyvip_pcxd; log_Id_view=1; c_pref=https%3A//edu.csdn.net/learn/4545; c_ref=https%3A//edu.csdn.net/help; c_page_id=default; dc_tos=r4ey7u; log_Id_pv=8")
                .addHeader("origin", "https://edu.csdn.net")
                .addHeader("referer", "https://edu.csdn.net/learn/4545")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Microsoft Edge\";v=\"96\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36 Edg/96.0.1054.62")
                .addHeader("x-ca-key", "203866374")
                .addHeader("x-ca-nonce", "9e4872cd-b6df-46c2-8a8b-a1a15ce3243f")
                .addHeader("x-ca-signature", "15JnDWNkL7JRM0A1EjW4ptleY1IR4ECXUFNkp1R8D0s=")
                .addHeader("x-ca-signature-headers", "x-ca-key,x-ca-nonce")
                .build();
        Response response = client.newCall(request).execute();
    }
}
