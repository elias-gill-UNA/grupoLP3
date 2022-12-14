package ejercicios;

// @author Abeld
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;

public class ejercicio4 extends plantilla implements test_interface {

    static JTextPane listaInputs[] = new JTextPane[]{
        new JTextPane(), // horas tranajadas
        new JTextPane() // tarifa por horas
    };
    // difinir el titulo del input
    static String listaTitulos[] = new String[]{
        "Horas Trabajadas",
        "Tarifa por Horas"
    };

    @Override
    public void calcular() {
        try {
            // Guarda los valores ingresados
            float horas = Float.parseFloat(listaInputs[0].getText());
            float tarifa = Float.parseFloat(listaInputs[1].getText());
            logica(new Float[]{horas, tarifa});
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese solo números!");
        }

    }

    // Hace la parte lógica de los cálculos
    @Override
    public void logica(Float... list) {
        /*
            Si la cantidad de horas es menor o igual a cero, el sueldo bruto se
        considera cero por defecto.
            Si la tarifa por hora es menor o igual a cero, el sueldo bruto se
        considera cero por defecto.
         */
        Float sueldoBruto = (float) 0;
        float horasExtras = 0;
        float horasTrabajadas = list[0];
        float tarifaPorHora = list[1];
        if (horasTrabajadas > 0 && tarifaPorHora > 0) {
            if (horasTrabajadas > 40) {
                horasExtras = horasTrabajadas - 40;
                horasTrabajadas = 40;
            }
            sueldoBruto = (float) (horasTrabajadas * tarifaPorHora + horasExtras * tarifaPorHora * 1.5);
        }
        // Actualiza el resultado
        res.setText("Sueldo a recibir: " + sueldoBruto);
    }
    // panel de resultado (pueden usar a7 nomas)
    static JTextPane res = new JTextPane();

    public ejercicio4() throws Exception {
        super(res, listaInputs, listaTitulos);
        bordes();

    }

    private void bordes() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (int i = 0; i < listaInputs.length; i++) {
            listaInputs[i].setBorder(blackline);
        }
    }

    public static void main(String[] args) {
        try {
            res.setEditable(false);
            new ejercicio4();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
