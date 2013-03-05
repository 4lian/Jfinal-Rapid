<#noparse>
<@layout>
</#noparse>
<h1>${(table.comment)?default("${table.name}")}管理 ---&gt; 查看${(table.comment)?default("${table.name}")}
</h1>
<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
	<#list table.columns as column> 
	<#if column.primaryKey>
		<input type="hidden" name="${table.name}.${column.name}" id="${table.name}.${column.name}"  value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!''}</#noparse>">
	<#else>
		<p>
			<label>${(column.comment)?default("${column.name}")}：</label>
			<input type="text" readonly="readonly" name="${table.name}.${column.name}" id="${table.name}.${column.name}" class="textInput" size="30" value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!''}</#noparse>"/>
		</p>
	</#if>
	</#list>
	</div>
</div>
<#noparse>
</@layout>
</#noparse>