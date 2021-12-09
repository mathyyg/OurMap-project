package org.jolmkbL2B.controllers;

import java.sql.*;
/**
 *
 *
 *
 *
 * @author Oualid Siraji
 */

public class Sinscrire extends javax.swing.JFrame {
    // Variables declaration
    private javax.swing.JButton btnconnins;
    private javax.swing.JButton btninscrire;
    private javax.swing.JButton btnvider;
    private javax.swing.JTextField insID;
    private javax.swing.JTextField insnom1;
    //private javax.swing.JTextField inspass;
    private javax.swing.JTextField inspass;
    private javax.swing.JTextField inspass2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel registername;
    // des attributs de la classe
	String id;
	String username;
	String password1;
	String password2;
	//String Erreur;
	String message;
    private Connection con;
	
    /**
     * Creates Sinscrire
     */
    public Sinscrire() {
        initComponents();
        try {
            this.con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
        }
        catch(SQLException e)   {
            e.printStackTrace();
            System.err.println("Connection failed in class Sinscrire.");
        }
    }



    @SuppressWarnings("unchecked")
    //
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();
        insID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btninscrire = new javax.swing.JButton();
        btnvider = new javax.swing.JButton();
        btnconnins = new javax.swing.JButton();
        registername = new javax.swing.JLabel();
        inspass = new javax.swing.JPasswordField();   //masquer les caractères de mot de passe dans la console lors de la saisie
        inspass2 = new javax.swing.JPasswordField(); //masquer les caractères de mot de passe dans la console lors de la saisie( pour confirmation de mot de passe)
        jLabel5 = new javax.swing.JLabel();
        insnom1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 58)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("S'inscrire");

        labelID.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelID.setText("Id Utilisateur");

        insID.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Mot de passe");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Confirmer mot de passe");

        btninscrire.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btninscrire.setText("S'inscrire");
        btninscrire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninscrireActionPerformed(evt);
            }
        });

        btnvider.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnvider.setText("Vider");
        btnvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnviderActionPerformed(evt);
            }
        });

        btnconnins.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnconnins.setText("Se connecter");
        btnconnins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {btnconninsActionPerformed(evt);}
        });

        registername.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        inspass.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        inspass2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Nom utilisateur");

        insnom1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inspass2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inspass, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnvider, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(btnconnins, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btninscrire, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insnom1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelID, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insID, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(registername, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelID)
                    .addComponent(insID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(insnom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inspass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(inspass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(registername, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnvider, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btninscrire, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconnins, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void Sinscrireform() //Methode Sinscrire pour l'inscription
    {


        try  {

 
 
             Statement stmt = con.createStatement();
             stmt.execute("USE ourmapdb;");

             ResultSet nameCheck = stmt.executeQuery("SELECT displayName FROM utilisateurs WHERE displayName = \"" + username +
                     "\" ;");


            if(username.equals("") || password1.equals("") || password2.equals(""))
            {
                message="Remplissez les champs SVP";
            }   else if (nameCheck.next()) {
                message ="Ce nom d'utilisateur est déjà utilisé, choisissez-en un autre.";
            }
            else if(password1.equals(password2) == false)
                 {
                     message="les Mots de passe sont différents. Recommencez";
                 }
            else {
                finaliserInscription(username, password1);
            }
            registername.setText(message);
           // new Seconnecter().setVisible(true);
            //dispose();
        }

        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }

    }

    /** Cette méthode execute les requetes SQL necessaire à l'inscription d'un nouvel utilisateur.
     * Elle inscrit d'abord l'utilisateur dans la table utilisateurs pui créé sa liste de favoris. Elle n'est executée
     * qu'une fois les verification de mot de passe et de nom d'utilisateurs sont effectuées.
     *
     * @param username, password1
     * @return true si l'inscription se déroule correctement, false en cas d'erreur
     * @version 1
     * @author Oualid Siraji */
    private boolean finaliserInscription(String username, String password1) {


        String log ="insert into utilisateurs (`displayName`, `password`)" +
                "values ( \""+ this.username +"\", \""+ this.password1 +"\" );" ;       //Creation d'un nouvel utilisateur
        //execution de la requete ci dessus
        try{
            Statement stmt = con.createStatement(); //Pre^paration de la requete d'insertion
            int success = stmt.executeUpdate(log);

            if(success == 1)    {
                con.commit();// validation de l'operation d'insertion
                this.message = "Vous êtes bien inscrit";

                /* Recupération de l'identifiant de l'utilisateur */
                String getLastIDQuery = "SELECT idutilisateur FROM utilisateurs ORDER BY idutilisateur DESC LIMIT 1;"; //Tri decroissant de la colonne idutilisateur pour recuperer le dernier identifiant créé (Auto-increment)
                ResultSet rs = stmt.executeQuery(getLastIDQuery);
                rs.next();
                long idutilisateur = rs.getLong(1);


                ListeController listeController = new ListeController(); //Instance du controlleur gêrant les listes
                long idFavList = listeController.createList(idutilisateur, "Favoris");
                //Creation de la liste de favoris du nouvel utilisateur

                stmt.executeUpdate("UPDATE `utilisateurs`\n" +
                        " SET `idFavList` = " + idFavList + "\n" +
                        "WHERE idutilisateur = " + idutilisateur + ";"); //Attribution de la liste de favoris a l'utilisateur
                con.commit(); //validation de l'opération

                return true;
            }
            else    {
                message = "Echec de l'inscription. Erreur réseau ou erreur SQL.";}
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.err.println("Inscription failed");
        }
        return false;
    }
    private void btninscrireActionPerformed(java.awt.event.ActionEvent evt) {//btninscrireActionPerformed
        this.username=insnom1.getText();
        this.password1=inspass.getText();
        this.password2=inspass2.getText();
        //this.Erreur=btninscrire.getText();
        Sinscrireform();
        
    }//GEN-LAST:event_btninscrireActionPerformed

    private void btnviderActionPerformed(java.awt.event.ActionEvent evt) {//Cette boutton est creer pour vider les champs
    insID.setText("");
    insnom1.setText("");
    inspass.setText("");
    inspass2.setText("");
    }
    private void btnconninsActionPerformed(java.awt.event.ActionEvent evt) {
        new Seconnecter().setVisible(true);
        dispose();
    }

    /**
     * @param args les arguments de la ligne de commande
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
            java.util.logging.Logger.getLogger(Sinscrire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sinscrire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sinscrire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sinscrire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Créer et afficher le formulaire */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sinscrire().setVisible(true);
            }
        });
    }


}
