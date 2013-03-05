package com.jfinal.ext.rapid.kit;

import java.util.HashMap;
import java.util.Map;

public class Constant {

	public static class ViewType {

        @SuppressWarnings("serial")
        private static final Map<String, String> map = new HashMap<String, String>() {

                                                         {
                                                             put("jsp", "JSP");
                                                             put("freemarker", "FREE_MARKER");
                                                             put("beetl", "BEETL");
                                                         }
                                                     };

        public static String get(String key) {
            return map.get(key);
        }
    }

    public static class Driver {

        @SuppressWarnings("serial")
        private static final Map<String, String> map = new HashMap<String, String>() {

                                                         {
                                                             put("mysql", "com.mysql.jdbc.Driver");
                                                             put("oracle", "oracle.jdbc.driver.OracleDriver");
                                                         }
                                                     };

        public static String get(String key) {
        	return map.get(key);
        }

    }
    
    public static class ViewFramework {
        
        @SuppressWarnings("serial")
        private static final Map<String, String> map = new HashMap<String, String>() {
            
            {
                put("dwz", "dwz");
                put("bootstrap", "bootstrap");
            }
        };
        
        public static String get(String key) {
            return map.get(key);
        }
        
    }

}
