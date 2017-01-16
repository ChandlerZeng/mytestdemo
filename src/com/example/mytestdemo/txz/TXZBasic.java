package com.example.mytestdemo.txz;

public class TXZBasic 
{   
    // "请说第几个选择或说取消"
    public final static int ACTION_TYPE_ITEM_CANCEL = 0; 
    public final static int ACTION_TYPE_ITEM_1= 1;
    public final static int ACTION_TYPE_ITEM_2 = 2;
    public final static int ACTION_TYPE_ITEM_3 = 3;
    public final static int ACTION_TYPE_ITEM_4 = 4;
    public final static int ACTION_TYPE_ITEM_5 = 5;
    public final static int ACTION_TYPE_ITEM_6 = 6;      
      
    // 同行者-地图操作
    public final static int ACTION_TYPE_REALTIMETRAFFIC_OPEN = 20;// 打开/显示/开启实时路况
    public final static int ACTION_TYPE_REALTIMETRAFFIC_CLOSE = 21;// 关闭/退出/取消实时路况
    
    public final static int ACTION_TYPE_CHANGEROUTETYPE_FAST = 22;// 切换成高速路线
    public final static int ACTION_TYPE_CHANGEROUTETYPE_SHORT = 23;// 切换成最短路线
    public final static int ACTION_TYPE_CHANGEROUTETYPE_ECONOMIC = 24;// 切换成经济路线
    public final static int ACTION_TYPE_CHANGEROUTETYPE_OPTIMAL = 25;// 切换成推荐路线
    
    public final static int ACTION_TYPE_VIEWADDRESSBOOK_OPEN = 26;// 查看地址薄/打开我的地址/地址薄/选择地址/查看保存的地址
    public final static int ACTION_TYPE_VIEWADDRESSBOOK_CLOSE = 27;// 关闭地址簿
    
    public final static int ACTION_TYPE_HISTORYRECORD_OPEN = 28;// 历史记录/打开历史记录/查看历史记录
    public final static int ACTION_TYPE_HISTORYRECORD_CLOSE = 29;// 关闭历史记录
    
    public final static int ACTION_TYPE_USUALLYPLACE_OPEN = 30;// 打开常用点
    public final static int ACTION_TYPE_USUALLYPLACE_CLOSE = 31;// 关闭常用点
    
    public final static int ACTION_TYPE_GO_HOME = 32;// 回家/我要回家
    public final static int ACTION_TYPE_GO_TO_COMPANY = 33;// 单位/去单位/公司/去公司
    
    public final static int ACTION_TYPE_VIEWROUTE = 34;// 概览路线/查看和预览路线/查看经过道路/查看路线/查看全程/经过道路
    public final static int ACTION_TYPE_BACK_TO_MAP = 35;// 回地图/返回地图
    
    public final static int ACTION_TYPE_ROUTEPLAN_OPEN = 36; // 路线规划/路径规划
    public final static int ACTION_TYPE_ROUTEPLAN_CLOSE = 37;// 关闭（退出）路线规划/路径规划
    
    public final static int ACTION_TYPE_EAGELVIEW_OPEN = 38;// 打开鹰眼模式    
    public final static int ACTION_TYPE_DOUBLESCREEN_OPEN = 40;// 打开双屏(模式)    
    public final static int ACTION_TYPE_SINGLESCREEN = 42; // 单屏模式
    
    public final static int ACTION_TYPE_DETAIL_OPEN = 43; // 查看地址详情/查看详情/打开详情
    public final static int ACTION_TYPE_DETAIL_CLOSE = 44;// 关闭详情栏
    
    public final static int ACTION_TYPE_CHANGEVIEWMODE = 45;// 切换地图模式/切换显示方式/切换视图    
        
    public final static int ACTION_TYPE_SCALEMAP_ZOOMOUT = 46; // 缩小/地图缩小/缩小地图
    public final static int ACTION_TYPE_SCALEMAP_ZOOMIN = 47; // 放大/地图放大/放大地图
    
    public final static int ACTION_TYPE_BROADCASTAGAIN = 48; // 再说一次/重新播报/我没听清楚
    public final static int ACTION_TYPE_NEXTSERVICE = 49; // 距离下一服务区多远/服务区还有多远/前方服务区距离
    
    public final static int ACTION_TYPE_CHANGEPAGE_PREVIOUS = 50; // 上一页/前一页
    public final static int ACTION_TYPE_CHANGEPAGE_NEXT = 51; // 下一页/后一页
    

}
