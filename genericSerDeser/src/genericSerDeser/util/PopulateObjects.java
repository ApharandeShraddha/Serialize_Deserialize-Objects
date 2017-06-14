package genericSerDeser.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import genericSerDeser.fileOperations.FileDisplayInterface;
import genericSerDeser.strategy.SerStrategy;


public class PopulateObjects  {
	private List<Object> firstSecondClassObj = new ArrayList<>();
	private Map<String, Class> dataTypes;
	private FileDisplayInterface fp;
	private Object obj = null;
	

	// Constructor
	public PopulateObjects(FileDisplayInterface fp) {
		this.fp = fp;
		addInitialDataTypesMap();
	}

	/**
	 * @return void
	 */
	private void addInitialDataTypesMap() {
		dataTypes = new HashMap<String, Class>();
		dataTypes.put("int", Integer.TYPE);
		dataTypes.put("byte", Byte.TYPE);
		dataTypes.put("float", Float.TYPE);
		dataTypes.put("long", Long.TYPE);
		dataTypes.put("double", Double.TYPE);
		dataTypes.put("char", Character.TYPE);
		dataTypes.put("short", Short.TYPE);
		dataTypes.put("String", String.class);
		dataTypes.put("boolean", Boolean.TYPE);
	}

	/**
	 * This Method parse input file and have actual logic to create objects
	 * @return void
	 */
	
	public List<Object> deserObjects() {
		Class cls = null;
		Object obj = null;
		CharSequence fqn = ":";
		CharSequence endFqn = "</fqn";

		String line = fp.readFile();
		if (line == null) {
			System.err.println("Input file is empty");
			System.exit(1);
		}
		while (line != null && !("".equals(line))) {
			if (line.contains(fqn)) {
				cls = parseLineWithFqn(line);
				line = fp.readFile();
				while (!((line.contains(endFqn)))) {
					obj = parseLineWithDataTypes(line, cls);
					line = fp.readFile();
				}
			}
			addObjectToList(obj);
			line = fp.readFile();
		}
		
		return firstSecondClassObj;
		
	}

	/**
	 * This method create class from fully qualified name
	 * @param line Line form file
	 * @return Class
	 */
	private Class parseLineWithFqn(String line) {
		Class cls = null;
		String clsName = (line.split(":")[1].trim()).substring(0, (line.split(":")[1].trim()).length() - 1);
		clsName.replaceAll("\\s+", "");
		try {
			cls = Class.forName(clsName);
			obj = cls.newInstance();
		} catch (InstantiationException e) {
			System.err.println("Instantiation Exception occured: " + e.getMessage());
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.err.println("Class Not Found: " + e.getMessage());
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.err.println("Illegal access: " + e.getMessage());
			System.exit(1);
		} finally {
		}
		return cls;
	}

	/**
	 * This method parse line from file and create object
	 * @param line Line from File
	 * @param cls Class
	 * @return Object
	 */
	private Object parseLineWithDataTypes(String line, Class cls) {
		if (line != null) {
			line.replaceAll("\\s+", "");
		}
		String[] lineData = line.split(", ");
		String type = lineData[0].split("=")[1].trim();
		String var = lineData[1].split("=")[1].trim();
		String value = (lineData[2].split("=")[1].trim()).substring(0, (lineData[2].split("=")[1].trim().length() - 1));
		Class[] signature = new Class[1];
		signature[0] = dataTypes.get(type);
		String methodName = "set" + var;
		try {
			Method meth = cls.getMethod(methodName, signature);
			Object[] params = new Object[1];
			params[0] = getParameter(type, value);
			meth.invoke(obj, params);
		} catch (IllegalAccessException e) {
			System.err.println("Illegal access: " + e.getMessage());
			System.exit(1);
		} catch (NoSuchMethodException e) {
			System.out.println("No such method: " + e.getMessage());
			System.exit(1);
		} catch (InvocationTargetException e) {
			System.out.println("Invocation target exception: " + e.getMessage());
			System.out.println("Exception: " + e.getTargetException().getMessage());
			System.exit(1);
		} finally {

		}
		return obj;
	}

	/**
	 * @param type
	 * @param value
	 * @return Object
	 */
	private Object getParameter(String type, String value) {
		Object param = null;
		try {
			if (type.equals("int")) {
				param = new Integer(Integer.parseInt(value));
			} else if (type.equals("float")) {
				param = new Float(Float.parseFloat(value));
			} else if (type.equals("double")) {
				param = new Double(Double.parseDouble(value));
			} else if (type.equals("short")) {
				param = new Short(Short.parseShort(value));
			} else if (type.equals("String")) {
				param = new String(value);
			} else if (type.equals("boolean")) {
				param = new Boolean(Boolean.parseBoolean(value));
			} else if (type.equals("char")) {
				char c = value.charAt(0);
				param = new Character(c);
			} else if (type.equals("byte")) {
				param = new Byte(Byte.valueOf(value));
			} else if (type.equals("long")) {
				param = new Long(Long.parseLong(value));
			}
		} catch (Exception e) {
			System.err.println(
					"Error while conversion to param:mismatch between input value and its data type " + e.getMessage());
			System.exit(1);
		}
		return param;
	}

	/**
	 * This method add objects to set
	 * 
	 * @param void
	 */
	private void addObjectToList(Object obj) {
		
		if (obj instanceof First) {
			firstSecondClassObj.add((First) obj);
		} else if (obj instanceof Second) {
			firstSecondClassObj.add((Second) obj);
		}
	}

	public  void processObjects(List<Object> firstSecondClassObj ,SerStrategy dpmlSerializationStrategy ){
		
		if(firstSecondClassObj !=null && dpmlSerializationStrategy !=null){
			dpmlSerializationStrategy.doSerialize(firstSecondClassObj);
		}
		
	}


}
