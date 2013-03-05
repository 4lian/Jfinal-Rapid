package com.jfinal.ext.rapid.kit.script;

import java.util.Map;

import bsh.EvalError;

/**
 * BeanShell��װ���Ĺ�������,���ڴ���BeanShell��װ����
 * @author ���
 * @since 2007-10-24
 *
 */
public class BeanShellFactory {

	public static BeanShellWrapper createBeanShell(Map<String, Object> model) throws EvalError{
		return new BeanShellWrapper(model);
	}
	
	public static BeanShellWrapper createBeanShell(String name, Object obj) throws EvalError{
		return new BeanShellWrapper(name, obj);
	}
	
	public static BeanShellWrapper createBeanShell() throws EvalError{
		return new BeanShellWrapper();
	}
	

}
