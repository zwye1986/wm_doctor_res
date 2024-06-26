package com.pinde.core.util;

import java.util.List;

public class TagUtil {

	public static boolean contain(Object obj,List<Object> list) {
		if(null==list||null==obj){
			return false;
		}
		return list.contains(obj);
	}
}
