package ar.com.lautaro.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Lautaro Toloza
 */
public class testSByte {

    public static void main(String[] args) {
        /*
        Consignas: 
        Generar un txt con 5 nombres
        Generar un programa que reciba un SI o un NO
        Con salida mínima de interfaz Grafica del JOPtionPane
        En caso de que la persona ingrese de manera incorrecta 3 veces el no 
        Se cierra el programa 
        En caso de que la entrada sea un SI 
        Cargue 5 nombres, hacer el traslado del archivo (rutaRelativa)
         */
        String direccion = "Nombres.txt";
        String direccionFinal = "TrasladarNombres.txt";
        List<String> nombres = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String op = JOptionPane.showInputDialog("Ingrese 'si' o 'no' si quiere cargar una lista de 5 nombres: ");
            boolean valor = opcionCorrecta(op.toLowerCase());
            if (valor) {
                if (op.equals("si")) {

                    JOptionPane.showMessageDialog(null, "Su opción fue si, realizara la carga de 5 nombres!! ");
                    for (int j = 0; j < 5; j++) {
                        String mensaje = "Ingrese el nombre n°" + (j + 1) + ": ";
                        nombres.add(JOptionPane.showInputDialog(mensaje));

                    }
                    escritura(nombres, direccion);
                    leer(direccion);
                    List<Integer> datos = recuperador(direccion);
                    trasladar(datos, direccionFinal);

                } else {
                    JOptionPane.showMessageDialog(null, "Su opción fue no, no realizara la carga de nombres!! ");
                }
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Ingreso una opcion incorrecta!! ");
            }
        }
        JOptionPane.showMessageDialog(null, "Finalizo el programa.");

    }

    public static boolean opcionCorrecta(String opcion) {
        return opcion.matches("^(si|no){1}$");

    }

    public static void escritura(List<String> lista, String direc) {
        List<String> usar = lista.stream().map(x -> x + "\n").collect(Collectors.toList());
        try {
            // Poner el true para no perder la información!!
            BufferedWriter bw = new BufferedWriter(new FileWriter(direc, true));
            for (String e : usar) {
                bw.write(e);
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error!!");
            ex.printStackTrace(System.out);
        }

    }

    public static void leer(String direccion) {
        try {
            FileReader fr = new FileReader(direccion);
            BufferedReader bf = new BufferedReader(fr);

            String linea = bf.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = bf.readLine();

            }

            bf.close();
            fr.close();
        } catch (IOException ex) {
            System.out.println("Error!!");
            ex.printStackTrace(System.out);
        }

    }

    public static List<Integer> recuperador(String direc) {

        List<Integer> retorno = new ArrayList<>();
        int c;
        try {
            FileInputStream archivo = new FileInputStream(direc);
            c = archivo.read();
            while (c != -1) {
                retorno.add(c);
                c = archivo.read();
            }
            archivo.close();
        } catch (IOException ex) {
            System.out.println("Error!!");
        }

        return retorno;

    }

    public static void trasladar(List<Integer> lista, String direc) {
        try {
            FileOutputStream fo = new FileOutputStream(direc);

            for (Integer e : lista) {
                fo.write(e);
            }
            fo.close();
        } catch (IOException ex) {
            System.out.println("Error!!");
        }

    }

}
