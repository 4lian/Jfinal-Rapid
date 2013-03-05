package com.jfinal.ext.rapid.designer.field;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
@SuppressWarnings("serial")
public class Field extends Model<Field> {

	public static final Field dao = new Field();
	
	public Page<Field> page(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *","from field order by id asc");
	}
	
}