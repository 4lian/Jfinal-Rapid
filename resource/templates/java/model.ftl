package ${project.packageName}.${entity.name?lower_case};

import com.jfinal.plugin.activerecord.Model;
<#if project.viewType != "jsp">
import com.jfinal.plugin.activerecord.Page;
</#if>
@SuppressWarnings("serial")
<#assign entityName= entity.name?lower_case?cap_first/>
public class ${entityName} extends Model<${entityName}> {

	public static final ${entityName} dao = new ${entityName}();
	
	<#if _hasGetterAndSetter == "true">
	<#list entity.fields as field>
	public ${field.type?lower_case?cap_first} get${field.name?cap_first}(){
		return get("${field.name}");
	}
	public void set${field.name?cap_first}(${field.type?lower_case?cap_first} ${field.name}){
		set("${field.name}",${field.name});
	}
	</#list>
	</#if>
	
	public Page<${entityName}> page(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *","from ${entityName?lower_case} order by id asc");
	}
}