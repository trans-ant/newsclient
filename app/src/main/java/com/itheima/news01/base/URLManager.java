package com.itheima.news01.base;

/**
 * url管理类
 *
 * @author WJQ
 */
public class URLManager {

    // 请求的url格式：
    // http://c.m.163.com/nc/article/headline/新闻类别id/偏移量-每页条数.html

    // 请求的url示例：
    // 头条： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
    // 头条： 第2页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348647909107/20-20.html
    // 头条： 第3页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348647909107/40-20.html

    // 社会： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348648037603/0-20.html
    // 科技： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348649580692/0-20.html
    // 财经： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348648756099/0-20.html
    // 体育： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348649079062/0-20.html
    // 汽车： 第1页，一页有20条
    // http://c.m.163.com/nc/article/headline/T1348654060988/0-20.html

    // 支持的一些新闻类别类别id如下：
    public final String[] channelId = new String[] {
            "T1348647909107",   // 头条
            "T1348648037603",   // 社会
            "T1348649580692",   // 科技
            "T1348648756099",   // 财经
            "T1348649079062",   // 体育
            "T1348654060988",   // 汽车
    };

    /**
     * 获取一页新闻数据
     * @param newsCategoryId 新闻类别id
     * @return
     */
    public static String getUrl(String newsCategoryId) {
        // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        return "http://c.m.163.com/nc/article/headline/" + newsCategoryId + "/0-20.html";
    }

    /**
     * 获取分页数据
     *
     * @param newsCategoryId 新闻类别id
     * @param pageNo 获取第几页数据
     * @param pageSize 每页数据有多少条
     * @return
     */
    public static String getUrl(String newsCategoryId, int pageNo, int pageSize) {
        // 第1页： 偏移量为 0,     1页10条,
        // 第2页： 偏移量为 10,    1页10条,
        // 第3页： 偏移量为 20,    1页10条,
        int offset = (pageNo -1) * pageSize;
        return "http://c.m.163.com/nc/article/headline/"
                + newsCategoryId + "/"+ offset +"-"+ pageSize +".html";
    }

    /**
     * 获取分页数据
     *
     * @param newsCategoryId 新闻类别id
     * @param pageNo 获取第几页数据
     * @return
     */
    public static String getUrl(String newsCategoryId, int pageNo) {
        int pageSize = 10;
        // 第1页： 偏移量为 0,     1页10条,
        // 第2页： 偏移量为 10,    1页10条,
        // 第3页： 偏移量为 20,    1页10条,
        int offset = (pageNo -1) * pageSize;
        return "http://c.m.163.com/nc/article/headline/"
                + newsCategoryId + "/"+ offset +"-"+ pageSize +".html";
    }

    // 视频url路径
    public static final String VideoURL = //
//            "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-20.html";
            "http://192.168.56.1:8080/0-20.html";
}