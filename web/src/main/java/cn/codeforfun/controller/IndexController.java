package cn.codeforfun.controller;

import cn.codeforfun.jfinal.mq.activemq.core.JFinalQueue;
import com.jfinal.core.Controller;

/**
 * Created by Administrator on 2016/3/24.
 */
public class IndexController extends Controller {
    public void index() {
        renderJsp("index.jsp");
    }

    public void send() {
        JFinalQueue.sendMessage(getPara("param"));
        setAttr("result", "success");
        renderJson();
    }
}
