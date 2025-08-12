package registro.clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Alumnos extends javax.swing.JFrame {

    public Alumnos() {
        initComponents();
        this.setLocationRelativeTo(this);
        mostrardatos("");
    }
    conectar cc = new conectar();
    Connection cn = cc.conexion();

    void mostrardatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Carrera");
        tblreg.setModel(modelo);
        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT * FROM users";
        } else {
            sql = "SELECT * FROM users WHERE  Nombre=+valor";
        }
        String[] datos = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtcarrera = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblreg = new javax.swing.JTable();
        btneliminar = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(630, 490));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 25, -1, -1));
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 23, 230, -1));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setText("Apellido:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 74, -1, -1));
        jPanel1.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 72, 230, -1));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setText("Carrera:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 122, -1, -1));
        jPanel1.add(txtcarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 120, 230, -1));

        btnguardar.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setPreferredSize(new java.awt.Dimension(131, 36));
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, -1));

        tblreg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblreg.setOpaque(false);
        jScrollPane3.setViewportView(tblreg);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 590, 240));

        btneliminar.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, -1, -1));
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 490));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        int fila = tblreg.getSelectedRow();
        if (fila >= 0) {
            txtnombre.setText(tblreg.getValueAt(fila, 1).toString());
            txtapellido.setText(tblreg.getValueAt(fila, 2).toString());
            txtcarrera.setText(tblreg.getValueAt(fila, 3).toString());
        } else {
            System.out.println("Fila no seleccionada !!!");
            JOptionPane.showMessageDialog(null, "No selecciono fila para eliminar !!!");
        }
        String cod = "";
        cod = tblreg.getValueAt(fila, 0).toString();
        try {
            PreparedStatement pst = cn.prepareStatement("DELETE FROM alumnos WHERE codigo = ' " + cod + "'");
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro Eliminado !!!");
            pst.executeUpdate();
            mostrardatos("");
            txtnombre.setText("");
            txtapellido.setText("");
            txtcarrera.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se encontro el registro !!!");
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO alumnos( Codigo,Nombre,Apellido,Carrera)values(?,?,?,?)");
            int n = 0;
            pst.setInt(1, n);
            pst.setString(2, txtnombre.getText());
            pst.setString(3, txtapellido.getText());
            pst.setString(4, txtcarrera.getText());

            pst.executeUpdate();
            mostrardatos("");
            JOptionPane.showMessageDialog(null, "Registro Almacenado !!!");
            pst.executeUpdate();

            txtnombre.setText("");
            txtapellido.setText("");
            txtcarrera.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se almaceno el registro  !!!" + e.getMessage());
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnguardarActionPerformed

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
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblreg;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtcarrera;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

}
