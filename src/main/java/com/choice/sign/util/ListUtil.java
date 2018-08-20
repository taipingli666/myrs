package com.choice.sign.util;

import java.util.*;

public class ListUtil {
	
	/**
	 * 判断list不为空
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean listIsNotEmpty(List list){
		if(list != null && list.size() >= 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 提取list的第一行数据
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object distillFirstRow(List list){
		if(listIsNotEmpty(list)){
			return list.get(0);
		}else{
			return null;
		}
	}


	/**
	 * 过滤重复list
	 * @param li
	 * @return
	 */
	public static  List <?> dilterDuplication(List<?> li){
		/*//创建新的list*/
		List<Object> list = new ArrayList();
		for(int i=0; i<li.size(); i++){
			/*查看新集合中是否有指定的元素，如果没有则加入*/
			if(!list.contains(li.get(i))){
				list.add(li.get(i));
			}
		}
		return list;
	}

	/**
	 * listmap排序
	 * @param list
	 * @param key 排序字段
	 * @param sortType 排序类型 为空正序
	 * @return
	 */
	public static  List<Map< String , Object >> listMapSort(List< Map< String , Object > > list , final String key , final String sortType){
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				/*name1是从你list里面拿出来的一个*/
				String name1 = o1.get( key ).toString() ;
				/*name1是从你list里面拿出来的第二个name*/
				String name2 = o2.get( key ).toString() ;
				if("".equals( sortType )){
					return name1.compareTo(name2);
				}else{
					return name2.compareTo(name1);
				}

			}
		});
		return list;
	}


}
