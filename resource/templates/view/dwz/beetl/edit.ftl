
<div class="pageContent">
	<form method="post" action="<#noparse>${PATH}</#noparse>/${table.name}/update" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<#noparse><%includeFileTemplate</#noparse>("/${table.name}/_form.html")<#noparse>{}%></#noparse>
	</form>
</div>