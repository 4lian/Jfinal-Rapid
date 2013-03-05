package com.jfinal.ext.rapid.designer.service;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ServiceValidator extends Validator {
	
	protected void validate(Controller controller) {
			validateString("service.name", 0, 20, "nameErrMsg", "长度超过指定范围");
			validateRequired("service.name", "nameErrMsg", "字段不能为空");
			validateString("service.code", 0, 10, "codeErrMsg", "长度超过指定范围");
			validateRequired("service.code", "codeErrMsg", "字段不能为空");
			
	}
	
	protected void handleError(Controller controller) {
		
		controller.keepModel(Service.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/service/save"))
			controller.render("add.html");
		else if (actionKey.equals("/service/update"))
			controller.render("edit.html");
	}
}
