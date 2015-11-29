package com.automateddocsys.pma.web.mvc;

//@Controller
public class DefaultController {

   // @RequestMapping("/**")
    public String notFound() {
        return "errors/404";
    }
}
