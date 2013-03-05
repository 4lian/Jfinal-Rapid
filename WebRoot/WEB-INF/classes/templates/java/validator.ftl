package ${project.packageName}.${entity.name?lower_case};

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ${entity.name?cap_first}Validator extends Validator {
	
	protected void validate(Controller controller) {
		<#list entity.fields as field>
			<#if field.isPrimaryKey==1 >
			validateString("${entity.name}.${field.name}", 0, ${field.length}, "${field.name}ErrMsg", "长度超过指定范围");
			</#if>
		</#list>
			
	}
	
	protected void handleError(Controller controller) {
		
		controller.keepModel(${entity.name?cap_first}.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/${entity.name}/save"))
			controller.render("add.html");
		else if (actionKey.equals("/${entity.name}/update"))
			controller.render("edit.html");
	}
}
