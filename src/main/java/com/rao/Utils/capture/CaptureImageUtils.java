package com.rao.Utils.capture;

import com.rao.bean.capture.CaptureNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2019/3/18.
 */
public class CaptureImageUtils {

    public static List<CaptureNode> captureImage(){
        List<CaptureNode> nodeList = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect("https://wap.mn52.com/wap.php?action=article&id=24193").maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element column = document.getElementsByClass("column").get(0);
        Elements aElements = column.select("div").select("a");
        for (Element aElement : aElements) {
            CaptureNode captureNode = new CaptureNode();
            captureNode.setAnode("https:"+aElement.attributes().get("href"));
            nodeList.add(captureNode);
        }
        return nodeList;
    }

}
