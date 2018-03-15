package com.tsalapova.notebook.controller;

import com.tsalapova.notebook.util.PageConstant;
import com.tsalapova.notebook.util.SessionConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Controller
public class FrontController {
    private static Log log = LogFactory.getLog(FrontController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/lang")
    public String lang(@RequestParam("lang") String lang, HttpSession session) {
        session.setAttribute(SessionConstant.LANG, lang);
        log.info("Language changed to: "+lang);
        return PageConstant.MAIN;
    }
}
