package org.jolmkbL2B.vue.frame;

import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.controllers.Seconnecter;
import org.jolmkbL2B.controllers.Sinscrire;

import javax.swing.*;
import java.sql.*;

/**
 * @author Mohammed Jalal El Hani
 * Cette classe permet de se connecter à l'application
 */
public class LoginFrame extends JFrame {
    private final AppControllers app;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler;
    private javax.swing.JButton btnconn;
    private javax.swing.JButton btnisncrire1;
    private javax.swing.JTextField connnom1;
    private javax.swing.JTextField connpass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel message;


    private String usernameconn; //texte field du formulaire pour remplire le nom d'utilisateur
    private String passwordconn;   //texte field du formulaire pour remplire le mot de passe
    private String mmessage; //pour afficher les erreurs de connexion
    /**
     Creation de formulaire de connexion
     */
    public LoginFrame(AppControllers app) {
        super("Se connecter");
        this.app = app;
        initComponents(); //appel de la methode qui contient les composants de formulaire
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel(); // label titre de formulaire
        jLabel2 = new javax.swing.JLabel(); // label pour username
        jLabel4 = new javax.swing.JLabel(); //label pour password
        connpass = new javax.swing.JPasswordField(); //text field username
        btnconn = new javax.swing.JButton(); //boutton de connexion
        Annuler = new javax.swing.JButton(); //boutton annuler qui permetra l'utilisateur de vider le formulaire
        message = new javax.swing.JLabel(); //label pour l'affichage des erreurs de formulaires
        btnisncrire1 = new javax.swing.JButton(); //boutton inscription
        connnom1 = new javax.swing.JTextField();//text field pour nom utilisateur

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Se connecter");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nom utilisateur");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mot de passe");

        connpass.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnconn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnconn.setText("Se connecter");
        btnconn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconnActionPerformed(evt);
            }
        });

        Annuler.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Annuler.setText("Mode invité");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });

        message.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnisncrire1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnisncrire1.setText("S'inscrire");
        btnisncrire1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnisncrire1ActionPerformed(evt);
            }
        });

        connnom1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(60, 60, 60))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(connpass, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(Annuler, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                                                .addComponent(btnisncrire1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(btnconn, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(52, 52, 52))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(283, Short.MAX_VALUE)
                                        .addComponent(connnom1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(btnconn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(btnisncrire1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(Annuler, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                                                .addGap(45, 45, 45))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(connpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(179, 179, 179)
                                        .addComponent(connnom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(259, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {
        HubFrameInvite hubFrameInvite = new HubFrameInvite(app);
        hubFrameInvite.setVisible(true);
        this.dispose();
    }

    //Bouttoun se connecter
    private void btnconnActionPerformed(java.awt.event.ActionEvent evt) {

        usernameconn = btnconn.getText();
        passwordconn=btnconn.getText();
        mmessage=btnconn.getText();
        if(evt.getSource()==btnconn){
            SeconnecterForm();

        }


    }
    //Methode Se connecter
    private void SeconnecterForm()
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            String log ="select idutilisateur, displayName,password from utilisateurs where displayName='"+connnom1.getText()+"' and password='"+connpass.getText()+"'";


            PreparedStatement ps =  con.prepareStatement(log);
            ResultSet resultat = ps.executeQuery();
            if(resultat.next()){
                this.app.idUtilisateurConnecte = resultat.getLong(1);
                this.second();
            }

            else
            {
                mmessage="Désolé, nous n'avons pas pu trouver votre compte.";
            }
            message.setText(mmessage);
        }

        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
    }
    private void btnisncrire1ActionPerformed(java.awt.event.ActionEvent evt) {

        new RegisterFrame(this.app).setVisible(true);
        dispose();
    }

    public void second() throws SQLException {
        HubFrame next = new HubFrame(app);
        next.setVisible(true);
        this.dispose();
    }


//    public static void main(String args[]) {
//
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Seconnecter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Seconnecter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Seconnecter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Seconnecter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//
//
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Seconnecter().setVisible(true);
//            }
//        });
//    }
}
