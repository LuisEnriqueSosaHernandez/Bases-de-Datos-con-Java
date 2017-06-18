package System.Helper;

import static System.Settings.JConexion.nombre;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase fue desarrollada para tener codigo funcional para cualquier caso
 *
 * @author Nekszer
 * @version JConexionDB 1.2.1
 * @since JConexionDB 1.1
 */
public class IO {

    /**
     * Obtiene el modelo base de la tabla a utilizar --- JDialogTable InternalTable
     */
    private static DefaultTableModel tabla;
    
    /**
     * Objeto usado para poder alinear las celdas de la tabla
     */
    private static DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
    
    /**
     * Objeto para manipular un segundo modelo de tabla
     */
    private static DefaultTableModel tableModel = new DefaultTableModel();

    //<editor-fold defaultstate="collapsed" desc="Opciones de consola"> 
    
    /**
     * Este metodo imprime matrices de cualquier tamaño
     *
     * @param a recibe el arreglo multidimensional que se desea imprimir
     * @since JConexionDB 1.1
     */
    public static void println(Object[][] a) {
        for (int i = 0; i < (a.length); i++) {
            for (int j = 0; j < (a[i].length); j++) {
                if (j == 0) {
                    System.out.print("| " + a[i][j]);
                } else {
                    if (j == (a[i].length - 1)) {
                        System.out.print(" | " + a[i][j] + " |\n");
                    } else {
                        System.out.print(" | " + a[i][j]);
                    }
                }
            }
        }
    }

