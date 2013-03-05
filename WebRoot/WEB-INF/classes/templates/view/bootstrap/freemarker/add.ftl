<#noparse>
<#include "/menu.html"/>
<@layout>
</#noparse>
<div class="well">
	<span class="label label-success">添加${(entity.comment)?default("${entity.name}")}</span>
	<form id="from1" class="well form-horizontal" action="<#noparse>${PATH}</#noparse>/${entity.name}/save" method="post">
		<#noparse>
		<#include "_form.html" />
		</#noparse>
	</form>
</div>
<#noparse>
 <script>
	$(function() { 
     $("#form1").validity(function() {
</#noparse>
	<#assign x="$(\""
		y="\").requrie();"
	>
        <#list entity.fields as field>
        <#if field.isPrimaryKey==0>
        ${x}${field.name}${y}
        </#if>
        </#list>
<#noparse>
     });
 }); 
</script>
</@layout>
</#noparse>