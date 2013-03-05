package com.jfinal.ext.rapid.designer.field;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class FieldValidator extends Validator {
	
	protected void validate(Controller controller) {
			validateString("field.name", 0, 20, "nameErrMsg", "长度超过指定范围");
			validateRequired("field.name", "nameErrMsg", "字段不能为空");
			validateString("field.label", 0, 50, "labelErrMsg", "长度超过指定范围");
			validateRequired("field.label", "labelErrMsg", "字段不能为空");
			validateString("field.display", 0, 10, "displayErrMsg", "长度超过指定范围");
			validateRequired("field.display", "displayErrMsg", "字段不能为空");
			validateString("field.isPrimaryKey", 0, 10, "isPrimaryKeyErrMsg", "长度超过指定范围");
			validateRequired("field.isPrimaryKey", "isPrimaryKeyErrMsg", "字段不能为空");
			validateString("field.search", 0, 10, "searchErrMsg", "长度超过指定范围");
			validateRequired("field.search", "searchErrMsg", "字段不能为空");
			validateString("field.isReaderOnly", 0, 10, "isReaderOnlyErrMsg", "长度超过指定范围");
			validateRequired("field.isReaderOnly", "isReaderOnlyErrMsg", "字段不能为空");
			validateString("field.isHidder", 0, 10, "isHidderErrMsg", "长度超过指定范围");
			validateRequired("field.isHidder", "isHidderErrMsg", "字段不能为空");
			validateString("field.defaultValue", 0, 20, "defaultValueErrMsg", "长度超过指定范围");
			validateRequired("field.defaultValue", "defaultValueErrMsg", "字段不能为空");
			validateString("field.validator", 0, 10, "validatorErrMsg", "长度超过指定范围");
			validateRequired("field.validator", "validatorErrMsg", "字段不能为空");
			validateString("field.length", 0, 10, "lengthErrMsg", "长度超过指定范围");
			validateRequired("field.length", "lengthErrMsg", "字段不能为空");
			validateString("field.scale", 0, 10, "scaleErrMsg", "长度超过指定范围");
			validateRequired("field.scale", "scaleErrMsg", "字段不能为空");
			validateString("field.fieldType", 0, 10, "fieldTypeErrMsg", "长度超过指定范围");
			validateRequired("field.fieldType", "fieldTypeErrMsg", "字段不能为空");
			validateString("field.javaType", 0, 10, "javaTypeErrMsg", "长度超过指定范围");
			validateRequired("field.javaType", "javaTypeErrMsg", "字段不能为空");
			validateString("field.entity_id", 0, 10, "entity_idErrMsg", "长度超过指定范围");
			validateRequired("field.entity_id", "entity_idErrMsg", "字段不能为空");
			
	}
	
	protected void handleError(Controller controller) {
		
		controller.keepModel(Field.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/field/save"))
			controller.render("add.html");
		else if (actionKey.equals("/field/update"))
			controller.render("edit.html");
	}
}
