import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class SyslogMonitor {
    
    // Lista de palabras clave sospechosas
    private static final List<String> PALABRAS_CLAVE_SOSPECHOSAS = Arrays.asList("error", "fail", "warning", "critical", "unauthorized");
    
    public static void main(String[] args) {
        int puerto = 514;  // Puerto estándar para Syslog
        String archivoLog = "syslog_sospechosos.log"; // Nombre del archivo de log
        
        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Escuchando en puerto " + puerto + " para Syslogs...");
            byte[] buffer = new byte[1024];
            
            // Abrir el archivo de log
            FileWriter writer = new FileWriter(archivoLog, true);

            while (true) {
                // Recibir el paquete de datos
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String mensaje = new String(packet.getData(), 0, packet.getLength());

                // Verificar si el mensaje contiene alguna palabra clave sospechosa
                if (contienePalabraClaveSospechosa(mensaje)) {
                    String logSospechoso = "Log sospechoso de " + packet.getAddress() + ": " + mensaje;
                    // Escribir en el archivo de log
                    writer.write(logSospechoso + "\n");
                    writer.flush();  // Asegurarse de que los datos se escriban inmediatamente
                    System.out.println(logSospechoso);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para verificar si el mensaje contiene alguna palabra clave sospechosa
    private static boolean contienePalabraClaveSospechosa(String mensaje) {
        mensaje = mensaje.toLowerCase(); // Convertir a minúsculas para comparación
        for (String palabra : PALABRAS_CLAVE_SOSPECHOSAS) {
            if (mensaje.contains(palabra)) {
                return true;
            }
        }
        return false;
    }
}
