
	<#list entity.fields as field> 
	<#if field.isPrimaryKey==1>
	<input type="hidden" name="${entity.name}.${field.name}" id="${field.name}" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>">
	<#else>
	<div class="control-group">
		<label class="control-label" for="focusedInput">${(field.comment)?default("${field.name}")}:</label>
		<div class="controls" >
		<input class="input-xlarge focused" type="text" name="${entity.name}.${field.name}" id="${entity.name}.${field.name}" value="<#noparse>${(</#noparse>${entity.name}.${field.name}<#noparse>)!}</#noparse>" style="height: 26" />
		</div>
	</div>
	</#if>
	</#list>
	<div class="form-actions">
        <button type="submit" class="btn btn-primary" onsubmit="add()">确 定</button>
        <button type="reset" class="btn">重 置</button>
    </div>
