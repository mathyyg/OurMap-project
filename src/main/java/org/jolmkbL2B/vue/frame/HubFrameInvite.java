/*
 * Created by JFormDesigner on Wed Dec 08 22:25:34 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import net.miginfocom.swing.*;

import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.*;
import org.jolmkbL2B.vue.panel.*;
import org.jxmapviewer.JXMapViewer;

/**
 * @author Mathys Gagner
 * Cette classe permet l'affichage de la fenêtre principale pour un utilisateur non authentifié (un invité)
 * Aucune modification ni envoi de suggestion n'est possible dans ce mode, mais les menus permettent de revenir en arrière
 * pour se connecter ou s'inscrire
 */
public class HubFrameInvite extends JFrame {
    private final AppControllers app;

    public HubFrameInvite(AppControllers app) {
        this.app = app;
        initComponents();
    }

    //MouseListener pour afficher les coordonnées d'un point gps lors d'un clic sur la carte
    MouseListener getMarqueurGPS = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            enableMarqueurSelection(false);
            menuGetGPS.setEnabled(false);

            /** création d'un marqueur en cliquant  */
            JXMapViewer me_src = (JXMapViewer) me.getSource();
            double latitude = me_src.convertPointToGeoPosition(me.getPoint()).getLatitude();
            double longitude = me_src.convertPointToGeoPosition(me.getPoint()).getLongitude();

            JOptionPane.showMessageDialog(getParent(), "Point sélectionné: \nLatitude: " + latitude +"\nLongitude: "+longitude);

