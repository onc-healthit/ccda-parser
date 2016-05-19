package org.sitenv.ccdaparsing.util;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.sitenv.ccdaparsing.model.CCDAAddress;
import org.sitenv.ccdaparsing.model.CCDACode;
import org.sitenv.ccdaparsing.model.CCDADataElement;
import org.sitenv.ccdaparsing.model.CCDAEffTime;
import org.sitenv.ccdaparsing.model.CCDAFrequency;
import org.sitenv.ccdaparsing.model.CCDAII;
import org.sitenv.ccdaparsing.model.CCDAPQ;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ApplicationUtil {
	
	public static CCDACode readCode(Element codeElement)
	{
		CCDACode code = null;
		if(codeElement != null)
		{
			code = new CCDACode();
			if(!isEmpty(codeElement.getAttribute("code")))
			{
				code.setCode(codeElement.getAttribute("code"));
			}
			if(!isEmpty(codeElement.getAttribute("codeSystem")))
			{
				code.setCodeSystem(codeElement.getAttribute("codeSystem"));
			}
			if(!isEmpty(codeElement.getAttribute("codeSystemName")))
			{
				code.setCodeSystemName(codeElement.getAttribute("codeSystemName"));
			}
			if(!isEmpty(codeElement.getAttribute("displayName")))
			{
				code.setDisplayName(codeElement.getAttribute("displayName"));
			}
			if(!isEmpty(codeElement.getAttribute("xsi:type")))
			{
				code.setXpath(codeElement.getAttribute("xsi:type"));
			}
		}
		return code;
	}
	
	public static CCDAII readTemplateID(Element templateElement)
	{
		CCDAII templateID = null;
		
		if(templateElement != null)
		{
			templateID = new CCDAII();
			if(!isEmpty(templateElement.getAttribute("root")))
			{
				templateID.setRootValue(templateElement.getAttribute("root"));
			}
			if(!isEmpty(templateElement.getAttribute("extension")))
			{
				templateID.setExtValue(templateElement.getAttribute("extension"));
			}
		}
		return templateID;
	}
	
	public static ArrayList<CCDAII> readTemplateIdList(NodeList templateIDNodeList)
	{
		ArrayList<CCDAII> templateList = null;
		if( ! isNodeListEmpty(templateIDNodeList))
		{
			templateList = new ArrayList<>();
		}
		Element templateElement;
		for (int i = 0; i < templateIDNodeList.getLength(); i++) {
			templateElement = (Element) templateIDNodeList.item(i);
			templateList.add(readTemplateID(templateElement));
		}
		return templateList;
	} 
	
	public static CCDAEffTime readEffectivetime(Element effectiveTimeElement,XPath xPath) throws XPathExpressionException
	{
		CCDAEffTime effectiveTime = null;
		
		if(effectiveTimeElement != null)
		{
			effectiveTime = new CCDAEffTime();
			
			effectiveTime.setLow(readDataElement((Element) xPath.compile("./low[not(@nullFlavor)]").
	    				evaluate(effectiveTimeElement, XPathConstants.NODE)));
			effectiveTime.setHigh(readDataElement((Element) xPath.compile("./high[not(@nullFlavor)]").
	    				evaluate(effectiveTimeElement, XPathConstants.NODE)));
			if(effectiveTime.getLow() != null)
			{
				effectiveTime.setLowPresent(true);
			}else
				effectiveTime.setLowPresent(false);
			
			if(effectiveTime.getHigh() != null)
			{
				effectiveTime.setHighPresent(true);
			}else
				effectiveTime.setHighPresent(false);
				
		}
		return effectiveTime;
	}
	
	public static CCDADataElement readDataElement(Element nodeElement)
	{
		CCDADataElement dataElement = null;
		if(nodeElement != null)
		{
			dataElement = new CCDADataElement();
			if(!isEmpty(nodeElement.getAttribute("value")))
			{
				dataElement.setValue(nodeElement.getAttribute("value"));
			}
			if(!isEmpty(nodeElement.getAttribute("lineNumber")))
			{
				dataElement.setLineNumber(Integer.parseInt(nodeElement.getAttribute("lineNumber")));
			}
			if(!isEmpty(nodeElement.getAttribute("xpath")))
			{
				dataElement.setXpath(nodeElement.getAttribute("xpath"));
			}
			if(!isEmpty(nodeElement.getAttribute("use")))
			{
				dataElement.setUse(nodeElement.getAttribute("use"));
			}
		}
		return dataElement;
	}
	
	public static ArrayList<CCDADataElement> readDataElementList(NodeList dataElementNodeList)
	{
		ArrayList<CCDADataElement> dataElementList = null;
		if( ! isNodeListEmpty(dataElementNodeList))
		{
			dataElementList = new ArrayList<>();
		}
		Element dataElement;
		for (int i = 0; i < dataElementNodeList.getLength(); i++) {
			dataElement = (Element) dataElementNodeList.item(i);
			dataElementList.add(readDataElement(dataElement));
		}
		return dataElementList;
	}
	
	public static CCDAPQ readQuantity(Element quantityElement)
	{
		CCDAPQ quantity = null;
		if(quantityElement != null)
		{
			quantity = new CCDAPQ();
			if(!isEmpty(quantityElement.getAttribute("unit")))
			{
				quantity.setUnits(quantityElement.getAttribute("unit"));
			}
			if(!isEmpty(quantityElement.getAttribute("value")))
			{
				quantity.setValue(quantityElement.getAttribute("value"));
			}
			quantity.setXsiType("PQ");
		}
		return quantity;
	}
	
	public static ArrayList<CCDACode> readCodeList(NodeList codeNodeList)
	{
		ArrayList<CCDACode> codeList = null;
		if( ! isNodeListEmpty(codeNodeList))
		{
			codeList = new ArrayList<>();
		}
		Element codeElement;
		for (int i = 0; i < codeNodeList.getLength(); i++) {
			codeElement = (Element) codeNodeList.item(i);
			codeList.add(readCode(codeElement));
		}
		return codeList;
	}
	
	public static CCDAFrequency readFrequency(Element frequencyElement)
	{
		CCDAFrequency frequency = null;
		if(frequencyElement != null)
		{
			frequency =new CCDAFrequency();
			if(!isEmpty(frequencyElement.getAttribute("institutionSpecified")))
			{
				frequency.setInstitutionSpecified(Boolean.parseBoolean(frequencyElement.getAttribute("institutionSpecified")));
			}
			if(!isEmpty(frequencyElement.getAttribute("operator")))
			{
				frequency.setOperator(frequencyElement.getAttribute("operator"));
			}
			Element periodElement  = (Element) frequencyElement.getElementsByTagName("period").item(0);
			if(periodElement != null)
			{
				if(!isEmpty(periodElement.getAttribute("value")))
				{
					frequency.setValue(periodElement.getAttribute("value"));
				}
				if(!isEmpty(periodElement.getAttribute("unit")))
				{
					frequency.setUnit(periodElement.getAttribute("unit"));
				}
			}
		}
		
		return frequency;
	}
	
	public static boolean isEmpty(final String str)
	{
		return str == null || str.trim().length() == 0;
	}
	
	public static boolean isNodeListEmpty(final NodeList nodeList)
	{
		return nodeList == null || nodeList.getLength() == 0;
	}
	
	public static ArrayList<CCDAAddress> readAddressList(NodeList addressNodeList, XPath xpath)throws XPathExpressionException
	{
		ArrayList<CCDAAddress> addressList = null;
		if( ! isNodeListEmpty(addressNodeList))
		{
			addressList = new ArrayList<CCDAAddress>(); 
		}
		Element addrElement;
		for (int i = 0; i < addressNodeList.getLength(); i++) {
			addrElement = (Element) addressNodeList.item(i);
			addressList.add(readAddress(addrElement, xpath));
		}
		return addressList;
	}
	
	public static ArrayList<CCDADataElement> readTextContentList(NodeList inputNodeList)
	{
		ArrayList<CCDADataElement> dataList = null;
		if( ! isNodeListEmpty(inputNodeList))
		{
			dataList = new ArrayList<CCDADataElement>();
		}
		for (int i = 0; i < inputNodeList.getLength(); i++) {
			Element value = (Element) inputNodeList.item(i);
			dataList.add(readTextContext(value));
		}
		return dataList;
	}
	
	public static CCDADataElement readTextContext(Element element)
	{
		return element == null ? null : new CCDADataElement(element.getTextContent()) ;
	}
	
	public static CCDAAddress readAddress(Element addrElement , XPath xPath)throws XPathExpressionException
	{
		CCDAAddress address = null;
		if(addrElement != null)
		{
			address = new CCDAAddress();
			
			if(!isEmpty(addrElement.getAttribute("use")))
			{
				address.setPostalAddressUse(addrElement.getAttribute("use"));
			}
			
			address.setAddressLine1(readTextContext((Element) xPath.compile("./streetAddressLine[not(@nullFlavor)]").
	    				evaluate(addrElement, XPathConstants.NODE)));
			
			address.setAddressLine2(readTextContext((Element) xPath.compile("./streetAddressLine2[not(@nullFlavor)]").
	    				evaluate(addrElement, XPathConstants.NODE)));
			
			address.setCity(readTextContext((Element) xPath.compile("./city[not(@nullFlavor)]").
	    				evaluate(addrElement, XPathConstants.NODE)));
			
			address.setState(readTextContext((Element) xPath.compile("./state[not(@nullFlavor)]").
	    				evaluate(addrElement, XPathConstants.NODE)));
			
			address.setPostalCode(readTextContext((Element) xPath.compile("./postalCode[not(@nullFlavor)]").
	    				evaluate(addrElement, XPathConstants.NODE)));
			
			address.setCountry(readTextContext((Element) xPath.compile("./country[not(@nullFlavor)]").
	    				evaluate(addrElement, XPathConstants.NODE)));
		}
		return address;
	}
}
