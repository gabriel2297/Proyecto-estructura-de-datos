package model.empleados;

import model.Empleado;

/**
 *
 * @author Alonso
 */
public class Empleados {
    private static String NewEmpleado[][]= new String [100][3];
    private static int Empleado[][]= new int [100][2];
    private static int j=0;
    
    public static void creaEmpleado(Empleado empleado){
        NewEmpleado[j][0]=empleado.getNombre();
        Empleado[j][0]=empleado.getCodigo();
        NewEmpleado[j][1]=empleado.getContratador();
        NewEmpleado[j][2]=empleado.getPuesto();
        Empleado[j][1]=(int)empleado.getSalario();
        j++;
    }
    
    public static void eliminaEmpleado(Empleado empleado){
        int  i = 0;
        int d = 0, c=0;
        int elimina = empleado.getCodigo();
        while (i != 100) {
            if (elimina == Empleado[i][0]) {
                while (d <= 2) {
                    NewEmpleado[i][d] = null;                            
                    d++;
                } while(c <=1){
                      Empleado[i][c] = 0;
                      c++;
                }
            }
            i++;
        }
    }
}
