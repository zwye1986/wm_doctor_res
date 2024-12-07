package com.pinde.core.util;

import java.math.BigDecimal;

public class MathUtil {
	
	public static double scale2Double(double f){
		BigDecimal b = new BigDecimal(f);  
		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
//	public static String[][] permutation(String [] array){
//		if(array==null){
//			throw new NullPointerException();
//		}
//		ICombinatoricsVector<String> originalVector = Factory.createVector(array);
//		// Create the permutation generator by calling the appropriate method in the Factory class
//		Generator<String> gen = Factory.createPermutationGenerator(originalVector,true);
////		List<List<String>> array2List = new ArrayList<List<String>>();
//		// Print the result
//	    int row = gen.generateAllObjects().size();
//	    int col = array.length;
//		String[][] resultArray = new String[row][col];
//		int a = 0;
//		for (ICombinatoricsVector<String> perm : gen){
////			List<String> array1List = new ArrayList<String>();
////			perm.getVector().toArray();
//			int b = 0;
//		    for(String str : perm){
//				resultArray[a][b++] = str;
////		    	System.err.print(str+",");
//		    }
//		    a++;
////		    System.err.println();
////		    array2List.add(array1List);
//		}
//		return resultArray;
//	}
//	
//	public static String[][] permutation(List<String> list){
//		if(list==null || list.size()==0){
//			throw new NullPointerException();
//		}
//		String[] array = new String[list.size()];
//		int i = 0;
//		for(String str : list){
//			array[i++] = str;
//		}
//		return permutation(array);
//	}
	
	public static void main(String[] args){
//		System.out.println((int)Math.ceil(1.1));  
//		System.out.println(1.0*7);       
		System.out.println(DateUtil.signDaysBetweenTowDate("2015-09-01","2015-09-01"));
//		String[] array = new String[]{"1","2","3"};
//		{
//		long start = System.currentTimeMillis();
//		List<String> deptList = Arrays.asList(array);
//		Iterator<List<String>> it = new PermutationIterator<String>(deptList);
//		int total = 0;
//		while (it.hasNext()) {
////			List<String> schDeptFlowArray = it.next();
//			it.next();
//			total++;
//		}
//		System.err.println(total);
//		long end = System.currentTimeMillis();
//		System.err.println("times 1 eslape:"+(end-start));
////		
//		}
		

//		long start = System.currentTimeMillis();
//		String[][] permutationDoctorFlowList = MathUtil.permutation(array);
//		System.err.println(permutationDoctorFlowList.length);
//		long end = System.currentTimeMillis();
//		System.err.println("times 2 eslape:"+(end-start));
		
//		System.err.println(permutationDoctorFlowList.length);
//		for(String ttt [] : permutationDoctorFlowList){
//			for(String t : ttt){
//				System.err.print(t+" ");
//			}
//			System.err.println();
//		}
	}

}
