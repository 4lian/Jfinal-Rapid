<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" path="${project.src}"/>
	<classpathentry kind="src" path="${project.config}"/>
	<#list _libs as lib>
	<classpathentry kind="lib" path="WebRoot/WEB-INF/lib/${lib}"/>
	</#list>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-${project.compileLeve}"/>
	<classpathentry kind="output" path="${project.webRoot}/WEB-INF/classes"/>
</classpath>
