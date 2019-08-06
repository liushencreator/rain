package com.rao.test;

import com.rao.Utils.capture.CaptureImageUtils;
import com.rao.bean.capture.CaptureNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lenovo on 2019/1/29.
 */
public class MyTest {

    @Test
    public void test004(){
        List<String> listOne = new ArrayList<>();
        listOne.add("java");
        listOne.add("php");
        listOne.add("python");
        listOne.add("oracle");
        listOne.add("mysql");
        listOne.add("go");
        listOne.add("linux");
        listOne.add("tomcat");
        listOne.add("nginx");
        listOne.add("zookeeper");


        List<String> listTwo = new ArrayList<>();
        listTwo.add("java");
        listTwo.add("php");
        listTwo.add("python");
        listTwo.add("oracle");
        listTwo.add("mysql");
        listTwo.add("go");
        listTwo.add("aaaaaaaaaa");
        listTwo.add("bbbbbbbbbb");

        System.out.println("处理前的数据");
        System.out.println(listOne);
        System.out.println(listTwo);

        List<String> collectAdd = listOne.stream().filter(item -> !listTwo.contains(item)).collect(Collectors.toList());
        List<String> collectRem = listTwo.stream().filter(item -> !listOne.contains(item)).collect(Collectors.toList());

        System.out.println("=====================================================");
        System.out.println("增加的：" + collectAdd);
        System.out.println("删除的：" + collectRem);

        listTwo.addAll(collectAdd);
        listTwo.removeAll(collectRem);

        System.out.println("=====================================================");
        System.out.println("处理后的数据");
        System.out.println(listOne);
        System.out.println(listTwo);
    }


    @Test
    public void test003() throws Exception {
        List<CaptureNode> nodeList = CaptureImageUtils.captureImage();
        URL url = new URL(nodeList.get(0).getAnode());

        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        String imageName =  "E:/test.jpg";

        FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;

        while ((length = dataInputStream.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        byte[] context=output.toByteArray();
        fileOutputStream.write(output.toByteArray());
        dataInputStream.close();
        fileOutputStream.close();

    }

    @Test
    public void test002() throws IOException {
        Document document = Jsoup.connect("https://wap.mn52.com/wap.php?action=article&id=24193").maxBodySize(0).get();
        Element column = document.getElementsByClass("column").get(0);
        Elements aElements = column.select("div").select("a");
        for (Element aElement : aElements) {
            String href = aElement.attributes().get("href");
            System.out.println(href);
        }
    }

    @Test
    public void test001()throws Exception{
        Document document = Jsoup.connect("http://doc.chacuo.net/iso-3166-1").maxBodySize(0).get();
        Elements elements = document.getElementsByClass("f14");
        Elements tr = elements.select("tbody").select("tr");
        for (Element element : tr) {
            System.out.println(element);


        }
    }

}
