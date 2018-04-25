import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistFrame extends JFrame
{
	JButton goToAdd;
	JButton playAll;
	JButton playNext;
	JButton playRandom;
	JTextArea received;
	JPanel south;
	ListNode currSong;
	
	void go (int width, int height, Playlist playlist, JTextArea list)
	{
		// set up initial frame
		this.setTitle("Music Player");
		this.setResizable(true);
		this.setSize(width, height);
		
		// start current song at the front of playlist
		currSong = playlist.head;
		
		// get the content pane
		Container cPane = this.getContentPane();
		cPane.setLayout(new BorderLayout());
				
		// show the playlist on the right side of the frame
		cPane.add(BorderLayout.EAST, list);
		
		// filler message that will eventually be received over network
		received = new JTextArea("Julia is listening to a song");
		
		
		// button to play ALL the songs in the playlist
		playAll = new JButton("Play All");
		// local class definition for the Action Listener for play
		class PlayAllAL implements ActionListener {
			public void actionPerformed (ActionEvent a) {
				while (currSong != null) {
					// play the song in currNode
					currSong.song.play();
					// advance currSong
					currSong = currSong.next;
				}
			}
		}
		// create the action listener
		PlayAllAL playAllAL = new PlayAllAL();
		// add Action Listener to play button
		playAll.addActionListener(playAllAL);
		
		
		// button to play the next song in the playlist
		playNext = new JButton("Play Next Song");
		// local class definition for the Action Listener for play
		class PlayNextAL implements ActionListener {
			public void actionPerformed (ActionEvent a) {
				// move currSong to the next song
				currSong = currSong.next;
				// play the song
				currSong.song.play();
			}
		}
		// create the action listener
		PlayNextAL playNextAL = new PlayNextAL();
		// add Action Listener to play button
		playNext.addActionListener(playNextAL);
		
		
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
		south.add(playAll);
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
