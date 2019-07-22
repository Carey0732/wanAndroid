package com.carey.myapplication.data.net

/**
 * @author Carey
 * @date 2019/7/22
 */
object NetConfig {
    /****************************************************首页相关********************************************************/
    /**
     * 首页文章列表
     */
    val GET_INDEX = "article/list/{page}/json"
    /**
     * 首页banner列表
     */
    val GET_INDEX_BANNER = "/banner/json"
    /**
     * 常用网站
     */
    val GET_COMMON_WEBSITE = "/friend/json"
    /**
     * 搜索热词
     */
    val GET_HOT_KEY = "/hotkey/json"
    /**
     * 置顶文章
     */
    val GET_TOP_ESSAY = "/article/top/json"
    /****************************************************知识体系*******************************************************/
    /**
     * 知识体系列表
     */
    val get_lore_tree = "/tree/json"
    /**
     * 知识体系下的文章
     * cid 分类的id，上述二级目录的id
     */
    val GET_LORE_ESSAY = "/article/list/{page}/json"
    /*******************************************************导航********************************************************/
    /**
     * 导航数据
     */
    val GET_NAVI = "/navi/json"
    /****************************************************公众号相关******************************************************/
    /**
     *获取公众号列表
     */
    val GET_ARTICLE = "/wxarticle/chapters/json"
}