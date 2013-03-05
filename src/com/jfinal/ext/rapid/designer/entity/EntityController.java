package com.jfinal.ext.rapid.designer.entity;

import com.jfinal.core.Controller;
import com.jfinal.ext.rapid.designer.project.Project;

public class EntityController extends Controller {
	public void index() throws Exception {
        setAttr("page", Entity.dao.page(getParaToInt(0, 1), 5));
    }
	
	public void add() {
        setAttr("projects", Project.dao.findAll());
	}
	
	public void save() {
		Entity entity = getModel(Entity.class);
		// oracle entity.set("ID", UUID.randomUUID().toString());
		entity.save();
        forwardAction("/entity");
	}
	
	public void edit() {
        setAttr("projects", Project.dao.findAll());
		setAttr("entity", Entity.dao.findById(getPara(0)));
	}
	public void view() {
        setAttr("projects", Project.dao.findAll());
		setAttr("entity", Entity.dao.findById(getPara(0)));
	}
	
	public void update() {
		getModel(Entity.class).update();
        forwardAction("/entity");
	}
	
	public void delete() {
		Entity.dao.deleteById(getPara(0));
        forwardAction("/entity");
	}
}


