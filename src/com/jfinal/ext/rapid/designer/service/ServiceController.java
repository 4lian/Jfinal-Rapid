package com.jfinal.ext.rapid.designer.service;

import com.jfinal.core.Controller;

public class ServiceController extends Controller {
	public void index() {
        setAttr("page", Service.dao.page(getParaToInt(0, 1), 5));
    }
	
	public void add() {
	}
	
	public void save() {
		Service service = getModel(Service.class);
		// oracle service.set("ID", UUID.randomUUID().toString());
		service.save();
        forwardAction("/service");
	}
	
	public void edit() {
		setAttr("service", Service.dao.findById(getPara(0)));
	}
	public void view() {
		setAttr("service", Service.dao.findById(getPara(0)));
	}
	
	public void update() {
		getModel(Service.class).update();
        forwardAction("/service");
	}
	
	public void delete() {
		Service.dao.deleteById(getPara(0));
        forwardAction("/service");
	}
}


