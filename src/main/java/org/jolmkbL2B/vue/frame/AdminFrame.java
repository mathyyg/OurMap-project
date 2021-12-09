/*
 * Created by JFormDesigner on Thu Dec 09 12:45:10 CET 2021
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
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.*;
import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.*;
import org.jolmkbL2B.vue.panel.*;
import org.jxmapviewer.JXMapViewer;

/**
 * @author Mathys Gagner
 * Cette classe affiche l'interface en mode administrateur.
 */
public class AdminFrame extends JFrame {
    private final AppControllers app;
    private ThemeFrame themeFrame = new ThemeFrame();
    private HashMap<String, IntelliJTheme.ThemeLaf> themes = new HashMap<String, IntelliJTheme.ThemeLaf>();

    public AdminFrame(AppControllers app) {
        this.app = app;
        initComponents();
    }

    /* MouseListener de sélection de marqueur */
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
        menuQUitter = new JMenuItem();
        menu1 = new JMenu();
        goNormal = new JMenuItem();
        checkBoxMenuItem1 = new JCheckBoxMenuItem();
        menu3 = new JMenu();
        menuSeDeco = new JMenuItem();
        mapPanel1 = new MapPanel(this.app);
        marqueurInfosPanel1 = new MarqueurInfosPanel();
        panel1 = new JPanel();
        buttonSuggestions = new JButton();
        buttonUsers = new JButton();
        label1 = new JLabel();

        //======== this ========
        setTitle("OurMap project (mode admin)");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== menu2 ========
            {
                menu2.setText("Fichier");

                //---- menuQUitter ----
                menuQUitter.setText("Quitter");
                menuQUitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
                menu2.add(menuQUitter);
            }
            menuBar1.add(menu2);

            //======== menu1 ========
            {
                menu1.setText("Affichage");

                //---- goNormal ----
                goNormal.setText("Retourner en mode normal");
                menu1.add(goNormal);

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
        contentPane.add(mapPanel1, BorderLayout.CENTER);
        contentPane.add(marqueurInfosPanel1, BorderLayout.NORTH);

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]"));

            //---- buttonSuggestions ----
            buttonSuggestions.setText("Afficher toutes les suggestions");
            panel1.add(buttonSuggestions, "cell 0 0");

            //---- buttonUsers ----
            buttonUsers.setText("Afficher tous les utilisateurs");
            panel1.add(buttonUsers, "cell 1 0");

            //---- label1 ----
            label1.setText("Bienvenue dans l'interface admin, ");
            panel1.add(label1, "cell 5 1");
        }
        contentPane.add(panel1, BorderLayout.SOUTH);
        setSize(760, 495);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        enableMarqueurSelection(true);
        label1.setText(label1.getText() + app.utilisateurController.getUtilisateurById((int) app.idUtilisateurConnecte).getDisplayName());
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
        goNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HubFrame hubFrame = new HubFrame(app);
                    hubFrame.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonSuggestions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSuggestionsFrame listSuggestionsFrame = null;
                try {
                    listSuggestionsFrame = new ListSuggestionsFrame(app);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                listSuggestionsFrame.setVisible(true);
            }
        });

        buttonUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListUsersFrame listUsersFrame = null;
                try {
                    listUsersFrame = new ListUsersFrame(app);
                    listUsersFrame.setVisible(true);
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

    /**
     * @author Mathys Gagner
     * Méthode qui active ou non la sélection de marqueur sur la carte
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
     * Méthode qui désactive les boutons liés aux mémos, commentaires et informations sur les marqueurs
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
        //Lancement de la fenêtre associée au type de marqueur
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

    /**
     * @author Mathys Gagner
     * Cette méthode ouvre la fenêtre des mémos associés au marqueur
     * @param mq le marqueur dont on veut gérer les mémos (personnels)
     * @throws SQLException
     */
    public void enableMemos(Marqueur mq) throws SQLException {
//        ResultSet memos = this.app.memoController.fetchUserMemo(mq.getLieuID(), this.app.idUtilisateurConnecte);
        MemoFrame memoFrame = new MemoFrame(this.app, mq);
        memoFrame.setVisible(true);
    }

    /**
     * @author Mathys Gagner
     * Cette méthode ouvre la fenêtre des commentaires associés au marqueur
     * @param mq le marqueur dont on veut gérer les commentaires (publics)
     * @throws SQLException
     */
    public void enableCommentaires(Marqueur mq) throws SQLException {
        CommentFrame commentFrame = new CommentFrame(this.app, mq);
        JButton butSup = new JButton("Cacher le commentaire");
        commentFrame.add(butSup, "wrap");
        butSup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.commentaireController.setCertainCommVisible((long)app.utilisateurController.getUtilisateurByName(commentFrame.table1.getValueAt(commentFrame.table1.getSelectedRow(), 1).toString()).getIdutilisateur(),
                        commentFrame.mq.getLieuID(),commentFrame.table1.getValueAt(commentFrame.table1.getSelectedRow(), 0).toString(), false )) {
                    ((DefaultTableModel)commentFrame.table1.getModel()).removeRow(commentFrame.table1.getSelectedRow());
                    JOptionPane.showMessageDialog(commentFrame.getParent(), "Le commentaire a bien été caché.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(commentFrame.getParent(), "Erreur lors de la sélection du commentaire", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        commentFrame.setVisible(true);
    }

    /**
     * Cette méthode initialise la HashMap de thèmes de couleur de la fenêtre
     * @author Mathys Gagner
     */
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

    /**
     * @author Mathys Gagner
     * Méthode qui met à jour les composants graphiques avec le nouveau Look & Feel Swing sélectionné dans la liste.
     * @param lf Nom du Look & Feel Swing dans la liste à aller chercher dans la HashMap
     */
    public void updateLF(String lf) {
        try {
            UIManager.setLookAndFeel(themes.get(lf));
            FlatLaf.updateUI();

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JMenuBar menuBar1;
    public JMenu menu2;
    public JMenuItem menuQUitter;
    public JMenu menu1;
    public JMenuItem goNormal;
    private JCheckBoxMenuItem checkBoxMenuItem1;
    public JMenu menu3;
    private JMenuItem menuSeDeco;
    public MapPanel mapPanel1;
    public MarqueurInfosPanel marqueurInfosPanel1;
    public JPanel panel1;
    public JButton buttonSuggestions;
    public JButton buttonUsers;
    public JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
