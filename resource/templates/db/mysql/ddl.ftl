<#list entities as entity>
DROP TABLE IF EXISTS `${entity.name}`;
CREATE TABLE `${entity.name}` (
	<#list entity.fields as field>
	`${field.name}` ${field.fieldType?lower_case}(${field.length}) COMMENT '${(field.label)!}' <#if field.isPrimaryKey==1>AUTO_INCREMENT</#if>,
	</#list>
	<#list entity.fields as field>
	<#if field.isPrimaryKey==1>
	 PRIMARY KEY (`${field.name}`)
	</#if>
	</#list>
)ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='${(entity.label)!}';

</#list>
