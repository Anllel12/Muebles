/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author angel
 */
public class SAX {
    SAXParser parser;
    ManejadorSAX sh;
    File ficheroXML = new File ("/home/angel/Escritorio/muebles.xml");// pongo el archivo
    
    public boolean abrirXML_SAX(){
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                parser = factory.newSAXParser();
                
                sh = new ManejadorSAX();
                
                return true;// devuelve true si lo ha creado correctamente
            } 
            catch (Exception e){
                return false;
            }
        }
    
    public String recorrerSAX(){
        System.out.println("he entrado");
            try {
                sh.cadenaResultado = "";
                parser.parse(ficheroXML, sh);// parseo el XML
                return sh.cadenaResultado;
            } 
            catch (SAXException ex) {
                return "Error al parsear coon SAX";
            }
            catch(IOException e){
                return "Error al parsear con SAX";
            }
        }

        class ManejadorSAX extends DefaultHandler{
            String cadenaResultado = "";
            
            @Override
            public void characters(char[] ch, int start, int legth) throws SAXException{
                for (int i = start; i < legth+start; i++) {
                    cadenaResultado = cadenaResultado + ch[i];
                }
                cadenaResultado = cadenaResultado.trim() + "\n";
            }
            
            @Override // detecta el final de los elementos del xml
            public void endElement(String uri, String localName, String qName) throws  SAXException{
                if(qName.equals("Mueble")){
                    cadenaResultado = cadenaResultado + "\n" + "----------------------";
                }
                else if(qName.equals("Tamaño")){
                    cadenaResultado = cadenaResultado + " ] ";
                }
                else if(qName.equals("Materiales")){
                    cadenaResultado = cadenaResultado + " ] ";
                } 
                else if(qName.equals("Paquete")){
                    cadenaResultado = cadenaResultado + "   ]";
                }
                else if(qName.equals("Embalaje")){
                    cadenaResultado = cadenaResultado + "]";
                }                
            }
            
            @Override // detecta el principio de los elementos del xml y al encontrar uno de los elementos puestos en el xml pone en el String cadenaResultado lo que quieres
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
                if(qName.equals("Muebles")){
                    cadenaResultado = String.format("%s ----------------------\n Se van a mostrar los muebles del XML \n----------------------\n", cadenaResultado);
                }
                else if(qName.equals("Mueble")){// pone el numero del libro que va a comenzar
                    cadenaResultado = String.format("%s \nModelo: %s", cadenaResultado, attributes.getValue(attributes.getQName(0).trim()));
                }
                else if(qName.equals("Nombre")){
                    cadenaResultado = cadenaResultado + "Nombre: ".trim();
                }
                else if(qName.equals("Precio")){
                    cadenaResultado = cadenaResultado + "Precio: ".trim();
                }
                else if(qName.equals("Tamaño")){
                    cadenaResultado = cadenaResultado + "\n" + "Tamaños [ ".trim();
                }
                else if(qName.equals("Ancho")){
                    cadenaResultado = cadenaResultado + "   Ancho: ";
                }
                else if(qName.equals("Fondo")){
                    cadenaResultado = cadenaResultado + "   Fondo: ";
                }
                else if(qName.equals("Altura")){
                    cadenaResultado = cadenaResultado + "   Altura: ";
                }
                else if(qName.equals("PesoBalda")){
                    cadenaResultado = cadenaResultado + "   PesoBalda: ";
                }
                else if(qName.equals("Materiales")){
                    cadenaResultado = cadenaResultado + "\n" + "Materiales [ ".trim();
                }     
                else if(qName.equals("Principal")){
                    cadenaResultado = cadenaResultado + "   Principal: ";
                }
                else if(qName.equals("Secundario")){
                    cadenaResultado = cadenaResultado + "   Secundario: ";
                }
                else if(qName.equals("Embalaje")){
                    cadenaResultado = String.format("%s \nEmbalaje [ \n   Peso: %s", cadenaResultado, attributes.getValue(attributes.getQName(0).trim()));
                }
                else if(qName.equals("Paquete")){
                    cadenaResultado = String.format("%s   Paquete [ \n        Cantidad:  %s", cadenaResultado, attributes.getValue(attributes.getQName(0)));
                }
                else if(qName.equals("Parte")){
                    cadenaResultado = cadenaResultado + "       Parte: ";
                }
                else if(qName.equals("Numero")){
                    cadenaResultado = cadenaResultado + "       Numero: ";
                }             
            }
        }
    
}
