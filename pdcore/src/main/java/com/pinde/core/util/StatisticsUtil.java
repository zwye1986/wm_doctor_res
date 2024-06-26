package com.pinde.core.util;

public class StatisticsUtil {

	public static void main(String[] args) {
		double[] testData = new double[] {4.0,5.0,5.0,4.0,4.0,5.0,4.0,4.0,5.0,5.0,5.0,5.0,4.0,4.0,5.0,4.0,3.0};
		System.out.println("方差：" + getVariance(testData));
		System.out.println("标准差：" + getStandardDiviation(testData));
		System.out.println("变异系数：" + coefficientOfVariation(testData));
		
		testData = new double[] {4.0,4.0,5.0,5.0,5.0,5.0,4.0,4.0,5.0,4.0,3.0};
		System.out.println("方差：" + getVariance(testData));
		System.out.println("标准差：" + getStandardDiviation(testData));
		System.out.println("变异系数：" + coefficientOfVariation(testData));
		
		testData = new double[] {4.0,5.0,5.0,4.0,4.0,5.0};
		System.out.println("方差：" + getVariance(testData));
		System.out.println("标准差：" + getStandardDiviation(testData));
		System.out.println("变异系数：" + coefficientOfVariation(testData));
	}
	
//	public static double arrange(double[][] data){
//		if(data==null){
//			return 0d;
//		}
//		double cvArray [] = new double [data.length];
//		for(int i=0;i<data.length;i++){
//			cvArray[i] = coefficientOfVariation(data[i]);
////			System.err.println(cvArray[i] );
//		}
//		return MathUtil.scale2Double(getSum(cvArray));		
//	}
	
	public static double arrange(double[][] data){
		if(data==null){
			return 0d;
		}
		double cvArray [] = new double [data.length];
		double sumArray [] = new double [data.length];
		for(int i=0;i<data.length;i++){
			cvArray[i] = coefficientOfVariation(data[i]);
			sumArray[i] = getSum(data[i]);
		}
		double total = 0;
		for(int i=0;i<data.length;i++){
			total += getSum(data[i]);
		}
		double qunzhong [] = new double [data.length];
		double index = 0;
		for(int i=0;i<data.length;i++){
			if(total==0){
				qunzhong[i] = 0 ;
			}else{
				qunzhong[i] = sumArray[i]/total;
			}
			double qun = cvArray [i] * qunzhong[i];
			index += qun;
		}
		return MathUtil.scale2Double(index);		
	}
	
	/**
	  * 求给定双精度数组中值的最大值
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果,如果输入值不合法，返回为-1
	  */
	 public static double getMax(double[] inputData) {
	  if (inputData == null || inputData.length == 0)
	   return -1;
	  int len = inputData.length;
	  double max = inputData[0];
	  for (int i = 0; i < len; i++) {
	   if (max < inputData[i])
	    max = inputData[i];
	  }
	  return max;
	 }

	 /**
	  * 求求给定双精度数组中值的最小值
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果,如果输入值不合法，返回为-1
	  */
	 public static double getMin(double[] inputData) {
	  if (inputData == null || inputData.length == 0)
	   return -1;
	  int len = inputData.length;
	  double min = inputData[0];
	  for (int i = 0; i < len; i++) {
	   if (min > inputData[i])
	    min = inputData[i];
	  }
	  return min;
	 }

	 /**
	  * 求给定双精度数组中值的和
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果
	  */
	 public static double getSum(double[] inputData) {
	  if (inputData == null || inputData.length == 0)
	   return -1;
	  int len = inputData.length;
	  double sum = 0;
	  for (int i = 0; i < len; i++) {
	   sum = sum + inputData[i];
	  }

	  return sum;

	 }

	 /**
	  * 求给定双精度数组中值的数目
	  * 
	  * @param input
	  *            Data 输入数据数组
	  * @return 运算结果
	  */
	 public static int getCount(double[] inputData) {
	  if (inputData == null)
	   return -1;

	  return inputData.length;
	 }

	 /**
	  * 求给定双精度数组中值的平均值
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果
	  */
	 public static double getAverage(double[] inputData) {
	  if (inputData == null || inputData.length == 0)
	   return -1;
	  int len = inputData.length;
	  double result;
	  result = getSum(inputData) / len;
	  
	  return result;
	 }

	 /**
	  * 求给定双精度数组中值的平方和
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果
	  */
	 public static double getSquareSum(double[] inputData) {
	  if(inputData==null||inputData.length==0)
	      return -1;
	     int len=inputData.length;
	  double sqrsum = 0.0;
	  for (int i = 0; i <len; i++) {
	   sqrsum = sqrsum + inputData[i] * inputData[i];
	  }

	  
	  return sqrsum;
	 }

	 /**
	  * 求给定双精度数组中值的方差
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果
	  */
	 public static double getVariance(double[] inputData) {
	  int count = getCount(inputData);
	  double sqrsum = getSquareSum(inputData);
	  double average = getAverage(inputData);
	  double result;
	  result = (sqrsum - count * average * average) / count;

	     return result; 
	 }

	 /**
	  * 求给定双精度数组中值的标准差
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果
	  */
	 public static double getStandardDiviation(double[] inputData) {
	  double result;
	  //绝对值化很重要
	  result = Math.sqrt(Math.abs(getVariance(inputData)));
	  
	  return result;

	 }

	 /**
	  * 求给定双精度数组中值的变异值
	  * 
	  * @param inputData
	  *            输入数据数组
	  * @return 运算结果
	  */
		
	public static double coefficientOfVariation(double[] data){
		if(getAverage(data)==0){
			return 0;
		}
		return MathUtil.scale2Double(getStandardDiviation(data)/getAverage(data));		
	}

}
