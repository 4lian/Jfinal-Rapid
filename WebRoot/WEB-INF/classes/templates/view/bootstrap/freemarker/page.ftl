<#noparse>
<#include "/menu.html"/>
<@layout>
</#noparse>
<div  class="well">
<a class="btn btn-success" href="<#noparse>${PATH}</#noparse>/${entity.name}/add"><i class="icon-user icon-white"></i>添加${(entity.comment)?default("${entity.name}")}</a>
</div>
<div class="well">
	<table class="table table-bordered table-striped">
	<span class="label label-success">${(entity.comment)?default("${entity.name}")}管理</span>
		<thead>
			<tr>
				<#list entity.fields as field>
				<#if field.isPrimaryKey==0>
				<th>${(field.comment)?default("${field.name}")}</th>
				</#if>
				</#list>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#noparse><#list page.list as item></#noparse>
			<tr>
				<#list entity.fields as field>
				<#if field.isPrimaryKey==0>
				<td>
					<#noparse>${(</#noparse>item.${field.name}<#noparse>)!}</#noparse>
				</td>
				</#if>
				</#list>
				<td>
					<div class="btn-group">
					 	<a class="btn btn-primary" href="<#noparse>${PATH}</#noparse>/${entity.name}/view/${r"${item.ID}"}"><i class="icon-trash icon-white"></i>查看</a>
					 	<a class="btn btn-primary" href="<#noparse>${PATH}</#noparse>/${entity.name}/edit/${r"${item.ID}"}"><i class="icon-trash icon-white"></i>修改</a>
					 	<a class="btn btn-primary" href="<#noparse>${PATH}</#noparse>/${entity.name}/delete/${r"${item.ID}"}"><i class="icon-trash icon-white"></i>删除</a>
					</div>
				</td>
			</tr>
			<#noparse></#list></#noparse>
		</tbody>
	</table>
	<#noparse>
	<#include "/common/paginate.ftl" />
	<@paginate currentPage=page.pageNumber totalPage=page.totalPage actionUrl="${PATH}/</#noparse>${entity.name}<#noparse>/"/>
</div>
</@layout>
</#noparse>
