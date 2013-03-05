package com.jfinal.ext.rapid.designer.entity;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class EntityValidator extends Validator {
	
	protected void validate(Controller controller) {
			validateString("entity.name", 0, 20, "nameErrMsg", "长度超过指定范围");
			validateRequired("entity.name", "nameErrMsg", "字段不能为空");
			validateString("entity.type", 0, 10, "typeErrMsg", "长度超过指定范围");
			validateRequired("entity.type", "typeErrMsg", "字段不能为空");
			validateString("entity.label", 0, 200, "labelErrMsg", "长度超过指定范围");
			validateRequired("entity.label", "labelErrMsg", "字段不能为空");
			validateString("entity.service_id", 0, 10, "service_idErrMsg", "长度超过指定范围");
			validateRequired("entity.service_id", "service_idErrMsg", "字段不能为空");
			
	}
	
	protected void handleError(Controller controller) {
		
		controller.keepModel(Entity.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/entity/save"))
			controller.render("add.html");
		else if (actionKey.equals("/entity/update"))
			controller.render("edit.html");
	}
}
