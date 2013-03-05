
	<div class="pageFormContent" layoutH="56">
	<#list table.columns as column> 
	<#if column.primaryKey>
		<input type="hidden" name="${table.name}.${column.name}" id="${table.name}.${column.name}"  value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!''}</#noparse>">
	<#else>
		<p>
			<label>${(column.comment)?default("${column.name}")}：</label>
			<input type="text" name="${table.name}.${column.name}" id="${table.name}.${column.name}" class="textInput" size="30" value="<#noparse>${</#noparse>${table.name}.${column.name}<#noparse>!''}</#noparse>"/>
		</p>
	</#if>
	</#list>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
		</ul>
	</div>
