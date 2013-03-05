package com.jfinal.ext.rapid;

import com.alibaba.druid.filter.stat.StatFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.rapid.designer.IndexController;
import com.jfinal.ext.rapid.designer.entity.Entity;
import com.jfinal.ext.rapid.designer.entity.EntityController;
import com.jfinal.ext.rapid.designer.field.Field;
import com.jfinal.ext.rapid.designer.field.FieldController;
import com.jfinal.ext.rapid.designer.project.Project;
import com.jfinal.ext.rapid.designer.project.ProjectController;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
public class CodeConfig extends JFinalConfig {
	
	public void configConstant(Constants me) {
		loadPropertyFile("classes/config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setViewType(ViewType.FREE_MARKER);
	}
	
	public void configRoute(Routes me) {
		me.add("/",IndexController.class);
		me.add("/entity", EntityController.class);
		me.add("/field", FieldController.class);
		me.add("/project", ProjectController.class);
	}
	
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcurl"),getProperty("username"),getProperty("password").trim());
		druidPlugin.addFilter(new StatFilter());
		druidPlugin.setDriverClass("com.mysql.jdbc.Driver");
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setDialect(new MysqlDialect());
		arp.setContainerFactory(new CaseInsensitiveContainerFactory()).setShowSql(true);
		arp.addMapping("entity", Entity.class);
		arp.addMapping("field", Field.class);
		arp.addMapping("project", Project.class);
	
		me.add(druidPlugin);
		me.add(arp);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("PATH"));
	}
	
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8888, "/code", 5);
	}
}