            System.out.println(" mouse  x coordinates =" + me_src.getMousePosition().getX() + "/ mouse y coordinates =" + me_src.getMousePosition().getY());
            System.out.println("CONVERTING MOUSE COORDINATES TO geoposition ones =====> latitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLatitude() + "  longitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLongitude());
            enableMarqueurCreation(false);
            enableMarqueurSelection(true);
//            menuGetGPS.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    menuGetGPS.setEnabled(false);
//                    enableMarqueurCreation(true);
//                }
//            });
        }

        public void mousePressed(MouseEvent e) {} public void mouseReleased(MouseEvent e) {} public void mouseEntered(MouseEvent e) {} public void mouseExited(MouseEvent e) {}
    };

    //MouseListener pour sélectionner un marqueur sur la map
    MouseListener selectMarqueurMap = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            JXMapViewer me_src = (JXMapViewer) me.getSource();
            for(Marqueur wp : mapPanel1.waypoints) {
                if (Math.abs(wp.getPosition().getLatitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLatitude())  <= 0.0005
                        && Math.abs(wp.getPosition().getLongitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLongitude()) <= 0.0005 ) {
                    marqueurInfosPanel1.buttonInfos.setEnabled(true);
                    marqueurInfosPanel1.buttonCommentaires.setEnabled(true);
                    System.out.println(wp.getName() + " | " + wp.getPlaceType().toString());

                    marqueurInfosPanel1.tfNom.setText(app.marqueurController.fetchMarqueurNom(wp.getLieuID(), wp.getPlaceType()) + " (" + wp.getPlaceType().toString() + ")");

                    System.out.println(app.marqueurController.fetchMarqueurDescription(wp.getLieuID(), wp.getPlaceType()));
                    System.out.println(wp.getLieuID());

                    marqueurInfosPanel1.taDesc.setText(app.marqueurController.fetchMarqueurDescription(wp.getLieuID(), wp.getPlaceType()));
//                    marqueurInfosPanel1.taDesc.setText(wp.);
                    marqueurInfosPanel1.buttonInfos.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                enablePlusDinfos(wp);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    marqueurInfosPanel1.buttonCommentaires.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                enableCommentaires(wp);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    break;
                }
                else {
                    marqueurInfosPanel1.tfNom.setText("aucun marqueur sélectionné");
                    marqueurInfosPanel1.taDesc.setText("");
                    marqueurInfosPanel1.buttonInfos.setEnabled(false);
                    marqueurInfosPanel1.buttonCommentaires.setEnabled(false);
                    marqueurInfosPanel1.buttonMemos.setEnabled(false);
                    disableButtons();
                }
//                System.out.println(wp.getPosition().toString());
            }
            System.out.println("CONVERTING MOUSE COORDINATES TO geoposition ones =====> latitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLatitude() + "  longitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLongitude());
        }
        public void mousePressed(MouseEvent e) {} public void mouseReleased(MouseEvent e) {} public void mouseEntered(MouseEvent e) {} public void mouseExited(MouseEvent e) {}
    };

    /**
     * @author Mathys Gagner
     * Méthode créée par JFormDesigner qui initialise tous les composants Swing
     * Le reste des instructions est personnalisé et contient toutes les autres initialisations ;
     * listeners, états de composants, ...
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        menuQuitter = new JMenuItem();
        menu1 = new JMenu();
        checkBoxMenuItem1 = new JCheckBoxMenuItem();
        menuGetGPS = new JMenuItem();
        menu3 = new JMenu();
        menuLogin = new JMenuItem();
        menuSinscrire = new JMenuItem();
        mapPanel1 = new MapPanel(this.app);
        marqueurInfosPanel1 = new MarqueurInfosPanel();

        //======== this ========
        setTitle("OurMap project (mode invit\u00e9)");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("Fichier");

                //---- menuQuitter ----
                menuQuitter.setText("Quitter");
                menuQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
                menu2.add(menuQuitter);
            }
            menuBar1.add(menu2);

            //======== menu1 ========
            {
                menu1.setText("Affichage");

                //---- checkBoxMenuItem1 ----
                checkBoxMenuItem1.setText("Liste de th\u00e8mes");
                checkBoxMenuItem1.setSelected(true);
                menu1.add(checkBoxMenuItem1);
                menu1.addSeparator();

                //---- menuGetGPS ----
                menuGetGPS.setText("Afficher les coordonn\u00e9es du clic...");
                menu1.add(menuGetGPS);
            }
            menuBar1.add(menu1);

            //======== menu3 ========
            {
                menu3.setText("Compte");

                //---- menuLogin ----
                menuLogin.setText("Se connecter");
                menuLogin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
                menu3.add(menuLogin);

                //---- menuSinscrire ----
                menuSinscrire.setText("S'inscrire");
                menuSinscrire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
                menu3.add(menuSinscrire);
            }
            menuBar1.add(menu3);
        }
        setJMenuBar(menuBar1);
        contentPane.add(mapPanel1, BorderLayout.CENTER);
        contentPane.add(marqueurInfosPanel1, BorderLayout.SOUTH);
        setSize(760, 470);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        menuGetGPS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuGetGPS.setEnabled(true);
                enableMarqueurCreation(true);
            }
        });
        enableMarqueurSelection(true);
        menuQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menuLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginFrame loginFrame = new LoginFrame(app);
                loginFrame.setVisible(true);
            }
        });
        menuSinscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegisterFrame registerFrame = new RegisterFrame(app);
                registerFrame.setVisible(true);
            }
        });
    }

    /**
     * @author Mathys Gagner
     * Méthode permettant l'activation ou non du mode "sélection de marqueur"
     * @param etat true ou false
     */
    public void enableMarqueurSelection(boolean etat) {
        if(etat == true) {
            mapPanel1.OurMap.addMouseListener(selectMarqueurMap);
            this.marqueurInfosPanel1.buttonInfos.setEnabled(false);
            this.marqueurInfosPanel1.buttonCommentaires.setEnabled(false);
            this.marqueurInfosPanel1.buttonMemos.setEnabled(false);
        }
        else {
            mapPanel1.OurMap.removeMouseListener(selectMarqueurMap);
        }
    }

    /**
     * @author Mathys Gagner
     * Méthode permettant l'activation ou non du listener qui affiche les coordonnées GPS lors d'un clic sur la carte
     * @param etat true ou false
     */
    public void enableMarqueurCreation(boolean etat) {
        /** mouse clicks to waypoints method  */
        if(etat == true) {
            mapPanel1.OurMap.addMouseListener(getMarqueurGPS);
            enableMarqueurSelection(false);
        }
        else {
            mapPanel1.OurMap.removeMouseListener(getMarqueurGPS);
            menuGetGPS.setEnabled(true);
        }
    }

    /**
     * @author Mathys Gagner
     * Méthode qui désactive les boutons de mémos, commentaires et informations détaillées d'un marqueur
     * (pas de marqueur sélectionné = boutons désactivés)
     */
    public void disableButtons() {
        for(ActionListener al : this.marqueurInfosPanel1.buttonInfos.getActionListeners()) {
            this.marqueurInfosPanel1.buttonInfos.removeActionListener(al);
        }
        for(ActionListener al : this.marqueurInfosPanel1.buttonMemos.getActionListeners()) {
            this.marqueurInfosPanel1.buttonMemos.removeActionListener(al);
        }
        for(ActionListener al : this.marqueurInfosPanel1.buttonCommentaires.getActionListeners()) {
            this.marqueurInfosPanel1.buttonCommentaires.removeActionListener(al);
        }
    }

    /**
     * @author Mathys Gagner
     * Cette méthode ouvre la fenêtre des commentaires associés au marqueur
     * @param mq le marqueur dont on veut gérer les commentaires (publics)
     * @throws SQLException
     */
    public void enableCommentaires(Marqueur mq) throws SQLException {
        CommentFrame commentFrame = new CommentFrame(this.app, mq);
        commentFrame.ButtonAddComment.setEnabled(false);
        commentFrame.textAComment.setEditable(false);
        commentFrame.setVisible(true);
    }

    /**
     * @author Mathys Gagner
     * Méthode qui affiche les informations détaillées d'un marqueur selon son type.
     * @param mq Marqueur dont on veut afficher les informations
     * @throws SQLException
     */
    public void enablePlusDinfos(Marqueur mq) throws SQLException {
//        System.out.println(mq.getName());
        ResultSet infos = this.app.marqueurController.fetchAllInfo(mq.getLieuID(), mq.getPlaceType());
//        while (infos.next()) {
//            for(int i=1; i<=infos.getMetaData().getColumnCount(); i++) {
//                if(i>1) {
//                    System.out.println(", ");
//                }
//                System.out.println(infos.getString(i) + " "+ infos.getMetaData().getColumnName(i) + " " +infos.getObject(i).getClass().getName());
//            }
//        }

        switch(mq.getPlaceType()) {
            case CUSTOM:
                infos.next();
                CustomMarqueur customMarqueur = new CustomMarqueur(PlaceType.CUSTOM, 0.0, 0.0, infos.getLong(1), infos.getString(5),
                        infos.getString(7), infos.getLong(6));
                CustomFrame custom = new CustomFrame(customMarqueur, this.app);
                custom.buttonSauvegarder.setEnabled(false);
                custom.button1.setEnabled(false);

                custom.setVisible(true);
                break;
            case ARRETBUS:
                infos.next();
                ArretBus arretBus = new ArretBus(infos.getInt(1), 0.0, 0.0,
                        infos.getString(5), infos.getString(6), infos.getString(7), infos.getBoolean(8));
                ArretBusFrame arretBusFrame = new ArretBusFrame(arretBus, this.app);
                arretBusFrame.buttonSuggestion.setEnabled(false);

                arretBusFrame.setVisible(true);
                break;
            case HOTEL:
                infos.next();
                Hotel hotel = new Hotel(infos.getInt(1), 0.0, 0.0, infos.getString(5), infos.getString(6), infos.getString(7),
                        infos.getString(8), infos.getBoolean(9), infos.getString(10), infos.getInt(11), infos.getString(12),
                        infos.getString(13), new boolean[]{infos.getBoolean(14), infos.getBoolean(15), infos.getBoolean(16), infos.getBoolean(17)},
                        infos.getBoolean(17));
                HotelFrame hotelFrame = new HotelFrame(hotel, this.app);
                hotelFrame.buttonSuggestion.setEnabled(false);

                hotelFrame.setVisible(true);
                break;
            case SCHOOL:
                infos.next();
                School school = new School(0.0, 0.0, infos.getLong(1), infos.getString(5), infos.getString(6), infos.getString(7),
                        infos.getString(10), SchoolType.valueOf(infos.getString(8)), SchoolStatus.valueOf(infos.getString(9)));
                SchoolFrame schoolFrame = new SchoolFrame(school, this.app);
                schoolFrame.buttonSuggestion.setEnabled(false);

                schoolFrame.setVisible(true);
                break;
            default:
                throw new IllegalArgumentException("PlaceType invalide");
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu2;
    public JMenuItem menuQuitter;
    private JMenu menu1;
    public JCheckBoxMenuItem checkBoxMenuItem1;
    public JMenuItem menuGetGPS;
    private JMenu menu3;
    public JMenuItem menuLogin;
    public JMenuItem menuSinscrire;
    public MapPanel mapPanel1;
    public MarqueurInfosPanel marqueurInfosPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
