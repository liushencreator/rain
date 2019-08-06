package com.rao.controller.capture;

import com.rao.Utils.capture.CaptureImageUtils;
import com.rao.bean.capture.CaptureNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Lenovo on 2019/3/18.
 */
@Controller
@RequestMapping("/web/capture")
public class WebCaptureController {

    @GetMapping("/image")
    public String image(Model model){
        List<CaptureNode> nodeList = CaptureImageUtils.captureImage();
        model.addAttribute("nodeList",nodeList);
        return "/capture/capture/index";
    }


}
