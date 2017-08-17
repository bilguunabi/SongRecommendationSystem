package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import entity.Song;

public class SongListCellRenderer implements ListCellRenderer {
	private JPanel panel;
	private JLabel label2;

	public SongListCellRenderer(JList list) {
		panel = new JPanel();
		label2 = new JLabel();
		label2.setBackground(Color.decode("#efefef"));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label2);
                panel.setBounds(0, 0, 600, 0);
		// list.add(panel);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean CellHasFocus) {

		Song song = (Song) value;
		Color hover = new Color(200, 200, 50);
		Color normal = Color.decode("#ffffff");
		label2.setFont(new Font("Courier New", Font.PLAIN, 14));
		label2.setText(songToString(song)+ "                                                                                                                                                              ");
		label2.setOpaque(true);
		panel.setBackground(CellHasFocus ? hover : normal);
		panel.setToolTipText("<html><pre> " + " Danceability: " + song.getDanceability() + "<br> " + " Lyrics: "
				+ song.getLyrics() + "<br> " + " Tempo: " + song.getTempo() + "<br> " + " Likes: " + song.getLikes()
				+ "<br> " + " Score: " + song.getScore());
		return panel;
	}

	private String songToString(Song song) {
		return "<html><pre> " + " Title: " + song.getTitle() + "<br> " + " Artist: " + song.getArtistName() + "<br> "
				+ " Album: " + song.getAlbum();
	}
}
