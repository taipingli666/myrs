package com.choice.sign.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.util.XMLErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Xml工具类
 * Created by denglongcao on 2017/3/27 0027.
 */
public class XmlUtil {
    private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    /**
     * @方法描述:【String 转 xml】
     * @创建日期:2015-12-17 时间：下午02:57:27
     * @入参:@param xmlStr
     * @入参:@return
     * @返回类型:Document
     * @版本:1.0
     */
    public static Document StringToXml(String xmlStr) {
        if ("".equals(xmlStr) || xmlStr == null) {
            return null;
        }
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xmlStr);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return doc;
    }

    /**
     * @方法描述:【本地xml 文件 转 xml】
     * @创建日期:2015-12-17 时间：下午02:57:27
     * @入参:@param xmlStr
     * @入参:@return
     * @返回类型:Document
     * @版本:1.0
     */
    public static Document fileToXml(String xmlFileName) {
        Document doc = null;
        try {
            xmlFileName = getXmlFile(xmlFileName);
            SAXReader xmlReader = new SAXReader();
            doc = (Document) xmlReader.read(new File(xmlFileName));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * @方法描述:【xml 转String 】
     * @创建日期:2015-12-17 时间：下午02:57:27
     * @入参:@param xmlStr
     * @入参:@return
     * @返回类型:Document
     * @版本:1.0
     */
    public static String XmlToString(Document doc) {
        String xmlStr = "";
        try {
            xmlStr = doc.asXML();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return xmlStr;
    }

    /**
     * @param doc
     * @param xpathPattern
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object parseXmlStr(Document doc, String xpathPattern,
                                     Class cls) {
        Object obj = null;
        try {
            // 初始化Object
            obj = newInstance(cls);
            // 创建一个读取工具
            // 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                try {
                    // 映射数据值对象
                    setProperty(obj, node.getName(), node.getStringValue());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        return obj;

    }


    // 初始化Object
    private static Object newInstance(Class<?> cls) throws Exception {
        return cls.newInstance();
    }

    private static void setProperty(Object obj, String name, Object value)
            throws Exception {
        Class<? extends Object> clazz = obj.getClass();
        String methodName = returnSetMethodName(name);
        Method[] ms = clazz.getMethods();
        for (Method m : ms) {
            if (m.getName().equals(methodName)) {
                if (m.getParameterTypes().length == 1) {
                    Class<?> clazzParameterType = m.getParameterTypes()[0];
                    setFieldValue(clazzParameterType.getName(), value, m, obj);
                    break;
                }
            }
        }
    }

    private static String returnSetMethodName(String name) {
        name = name.substring(0, 1).toUpperCase()
                + name.substring(1, name.length());
        return "set" + name;
    }

    private static void setFieldValue(String parameterTypeName, Object value,
                                      Method m, Object obj) throws Exception {
        if (parameterTypeName.equals(int.class.getName())) {
            value = new Integer(Integer.parseInt(value.toString()));
            m.invoke(obj, value);
            return;
        } else if (parameterTypeName.equals(double.class.getName())) {
            value = new Double(Double.parseDouble(value.toString()));
            m.invoke(obj, value);
            return;
        } else if (parameterTypeName.equals(String.class.getName())) {
            m.invoke(obj, value);
            return;
        } else if (parameterTypeName.equals(Long.class.getName())) {
            //Long  型处理
            try {
                value = new Long(value.toString());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                value = null;
            }
            m.invoke(obj, value);
            return;
        } else if (parameterTypeName.equals("java.util.Date")) {
            value = DateUtil.stringToDate(value.toString(),DateUtil.FORMAT_PATTERN_yyyy_MM_dd);
            m.invoke(obj, value);
            return;
        }
    }

    /**
     * 通过XSD（XML Schema）校验XML
     */
    public static String validateXMLByXSD(Document doc, String xsdFileName) {
        String msg = "";
        xsdFileName = getXsdFile(xsdFileName);
        try {
            // 创建默认的XML错误处理器
            XMLErrorHandler errorHandler = new XMLErrorHandler();
            // 获取基于 SAX 的解析器的实例
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // 解析器在解析时验证 XML 内容。
            factory.setValidating(true);
            // 指定由此代码生成的解析器将提供对 XML 名称空间的支持。
            factory.setNamespaceAware(true);
            // 使用当前配置的工厂参数创建 SAXParser 的一个新实例。
            SAXParser parser = factory.newSAXParser();
            // 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                    "http://www.w3.org/2001/XMLSchema");
            parser.setProperty(
                    "http://java.sun.com/xml/jaxp/properties/schemaSource",
                    "file:" + xsdFileName);
            // 创建一个SAXValidator校验工具，并设置校验工具的属性
            SAXValidator validator = new SAXValidator(parser.getXMLReader());
            // 设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。
            validator.setErrorHandler(errorHandler);
            // 校验
            validator.validate(doc);

            // 如果错误信息不为空，说明校验失败，打印错误信息
            if (errorHandler.getErrors().hasContent()) {
                msg = "XML文件通过XSD文件校验失败！";
                msg += errorHandler.getErrors().getStringValue();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            msg = "XML文件通过XSD文件校验失败！";
            msg += "原因： " + ex.getMessage();
            ex.printStackTrace();
        }
        return msg;
    }

    /**
     * @方法描述:根据xsd 名称获取路径
     * @创建日期:2015-12-19 时间：下午02:54:02
     * @入参:@param path
     * @入参:@return
     * @返回类型:Document
     * @版本:1.0
     */
    public static String getXsdFile(String path) {
        String imgPath = Thread.currentThread().getContextClassLoader()
                .getResource("").toString();
        imgPath = imgPath.split("WEB-INF", -1)[0];
        imgPath = imgPath.replace("file:/", "");
        imgPath = imgPath.replaceAll("%20", " ");
        imgPath = imgPath + "WEB-INF/classes/schema/" + path + ".xsd";
        //判断文件是否存在
        File imgfile = new File(imgPath);
        if (!imgfile.exists()) {
            System.out.println(imgPath + "文件不存在！请检查");
            logger.error(imgPath + "文件不存在！请检查");
            return null;
        }
        return imgPath;
    }

    /**
     * @方法描述:根据xml 名称获取路径
     * @创建日期:2015-12-19 时间：下午02:54:02
     * @入参:@param path
     * @入参:@return
     * @返回类型:Document
     * @版本:1.0
     */
    public static String getXmlFile(String path) {
        String imgPath = Thread.currentThread().getContextClassLoader()
                .getResource("").toString();
        imgPath = imgPath.split("WEB-INF", -1)[0];
        imgPath = imgPath.replace("file:/", "");
        imgPath = imgPath.replaceAll("%20", " ");
        imgPath = imgPath + "WEB-INF/classes/" + path + ".xml";
        //判断文件是否存在
        File imgfile = new File(imgPath);
        if (!imgfile.exists()) {
            System.out.println(imgPath + "文件不存在！请检查");
            logger.error(imgPath + "文件不存在！请检查");
            return null;
        }
        return imgPath;
    }

    @SuppressWarnings("unchecked")
    public static String getdbtype(String jgbh) {
        String dbtype = "";
        Document doc = XmlUtil.fileToXml("hospital_conf");
        List nodes = doc.selectNodes("/hospitals/*");
        for (int i = 0; i < nodes.size(); i++) {
            Node node = (Node) nodes.get(i);
            Element element = (Element) node;
            if (jgbh.equals(element.elementText("jgbh"))) {
                dbtype = element.elementText("id") + "DataSource";
                break;
            }
        }

        return dbtype;

    }

    public static Map<String, String> parseXml_Map(String docs,
                                                   String xpathPattern) {
        Document doc = StringToXml(docs);
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 创建一个读取工具
            // 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
            @SuppressWarnings("rawtypes")
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                try {
                    // 映射数据值对象
                    map.put(node.getName(), node.getStringValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return map;
    }



    /**
     *
     * @param doc
     * @param xpathPattern
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseXmlStr_Map(Document doc, String xpathPattern) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 创建一个读取工具
            // 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                try {
                    // 映射数据值对象
                    map.put(node.getName(), node.getStringValue());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        return map;

    }

    /**
     * @方法描述:【解析xml 返回list】
     * @创建日期:2016-1-9 时间：下午01:13:18
     * @入参:@param doc
     * @入参:@param xpathPattern
     * @入参:@return
     * @返回类型:List<Map<String,Object>>
     * @版本:1.0
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> parseXmlStr_list(Document doc, String xpathPattern) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                if ("item".equals(node.getName())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Element element = (Element) node;
                    for (Iterator i_pe = element.elementIterator(); i_pe.hasNext(); ) {
                        Element e_pe = (Element) i_pe.next();
                        map.put(e_pe.getName(), e_pe.getTextTrim());
                    }
                    list.add(map);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        return list;

    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> parseXmlStr_list(Document doc, String xpathPattern, String item) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                if (item.equals(node.getName())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Element element = (Element) node;
                    for (Iterator i_pe = element.elementIterator(); i_pe.hasNext(); ) {
                        Element e_pe = (Element) i_pe.next();
                        map.put(e_pe.getName(), e_pe.getTextTrim());
                    }
                    list.add(map);
                }
            }
        } catch (Exception ex) {
            logger.error("XmlUnit.parseXmlStr_list()方法，遍历节点的时候出错；doc.asXML()=" + doc.asXML() + "  " + ex.getMessage(), ex);
            logger.error("XmlUnit.parseXmlStr_list()方法，遍历节点的时候出错；xpathPattern=" + xpathPattern + "  " + ex.getMessage(), ex);
            logger.error("XmlUnit.parseXmlStr_list()方法，遍历节点的时候出错；item=" + item + "  " + ex.getMessage(), ex);
        }

        return list;

    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> parseXmlStrListJiedian(Document doc, String xpathPattern, String item, String jiedian) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try {
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                if (item.equals(node.getName())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    List<String> jiedianList = new ArrayList<String>();
                    Element element = (Element) node;
                    for (Iterator i_pe = element.elementIterator(); i_pe.hasNext(); ) {
                        Element e_pe = (Element) i_pe.next();
                        map.put(e_pe.getName(), e_pe.getTextTrim());

                        if (e_pe.getName().equals(jiedian)) {
                            jiedianList.add(e_pe.asXML());
                        }

                    }
                    map.put(jiedian, jiedianList);
                    list.add(map);
                }
            }
        } catch (Exception ex) {
            System.out.println(doc.asXML());
            System.out.println(xpathPattern);
            System.out.println(item);
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        return list;

    }

    /**
     * 获取节点值
     * @param doc
     * @param xpathPattern
     * @param item
     * @return 返回节点item节点值列表
     */
    public static List<String> parseXmlStrListItemValue(Document doc, String xpathPattern, String item) {
        List<String> list = new ArrayList<String>();

        try {
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                if (item.equals(node.getName())) {
                    list.add(node.getText());
                }
            }
        } catch (Exception ex) {
            System.out.println(doc.asXML());
            System.out.println(xpathPattern);
            System.out.println(item);
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        return list;

    }

    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> parseXmlStr(Document doc, String xpathPattern, String item, String zjdz) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            List nodes = doc.selectNodes(xpathPattern);
            // 遍历节点
            for (int i = 0; i < nodes.size(); i++) {
                Node node = (Node) nodes.get(i);
                if (item.equals(node.getName())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Element element = (Element) node;
                    for (Iterator i_pe = element.elementIterator(); i_pe.hasNext(); ) {
                        Element e_pe = (Element) i_pe.next();
                        if (zjdz.equals(e_pe.getName())) {
                            map.put(e_pe.getName(), e_pe.asXML());
                        } else {
                            map.put(e_pe.getName(), e_pe.getTextTrim());
                        }
                    }
                    list.add(map);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        return list;

    }


    @SuppressWarnings("unchecked")
    public static String get_jiedianz(Document doc, String xpathPattern) {
        String str = "";
        try {
            List<Element> list = doc.selectNodes(xpathPattern);
            for (Element ele : list) {
                str = ele.getTextTrim();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return str;
    }

    //map转换为对象
    public static Object mapToObj(Map<String, Object> map, Class cls) {
        Object obj = null;
        try {
            // 初始化Object
            obj = newInstance(cls);
            for (String k : map.keySet()) {
                try {
                    // 映射数据值对象
                    setProperty(obj, k, map.get(k).toString());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    e.printStackTrace();
                }

            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }

        return obj;
    }

    /**
     * @param element
     * @return
     * @Title: elementToMap
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    private static Map<String, String> elementToMap(Element element) {
        Map<String, String> map = new HashMap<String, String>();
        List<Element> elementList = element.elements();
        for (Element ele : elementList) {
            map.put(ele.getName(), ele.getText());
        }
        return map;
    }

    /**
     *@方法描述:判断是否包含在不组织xml 的字段中
     *@创建人: GEWW
     *@创建日期:2016-8-1 时间：上午10:37:28
     *@入参:@param field
     *@入参:@return
     *@返回类型:boolean
     *@版本:1.0
     */
    public static boolean contains(String field ) {
        // 转换为list
        List<String> tempList = Arrays.asList(InitDate.unfield);
        // 利用list的包含方法,进行判断
        if(tempList.contains(field ))
        {
            return true;
        } else {
            return false;
        }
    }


    /**
     *
     * @Description: 生成非list类型节点
     * @param f
     * @param ob
     * @return
     */
    public static Element getOrdinaryElement(Field f, Object ob) {
        String value = "";
        Element fieldname = DocumentHelper.createElement(f.getName()
                .toLowerCase());
        try {
            value =  Functions.showstr(f.get(ob).toString());
            if (f.getType() == Date.class) {
                value = Functions.datefmort(value);
            }
        } catch (Exception e) {
        }
        fieldname.setText(value);
        return fieldname;
    }




    /**
     *
     * @Description: 生成list类型节点
     * @param f
     * @param ob
     * @return
     */
    public static Element getListElement(Field f, Object ob) {
		/* 特殊list 处理 */
        Element fieldname = DocumentHelper.createElement(f.getName() + "S");
        List lsit1 = new ArrayList();
        try {
            Object obj = f.get(ob);
            lsit1 = (List) obj;
        } catch (Exception e) {
            logger.error("生成list类型节点错误：f.getName()：【{}】  ， ob：【{}】", f ,ob );
        }
		/* 遍历list */
        if(lsit1!=null){
        for (Object listobj : lsit1) {
            Element fieldname2 = DocumentHelper.createElement(f.getName());
            Class cc = listobj.getClass();
            Field[] fieldss = cc.getDeclaredFields(); // 获取字段
            for (int m = 0; m < fieldss.length; m++) {
                String valuelist = "";
                Field ff = fieldss[m];
                ff.setAccessible(true);
                // 判断字段类型
                Element fieldname3 = DocumentHelper.createElement(ff.getName()
                        .toLowerCase());
                String clazzParameterType = ff.getType().getName();
                if (clazzParameterType.equals(List.class.getName())) {
                    fieldname3 = getListElement(ff, listobj);// 子节点仍是list类型
                } else {
                    try {
                        valuelist = Functions.showstr(ff.get(listobj)
                                .toString());
                    } catch (Exception e) {
                    }
                    fieldname3.setText(valuelist);
                }
                fieldname2.add(fieldname3);
            }
            fieldname.add(fieldname2);
        }
        }
        return fieldname;
    }

    /**
     * 得到一个节点下的所有的数据,返回string字符串
     */
    public static String getNodeAllToString(String s, String nodeName){
        try{
            s = s.toUpperCase();//转为所有大写
            String nodeName1 = "<"+nodeName+">";
            String  nodeName2 = "</"+nodeName+">";
            int start = s.indexOf(nodeName1);//得到开始索引
            int end = s.lastIndexOf(nodeName2);//得到结束索引
            if(start == -1 || end == -1){
                return null;
            }
            start = start + nodeName1.length();
            return s.substring(start,end);
        }catch (Exception e){
            logger.info("异常信息:",e);
            return null;
        }
    }
    


}