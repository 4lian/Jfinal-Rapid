package com.jfinal.ext.rapid.designer.field;

import com.jfinal.core.Controller;
import com.jfinal.ext.rapid.designer.entity.Entity;

public class FieldController extends Controller {
	public void index() {
        setAttr("page", Field.dao.page(getParaToInt(0, 1), 5));
    }
	
	public void add() {
	    setAttr("entities", Entity.dao.findAll());
	}
	
	public void save() {
		Field field = getModel(Field.class);
		// oracle field.set("ID", UUID.randomUUID().toString());
		field.save();
        forwardAction("/field");
	}
	
	public void edit() {
		setAttr("field", Field.dao.findById(getPara(0)));
	    setAttr("entities", Entity.dao.findAll());
	}
	public void view() {
		setAttr("field", Field.dao.findById(getPara(0)));
	      setAttr("entities", Entity.dao.findAll());

	}
	
	public void update() {
		getModel(Field.class).update();
        forwardAction("/field");
	}
	
	public void delete() {
		Field.dao.deleteById(getPara(0));
        forwardAction("/field");
	}
}


