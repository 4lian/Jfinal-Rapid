package com.jfinal.ext.rapid.designer.project;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ProjectValidator extends Validator {
	
	protected void validate(Controller controller) {
			validateString("project.name", 0, 20, "nameErrMsg", "长度超过指定范围");
			validateRequired("project.name", "nameErrMsg", "字段不能为空");
			validateString("project.desc", 0, 200, "descErrMsg", "长度超过指定范围");
			validateRequired("project.desc", "descErrMsg", "字段不能为空");
			validateString("project.workspace", 0, 200, "workspaceErrMsg", "长度超过指定范围");
			validateRequired("project.workspace", "workspaceErrMsg", "字段不能为空");
			validateString("project.viewFramework", 0, 20, "viewFrameworkErrMsg", "长度超过指定范围");
			validateRequired("project.viewFramework", "viewFrameworkErrMsg", "字段不能为空");
			validateString("project.dbPool", 0, 20, "dbPoolErrMsg", "长度超过指定范围");
			validateRequired("project.dbPool", "dbPoolErrMsg", "字段不能为空");
			validateString("project.ip", 0, 32, "ipErrMsg", "长度超过指定范围");
			validateRequired("project.ip", "ipErrMsg", "字段不能为空");
			validateString("project.prot", 0, 10, "protErrMsg", "长度超过指定范围");
			validateRequired("project.prot", "protErrMsg", "字段不能为空");
			validateString("project.templates", 0, 200, "templatesErrMsg", "长度超过指定范围");
			validateRequired("project.templates", "templatesErrMsg", "字段不能为空");
			validateString("project.webRoot", 0, 200, "webRootErrMsg", "长度超过指定范围");
			validateRequired("project.webRoot", "webRootErrMsg", "字段不能为空");
			validateString("project.src", 0, 200, "srcErrMsg", "长度超过指定范围");
			validateRequired("project.src", "srcErrMsg", "字段不能为空");
			validateString("project.config", 0, 200, "configErrMsg", "长度超过指定范围");
			validateRequired("project.config", "configErrMsg", "字段不能为空");
			validateString("project.dbType", 0, 20, "dbTypeErrMsg", "长度超过指定范围");
			validateRequired("project.dbType", "dbTypeErrMsg", "字段不能为空");
			validateString("project.jdbcurl", 0, 30, "jdbcurlErrMsg", "长度超过指定范围");
			validateRequired("project.jdbcurl", "jdbcurlErrMsg", "字段不能为空");
			validateString("project.username", 0, 20, "usernameErrMsg", "长度超过指定范围");
			validateRequired("project.username", "usernameErrMsg", "字段不能为空");
			validateString("project.password", 0, 20, "passwordErrMsg", "长度超过指定范围");
			validateRequired("project.password", "passwordErrMsg", "字段不能为空");
			validateString("project.packageName", 0, 50, "packageNameErrMsg", "长度超过指定范围");
			validateRequired("project.packageName", "packageNameErrMsg", "字段不能为空");
			validateString("project.viewType", 0, 20, "viewTypeErrMsg", "长度超过指定范围");
			validateRequired("project.viewType", "viewTypeErrMsg", "字段不能为空");
			validateString("project.compleLeve", 0, 4, "compleLeveErrMsg", "长度超过指定范围");
			validateRequired("project.compleLeve", "compleLeveErrMsg", "字段不能为空");
			validateString("project.embedded", 0, 10, "embeddedErrMsg", "长度超过指定范围");
			validateRequired("project.embedded", "embeddedErrMsg", "字段不能为空");
			validateString("project.build", 0, 20, "buildErrMsg", "长度超过指定范围");
			validateRequired("project.build", "buildErrMsg", "字段不能为空");
			
	}
	
	protected void handleError(Controller controller) {
		
		controller.keepModel(Project.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/project/save"))
			controller.render("add.html");
		else if (actionKey.equals("/project/update"))
			controller.render("edit.html");
	}
}
