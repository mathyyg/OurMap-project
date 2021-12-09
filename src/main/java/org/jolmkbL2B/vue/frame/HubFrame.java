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
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme.ThemeLaf;
import com.formdev.flatlaf.intellijthemes.*;
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
    private ThemeFrame themeFrame = new ThemeFrame();
    private HashMap<String, ThemeLaf> themes = new HashMap<String, ThemeLaf>();
    public HubFrame(AppControllers app) throws SQLException {
        this.app = app;
        initComponents();
    }

    MouseListener addMarqueur = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            enableMarqueurSelection(false);

            /** création d'un marqueur en cliquant
             * getsource() est une méthode qui sert à identifier le boutton cliqué sur la carte
             * la methode convertPointToGeoPosition prend en paramétres les coordonées X et Y
             * et ensuite les converti en Latitude et Longitude respectivement
             * grace à cette conversion nous avons maintenat les coordonnées exactes de notre clique
             * que nous pouvons ainsi attribuer à un marqueur CUSTOM pour que l'utilisateur puisse le placer
             *  n'importe où sur la carte
             *
             * au final nous avons aussi un affichage des coordonnées x et y ainsi que leur conversion
             * qui servait au début à tester l'exactitude de nos cliques et de leur conversion et qui maintenant sert à renseigner
             * les données géographique d'un marqueur quelconque
             * @param me_src,clickwaypoint
             * @khaled mahdi
             * */
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
    /**
     *
     *
     *
     * */
    MouseListener selectMarqueurMap = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            JXMapViewer me_src = (JXMapViewer) me.getSource();
            for(Marqueur wp : mapPanel1.waypoints) {
                if (Math.abs(wp.getPosition().getLatitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLatitude())  <= 0.0025
                && Math.abs(wp.getPosition().getLongitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLongitude()) <= 0.0025 ) {
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



    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu2 = new JMenu();
        menuRefreshLists = new JMenuItem();
        separator1 = new JSeparator();
        menuQUitter = new JMenuItem();
        menu1 = new JMenu();
        modeAdminMenu = new JMenuItem();
        checkBoxMenuItem1 = new JCheckBoxMenuItem();
        menu3 = new JMenu();
        menuSeDeco = new JMenuItem();
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

                //---- menuRefreshLists ----
                menuRefreshLists.setText("Rafra\u00eechir les listes");
                menuRefreshLists.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
                menu2.add(menuRefreshLists);
                menu2.add(separator1);

                //---- menuQUitter ----
                menuQUitter.setText("Quitter");
                menuQUitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
                menu2.add(menuQUitter);
            }
            menuBar1.add(menu2);

            //======== menu1 ========
            {
                menu1.setText("Affichage");

                //---- modeAdminMenu ----
                modeAdminMenu.setText("Lancer le mode admin");
                menu1.add(modeAdminMenu);

                //---- checkBoxMenuItem1 ----
                checkBoxMenuItem1.setText("Liste des th\u00e8mes");
                menu1.add(checkBoxMenuItem1);
            }
            menuBar1.add(menu1);

            //======== menu3 ========
            {
                menu3.setText("Compte");

                //---- menuSeDeco ----
                menuSeDeco.setText("Se d\u00e9connecter");
                menuSeDeco.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
                menu3.add(menuSeDeco);
            }
            menuBar1.add(menu3);
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
        menuQUitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menuSeDeco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame loginFrame = new LoginFrame(app);
                loginFrame.setVisible(true);
                dispose();
            }
        });
        modeAdminMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.utilisateurController.isAdmin((int) app.idUtilisateurConnecte)) {
                    AdminFrame adminFrame = new AdminFrame(app);
                    adminFrame.setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Vous ne disposez pas des droits administrateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuRefreshLists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listesMarqueursPanel1.initTree();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        initThemes();
        DefaultListModel listModel = new DefaultListModel();
        for(String theme : this.themes.keySet()) {
            listModel.addElement(theme);
        }
        this.themeFrame.jlistThemes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.themeFrame.jlistThemes.setModel(listModel);
        this.themeFrame.jlistThemes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String res = "Arc";
                if(!e.getValueIsAdjusting()) {
                    res = themeFrame.jlistThemes.getSelectedValue().toString();
                }
                updateLF(res);
            }
        });
        this.themeFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                checkBoxMenuItem1.setSelected(false);
                e.getWindow().dispose();
            }
        });

