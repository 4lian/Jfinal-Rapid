package com.jfinal.ext.rapid.kit.script;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bsh.BshClassManager;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;

/**
 * ������BeanShell�İ�װ��,Ŀ����ʹ����BeanShell��java�ű����.
 * �ð�װ����Ҫ��װ�Ķ�����<code>Interpreter</code>�࣬����ʵ�ֶ��ַ���
 * ��java�������ʽ��ִ̬��.
 * <p>���磺
 *  BeanShellFactory.createBeanShell().eval("1 == 1");
 * @author ���
 * @since 2007-10-24
 * @see bsh.Interpreter
 *
 */
public class BeanShellWrapper extends ClassLoader {

	Interpreter interpreter = new Interpreter();
	BshClassManager classMgr = BshClassManager.createClassManager(this.interpreter);
	NameSpace nameSpace = new NameSpace(classMgr, "improt_package"+hashCode());
	Map<String, Object> model;
	BeanShellWrapper(Map<String, Object> model) throws EvalError{
		BeanShellWrapper(model);
	}
	
	BeanShellWrapper(String name, Object obj) throws EvalError{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(name, obj);
		BeanShellWrapper(model);
	}
	

	BeanShellWrapper() throws EvalError{
		BeanShellWrapper(null);
	}
	
	private void BeanShellWrapper(final Map<String, Object> model) throws EvalError{
		this.model = model;
		Package[] packages = this.getPackages();
		for (Package importPackage : packages) {
			String pagkageName = importPackage.getName();
			nameSpace.importPackage(pagkageName);
		}

		if(model != null){
			Set<String> keySet = model.keySet();
			for (String key : keySet) {
				this.interpreter.set(key, model.get(key));
			}
		}
	}
	
	/**
	 * Ϊ����ռ���ӵ��õİ���
	 * @param pagkageName ����
	 */
	public void importPackage(String pagkageName){
	    
		nameSpace.importPackage(pagkageName);
	}
	
	public void importStatic(Class clazz){
	    nameSpace.importStatic(clazz);
	}
	
	/**
	 * ִ�и��java�ű�,����ִ�к�Ľ���
	 * @param script �ű��ַ�
	 * @return ���ض�ִ̬�к�Ľ��
	 * @throws EvalError
	 */
	public Object eval(String script) throws EvalError{
		return this.interpreter.eval(script);
	}
	
	/**
	 * ��ȡ���ģ��,һ�����ģ�;���һ��Map
	 * �������Ϊ���Ǳ�����,��ֵ���Ǹñ��������õĶ���
	 * @return �������ģ��
	 */
	public Map<String, Object> getModel(){
		return this.model;
	}
	
	public void put(String key, Object value) throws EvalError{
		if(this.model == null)
			this.model = new HashMap<String, Object>();
		this.model.put(key, value);
		this.interpreter.set(key, value);
	}
	
	public void remove(String key){
		if(this.model == null || this.model.size() == 0)
			return;
		
		this.model.remove(key);
	}
	
	public Object get(String key){
		return this.model.get(key);
	}
	
}
