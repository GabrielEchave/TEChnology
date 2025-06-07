package ventanas;

import java.sql.*;
import clases.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gabriel Echave
 */
public class Informacion_Cliente extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();

    int IDcliente_update = 0;
    public static int IDequipo = 0;
    String user = "";

    /**
     * Creates new form Informacion_Cliente
     */
    public Informacion_Cliente() {
        initComponents();
        user = Login.user;
        IDcliente_update = GestionarClientes.IDcliente_update;

        setSize(630, 450);
        setResizable(false);
        setLocationRelativeTo(null); //centra el programa en la pantalla

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//permite que la ventana se cierre sin cerrar todo el programa

        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();//permite que se guarden los cambios

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select * from clientes where id_clientes = '" + IDcliente_update + "'"); //Seleccionar todo de la tabla clientes
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                setTitle("Infomacion del cliente " + rs.getString("nombre_cliente") + " - sesion de " + user);
                jLabel_titulo.setText("Informacion del cliente " + rs.getString("nombre_cliente"));

                txt_nombre.setText(rs.getString("nombre_cliente"));
                txt_mail.setText(rs.getString("email_cliente"));
                txt_telefono.setText(rs.getString("tel_cliente"));
                txt_direccion.setText(rs.getString("dir_cliente"));
                txt_ultimaModifiacion.setText(rs.getString("ultima_modificacion"));
            }
            cn.close();
        } catch (SQLException e) {
            System.err.println("Error al intentar ver la informacion del cliente" + e);
            JOptionPane.showMessageDialog(null, "Error al cargar informacion del cliente");
        }
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select id_equipo, tipo_equipo, marca, estatus from equipos where id_cliente = '"
                    + IDcliente_update + "'");//seleccionar elementos de la tabla equipos
            ResultSet rs = pst.executeQuery();
            jTable_equipos = new JTable(model); //Con el model podremos colocar datos dentro de la tabla
            jScrollPane_equipos.setViewportView(jTable_equipos);

            //Creamos las columnas de la tabla
            model.addColumn("Id_equipo");
            model.addColumn("Tipo de equipo");
            model.addColumn("Marca");
            model.addColumn("Estatus");

            //Creamos una estructura repetitiva para que nos muestre todos los elementos de la tabla
            while (rs.next()) {
                Object[] fila = new Object[4];
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);//agregamos los elementos a las columnas
            }
            cn.close();

        } catch (SQLException e) {
            System.err.println("Error en el llenado de la tabla equipos");
        }

        jTable_equipos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { //Metodo para obtener el id de los clientes
                int fila_point = jTable_equipos.rowAtPoint(e.getPoint());
                int columna_point = 0;
                if (fila_point > -1) {
                    IDequipo = (int) model.getValueAt(fila_point, columna_point);
                    InformacionEquipo informacion_equipo = new InformacionEquipo();
                    informacion_equipo.setVisible(true);
                }
            }
        });
    }

    @Override
    public Image getIconImage() { //Permite cambiar el icono de la interfas
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane_equipos = new javax.swing.JScrollPane();
        jTable_equipos = new javax.swing.JTable();
        jLabel_titulo = new javax.swing.JLabel();
        jLabel_Nombre = new javax.swing.JLabel();
        jLabel_mail = new javax.swing.JLabel();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_direccion = new javax.swing.JLabel();
        jLabel_UltimaModificacion = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_mail = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        txt_ultimaModifiacion = new javax.swing.JTextField();
        jButton_Registrar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_imprimirReporte = new javax.swing.JButton();
        jLabel_Footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_equipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane_equipos.setViewportView(jTable_equipos);

        getContentPane().add(jScrollPane_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 380, 180));

        jLabel_titulo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel_titulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_titulo.setText("Informacion del cliente");
        getContentPane().add(jLabel_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_Nombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Nombre.setText("Nombre:");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel_mail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_mail.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_mail.setText("Em@il:");
        getContentPane().add(jLabel_mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel_telefono.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_telefono.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_telefono.setText("Telefono:");
        getContentPane().add(jLabel_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel_direccion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_direccion.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_direccion.setText("Direccion:");
        getContentPane().add(jLabel_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel_UltimaModificacion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel_UltimaModificacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_UltimaModificacion.setText("Ultima modificacion por: ");
        getContentPane().add(jLabel_UltimaModificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(49, 133, 221));
        txt_nombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nombre.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        txt_mail.setBackground(new java.awt.Color(49, 133, 221));
        txt_mail.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_mail.setForeground(new java.awt.Color(255, 255, 255));
        txt_mail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_mail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        txt_telefono.setBackground(new java.awt.Color(49, 133, 221));
        txt_telefono.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_telefono.setForeground(new java.awt.Color(255, 255, 255));
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        txt_direccion.setBackground(new java.awt.Color(49, 133, 221));
        txt_direccion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_direccion.setForeground(new java.awt.Color(255, 255, 255));
        txt_direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_direccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, -1));

        txt_ultimaModifiacion.setBackground(new java.awt.Color(49, 133, 221));
        txt_ultimaModifiacion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_ultimaModifiacion.setForeground(new java.awt.Color(255, 255, 255));
        txt_ultimaModifiacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ultimaModifiacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_ultimaModifiacion.setEnabled(false);
        getContentPane().add(txt_ultimaModifiacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, -1));

        jButton_Registrar.setBackground(new java.awt.Color(49, 133, 221));
        jButton_Registrar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Registrar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Registrar.setText("Registrar equipo");
        jButton_Registrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 210, 35));

        jButton_Actualizar.setBackground(new java.awt.Color(49, 133, 221));
        jButton_Actualizar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Actualizar.setText("Actualizar cliente");
        jButton_Actualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 210, 35));

        jButton_imprimirReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/impresora.png"))); // NOI18N
        jButton_imprimirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimirReporteActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_imprimirReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 120, 100));

        jLabel_Footer.setText("Creado por Gabriel Echave");
        getContentPane().add(jLabel_Footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarActionPerformed
        RegistrarEquipo registrar_equipo = new RegistrarEquipo();
        registrar_equipo.setVisible(true);
    }//GEN-LAST:event_jButton_RegistrarActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        int validacion = 0;
        String nombre, mail, telefono, direccion;
        nombre = txt_nombre.getText().trim();
        mail = txt_mail.getText().trim();
        telefono = txt_telefono.getText().trim();
        direccion = txt_direccion.getText().trim();
        if (nombre.equals("")) {
            txt_nombre.setBackground(Color.red);
            validacion++;
        }
        if (mail.equals("")) {
            txt_mail.setBackground(Color.red);
            validacion++;
        }
        if (telefono.equals("")) {
            txt_telefono.setBackground(Color.red);
            validacion++;
        }
        if (direccion.equals("")) {
            txt_direccion.setBackground(Color.red);
            validacion++;
        }
        if (validacion == 0) {
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "update clientes set nombre_cliente =?, email_cliente=?, tel_cliente=?, "
                        + "dir_cliente=?, ultima_modificacion=? where id_clientes= '" + IDcliente_update + "'");
                pst.setString(1, nombre);
                pst.setString(2, mail);
                pst.setString(3, telefono);
                pst.setString(4, direccion);
                pst.setString(5, user);

                pst.executeUpdate();
                cn.close();

                Limpiar();
                txt_nombre.setBackground(Color.green);
                txt_mail.setBackground(Color.green);
                txt_telefono.setBackground(Color.green);
                txt_direccion.setBackground(Color.green);
                txt_ultimaModifiacion.setText(user);

                JOptionPane.showMessageDialog(null, "Actualizacion Correcta");
                this.dispose();//
            } catch (SQLException e) {
                System.err.println("Error al actualizar cliente, en informacion al cliente");
                JOptionPane.showMessageDialog(null, "Error al actualizar cliente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos");
        }
    }//GEN-LAST:event_jButton_ActualizarActionPerformed
    //Boton para imprimir la informacion de la db en formato pdf
    private void jButton_imprimirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_imprimirReporteActionPerformed
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            //ruta donde se va a guardar el archivo pdf
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + txt_nombre.getText().trim() + ".pdf"));

            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/images/BannerPDF.png");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Informacion del cliente. \n \n");
            parrafo.setFont(FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tablaCliente = new PdfPTable(5);
            tablaCliente.addCell("ID");
            tablaCliente.addCell("Nombre");
            tablaCliente.addCell("Email");
            tablaCliente.addCell("Telefono");
            tablaCliente.addCell("Direccion");
            

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select * from clientes where id_clientes = '" + IDcliente_update + "'");
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tablaCliente.addCell(rs.getString(1));//id
                        tablaCliente.addCell(rs.getString(2));//nombre
                        tablaCliente.addCell(rs.getString(3));//email
                        tablaCliente.addCell(rs.getString(4));//telefono
                        tablaCliente.addCell(rs.getString(5));//direccion
                    } while (rs.next());

                    documento.add(tablaCliente);
                }

                Paragraph parrafo2 = new Paragraph();
                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.add("\n \n Equipos registrados. \n \n");
                parrafo2.setFont(FontFactory.getFont("Arial", 14, Font.BOLD, BaseColor.DARK_GRAY));
                documento.add(parrafo2);

                PdfPTable tablaEquipos = new PdfPTable(4);
                tablaEquipos.addCell("ID");
                tablaEquipos.addCell("Tipo de equipo");
                tablaEquipos.addCell("Marca");
                tablaEquipos.addCell("Estatus");
                try {
                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement(
                            "select id_equipo, tipo_equipo, marca, estatus from equipos where id_cliente = '" + IDcliente_update + "'");
                    ResultSet rs2 = pst2.executeQuery();
                    if (rs2.next()) {
                        do {
                            tablaEquipos.addCell(rs2.getString(1));
                            tablaEquipos.addCell(rs2.getString(2));
                            tablaEquipos.addCell(rs2.getString(3));
                            tablaEquipos.addCell(rs2.getString(4));
                        } while (rs2.next());
                        documento.add(tablaEquipos);
                    }
                } catch (SQLException e) {
                    System.err.println("Error al cargar la tabla de los equipos registrados");
                }
            } catch (SQLException e) {
                System.err.println("Error al obeter los datos del cliente, boton para imprimir");
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado correctamente");
        } catch (DocumentException | IOException e) {
            System.err.println("Error en PDF o ruta de imagen");
            JOptionPane.showMessageDialog(null, "Error al generar PDF, contacte al programador");
        }
    }//GEN-LAST:event_jButton_imprimirReporteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion_Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Registrar;
    private javax.swing.JButton jButton_imprimirReporte;
    private javax.swing.JLabel jLabel_Footer;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_UltimaModificacion;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_direccion;
    private javax.swing.JLabel jLabel_mail;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JLabel jLabel_titulo;
    private javax.swing.JScrollPane jScrollPane_equipos;
    private javax.swing.JTable jTable_equipos;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_mail;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_ultimaModifiacion;
    // End of variables declaration//GEN-END:variables

    public void Limpiar() {
        txt_nombre.setText("");
        txt_direccion.setText("");
        txt_mail.setText("");
        txt_telefono.setText("");
    }
}