    /**
     * Este metodo fue desarrollado para imprimir una matriz de cualquier tamaño
     *
     * @param matriz Matriz a imprimir, no import el tamaño [ArrayList]
     * @since JConexionDB 1.1
     */
    public static void println(ArrayList matriz) {
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < ((ArrayList) matriz.get(i)).size(); j++) {
                if (j == 0) {
                    System.out.print(" | " + (((ArrayList) matriz.get(i)).get(j)));
                } else {
                    if (j == (((ArrayList) matriz.get(i)).size()) - 1) {
                        System.out.print((((ArrayList) matriz.get(i)).get(j)) + " | \n");
                    } else {
                        System.out.print(" | " + (((ArrayList) matriz.get(i)).get(j)) + " | ");
                    }
                }
            }
        }
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ComboBox">
    
    public static void combobox_setArray(Object[] o, JComboBox jc){
        jc.removeAllItems();
        jc.addItem("Selecciona una opcion");
        for (int i = 0; i < o.length; i++) {
            jc.addItem(o[i].toString());
        }
    }
    
    /**
     * Agrega los datos de un ArrayList en un combobox
     *
     * @param l recibe el ArrayList
     * @param jc recibe el combobox a usar
     * @since JConexionDB 1.1
     */
    public static void combobox_setArrayList(ArrayList l, JComboBox jc) {
        jc.removeAllItems();
        jc.addItem("Selecciona una opcion");
        for (int i = 0; i < l.size(); i++) {
            String s1 = l.get(i).toString();
            if(l.get(i).toString().startsWith("[") && l.get(i).toString().endsWith("]")){
                s1 = l.get(i).toString().substring(1, l.get(i).toString().length()-1);
            }
            jc.addItem(s1);
        }
    }

    /**
     * Agrega los datos de una matriz [ArrayList] en un combobox
     *
     * @param matriz recibe la matriz
     * @param jc combobox a usar
     * @since JConexionDB 1.1
     */
    public static void combobox_setMatriz(ArrayList matriz, JComboBox jc) {
        jc.removeAllItems();
        jc.addItem("Selecciona una opcion");
        for (Object matriz1 : matriz) {
            jc.addItem((String) ((ArrayList) matriz1).get(0));
        }
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Opciones de JTextField"> 
    
    /**
     * Valida letras de un jTextField, unicamente se require implementar en el
     * evento keyTyped y enviar el evt del mismo evento.
     *
     * @param evt parametro del evento KeyEvent
     * @since JConexionDB 1.2
     */
    public static void textfield_validarTexto(KeyEvent evt) {
        char c1 = evt.getKeyChar();
        String s1 = String.valueOf(c1);

        if (!s1.matches("[a-zA-Z ]")) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Valida numeros de un jTextField, unicamente se require implementar en el
     * evento keyTyped y enviar el evt del mismo evento.
     *
     * @param evt parametro del evento KeyEvent
     * @since JConexionDB 1.2
     */
    public static void textfield_validarNumeros(KeyEvent evt) {
        char c1 = evt.getKeyChar();
        String s1 = String.valueOf(c1);

        if (!s1.matches("[0-9]")) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    /**
     * Este metodo valida texto alpha numerico en un jtextfield
     *
     * @param evt utilizado para recibir cada tecla typeada en el jtextfiel
     * @since JCOnexionDB 1.2
     */
    public static void textfield_validarAlphaNumerico(KeyEvent evt) {
        char c1 = evt.getKeyChar();
        String s1 = String.valueOf(c1);

        String s2 = s1.toUpperCase();

        if (!s1.matches("[A-Z0-9]")) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Valida numeros con punto decimal en un jTextField, unicamente se require
     * implementar en el evento keyTyped y enviar el evt del mismo evento.
     *
     * @param evt parametro del evento KeyEvent
     * @since JConexionDB 1.2
     */
    public static void textfield_validarDouble(KeyEvent evt) {
        char c1 = evt.getKeyChar();
        String s1 = String.valueOf(c1);

        if (!s1.matches("[0-9.]")) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Este metodo valida tantos jtextfields como sea posible, desde 1
     * jtextfield hasta n jtextfield, para usarlo es necesario mandar los
     * jtextfield como parametros
     *
     * @param x Variable argumento que recibe los jtextfield
     * @return true si y solo si todos los campos tienen texto, false en caso
     * contrario
     * @since JConexionDB 1.2
     */
    public static boolean textfield_requerido(JTextField... x) {
        int numero = x.length;
        int total = 0;
        boolean b1 = false;
        try {
            for (JTextField x1 : x) {
                String s1 = x1.getText();
                if (!s1.equals("")) {
                    total++;
                }
            }
            if (numero == total) {
                b1 = true;
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        return b1;
    }
    /**
     * Este metodo valida la edad de un cliente
     * @param  recibe un campo del tipo string 
     * @return true si y solo si los campos son numericos y no mayor de 3 cifras, false en caso
     * contrario
     * @since JConexionDB 1.2
     */
    public static boolean VerificaEdad(String palabra) {
        
        boolean b1 = true;
        if(palabra.charAt(0)!='0'){
        if(palabra.length()<4){
        for(int i=0;i<palabra.length();i++){
            if(palabra.charAt(i)!='0'&&palabra.charAt(i)!='1'&&palabra.charAt(i)!='2'&&palabra.charAt(i)!='3'&&palabra.charAt(i)!='4'&&palabra.charAt(i)!='5'&&
                    palabra.charAt(i)!='6'&&palabra.charAt(i)!='7'&&palabra.charAt(i)!='8'&&palabra.charAt(i)!='9'){
                b1=false;
                return b1;
            }
        }
        }else{
            b1=false;
            return b1;
        }
        }else{
            b1=false;
            return b1;
        }
        return b1;
    }
     /**
     * Este metodo valida el precio de un prodcuto
     * @param palabra un campo del tipo string 
     * @return true si y solo si los campos son numericos y es un decimal, false en caso
     * contrario
     * @since JConexionDB 1.2
     */
    public static boolean VerificaPrecio(String palabra) {
        
        boolean b1 = true;
        int bandera=0;
        for(int i=0;i<palabra.length();i++){
            if(palabra.charAt(i)!='0'&&palabra.charAt(i)!='1'&&palabra.charAt(i)!='2'&&palabra.charAt(i)!='3'&&palabra.charAt(i)!='4'&&palabra.charAt(i)!='5'&&
                    palabra.charAt(i)!='6'&&palabra.charAt(i)!='7'&&palabra.charAt(i)!='8'&&palabra.charAt(i)!='9'&&palabra.charAt(i)!='.'){
                b1=false;
                return b1;
            }
            if(palabra.charAt(i)=='.'){
                bandera+=1;
            }
        }
        if(bandera>1){
             b1=false;
                return b1;
        }
        
        return b1;
    }
     /**
     * Este metodo valida la existencia de un producto de un cliente
     * @param  recibe un campo del tipo string 
     * @return true si y solo si los campos son numericos , false en caso
     * contrario
     * @since JConexionDB 1.2
     */
    public static boolean Verificaexistencia(String palabra) {
        boolean b1 = true;
         if(palabra.charAt(0)!='0'){
        if(palabra.charAt(0)!='-'){
        for(int i=0;i<palabra.length();i++){
            if(palabra.charAt(i)!='0'&&palabra.charAt(i)!='1'&&palabra.charAt(i)!='2'&&palabra.charAt(i)!='3'&&palabra.charAt(i)!='4'&&palabra.charAt(i)!='5'&&
                    palabra.charAt(i)!='6'&&palabra.charAt(i)!='7'&&palabra.charAt(i)!='8'&&palabra.charAt(i)!='9'){
                b1=false;
                return b1;
            }
        }
        }else{
            b1=false;
            return b1;
        }
        }else{
            b1=false;
            return b1;
        }
        return b1;
    }
    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Opciones de JTable"> 
   
    /**
     * Este metodo imprime una matriz en una tabla, la tabla y la matriz deben
     * de ser del mismo tamaño Tabla [2][2] == Matriz [2][2] De lo contrario
     * ocurrira un error.
     *
     * @param matriz - ArrayList en formato de matriz
     * @param jt - JTable donde se asignaran los datos de la matiz
     */
    public static void tabla_setDatos(ArrayList matriz, JTable jt) {
        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < ((ArrayList) matriz.get(i)).size(); j++) {

                String dato = String.valueOf(((ArrayList) matriz.get(i)).get(j));

                jt.setValueAt(dato, i, j);

            }
        }
    }

    /**
     * Este metodo fue creado para generar el numero de filas exactas obtenidas
     * de una consulta
     *
     * @param jt JTabla file re setear las filas
     * @param filas numero de filas actuales de la tabla
     * @return true si se ha seteado las filas correctas para mostrar la tabla
     * @since JConexionDB 1.1
     */
    public static boolean tabla_setFilas(JTable jt, int filas) {
        tabla = (DefaultTableModel) jt.getModel();

        try {
            if (filas < jt.getRowCount()) {
                while (filas < jt.getRowCount()) {
                    tabla.removeRow(tabla.getRowCount() - 1);
                }
            } else {
                while (jt.getRowCount() < filas) {
                    tabla.addRow(new Object[]{});
                }
            }
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * Este metodo fue creado para generar el numero de columnas exactas
     * obtenidas de una consulta
     *
     * @param jt JTabla file re setear las columnas
     * @param columnas numero de columnas actuales de la tabla
     * @return true si se ha seteado las columnas correctas para mostrar la
     * tabla
     * @since JConexionDB 1.1
     */
    public static boolean tabla_setColumnas(JTable jt, int columnas) {
        try {
            while (columnas < jt.getColumnCount()) {
                jt.removeColumn(jt.getColumnModel().getColumn(1));
            }
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    /**
     * Asignamos los nombres de las columnas segun el arraylist, este arraylist
     * es obtenido de la consulta el cual tiene los nombres de las columnas de
     * la tabla consultada
     *
     * @param l ArrayList con los nombres de las columnas
     * @param jt JTable a la cual se asignara el nombre de las columnas
     * @since JConexionDB 1.1
     */
    public static void tabla_setNombreColumna(ArrayList<String> l, JTable jt) {
        int size = l.size();
        if(size == 0){
            return;
        }
        for(int i = 0; i < size; i++) {
            String nombre = l.get(i);
            jt.getColumn(jt.getColumnName(i)).setHeaderValue(nombre);
        }
        jt.updateUI();
    }

    /**
     * Este metodo redimenciona el alto de las filas, en caso de que algun texto
     * ocupe mas espacio del predeterminado en las columnas
     *
     * @param jt JTable a redimensionar
     * @since JConexionDB 1.1
     */
    public static void tabla_setHeightFilas(JTable jt) {
        try {
            for (int row = 0; row < jt.getRowCount(); row++) {
                int rowHeight = jt.getRowHeight();

                for (int column = 0; column < jt.getColumnCount(); column++) {
                    Component comp = jt.prepareRenderer(jt.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                jt.setRowHeight(row, rowHeight);
            }
        } catch (ClassCastException e) {
        }
    }

    /**
     * Este metodo sirve para alinear las columnas de una tabla de acuerdo al
     * SwingConstants utilizado SwingConstants.CENTER SwingConstants.LEFT
     * SwingConstants.RIGHT
     *
     * @param jt Recibe la tabla a modificar las columnas
     * @param columnas Recibe el numero de columnas que contiene la tabla para
     * centarlas
     * @param alineacion Recibe el tipo de alineacion [CENTER,RIGHT,LEFT]
     * @since JConexionDB 1.2.1
     */
    public static void tabla_setAlign(JTable jt, int columnas, String alineacion) {
        alineacion = alineacion.toUpperCase();
        int numero = 0;

        switch (alineacion) {
            case "CENTER":
                numero = 0;
                break;

            case "RIGHT":
                numero = 4;
                break;

            case "LEFT":
                numero = 2;
                break;

            default:
                numero = 3;
                break;

        }

        Alinear.setHorizontalAlignment(numero);

        for (int i = 0; i < columnas; i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }
    }

    /**
     * Este metodo fue desarrollado para asignar el tamaño de las columnas de
     * acuerdo al numero de columnas que se vallan file mostrar en nuestra tabla
     *
     * @param jd ventana a modificar su tamaño
     * @param columnas recibe el numero de columnas para asignar un tamaño
     * @since JConexionDB 1.1
     */
    public static void tabla_sizeColumnas(JDialog jd, int columnas) {

        int width = 70;

        switch (columnas) {
            case 1:
                width = columnas * (width * 2);
                break;

            case 2:
                width = columnas * (width * 2);
                break;

            case 3:
                width = columnas * (width * 2);
                break;

            case 4:
                width = columnas * (width * 2);
                break;

            case 5:
                width = columnas * (width * 2);
                break;

            default:
                width = columnas * width;
                break;
        }

        jd.setSize(width, 420);

    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Opciones de JDesktopPane"> 
    
    public static boolean setFilas(Object[][] o, JTable jt) {
        tabla = (DefaultTableModel) jt.getModel();

        try {
            if (o.length < jt.getRowCount()) {
                while (o.length < jt.getRowCount()) {
                    tabla.removeRow(tabla.getRowCount() - 1);
                }
            } else {
                while (jt.getRowCount() < o.length) {
                    tabla.addRow(new Object[]{});
                }
            }
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean setColumnas(Object[][] o, JTable jt) {
        try {
            while (o[0].length < jt.getColumnCount()) {
                jt.removeColumn(jt.getColumnModel().getColumn(1));
            }
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Corregir ruta"> 
   
 
    public static String obtenerRutaCorregidaWindows(String laruta, String sep, String nuevoSep)
    {
        
         StringTokenizer tokens=new StringTokenizer(laruta, sep);
         //Para guardar la ruta corregida
         String rutaCorregida = new String();
         //Contar los tokens (en este caso las carpetas, contado tambien
         // el nombre del archivo seleccionado).
         int noTokens = tokens.countTokens();
         int i = 1;
         do
         {      //Agregar el nuevo separador
            rutaCorregida += tokens.nextToken()+nuevoSep;
            i++;
         }while(i<noTokens);
         //Agregar a la ruta corregida el ultimo token, (nombre del archivo)
         rutaCorregida += tokens.nextToken();       
         //Mostrar la ruta corregida en la consola
         
         return rutaCorregida;
    }

    /**
     * Este metodo retorna una ruta que puede ser guardada en la base de datos
     *
     * @param a recibe la ruta que deseas arreglar
     * @since JConexionDB 1.1
     */
     //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Recuperar Id"> 
    
    /**
     * Este metodo retorna el primer campo con string tokenizer
     * @param  recibe un campo del tipo string 
     * @return String con el primer token
     * @since JConexionDB 1.2
     */
    public static String recuperarKey(String palabra){
        String key="";
        StringTokenizer tokens=new StringTokenizer(palabra);
	while(tokens.hasMoreTokens()){
            
            return tokens.nextToken();
        }
        
        return key;
    }
    /**
     * Este metodo retorna la key modificada
     * @param  recibe un campo del tipo string 
     * @return String con la key modificada
     * @since JConexionDB 1.2
     */
     public static String recuperarKeymodificada(String palabra){
        String key="";
        for(int i=1;i<palabra.length()-1;i++){
            key+=palabra.charAt(i);
        }
        return key;
    }
      public static String recuperarKeymodificada2(String palabra){
        String key="";
        for(int i=1;i<palabra.length();i++){
            key+=palabra.charAt(i);
        }
        return key;
    }
     /**
     * Este metodo retorna los campos sin los corchetes
     * @param  recibe un campo del tipo string 
     * @return String los campos sin corchetes 
     * @since JConexionDB 1.2
     */
     public static String recuperarcamposmodificados(String palabra){
        String campo="";
        for(int i=2;i<palabra.length()-2;i++){
            campo+=palabra.charAt(i);
        }
        return campo;
    }
      /**
     * Este metodo retorna el numero de folio sin los corchetes
     * @param  recibe un campo del tipo string 
     * @return String los campos sin corchetes 
     * @since JConexionDB 1.2
     */
     public static String recuperarnumFoliomodificado(String palabra){
        String campo="";
        for(int i=1;i<palabra.length()-1;i++){
            campo+=palabra.charAt(i);
        }
        return campo;
    }
    
    //</editor-fold>
    
}
