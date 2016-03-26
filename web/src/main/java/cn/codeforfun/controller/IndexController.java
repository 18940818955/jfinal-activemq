package cn.codeforfun.controller;

import cn.codeforfun.mq.WeChatQueue;
import com.jfinal.core.Controller;

/**
 * Created by Administrator on 2016/3/24.
 */
public class IndexController extends Controller {
    public void index() {
        renderJsp("index.jsp");
    }

    public void send() {
        WeChatQueue.sendMessage(getPara("param"));
        setAttr("result", "success");
        renderJson();
    }
}
