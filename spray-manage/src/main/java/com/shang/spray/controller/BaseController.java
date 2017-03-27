package com.shang.spray.controller;

import com.shang.spray.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * info:
 * Created by shang on 16/7/8.
 */
public class BaseController {

    protected final Logger _logger = LoggerFactory.getLogger(getClass());


    @Autowired
    protected NewsService newsService;
    @Autowired
    protected BeautifulService beautifulService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected SourcesService sourcesService;
    @Autowired
    protected FunnyService funnyService;
    @Autowired
    protected ConfigService configService;
    @Autowired
    protected TypeService typeService;


    public HashMap<String,Object> createMap() {
        return new HashMap<String,Object>();
    }

    /**
     * 异常日志扑捉打印
     */
    public String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
