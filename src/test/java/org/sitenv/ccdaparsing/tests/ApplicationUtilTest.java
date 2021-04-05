package org.sitenv.ccdaparsing.tests;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.springframework.util.StringUtils;

public class ApplicationUtilTest {

	// set xmlstring for the object with the xmlname space passed
	public static Object setXmlString(Object object, String xmlnamespace) {
		Field[] fields = object.getClass().getDeclaredFields();
		if (object.getClass().getName().equals(new CCDADataElement().getClass().getName())) {
			fields = reOrgFieldsCcda(fields);
		}
		if (object.getClass().getName().equals(new CCDAPQ().getClass().getName())) {
			fields = reOrgFieldsCcdaPq(fields);
		}
		if (checkXmlString(fields, object)) {
			setMethodXmlString(object, fields, xmlnamespace, false);
			setMethodXmlString(object, fields, xmlnamespace, true);
		}
		return object;
	}

	public static String getXmlStringValue(Field[] fields, Object object, String xmlnamespace) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("<");
		strBuffer.append(xmlnamespace);
		if (!xmlnamespace.equalsIgnoreCase("templateId")) {
			strBuffer.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		}
		fields = reOrgFieldsCcdacode(fields, object);
		for (int k = 0; k < fields.length; k++) {
			try {
				fields[k].setAccessible(true);
				if (fields[k].get(object) != null
						&& !"singleAdministrationValuePresent".equalsIgnoreCase(fields[k].getName())
						&& !"lowPresent".equalsIgnoreCase(fields[k].getName())
						&& !"highPresent".equalsIgnoreCase(fields[k].getName())) {
					if ("units".equalsIgnoreCase(fields[k].getName())) {
						strBuffer.append(" unit");

					} else if ("xsiType".equalsIgnoreCase(fields[k].getName())
							|| "xpath".equalsIgnoreCase(fields[k].getName())) {
						strBuffer.append(" xsi:type");
					} else {
						strBuffer.append(" ").append(fields[k].getName());

					}
					strBuffer.append("=\"");
					strBuffer.append(fields[k].get(object));
					strBuffer.append("\"");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		strBuffer.append("/>");
		return strBuffer.toString();
	}

	public static boolean checkXmlString(Field[] fields, Object object) {
		boolean returnObj = false;
		returnObj = checkXmlString(fields);
		if (!returnObj) {
			Field[] fieldsSupperClass = object.getClass().getSuperclass().getDeclaredFields();
			returnObj = checkXmlString(fieldsSupperClass);
		}
		return returnObj;
	}

	public static boolean checkXmlString(Field[] fields) {
		boolean returnObj = false;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase("xmlString")) {
				returnObj = true;
			}
		}
		return returnObj;
	}

	public static Object setMethodXmlString(Object object, Field[] fields, String xmlnamespace, boolean parentClass) {
		try {
			Method method = null;
			if (parentClass) {
				method = object.getClass().getSuperclass().getDeclaredMethod("setXmlString", String.class);
			} else {
				method = object.getClass().getDeclaredMethod("setXmlString", String.class);
			}
			if (method != null) {
				method.invoke(object, getXmlStringValue(fields, object, xmlnamespace));
			}
		} catch (NoSuchMethodException e) {
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;
	}
	// re organize filed position for CCDADataElement
	public static Field[] reOrgFieldsCcda(Field[] fields) {
		Field[] fieldsModified = new Field[fields.length];
		fieldsModified[0] = fields[3];
		fieldsModified[1] = fields[0];
		fieldsModified[2] = fields[1];
		fieldsModified[3] = fields[2];
		fieldsModified[4] = fields[4];
		return fieldsModified;
	}

	// re organize filed position for CCDAPG 
	public static Field[] reOrgFieldsCcdaPq(Field[] fields) {
		Field[] fieldsModified = new Field[fields.length];
		fieldsModified[0] = fields[1];
		fieldsModified[1] = fields[0];
		fieldsModified[2] = fields[2];
		return fieldsModified;
	}

	// Add xpath when xpath value is not null
	public static Field[] reOrgFieldsCcdacode(Field[] fields, Object object) {
		Field[] fieldsModified = new Field[fields.length + 1];
		if (new CCDACode().getClass().equals(object.getClass())) {
			fieldsModified[0] = fields[0];
			fieldsModified[1] = fields[1];
			fieldsModified[2] = fields[2];
			fieldsModified[3] = fields[3];
			fieldsModified[4] = fields[4];
			CCDACode ccdaCodeObj = (CCDACode) object;
			if (!StringUtils.isEmpty(ccdaCodeObj.getXpath())) {
				try {
					fieldsModified[5] = object.getClass().getSuperclass().getDeclaredField("xpath");
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				return fieldsModified;
			}
		}
		return fields;
	}
}
