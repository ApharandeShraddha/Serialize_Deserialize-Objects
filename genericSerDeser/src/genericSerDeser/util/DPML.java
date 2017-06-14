package genericSerDeser.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import genericSerDeser.fileOperations.FileDisplayInterface;
import genericSerDeser.strategy.SerStrategy;

public class DPML implements SerStrategy {
	private StringBuilder br=null;
	private FileDisplayInterface fp;

	public DPML(FileDisplayInterface fp) {
		this.fp = fp;
	}

	/*
	 * Method to deserialize objects and write to file
	 *@return Void
	 */
	@Override
	public void doSerialize(List<Object> firstSecondClassObj) {
		br = new StringBuilder();
		String fqn = "";
		if (firstSecondClassObj != null) {
			
			try {
				for (Object obj : firstSecondClassObj) {
					Class className = obj.getClass();
					fqn = className.getCanonicalName();
					br.append("<fqn:" + fqn + ">");
					br.append("\n");
					Method[] methods = className.getDeclaredMethods();
					Field[] fields = className.getDeclaredFields();

					for (int i = 0; i < fields.length; i++) {
						Method method = getMethodName(methods, fields[i]);
						if (method != null) {

							if (fields[i].getType() == String.class) {
								br.append("<type=String, var=" + fields[i].getName() + ", value=" + method.invoke(obj)
										+ ">");
								br.append("\n");
							} else {
								br.append("<type=" + fields[i].getType() + ", var=" + fields[i].getName() + ", value="
										+ method.invoke(obj) + ">");
								br.append("\n");
							}
						
						}
						

					}
					br.append("</fqn:" + fqn + ">");
					br.append("\n");
				
				}
				fp.write(br.toString().trim());
				

			}

			catch (SecurityException e) {
				System.err.println("Security Exception: " + e.getMessage());
				System.exit(1);
			} catch (IllegalAccessException e) {
				System.err.println("Illegal access: " + e.getMessage());
				System.exit(1);
			} catch (IllegalArgumentException e) {
				System.out.println("Illegal Argument exception: " + e.getMessage());
				System.exit(1);
			} catch (InvocationTargetException e) {
				System.out.println("Invocation target exception: " + e.getMessage());
				System.out.println("Exception: " + e.getTargetException().getMessage());
				System.exit(1);
			}
			
		}
		
		
	}
    
	/*
	 * @return Method
	 */
	private Method getMethodName(Method[] methods, Field field) {

		Method method = null;
		for (int j = 0; j < methods.length; j++) {
			if ((methods[j].getName()).startsWith("get")) {

				if ((methods[j].getName()).substring(3).toLowerCase().equalsIgnoreCase(field.getName().toLowerCase())) {
					method = methods[j];

				}
			}

		}
		return method;
	}

}
