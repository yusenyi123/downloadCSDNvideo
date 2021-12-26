package cn.edu.njau.webdemo.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessStreamHandle extends Thread{

    public static void main(String[] args) {
        String str=new String("1.00");
        System.out.println(Integer.valueOf(str));
    }


	private InputStream is;
	private List<String> list;//用于保存输出流信息，方便程序读取使用
	public ProcessStreamHandle(InputStream is,List<String> list) {
		this.is = is;
		this.list = list;
	}
	@Override
	public void run() {
		BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
        try {   
            String line1 = null;   
            while ((line1 = br1.readLine()) != null) {  
            	list.add(line1);
            }   
        }catch (IOException e) {
             e.printStackTrace();   
        }finally{   
             try {   
            	 is.close();//关闭通道，避免因过多通道未关而产生：java.io.IOException: Too many open files异常   
             } catch (IOException e) {   
                e.printStackTrace();   
             }   
        }   
   }   
}