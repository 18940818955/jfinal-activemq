package cn.codeforfun.controller;

import com.jfinal.core.Controller;

/**
 * Created by Administrator on 2016/3/24.
 */
public class IndexController extends Controller {
    public void index() {
        renderJsp("index.jsp");
    }
}
