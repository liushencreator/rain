package util.common;

/**
 * 分页参数工具类
 *
 * @author raojing
 * @date 2019/8/8 22:01
 */
public class PageParamsUtil {

    /**
     * 构建基本的分页参数和排序字段
     *
     * @param pageNumber 页码
     * @param pageSize 一页的记录数
     * @param orderByRule 分页规则（可以为空）
     * @return
     */
    public static Paramap buildPageParamAndSort(int pageNumber, int pageSize, String orderByRule){
        Paramap paramap = Paramap.create();
        paramap.put("pageBegin", (pageNumber - 1) * pageSize);
        paramap.put("pageSize", pageSize);
        paramap.put("orderByRule", orderByRule);
        return paramap;
    }


    /**
     * 构建基本的分页参数
     *
     * @param pageNumber 页码
     * @param pageSize 一页的记录数
     * @return
     */
    public static Paramap buildPageParam(int pageNumber, int pageSize){
        Paramap paramap = Paramap.create();
        paramap.put("pageBegin", (pageNumber - 1) * pageSize);
        paramap.put("pageSize", pageSize);
        return paramap;
    }
}
