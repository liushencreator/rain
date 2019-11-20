package com.rao.controller.capture;

import com.rao.util.file.DownLoadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.vo.resource.LocalFilePathNameVO;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地文件
 */
@Controller
@RequestMapping("/web/localFile")
public class WebLocalFileController {

    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public String index(Model model){
        File[] files = File.listRoots();
        List<String> localRoots = new ArrayList<>();
        for (File file : files) {
            localRoots.add(file.getPath().replace("\\","/"));
        }
        model.addAttribute("localRoots",localRoots);
        return "/capture/localFile/index";
    }

    /**
     * 查询
     */
    @GetMapping("/find")
    public String fingList(@RequestParam("path") String path,
                           @RequestParam(value = "oldPath",required = false) String oldPath,
                           Model model) throws Exception {
        File f = new File(path);
        if(!f.exists()) {
            f = new File(oldPath);
        }

        List<LocalFilePathNameVO> dirs = new ArrayList<>();
        List<LocalFilePathNameVO> emptyDirs = new ArrayList<>();
        List<LocalFilePathNameVO> files = new ArrayList<>();

        File[] listFiles = f.listFiles();
        if(listFiles.length > 0) {
            for (File file : listFiles) {
                String realpath = file.getPath();
                realpath = realpath.replaceAll("\\\\", "/");
                LocalFilePathNameVO localDir = new LocalFilePathNameVO();
                LocalFilePathNameVO localEmptyDir = new LocalFilePathNameVO();
                LocalFilePathNameVO localFile = new LocalFilePathNameVO();
                try{
                    if(file.isDirectory()) {
                        if(file.list().length > 0) {
                            localDir.setFilePath(realpath);
                            localDir.setFileName(file.getName());
                            dirs.add(localDir);
                        } else {
                            localEmptyDir.setFilePath(realpath);
                            localEmptyDir.setFileName(file.getName());
                            emptyDirs.add(localEmptyDir);
                        }
                    } else {
                        localFile.setFilePath(realpath);
                        localFile.setFileName(file.getName());
                        files.add(localFile);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
        }

        model.addAttribute("dirs", dirs);
        model.addAttribute("emptyDirs",emptyDirs);
        model.addAttribute("files", files);
        model.addAttribute("root", path);
        if(!"C:/".equals(path) && !"D:/".equals(path) && !"E:/".equals(path) && !"F:/".equals(path)) {
            model.addAttribute("prePath", f.getParent().replaceAll("\\\\", "/"));
        }
        return "/capture/localFile/fileList";
    }

    /**
     * 下载
     */
    @RequestMapping("/download")
    public String downloadFile(String filePath,
                               String fileName,
                               HttpServletResponse response) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            DownLoadUtil.downLoad(response,file,fileName);
        }
        return null;
    }


}
