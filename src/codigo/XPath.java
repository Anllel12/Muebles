/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author angel
 */
public class XPath {
     public String ejecutaXPath(String consulta){
        String cadenaResultado = "";
        
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
            Document XMLDoc = factory.newDocumentBuilder().parse(new File("src/XML/muebles.xml"));// creo el árbol DOM de la ruta dicha
            
            javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
            
            XPathExpression exp = xPath.compile(consulta);// pone la consulta deseada
            
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);// ejecuta la consulta
            NodeList nodeList = (NodeList) result;// devuelve el resultado como un nodelist
            
            for (int i = 0; i < nodeList.getLength(); i++) {// recorre el nodelist
                if (nodeList.item(i).getNodeName().equals("Mueble")) {
                   cadenaResultado = cadenaResultado + "\n" + nodeList.item(i).getAttributes().item(0).getFirstChild().getNodeValue();
                }
                else{
                    cadenaResultado = cadenaResultado + "\n" + nodeList.item(i).getFirstChild().getNodeValue();
                    }
            }
            return cadenaResultado;
        } 
        catch (Exception e){
            return ("Error: " + e.toString());
        }
    }
    
    private String[] procesarMueble(Node n) {       
        String datos[] = new String[80];
        Node ntemp = null;
        int contador = 1;
        
        datos[0] = n.getAttributes().item(0).getNodeValue();
        
        NodeList nodos = n.getChildNodes();
        
        for (int i = 0; i < nodos.getLength(); i++) {
            
            ntemp =  (Node) nodos.item(i);
            
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                
                datos[contador] = ntemp.getFirstChild().getNodeValue();
                contador++;
            }         
        }
        
        return datos;
    }
}
