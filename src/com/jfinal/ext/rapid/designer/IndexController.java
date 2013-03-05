package com.jfinal.ext.rapid.designer;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;
public class IndexController extends Controller {
    protected final Logger logger = Logger.getLogger(getClass());

	public  void index(){
		logger.debug("debug");
		render("/index.html");
	}
	
}
