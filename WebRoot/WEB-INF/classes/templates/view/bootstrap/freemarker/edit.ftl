<#noparse>
<#include "/menu.html"/>
<@layout>
</#noparse>
<div class="well">
	<span class="label label-success">修改${(entity.comment)?default("${entity.name}")}</span>
	<form id="from1" class="well form-horizontal" action="<#noparse>${PATH}</#noparse>/${entity.name}/update" method="post">
		<#noparse>
		<#include "_form.html" />
		</#noparse>
	</form>
</div>
<#noparse>
</@layout>
</#noparse>