package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entity.Artist;
import entity.Song;
import entity.User;
import main.Main;

public class UserProfileFrame extends JFrame {

    private Main mainController;
    private User loggedUser;
    private JTabbedPane tabbedPane;
    private JPanel Mainpanel, recommendedSongPane, allSongPane, likedSongPane;
    private JScrollPane rSongScrollPane, lSongScrollPane, aSongScrollPane;
    private JList rSongList, rArtistList, lSongList, lArtistList, aSongList;
    private JButton logOut, rLikeSongButton, rRefreshSongsButton;
    private JButton lRefreshSongsButton;
    private JButton aLikeSongButton;

    DefaultListModel rSongListModel, rArtistListModel, lSongListModel, lArtistListModel, aSongListModel,
            aArtistListModel;
    private JLabel name;

    public UserProfileFrame(User user, Main mainController) {
        this.mainController = mainController;
        this.loggedUser = user;
        initSwingComponents();
    }

    public UserProfileFrame() {
        initSwingComponents();
    }

    @SuppressWarnings({"rawtypes", "unchecked", "unchecked", "serial", "unchecked"})
    private void displayRecommendedSongs(JPanel panel) {
        rSongListModel = new DefaultListModel();
        rSongList = new JList(rSongListModel);
        rSongList.setCellRenderer(new SongListCellRenderer(rSongList));
        rSongScrollPane = new JScrollPane(rSongList);
        rRefreshSongsButton = new JButton("Refresh");
        rLikeSongButton = new JButton("Add to favourite list");
        rRefreshSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(UserProfileFrame.this, 
                            "Refreshed.", "Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    refreshRecommendedSongList();
                } catch (SQLException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        rLikeSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rSongList.getSelectedValue() != null) {
                        if (mainController.isLiked(loggedUser, (Song) rSongList.getSelectedValue())) {
                            JOptionPane.showMessageDialog(UserProfileFrame.this, 
                                    "Already added to your favorites.", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(UserProfileFrame.this, 
                                    "Added to your favorites.", "Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        likeSong(rSongList.getSelectedValue());
                    } else {
                        JOptionPane.showMessageDialog(UserProfileFrame.this, 
                                "Select your favourite song and press add to favourite list.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(rSongScrollPane, BorderLayout.CENTER);
        panel.add(rLikeSongButton, BorderLayout.NORTH);
        panel.add(rRefreshSongsButton, BorderLayout.SOUTH);
        try {
            refreshRecommendedSongList();
        } catch (SQLException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private void displayAllSongs(JPanel panel) {
        aSongListModel = new DefaultListModel();
        aSongList = new JList(aSongListModel);
        aSongList.setCellRenderer(new SongListCellRenderer(aSongList));
        aSongScrollPane = new JScrollPane(aSongList);
        List<Song> songs;
        try {
            songs = mainController.getAllSongList();
            aSongList.setListData(songs.toArray());
        } catch (IOException | SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        aLikeSongButton = new JButton("Add to favourite list");
        aLikeSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (aSongList.getSelectedValue() != null) {
                        if (mainController.isLiked(loggedUser, (Song) aSongList.getSelectedValue())) {
                            JOptionPane.showMessageDialog(UserProfileFrame.this, 
                                    "Already added to your favorites.", "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(UserProfileFrame.this, 
                                    "Added to your favorites.", "Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        likeSong(aSongList.getSelectedValue());
                    } else {
                        JOptionPane.showMessageDialog(UserProfileFrame.this, 
                                "Select your favourite song and press add to favourite list.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(aSongScrollPane, BorderLayout.CENTER);
        panel.add(aLikeSongButton, BorderLayout.NORTH);
    }

    private void displayLikedSongs(JPanel panel) {
        lSongListModel = new DefaultListModel();
        lSongList = new JList(lSongListModel);
        lSongList.setCellRenderer(new SongListCellRenderer(lSongList));
        lSongScrollPane = new JScrollPane(lSongList);
        lRefreshSongsButton = new JButton("Refresh");
        lRefreshSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(UserProfileFrame.this, 
                            "Refreshed.", "Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    refreshLikedSongList();
                } catch (SQLException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(lSongScrollPane, BorderLayout.CENTER);
        panel.add(lRefreshSongsButton, BorderLayout.AFTER_LAST_LINE);
        try {
            refreshLikedSongList();
        } catch (SQLException | IOException e1) {
            e1.printStackTrace();
        }
    }

    private void initSwingComponents() {
        setTitle("Musify! Welcome: " + loggedUser.getUserName().toUpperCase());
        setBounds(0, 0, 900, 700);
        setLayout(null);
        setResizable(false);

        Mainpanel = new JPanel();
        Mainpanel.setBounds(0, 0, getWidth(), getHeight());
        Mainpanel.setLayout(null);
        Mainpanel.setBackground(Color.decode("#ffffff"));
        Color tabColor = Color.decode("#ffffff");
        Color tabBackgroundColor = Color.decode("#ffffff");
        logOut = new JButton("Logout");
        logOut.setBounds(793, 0, 100, 30);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.openLogin();
                setVisible(false);
            }
        });

        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0, 10, getWidth()-2, getHeight()-40);
        tabbedPane.setBackground(tabColor);

        recommendedSongPane = new JPanel();
        recommendedSongPane.setLayout(new BorderLayout());
        recommendedSongPane.setBounds(0, 0, getWidth(), getHeight());
        recommendedSongPane.setBackground(tabBackgroundColor);
        displayRecommendedSongs(recommendedSongPane);

        allSongPane = new JPanel();
        allSongPane.setLayout(new BorderLayout());
        allSongPane.setBounds(0, 0, getWidth(), getHeight());
        allSongPane.setBackground(tabBackgroundColor);
        displayAllSongs(allSongPane);

        likedSongPane = new JPanel();
        likedSongPane.setLayout(new BorderLayout());
        likedSongPane.setBounds(0, 0, getWidth(), getHeight());
        likedSongPane.setBackground(tabBackgroundColor);
        displayLikedSongs(likedSongPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addTab("RECOMMENDED SONGS", null, recommendedSongPane, "Shows recommended songs based on user preferences");
        tabbedPane.addTab("SONG REPOSITORY", null, allSongPane, "Shows all songs in knowledge base");
        tabbedPane.addTab("FAVORITE SONGS", null, likedSongPane, "Shows all your songs in your favourite list");
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                try {
                    refresh();
                } catch (SQLException | IOException e) {
                    System.out.println("Error during Refresh");
                }
            }
        };
        tabbedPane.addChangeListener(changeListener);
        Mainpanel.add(logOut);
        Mainpanel.add(tabbedPane);
        add(Mainpanel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void refreshRecommendedSongList() throws FileNotFoundException, SQLException, IOException {
        List<Song> songs = mainController.getRecommendedSongList(loggedUser);
        rSongList.setListData(songs.toArray());
    }

    void refreshLikedSongList() throws FileNotFoundException, SQLException, IOException {
        List<Song> songs = mainController.getLikedSongList(loggedUser);
        lSongList.setListData(songs.toArray());
    }

    void refreshLikedArtistList() throws SQLException, FileNotFoundException, IOException {
        List<Artist> artists = mainController.getLikedArtistList(loggedUser);
        lArtistList.setListData(artists.toArray());
    }

    public void likeSong(Object object) throws SQLException {
        mainController.updateLikeSong((Song) object, loggedUser);
    }

    public void refresh() throws FileNotFoundException, SQLException, IOException {
        refreshLikedSongList();
        refreshRecommendedSongList();
    }

    public static void main(String args[]) {
        new UserProfileFrame();
    }
}
