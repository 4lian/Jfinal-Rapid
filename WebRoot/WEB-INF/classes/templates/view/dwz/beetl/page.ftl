
<form id="pagerForm" method="post" action="<#noparse>${PATH}</#noparse>/${table.name}/">
	<input type="hidden" name="pageNum" value="<#noparse>${page.pageNumber}</#noparse>" />
	<input type="hidden" name="numPerPage" value="<#noparse>${page.pageSize}</#noparse>" />

</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<#noparse>${PATH}</#noparse>/${table.name}/add" target="navTab" rel="rel_${table.name}_add"><span>添加</span></a></li>
			<li><a class="delete" href="<#noparse>${PATH}</#noparse>/${table.name}/delete/{sid_user}" rel="rel_${table.name}_delete" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<#noparse>${PATH}</#noparse>/${table.name}/edit/{sid_user}" target="navTab" rel="rel_${table.name}_edit"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<#noparse>${PATH}</#noparse>/${table.name}/download" target="dwzExport" targetType="navTab" rel="rel_${table.name}_dowmload" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<#list table.columns as column>
				<#if !column.primaryKey>
				<th width="80" align="center">${(column.comment)?default("${column.name}")}</th>
				</#if>
				</#list>
			</tr>
		</thead>
		<tbody>
			<#noparse><%for(item in page.list){ %></#noparse>
			<tr target="sid_user" rel="<#noparse>${item.id}</#noparse>">
				<#list table.columns as column>
				<#if !column.primaryKey>
				<td><#noparse>${</#noparse>item.${column.name}<#noparse>!''}</#noparse></td>
				</#if>
				</#list>
			</tr>
			<#noparse><%}%></#noparse>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>条，共<#noparse>${page.totalRow}</#noparse>条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="<#noparse>${page.totalRow}</#noparse>" numPerPage="<#noparse>${page.pageSize}</#noparse>" pageNumShown="<#noparse>${page.pageSize}</#noparse>" currentPage="<#noparse>${page.pageNumber}</#noparse>"></div>

	</div>
</div>


