package com.jfinal.ext.rapid.designer.service;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
@SuppressWarnings("serial")
public class Service extends Model<Service> {

	public static final Service dao = new Service();
	
	public Page<Service> page(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *","from service order by id asc");
	}
}