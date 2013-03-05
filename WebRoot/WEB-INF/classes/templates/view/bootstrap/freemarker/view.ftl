<#noparse>
<#include "/menu.html"/>
<@layout>
</#noparse>
<div class="well">
	<entity class="entity entity-bordered entity-striped">
	<span class="label label-success">查看${(entity.comment)?default("${entity.name}")}</span>
		<thead>
			<tr>
				<#list entity.fields as field>
				<th>${(field.comment)?default("${field.name}")}</th>
				</#list>
			</tr>
		</thead>
		<tbody>
			<tr>
				<#list entity.fields as field>
				<th><#noparse>${(</#noparse>${entity.name}.${field.name})<#noparse>!}</#noparse></th>
				</#list>
			</tr>
			</tr>
		</tbody>
	</entity>
	<#noparse>
</div>
</@layout>
</noparse>