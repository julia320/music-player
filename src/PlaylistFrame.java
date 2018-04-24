import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistFrame extends JFrame
{
	JButton goToAdd;
	JButton play;
	JTextArea received;
	JPanel south;
	ListNode currNode;
	
	void go (int width, int height, Playlist playlist, JTextArea list)
	{
		// set up initial frame
		this.setTitle("Music Player");
		this.setResizable(true);
		this.setSize(width, height);
		
		// get the content pane
		Container cPane = this.getContentPane();
		cPane.setLayout(new BorderLayout());
				
		// show the playlist on the right side of the frame
		cPane.add(BorderLayout.EAST, list);
		
		// filler message that will eventually be received over network
		received = new JTextArea("Julia is listening to a song");
		
		
		// button to play the songs in the playlist
		play = new JButton("Play Playlist");
		// local class definition for the Action Listener for play
		class PlayAL implements ActionListener {
			public void actionPerformed (ActionEvent a) {
				currNode = playlist.head;
				while (currNode != null) {
					System.out.println(currNode.song.title);
					currNode = currNode.next;
				}
			}
		}
		// create the action listener
		PlayAL playAL = new PlayAL();
		// add Action Listener to play button
		play.addActionListener(playAL);
		
		
		// button to go back to the Add Songs interface
		goToAdd = new JButton("Make A New Playlist");
		// local class definition for an Action Listener for goToAdd
		class AddMoreAL implements ActionListener {
			public void actionPerformed(ActionEvent a) {
				AddSongFrame addFrame = new AddSongFrame();
				addFrame.go(500,500);
			}
		}
		// create action listener
		AddMoreAL goToAddAL = new AddMoreAL();
		// add action listener to the goToAdd button
		goToAdd.addActionListener(goToAddAL);
		
		
		// new panel for message and buttons
		south = new JPanel();
		// give the south panel a Box Layout
		south.setLayout(new BoxLayout(south,BoxLayout.Y_AXIS));
		// add message to south panel
		south.add(received);
		// add play button to south panel
		south.add(play);
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
