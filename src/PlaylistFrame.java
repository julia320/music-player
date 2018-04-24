import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistFrame extends JFrame
{
	JButton goToAdd;
	
	void go (int width, int height, JTextArea list)
	{
		// set up initial frame
		PlaylistFrame frame = new PlaylistFrame();
		this.setTitle("Music Player");
		this.setResizable(true);
		this.setSize(width, height);
		
		// get the content pane
		Container cPane = this.getContentPane();
		cPane.setLayout(new BorderLayout());
				
		// show the playlist on the right side of the frame
		cPane.add(BorderLayout.EAST, list);
		
		// filler message that will eventually be received over network
		JTextArea received = new JTextArea("Julia is listening to a song");
		
		// button to go back to the Add Songs interface
		goToAdd = new JButton("Add More Songs");
		// local class definition for an Action Listener for goToAdd
		class AddMoreAL implements ActionListener {
			public void actionPerformed(ActionEvent a) {
				AddSongFrame addFrame = new AddSongFrame();
				addFrame.go(500,500);
			}
		}
		AddMoreAL goToAddAL = new AddMoreAL();
		// add the action listener to the goToAdd button
		goToAdd.addActionListener(goToAddAL);
		
		// new panel for message and button
		JPanel south = new JPanel();
		// give the south panel a Box Layout
		south.setLayout(new BoxLayout(south,BoxLayout.Y_AXIS));
		// add message to south panel
		south.add(received);
		// add "go back" button to south panel
		south.add(goToAdd);
		// add panel with the message and button to the South area
		cPane.add(BorderLayout.SOUTH, south);
		
		// put song info at the top
		JTextArea songInfo = new JTextArea("Current song:\nArtist:\nAlbum:");
		cPane.add(BorderLayout.NORTH,songInfo);
		
		// put image in center
		// image code not ready yet, text instead
		JTextArea coverArt = new JTextArea("COVER ART HERE");
		cPane.add(coverArt);

		
		// exit operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		// make visible
		this.setVisible(true);
	}
}
