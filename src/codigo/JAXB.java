/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import esquema.Muebles;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author angel
 */
public class JAXB {
    File ficheroXML = new File ("/home/angel/Escritorio/muebles.xml");// pongo el archivo
    
    Muebles misMuebles;
    
    String muebleCambiar;

    
    public boolean abrirXML_JAXB(){
        try {
            JAXBContext contexto = JAXBContext.newInstance(Muebles.class);// crea una instancia JAXB
            
            Unmarshaller u = contexto.createUnmarshaller();// crea el objeto
            
            misMuebles = (Muebles) u.unmarshal(ficheroXML);// deserializa el fichero
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    }
    
    public boolean validarMueble(String mueble) {
        if (!mueble.equals("")) {
            System.out.println(mueble);
            List<Muebles.Mueble> muebles = misMuebles.getMueble();//Buscamos que el muebe que hemos escrito existe
            for (int i = 0; i < muebles.size(); i++) {

                Muebles.Mueble muebletemp = muebles.get(i);
                if (muebletemp.getNombre().equals(mueble)) {
                    muebleCambiar = mueble;
                    System.out.println("Si existe");
                    return true;
                }
            }
        }

        return false;
    }

    public boolean cambiarValores(String aNombre, String nNombre, String aPrecio, String nPrecio) {// pongo todas las variables que puede a ver
        try {
            JAXBContext contexto = JAXBContext.newInstance(Muebles.class);
            Marshaller marshaller = contexto.createMarshaller();
            List<Muebles.Mueble> muebles = misMuebles.getMueble();

            for (int i = 0; i < muebles.size(); i++) {
                if (muebles.get(i).getNombre().equals(muebleCambiar)) {
                   System.out.println(nNombre);
                    if (!nNombre.equals("")) {
                        muebles.get(i).setNombre(nNombre);
                    }
                    if (!nPrecio.equals("")) {
                        muebles.get(i).setPrecio(nPrecio);
                    }
                }
            }
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //marshaller.marshal(misMuebles, new FileWriter(new File("series.xml")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
