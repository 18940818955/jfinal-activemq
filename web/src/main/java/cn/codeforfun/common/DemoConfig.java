package cn.codeforfun.common;

import cn.codeforfun.controller.IndexController;
import cn.codeforfun.jfinal.mq.activemq.core.ActiveMqPlugin;
import cn.codeforfun.mq.WeChatQueue;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.render.JspRender;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {

    /**
     * 配置常量
     */
    public void configConstant(Constants me) {
        // 加载少量必要配置，随后可用PropKit.get(...)获取值
        PropKit.use("config.properties");
        me.setViewType(ViewType.JSP);
        JspRender.setSupportActiveRecord(true);
        me.setDevMode(PropKit.getBoolean("devMode"));
    }

    /**
     * 配置路由
     */
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);    // 第三个参数为该Controller的视图存放路径
//		me.add("/blog", BlogController.class);			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
    }


    /**
     * 配置插件
     */
    public void configPlugin(Plugins me) {
        ActiveMqPlugin activeMqPlugin = new ActiveMqPlugin();
        WeChatQueue queue = new WeChatQueue("WeChatQueue");
        activeMqPlugin.setQueue(queue);
        me.add(activeMqPlugin);
    }

    /**
     * 配置全局拦截器
     */
    public void configInterceptor(Interceptors me) {
    }

    /**
     * 配置处理器
     */
    public void configHandler(Handlers me) {

    }

    /**
     * 建议使用 JFinal 手册推荐的方式启动项目
     * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
     */
//    public static void main(String[] args) {
//        JFinal.start("WebRoot", 80, "/", 5);
//    }
}
