
	<div class="pageFormContent" layoutH="56">
	<#list entity.fields as field> 
	<#if field.isPrimaryKey==1>
		<input type="hidden" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}"  value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>">
	<#else>
		<p>
			<label>${(field.comment)?default("${field.name}")}：</label>
			<input type="text" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}" class="textInput" size="30" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>"/>
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
