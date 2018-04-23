import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame 
{
	JTextField songTitle;
	JTextField artistName;
	JTextField albumName;
	JTextField songfilePath;
	JTextField coverartPath;
	JButton addSong;
	JButton goTo;
	Playlist playlist;
	
	void go (int width, int height)
	{
		// create playlist
		playlist = new Playlist();
		
		// set up initial frame
		this.setTitle("Add Songs");
		this.setResizable(true);
		this.setSize(width, height);
		
		// get the content pane
		Container cPane = this.getContentPane();
		cPane.setLayout(new BorderLayout());
		
		// put instructions in each JTexField
		songTitle = new JTextField("Enter the song title here");
		artistName = new JTextField("Enter the artist name here");
		albumName = new JTextField("Enter the album name here");
		songfilePath = new JTextField("Enter the path for the song file here");
		coverartPath = new JTextField("Enter the path for the album cover art here");
		
		// add all the text fields to the center of the frame
		// create a new panel
		JPanel panel = new JPanel();
		// add each JTextField to the panel
		panel.add(songTitle);
		panel.add(artistName);
		panel.add(albumName);
		panel.add(songfilePath);
		panel.add(coverartPath);
		// add this panel to content pane
		cPane.add(panel);
		
		// make the addSong button add the song entered by user to the playlist
		addSong = new JButton ("Add Song");
		// Local class definition for button Action Listener
		// adds song with the info provided to the playlist
		class AddSongAL implements ActionListener {
			public void actionPerformed (ActionEvent a) {
				// get the text entered in each field
				String title = songTitle.getText();
				String artist = artistName.getText();
				String album = albumName.getText();
				String songPath = songfilePath.getText();
				String artPath = coverartPath.getText();
				// make a new Song object
				Song newSong = new Song(title,artist,album,songPath,artPath);
				// add song to playlist
				playlist.add(newSong);
			}
		} // end of local class AddSongAL
		// instantiate the AddSong Action Listener
		AddSongAL songAL = new AddSongAL();
		// add Action Listener to addSong button
		addSong.addActionListener(songAL);
		
		// add buttons to panel
		cPane.add(BorderLayout.SOUTH, addSong);
		
		// exit operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// make visible
		this.setVisible(true);
	}
	
	public static void main (String[] args) 
	{
		GUI gui = new GUI();
		gui.go(500,500);
	}
}
