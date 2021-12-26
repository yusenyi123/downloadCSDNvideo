package cn.edu.njau.webdemo.test.util;

import java.io.*;

public class CmdUtil {


    public static void main(String[] args) {
        aaa("cmd.exe /C C:\\Users\\sen\\Downloads\\Compressed\\N_m3u8DL-CLI_v2.9.9_with_ffmpeg_and_SimpleG\\N_m3u8DL-CLI_v2.9.9.exe   https://vedu.csdnimg.cn/db440f5f18424520880e92756bf5576f/da75886450f6430fe4abe44c1669089b-S00000001-100000-od-encrypt-stream.m3u8?MtsHlsUriToken=jiFNO59vuCWudq7R-E2gAJUhC4_LrsiR_vo6Y8fxBVPMycJePAFF6fsnacbSPTqJ3vDeI9DjTienXqVYoiVFlQ   --workDir C:\\Users\\sen\\Desktop\\csdn视频下载\\Downloads   --saveName  4.inter8086处理器\n");
    }
    //这个方法比第一个好，执行时不会卡 stmt要执行的命令
    public static void aaa(String stam){
        BufferedReader br = null;
        try {
            File file = new File("e:\\daemonTmp");
            File tmpFile = new File("e:\\daemonTmp\\temp.tmp");//新建一个用来存储结果的缓存文件
            if (!file.exists()){
                file.mkdirs();
            }
            if(!tmpFile.exists()) {
                tmpFile.createNewFile();
            }
            ProcessBuilder pb = new ProcessBuilder().command("cmd.exe", "/c", stam).inheritIO();
            pb.redirectErrorStream(true);//这里是把控制台中的红字变成了黑字，用通常的方法其实获取不到，控制台的结果是pb.start()方法内部输出的。
            pb.redirectOutput(tmpFile);//把执行结果输出。
            pb.start().waitFor();//等待语句执行完成，否则可能会读不到结果。
            InputStream in = new FileInputStream(tmpFile);
            br= new BufferedReader(new InputStreamReader(in));
            String line = null;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            br = null;
            tmpFile.delete();//卸磨杀驴。
            System.out.println("执行完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
