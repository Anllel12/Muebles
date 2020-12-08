/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author angel
 */
public class DOM {
    Document doc;
    
    File ficheroXML = new File ("src/XML/muebles.xml");// pongo el archivo
    
    public boolean abrirXML_DOM(){
        doc = null;
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            doc = builder.parse(ficheroXML);
            
            return true;
        } 
        catch (Exception e) {
            return false;
        }       
    }
    
    public boolean añadirDom(String modelo, String nombre, String precio, String ancho,// pongo todas las variables que puede a ver
                        String fondo, String altura, String pesoBalda, String principal, 
                        String secundario, String peso, String cantidad, String parte, String numero){
        try{
            Node nNombre = doc.createElement("Nombre");// crea el elemento
            Node nNombreText = doc.createTextNode(nombre);// crea el elemento texto
            nNombre.appendChild(nNombreText);// añade el texto
            
            Node nPrecio = doc.createElement("Precio");
            Node nPrecioText = doc.createTextNode(precio);
            nPrecio.appendChild(nPrecioText);
            
            Node nAncho = doc.createElement("Ancho");
            Node nAnchoText = doc.createTextNode(ancho);
            nAncho.appendChild(nAnchoText);
            
            Node nFondo = doc.createElement("Fondo");
            Node nFondoText = doc.createTextNode(fondo);
            nFondo.appendChild(nFondoText);
            
            Node nAltura = doc.createElement("Altura");
            Node nAlturaText = doc.createTextNode(altura);
            nAltura.appendChild(nAlturaText);
            
            Node nPesoBalda = doc.createElement("PesoBalda");
            Node nPesoBaldaText = doc.createTextNode(pesoBalda);
            nPesoBalda.appendChild(nPesoBaldaText);
            
            Node nPrincipal = doc.createElement("Principal");
            Node nPrincipalText = doc.createTextNode(principal);
            nPrincipal.appendChild(nPrincipalText);
            
            Node nSecundario = doc.createElement("Secundario");
            Node nSecundarioText = doc.createTextNode(secundario);
            nSecundario.appendChild(nSecundarioText);
            
            Node nParte = doc.createElement("Parte");
            Node nParteText = doc.createTextNode(parte);
            nParte.appendChild(nParteText);
            
            Node nNumero = doc.createElement("Numero");
            Node nNumeroText = doc.createTextNode(numero);
            nNumero.appendChild(nNumeroText);
            
            Node nTamaño = doc.createElement("Tamaño");
            nTamaño.appendChild(nAncho);// añado los elementos a otro elemento
            nTamaño.appendChild(nFondo);
            nTamaño.appendChild(nAltura);
            nTamaño.appendChild(nPesoBalda);
            
            Node nMateriales = doc.createElement("Materiales");
            nMateriales.appendChild(nPrincipal);
            nMateriales.appendChild(nSecundario);
            
            Node nPaquete = doc.createElement("Paquete");
            ((Element)nPaquete).setAttribute("cantidad", cantidad);
            nPaquete.appendChild(nParte);
            nPaquete.appendChild(nNumero);
            
            Node nEmbalaje = doc.createElement("Embalaje");
            ((Element)nEmbalaje).setAttribute("peso", peso);
            nEmbalaje.appendChild(nPaquete);
            
            Node nMueble = doc.createElement("Mueble");
            ((Element)nMueble).setAttribute("modelo", modelo);
            nMueble.appendChild(nNombre);
            nMueble.appendChild(nPrecio);
            nMueble.appendChild(nTamaño);
            nMueble.appendChild(nMateriales);
            nMueble.appendChild(nEmbalaje);
            
            Node raiz = doc.getFirstChild();// añado todos los elementos a la raiz
            raiz.appendChild(nMueble);
            
            return true;
        }
        catch(Exception e){
            return false;
        }
    }    
}
