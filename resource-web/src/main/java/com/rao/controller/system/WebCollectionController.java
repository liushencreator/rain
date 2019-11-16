package com.rao.controller.system;

import constant.resource.CollectionConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.entity.resource.ResourceVideo;
import pojo.entity.resource.SourceCollections;
import pojo.vo.resource.ResourceVideoVO;
import service.resource.ResourceVideoService;
import service.resource.SourceCollectionsService;
import util.common.CheckAgentUtil;
import util.common.TwiterIdUtil;
import util.result.ResultMessage;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 个人收藏 controller
 * Created by Lenovo on 2018/10/5.
 */
@Controller
@RequestMapping("/web/collection")
public class WebCollectionController {

    @Resource
    private SourceCollectionsService sourceCollectionsService;
    @Resource
    private ResourceVideoService resourceVideoService;

    /**
     * 收藏列表
     * @param pageNumber
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestHeader("User-Agent") String userAgent,
                       @RequestParam(defaultValue = "1") Integer pageNumber,
                       @RequestParam(defaultValue = "100") Integer pageSize,
                       Model model){
        List<ResourceVideoVO> page = sourceCollectionsService.listByPage(pageNumber, pageSize);
        model.addAttribute("page",page);

        boolean isMobile = CheckAgentUtil.checkAgentIsMobile(userAgent);
        if(isMobile){
            return "/resource/mobile/collection/collection_list";
        }
        return "/resource/web/resourceList";
    }


    /**
     * 移动端收藏列表
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @PostMapping("/flow_list")
    public ResultMessage mobileList(@RequestParam(defaultValue = "1") Integer pageNumber,
                                    @RequestParam(defaultValue = "10") Integer pageSize){
        List<ResourceVideoVO> page = sourceCollectionsService.listByPage(pageNumber, pageSize);
        Integer totalSize = sourceCollectionsService.count(null);

        int totalPages = totalSize / pageSize;
        return ResultMessage.success()
                .add("list", page)
                .add("totalPages", totalSize % pageSize == 0 ? totalPages : totalPages + 1);
    }


    /**
     * 收藏或取消收藏操作
     * @param resourceId
     * @return
     */
    @ResponseBody
    @RequestMapping("/collectionOperation")
    public ResultMessage collectionOperation(@RequestParam Long resourceId){
        ResourceVideo video = resourceVideoService.find(resourceId);
        if(video==null){
            return ResultMessage.fail().addMessage("资源不存在");
        }
        SourceCollections coll = sourceCollectionsService.find("resourceId", resourceId);
        if(coll!=null){
           try{
               sourceCollectionsService.delete(coll.getId());
           } catch (Exception e){
               e.printStackTrace();
               return ResultMessage.fail().addMessage("取消收藏失败");
           }
           return ResultMessage.success().addMessage("取消收藏成功");
        }

        Date now = new Date();
        SourceCollections collections=new SourceCollections();
        collections.setId(TwiterIdUtil.getTwiterId());
        collections.setCollectionName(video.getVideoName());
        collections.setCollectionPath(video.getVideoPath());
        collections.setCollectionType(CollectionConstant.COLLECTION_TYPE_VIDEO);
        collections.setResourceId(resourceId);
        collections.setCreateTime(now);
        collections.setUpdateTime(now);
        try{
            sourceCollectionsService.save(collections);
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.fail().addMessage("收藏失败");
        }
        return ResultMessage.success().addMessage("收藏成功");
    }

    /**
     * 矫正收藏信息
     * @return
     */
    @ResponseBody
    @PostMapping("/adjust_collection")
    public ResultMessage adjustCollection(){
        try{
            sourceCollectionsService.adjustCollection();
        }catch (Exception e){
            return ResultMessage.fail().addMessage("矫正失败");
        }
        return ResultMessage.success().addMessage("矫正成功");
    }

}
