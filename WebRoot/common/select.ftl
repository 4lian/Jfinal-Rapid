<#macro select  id name value items>
   <div class="controls" >
        <select id="${id}" name="${name}">
		<#list items as item>
        <option value="${item.id}" <#if item.id==value>selected</#if> >
        ${item.desc}/${item.name}
        </option>
        </#list>      
        </select>
    </div>
</#macro>