package ${project.packageName}.${entity.name?lower_case};

import com.jfinal.core.Controller;
import com.jfinal.ext.render.excel.ExcelRender;
<#if project.viewFramework="dwz">
import com.jfinal.ext.render.DwzRender;
</#if>

public class ${entity.name?cap_first}Controller extends Controller {
	public void index() {
        <#if project.viewType="jsp">
        setAttr("page", ${entity.name?cap_first}.dao.find("select * from ${entity.name?lower_case} order by id asc"));
        renderJsp("index.jsp");
        <#elseif project.viewFramework="dwz">
        setAttr("page", ${entity.name?cap_first}.dao.page(getParaToInt("pageNum",1),getParaToInt("numPerPage",10)));
        <#else>
        setAttr("page", ${entity.name?cap_first}.dao.page(getParaToInt(0, 1), 5));
        </#if>
    }
	
	public void add() {
	}
	
	public void save() {
		${entity.name?cap_first} ${entity.name?lower_case} = getModel(${entity.name?cap_first}.class);
		// oracle ${entity.name?lower_case}.set("ID", UUID.randomUUID().toString());
		${entity.name?lower_case}.save();
		<#if project.viewFramework="dwz">
		setAttr("navTabId", "rel_${entity.name}");
		setAttr("callbackType","closeCurrent");
		render("/commom/ajaxDone.html");
		<#else>
        forwardAction("/${entity.name}");
        </#if>
	}
	
	public void edit() {
		setAttr("${entity.name}", ${entity.name?cap_first}.dao.findById(getPara(0)));
	}
	public void view() {
		setAttr("${entity.name}", ${entity.name?cap_first}.dao.findById(getPara(0)));
	}
	
	public void update() {
		getModel(${entity.name?cap_first}.class).update();
       <#if project.viewFramework="dwz">
		setAttr("navTabId", "rel_${entity.name}");
		setAttr("callbackType","closeCurrent");
		render("/commom/ajaxDone.html");
		<#else>
        forwardAction("/${entity.name}");
        </#if>
	}
	
	public void delete() {
		${entity.name?cap_first}.dao.deleteById(getPara(0));
        <#if project.viewFramework="dwz">
		render("/commom/ajaxDone.html");
		<#else>
        forwardAction("/${entity.name}");
        </#if>
	}
	public void excel() {
	    String[] headers= new String[]{};
	    render(ExcelRender.excel(${entity.name?cap_first}.dao.page(getParaToInt("pageNum",1),getParaToInt("numPerPage",10)).getList(),"${entity.label}.xls", headers));
	}
}


