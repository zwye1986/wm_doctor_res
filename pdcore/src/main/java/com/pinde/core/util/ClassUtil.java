package com.pinde.core.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

	/**
	 * package中获取所有的Class
	 * 
	 * @author shijian
	 * @create 2014.04.8
	 */
	public static Set<Class<?>> getClasses(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(
					packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					// System.err.println("jar类型的扫描");
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection())
								.getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx)
											.replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class")
											&& !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(
												packageName.length() + 1, name
														.length() - 6);
										try {
											// 添加到classes
											classes.add(Class
													.forName(packageName + '.'
															+ className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "."
						+ file.getName(), file.getAbsolutePath(), recursive,
						classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add(Thread.currentThread().getContextClassLoader()
							.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 根据类名反射创建对象
	 * @param name 类名
	 * @return 对象
	 * @throws Exception
	 */
	public static Object getInstance(String name) throws Exception {
		Class<?> cls = Class.forName(name);
		return cls.newInstance();
	}
	
	/**
	 * 反射方法，打印对象的属性，方法，构造器属性
	 * @param obj 被反射对象
	 */
	public static void reflect(Object obj) {
		Class<?> cls = obj.getClass();
		System.out.println("************构  造  器************");
		Constructor<?>[] constructors = cls.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println("构造器名称:" + constructor.getName() + "\t"+ "    "
					+ "构造器参数类型:"
					+ Arrays.toString(constructor.getParameterTypes()));
		}
		System.out.println("************属     性************");
		Field[] fields = cls.getDeclaredFields();
		// cls.getFields() 该方法只能访问共有的属性
		// cls.getDeclaredFields()  可以访问私有属性
		for (Field field : fields) {
			System.out.println("属性名称:" + field.getName() + "\t"+ "属性类型:"
					+ field.getType()+"\t");
		}
		System.out.println("************方   法************");
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			System.out.println("方法名:" + method.getName() + "\t" + "方法返回类型："
					+ method.getReturnType() + "\t"+ "方法参数类型:"
					+ Arrays.toString(method.getParameterTypes()));
		}
	}
	
	/**
	 * 
	 * @param obj 访问对象
	 * @param filedname  对象的属性
	 * @return 返回对象的属性值
	 * @throws Exception
	 */
	public static Object getFieldValue(Object obj,String filedname) throws Exception{
		//反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		//反射出类型字段
		try {
			field = cls.getDeclaredField(filedname);//获取属性时，压制Java对访问修饰符的检查 
			field.setAccessible(true);
			//在对象obj上读取field属性的值
			Object val = field.get(obj);
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("没有这个字段："+filedname);
	   }
		 return null;
		
		
	}
	
	/**
	 * 
	 * @param obj 访问对象
	 * @param filedname  对象的属性
	 * @return 返回对象的属性值
	 * @throws Exception
	 */
	public static Object setIdKeyValue(Object obj,String filedname,String value) throws Exception{
		//反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		//反射出类型字段
		 try {
			  field = cls.getDeclaredField(filedname);
		   } catch (Exception e) {
			   e.printStackTrace();
			   System.out.println("没有这个字段："+filedname);
		   }
		if(field==null){
			return null;
		}
		//获取属性时，压制Java对访问修饰符的检查 
		field.setAccessible(true);
		//---------------------------------------------------
		//针对表主键为字符类型进行赋值UUID,如果为int类型采用自增方式
		if(!field.getType().getName().contains("Integer")){
			field.set(obj, value);		
		}
		//---------------------------------------------------
		//在对象obj上读取field属性的值
		Object val = field.get(obj);
		field.setAccessible(false);
		return val;
	}
	
	
	
	/**
	 * 
	 * @param obj 访问对象
	 * @param filedname  对象的属性
	 * @return 返回对象的属性值
	 * @throws Exception
	 */
	public static Object setFieldValue(Object obj,String filedname,String value) throws Exception{
		//反射出类型
		Class<?> cls = obj.getClass();
		Field field = null;
		//反射出类型字段
		 try {
			  field = cls.getDeclaredField(filedname);
		   } catch (Exception e) {
			   e.printStackTrace();
			   System.out.println("没有这个字段："+filedname);
		   }
		if(field==null){
			return null;
		}
		//获取属性时，压制Java对访问修饰符的检查 
		field.setAccessible(true);
		//在对象obj上读取field属性的值
		Object val = field.get(obj);
		field.setAccessible(false);
		return val;
	}
	
	/**
	 * 反射调用对象的方法
	 * @param obj 对象 
	 * @param methodName  方法名称 
	 * @param 参数类型    new Class[]{int.class,double.class}
	 * @param params 参数值     new Object[]{2,3.5}
	 * @return
	 * @throws Exception 
	 */
	public static Object readObjMethod(Object obj,String methodName,Class<?>[] paramTypes,Object[] params) throws  Exception{
		//发现类型
		Class<?> cls = obj.getClass();
		//发现方法
		Method method = cls.getDeclaredMethod(methodName, paramTypes);
		//访问方法时,压制Java对访问修饰符的检查
		method.setAccessible(true);
		Object val = method.invoke(obj, params);
		return val;
	}
}
