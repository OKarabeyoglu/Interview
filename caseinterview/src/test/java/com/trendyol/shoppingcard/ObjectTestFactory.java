package com.trendyol.shoppingcard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ObjectTestFactory {
	private List<String> packageNameList;

	@Before
	public void setup() {
		packageNameList = new ArrayList<>(Arrays.asList("com"));
	}

	@Test
	@SuppressWarnings("rawtypes")
	public void testAccountObjects() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			IOException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		for (String string : packageNameList) {
			Class[] classes = getClasses(string);
			for (Class<?> packageClass : classes) {
				Object newInstances = null;
				Constructor[] allConstructors = packageClass.getDeclaredConstructors();
				if (allConstructors.length > 0 && Modifier.isPublic(allConstructors[0].getModifiers())) {
					try {
						Class<?>[] pType = allConstructors[0].getParameterTypes();
						if (pType.length == 0) {
							newInstances = ((Class<? extends Object>) packageClass).newInstance();
						} else {
							Class[] constructorParameters = new Class[pType.length];
							Object[] constructorObjects = new Object[pType.length];
							int temp = 0;
							for (Class<?> parameterType : pType) {
								constructorParameters[temp] = parameterType;
								if (parameterType.equals(String.class)) {
									constructorObjects[temp] = "abc";
								} else if (parameterType.equals(BigDecimal.class)) {
									constructorObjects[temp] = new BigDecimal(4);
								} else if (parameterType.equals(LocalDate.class)) {
									constructorObjects[temp] = LocalDate.now();
								} else if (parameterType.equals(Integer.class)) {
									constructorObjects[temp] = 2;
								} else if (parameterType.equals(Long.class)) {
									constructorObjects[temp] = 1L;
								} else if (parameterType.equals(Boolean.class)) {
									constructorObjects[temp] = Boolean.TRUE;
								} else if (parameterType.equals(boolean.class)) {
									constructorObjects[temp] = true;
								}
								temp++;
							}
							Boolean isEligbleForInstance = Boolean.TRUE;
							for (Object object : constructorObjects) {
								if (object == null) {
									isEligbleForInstance = Boolean.FALSE;
								}
							}
							if (isEligbleForInstance) {
								Constructor constructor = packageClass.getConstructor(constructorParameters);
								newInstances = constructor.newInstance(constructorObjects);
							}
						}

					} catch (Exception e) {
						continue;
					}
				}
				if (newInstances != null) {
					for (Method method : packageClass.getMethods()) {
						try {
							if (method.getName().startsWith("set")) {
								Class<?>[] methodParameters = method.getParameterTypes();
								if (methodParameters.length == 1) {
									Class<?> parameter = methodParameters[0];
									if (parameter.getName().equals(String.class.getName())) {
										setStringField(newInstances, method);
									} else if (parameter.getName().equals(BigDecimal.class.getName())) {
										setBigDecimalField(newInstances, method);
									} else if (parameter.getName().equals(LocalDate.class.getName())) {
										setLocalDateField(newInstances, method);
									} else if (parameter.getName().equals(Integer.class.getName())) {
										setIntegerField(newInstances, method);
									} else if (parameter.getName().equals(Long.class.getName())) {
										setLongField(newInstances, method);
									} else if (parameter.getName().equals(Boolean.class.getName())) {
										setBooleanField(newInstances, method);
									}

									String getMethodName = method.getName().replace("set", "get");
									for (Method getMethod : packageClass.getMethods()) {
										try {
											if (getMethod.getName().equals(getMethodName)) {
												getMethod.invoke(newInstances);
											}
										} catch (Exception e) {
											continue;
										}
									}
								}
							}
						} catch (Exception e) {
							continue;
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	@SuppressWarnings("rawtypes")
	private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	private void setBigDecimalField(Object filledObject, Method method)
			throws IllegalAccessException, InvocationTargetException {

		method.invoke(filledObject, new BigDecimal(100));

	}

	private void setLocalDateField(Object filledObject, Method method)
			throws IllegalAccessException, InvocationTargetException {
		method.invoke(filledObject, LocalDate.now());
	}

	private void setIntegerField(Object filledObject, Method method)
			throws IllegalAccessException, InvocationTargetException {
		method.invoke(filledObject, 4);
	}

	private void setStringField(Object filledObject, Method method)
			throws IllegalAccessException, InvocationTargetException {
		method.invoke(filledObject, "abc");
	}

	private void setLongField(Object filledObject, Method method)
			throws IllegalAccessException, InvocationTargetException {
		method.invoke(filledObject, 1L);
	}

	private void setBooleanField(Object filledObject, Method method)
			throws IllegalAccessException, InvocationTargetException {
		method.invoke(filledObject, Boolean.TRUE);
	}
}
