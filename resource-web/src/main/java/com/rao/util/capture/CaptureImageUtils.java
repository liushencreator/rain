package com.rao.util.capture;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.bo.capture.CaptureNodeBO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019/3/18.
 */
public class CaptureImageUtils {

    public static List<CaptureNodeBO> captureImage(){
        List<CaptureNodeBO> nodeList = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect("https://wap.mn52.com/wap.php?action=article&id=24193").maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element column = document.getElementsByClass("column").get(0);
        Elements aElements = column.select("div").select("a");
        for (Element aElement : aElements) {
            CaptureNodeBO captureNode = new CaptureNodeBO();
            captureNode.setAnode("https:"+aElement.attributes().get("href"));
            nodeList.add(captureNode);
        }
        return nodeList;
    }

}
