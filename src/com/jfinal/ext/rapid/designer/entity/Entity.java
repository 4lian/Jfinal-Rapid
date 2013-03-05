package com.jfinal.ext.rapid.designer.entity;

import java.util.List;

import com.jfinal.ext.rapid.designer.field.Field;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Entity extends Model<Entity> {

    public static final Entity dao = new Entity();

    public List<Entity> findAll() {
        return find("select * from entity");
    }
    public String getName() {
        return get("name");
    }

    public String getComment() {
        return get("comment");
    }

    public List<Field> getFields() {
        return Field.dao.find("select * from field where entity_id = ?", get("id"));
    }

    public Page<Entity> page(int pageNumber, int pageSize) {
        return paginate(pageNumber, pageSize, "select *", "from entity order by id asc");
    }

}
