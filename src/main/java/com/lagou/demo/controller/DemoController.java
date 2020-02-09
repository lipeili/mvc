package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotations.LagouAutowired;
import com.lagou.edu.mvcframework.annotations.LagouController;
import com.lagou.edu.mvcframework.annotations.LagouRequestMapping;
import com.lagou.edu.mvcframework.annotations.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 可以通过的url：http://localhost:8080/demo/pass?username=gege&name=666
 * 不可以通过的url：可以通过的url：http://localhost:8080/demo/pass?username=666&name=666
 *
 * 不可以通过的url：可以通过的url：http://localhost:8080/demo/query
 */
@LagouController
@LagouRequestMapping("/demo")
public class DemoController {


    /**
     * 可以通过的url：http://localhost:8080/demo/pass?username=gege&name=666
     * 不可以通过的url：可以通过的url：http://localhost:8080/demo/pass?username=666&name=666
     *
     * 不可以通过的url：可以通过的url：http://localhost:8080/demo/query
     */
    @LagouAutowired
    private IDemoService demoService;


    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param name
     * @return
     */
    @LagouRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response,String name) {
        return demoService.get(name);
    }

    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param name
     * @return
     */
    @Security({"lisi","gege"})
    @LagouRequestMapping("/pass")
    public String pass(HttpServletRequest request, HttpServletResponse response,String name) {
        return demoService.get(name);
    }

    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param name
     * @return
     */
    @Security("gege")
    @LagouRequestMapping("/deny")
    public String deny(HttpServletRequest request, HttpServletResponse response,String name) {
        return demoService.get(name);
    }
}
