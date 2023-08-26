package com.zhangkie.tinyspring.reader;

import com.zhangkie.tinyspring.beans.BeanDefinition;
import com.zhangkie.tinyspring.beans.PropertyValue;
import com.zhangkie.tinyspring.beans.BeanReference;
import com.zhangkie.tinyspring.io.UrlResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public final class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
    public XmlBeanDefinitionReader(UrlResourceLoader urlResourceLoader) {
        super(urlResourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        registerBeanDefinitions(document);
        inputStream.close();
    }

    public void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList nodeList = root.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++){
            Node item = nodeList.item(i);
            if(item instanceof Element)
                processBeanDefinition((Element)item);
        }
    }

    private void processBeanDefinition(Element element) {
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition=new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processBeanProperty(element,beanDefinition);

        getRegistry().put(name,beanDefinition);
    }

    /**
     * 在为xml中的bean属性进行解析时
     * 先判断其类型是否为ref
     * 若为ref则将property的value定义为BeanRef类型,在后续bean组装属性的阶段创建引用类型的属性值
     * 其余则正常存入
     * @param element
     * @param beanDefinition
     */
    private void processBeanProperty(Element element, BeanDefinition beanDefinition) {
        NodeList propertyNodes = element.getElementsByTagName("property");
        for(int i=0;i<propertyNodes.getLength();i++){
            Node propertyNode = propertyNodes.item(i);
            if(propertyNode instanceof Element){
                Element propertyElement = (Element) propertyNode;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if(value!=null&&!"".equals(value)){
                    beanDefinition.getPropertyValues().addProperty(new PropertyValue(name,value));
                }else {
                    String ref = propertyElement.getAttribute("ref");
                    if(ref!=null&&!"".equals(ref)){
                        beanDefinition.getPropertyValues().addProperty(new PropertyValue(name,new BeanReference(ref)));
                    }else
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                }
            }
        }
    }
}
