/*
 * Created by JFormDesigner on Wed Dec 01 13:08:35 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.*;
import org.jolmkbL2B.vue.panel.*;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * @author Mathys Gagner
 */
public class HubFrame extends JFrame {

    private AppControllers app;
    public HubFrame(AppControllers app) throws SQLException {
        super("OurMap : Hub");
        this.app = app;
        initComponents();
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    MouseListener addMarqueur = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            enableMarqueurSelection(false);

            /** création d'un marqueur en cliquant  */
            JXMapViewer me_src = (JXMapViewer) me.getSource();

            CustomMarqueur clickwaypoint = new CustomMarqueur(PlaceType.CUSTOM,me_src.convertPointToGeoPosition(me.getPoint()).getLatitude(),
                    me_src.convertPointToGeoPosition(me.getPoint()).getLongitude(), 0, "Marqueur custom", "...", app.idUtilisateurConnecte);

            int idnew = app.marqueurController.insertMarqueur(clickwaypoint);

            if(idnew > -1) {
                clickwaypoint.setLieuID(idnew);
                mapPanel1.waypoints.add(clickwaypoint);
                mapPanel1.OurMap.setOverlayPainter(mapPanel1.loader.updateDisplay(mapPanel1.waypoints));
                JOptionPane.showMessageDialog(getParent(), "Votre marqueur personnalisé a bien été ajouté. Pensez à le modifier.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(getParent(), "Erreur lors de la création du marqueur", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            System.out.println(" mouse  x coordinates =" + me_src.getMousePosition().getX() + "/ mouse y coordinates =" + me_src.getMousePosition().getY());
            System.out.println("CONVERTING MOUSE COORDINATES TO geoposition ones =====> latitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLatitude() + "  longitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLongitude());
            enableMarqueurCreation(false);
            listesMarqueursPanel1.createMarqueurButton.setEnabled(true);
            enableMarqueurSelection(true);
            listesMarqueursPanel1.createMarqueurButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listesMarqueursPanel1.createMarqueurButton.setEnabled(false);
                    enableMarqueurCreation(true);
                }
            });
        }

        public void mousePressed(MouseEvent e) {} public void mouseReleased(MouseEvent e) {} public void mouseEntered(MouseEvent e) {} public void mouseExited(MouseEvent e) {}
    };

    MouseListener selectMarqueurMap = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            JXMapViewer me_src = (JXMapViewer) me.getSource();
            for(Marqueur wp : mapPanel1.waypoints) {
                if (Math.abs(wp.getPosition().getLatitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLatitude())  <= 0.0005
                && Math.abs(wp.getPosition().getLongitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLongitude()) <= 0.0005 ) {
                    marqueurInfosPanel1.buttonInfos.setEnabled(true);
                    marqueurInfosPanel1.buttonCommentaires.setEnabled(true);
                    marqueurInfosPanel1.buttonMemos.setEnabled(true);
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

                        marqueurInfosPanel1.buttonMemos.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    enableMemos(wp);
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
                    marqueurInfosPanel1.tfNom.setText("aucun marqueur");
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



    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        separator1 = new JSeparator();
        menuItem1 = new JMenuItem();
        menu1 = new JMenu();
        checkBoxMenuItem1 = new JCheckBoxMenuItem();
        listesMarqueursPanel1 = new ListesMarqueursPanel(this.app);
        mapPanel1 = new MapPanel(this.app);
        marqueurInfosPanel1 = new MarqueurInfosPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("OurMap project");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("Fichier");

                //---- menuItem2 ----
                menuItem2.setText("Rafra\u00eechir la carte");
                menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
                menu2.add(menuItem2);
                menu2.add(separator1);

                //---- menuItem1 ----
                menuItem1.setText("Quitter");
                menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
                menu2.add(menuItem1);
            }
            menuBar1.add(menu2);

            //======== menu1 ========
            {
                menu1.setText("Affichage");

                //---- checkBoxMenuItem1 ----
                checkBoxMenuItem1.setText("Liste de th\u00e8mes");
                checkBoxMenuItem1.setSelected(true);
                menu1.add(checkBoxMenuItem1);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);
        contentPane.add(listesMarqueursPanel1, BorderLayout.EAST);
        contentPane.add(mapPanel1, BorderLayout.CENTER);
        contentPane.add(marqueurInfosPanel1, BorderLayout.SOUTH);
        setSize(845, 520);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        listesMarqueursPanel1.initTree();
        listesMarqueursPanel1.createMarqueurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listesMarqueursPanel1.createMarqueurButton.setEnabled(false);
                enableMarqueurCreation(true);
            }
        });

        enableMarqueurSelection(true);

    }

    public void enableMarqueurCreation(boolean etat) {
        /** mouse clicks to waypoints method  */
        if(etat == true) {
            mapPanel1.OurMap.addMouseListener(addMarqueur);
            enableMarqueurSelection(false);
        }
        else {
            mapPanel1.OurMap.removeMouseListener(addMarqueur);
        }
    }

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
                custom.setVisible(true);
                break;
            case ARRETBUS:
                infos.next();
                ArretBus arretBus = new ArretBus(infos.getInt(1), 0.0, 0.0,
                        infos.getString(5), infos.getString(6), infos.getString(7), infos.getBoolean(8));
                ArretBusFrame arretBusFrame = new ArretBusFrame(arretBus, this.app);
                arretBusFrame.setVisible(true);
                break;
            case HOTEL:
                infos.next();
                Hotel hotel = new Hotel(infos.getInt(1), 0.0, 0.0, infos.getString(5), infos.getString(6), infos.getString(7),
                        infos.getString(8), infos.getBoolean(9), infos.getString(10), infos.getInt(11), infos.getString(12),
                        infos.getString(13), new boolean[]{infos.getBoolean(14), infos.getBoolean(15), infos.getBoolean(16), infos.getBoolean(17)},
                        infos.getBoolean(17));
                HotelFrame hotelFrame = new HotelFrame(hotel, this.app);
                hotelFrame.setVisible(true);
                break;
            case SCHOOL:
                infos.next();
                School school = new School(0.0, 0.0, infos.getLong(1), infos.getString(5), infos.getString(6), infos.getString(7),
                        infos.getString(10), SchoolType.valueOf(infos.getString(8)), SchoolStatus.valueOf(infos.getString(9)));
                SchoolFrame schoolFrame = new SchoolFrame(school, this.app);
                schoolFrame.setVisible(true);
                break;
            default:
                throw new IllegalArgumentException("PlaceType invalide");
        }
    }

    public void enableMemos(Marqueur mq) throws SQLException {
//        ResultSet memos = this.app.memoController.fetchUserMemo(mq.getLieuID(), this.app.idUtilisateurConnecte);
        MemoFrame memoFrame = new MemoFrame(this.app, mq);
        memoFrame.setVisible(true);
    }

    public void enableCommentaires(Marqueur mq) throws SQLException {
        CommentFrame commentFrame = new CommentFrame(this.app, mq);
        commentFrame.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu2;
    private JMenuItem menuItem2;
    private JSeparator separator1;
    private JMenuItem menuItem1;
    private JMenu menu1;
    private JCheckBoxMenuItem checkBoxMenuItem1;
    public ListesMarqueursPanel listesMarqueursPanel1;
    public MapPanel mapPanel1;
    public MarqueurInfosPanel marqueurInfosPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
