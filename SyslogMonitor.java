/**
 *
 * @author aitjimluz
 */
import java.util.Scanner;
public class programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        Scanner leer = new Scanner(System.in);
        
        System.out.println("Introduzca nombre");
        String nombre = leer.nextLine();
        
        System.out.println("Introduzca primer numero");
        int primer = leer.nextInt();

        System.out.println("Introduzca segundo numero");
        int segundo = leer.nextInt();  
        
        
        System.out.println("Hola" + nombre + "la suma de los números es: " + primer + segundo);
        
    }
}
