package com.obd.util;

import org.apache.commons.lang.StringUtils;

public final class Inputs {
	
	public static final void checkNull(Object... objs){
		for(Object obj : objs){
			if(obj==null){
				throw new NullPointerException();
			}
		}
	}

	public static final String trimToNull(String str){
        String ts = StringUtils.trim(str);
        return StringUtils.isBlank(ts) ? null : ts;
	}

}
