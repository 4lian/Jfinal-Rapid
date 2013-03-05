		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>管理</h2>
					</div>
					<div class="accordionContent">
						<#list tables as table>
						<ul class="tree treeFolder">
							<li><a href="<#noparse>${PATH}</#noparse>/${table.name}/" target="navTab" rel="rel_${table.name}">${(table.comment)?default("${table.name}")}管理</a>
								
							</li>
						</ul>
						</#list>
					</div>
				</div>
			</div>
		</div>
		
		
		