//        jlistThemes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        jlistThemes.setModel(listModel);
//        jlistThemes.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                String res = "Arc";
//                if(!e.getValueIsAdjusting()) {
//                    res = jlistThemes.getSelectedValue().toString();
//                }
//                updateLF(res);
//            }
//        });
//
//        scrollPane1.setVisible(false);
        checkBoxMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkBoxMenuItem1.isSelected()) {
//                    scrollPane1.setVisible(true);
                    themeFrame.setVisible(true);
                }
                else {
//                    scrollPane1.setVisible(false);
                    themeFrame.setVisible(false);
                }
            }
        });
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

    private void initThemes() {
        this.themes.put("Arc", new FlatArcIJTheme());
        this.themes.put("Arc Orange", new FlatArcOrangeIJTheme());
        this.themes.put("Arc Dark", new FlatArcDarkIJTheme());
        this.themes.put("Arc Dark Orange", new FlatArcDarkOrangeIJTheme());
        this.themes.put("Carbon", new FlatCarbonIJTheme());
        this.themes.put("Cobalt 2", new FlatCobalt2IJTheme());
        this.themes.put("Cyan Light", new FlatCyanLightIJTheme());
        this.themes.put("Dark Flat", new FlatDarkFlatIJTheme());
        this.themes.put("Dark Purple", new FlatDarkPurpleIJTheme());
        this.themes.put("Dracula", new FlatDraculaIJTheme());
        this.themes.put("Gradianto Dark Fuchsia", new FlatGradiantoDarkFuchsiaIJTheme());
        this.themes.put("Grandianto Deep Ocean", new FlatGradiantoDeepOceanIJTheme());
        this.themes.put("Gradianto Midnight Blue", new FlatGradiantoMidnightBlueIJTheme());
        this.themes.put("Gradianto Nature Green", new FlatGradiantoNatureGreenIJTheme());
        this.themes.put("Gray", new FlatGrayIJTheme());
        this.themes.put("Gruvbox Dark Hard", new FlatGruvboxDarkHardIJTheme());
        this.themes.put("Gruvbox Dark Medium", new FlatGruvboxDarkMediumIJTheme());
        this.themes.put("Gruvbox Dark Soft", new FlatGruvboxDarkSoftIJTheme());
        this.themes.put("Hiberbee Dark", new FlatHiberbeeDarkIJTheme());
        this.themes.put("High Contrast", new FlatHighContrastIJTheme());
        this.themes.put("Light Flat", new FlatLightFlatIJTheme());
        this.themes.put("Material Design Dark", new FlatMaterialDesignDarkIJTheme());
        this.themes.put("Monocai", new FlatMonocaiIJTheme());
        this.themes.put("Nord", new FlatNordIJTheme());
        this.themes.put("One Dark", new FlatOneDarkIJTheme());
        this.themes.put("Solarized Dark", new FlatSolarizedDarkIJTheme());
        this.themes.put("Solarized Light", new FlatSolarizedLightIJTheme());
        this.themes.put("Spacegray", new FlatSpacegrayIJTheme());
        this.themes.put("Vuesion", new FlatVuesionIJTheme());
    }

    public void updateLF(String lf) {
        try {
            UIManager.setLookAndFeel(themes.get(lf));
            FlatLaf.updateUI();

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu2;
    public JMenuItem menuRefreshLists;
    private JSeparator separator1;
    public JMenuItem menuQUitter;
    private JMenu menu1;
    public JMenuItem modeAdminMenu;
    private JCheckBoxMenuItem checkBoxMenuItem1;
    private JMenu menu3;
    private JMenuItem menuSeDeco;
    public ListesMarqueursPanel listesMarqueursPanel1;
    public MapPanel mapPanel1;
    public MarqueurInfosPanel marqueurInfosPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
