

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PlaylistFrame extends JFrame
{
	private JButton goToAdd;
	private JButton playAll;
	private JButton playNext;
	private JButton playRandom;
	private JLabel received;
	private JPanel south;
	private JPanel playButtons;
	private JTextArea songInfo;
	private ListNode currSong;
	private String songMessage;
	private AlbumImage cover;
	
	void go (int width, int height, Playlist playlist, JTextArea list)
	{
		// new receiver
		new Thread(new Receiver()).start();
		
		// set up initial frame
		this.setTitle("Music Player");
		this.setResizable(true);
		this.setSize(width, height);
		
		// start current song at the front of playlist
		currSong = playlist.head;
		// initialize songInfo
		songInfo = new JTextArea();
		// show the first song information
		setSongInfo(playlist);
		// show the first song's album cover
		cover = new AlbumImage(currSong.song);
		// play the first song
		currSong.song.play();
		
		// get the content pane
		Container cPane = this.getContentPane();
		cPane.setLayout(new BorderLayout());
		
		// add the album cover to the content pane
		cPane.add(BorderLayout.CENTER,cover);
				
		// show the playlist on the right side of the frame
		cPane.add(BorderLayout.EAST, list);
		
		// filler message that will eventually be received over network
		received = new JLabel("Julia is listening to a song");
		
		
		// button to play ALL the songs in the playlist
		playAll = new JButton("Play All");
		// local class definition for the Action Listener for play
		class PlayAllAL implements ActionListener {
			public void actionPerformed (ActionEvent a) {
				// stop playing the current song
				currSong.song.stop();
				while (currSong != null) {
					// change current song info
					setSongInfo(playlist);
					// remove the current image
					cPane.remove(cover);
					// change current image
					cover = new AlbumImage(currSong.song);
					repaint();
					// add new image to panel
					cPane.add(BorderLayout.CENTER,cover);
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
				// stop playing the current song
				currSong.song.stop();
				// move currSong to the next song
				currSong = currSong.next;
				// change current song info
				setSongInfo(playlist);
				// send what person is listening to
				send(songMessage);
				// remove the current image
				cPane.remove(cover);
				// change current image
				cover = new AlbumImage(currSong.song);
				repaint();
				// add picture to the panel
				cPane.add(BorderLayout.CENTER,cover);
				// play the song
				playSong(playlist);
			}
		}
		// create the action listener
		PlayNextAL playNextAL = new PlayNextAL();
		// add Action Listener to play button
		playNext.addActionListener(playNextAL);
		
		
		// button to play a random song in the playlist
		playRandom = new JButton("Play Random Song");
		// local class definition for the Action Listener for playRandom
		class PlayRandomAL implements ActionListener {
			public void actionPerformed (ActionEvent a) {
				// stop playing the current song
				currSong.song.stop();
				// pick a random number from 1 to # of songs
				int random = (int)((Math.random()*playlist.size)+1);
				// move currSong to the first node
				currSong = playlist.head;
				// move currSong to the node chosen by random
				for (int i=0; i<random; i++) {
					currSong = currSong.next;
				}
				// change current song info
				setSongInfo(playlist);
				// remove the current image
				cPane.remove(cover);
				// change current image
				cover = new AlbumImage(currSong.song);
				repaint();
				// add picture to the panel
				cPane.add(BorderLayout.CENTER,cover);
				// play the song
				playSong(playlist);
			}
		}
		// create the action listener
		PlayRandomAL playRandomAL = new PlayRandomAL();
		// add Action Listener to play button
		playRandom.addActionListener(playRandomAL);
		
		
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
		
		
		// new panel to hold play buttons
		playButtons = new JPanel();
		// set Flow Layout
		playButtons.setLayout(new FlowLayout());
		// add buttons to panel
		playButtons.add(playAll);
		playButtons.add(playNext);
		playButtons.add(playRandom);
		
		// new panel to put all buttons and message at the bottom
		south = new JPanel();
		// give the south panel a Box Layout
		south.setLayout(new BoxLayout(south,BoxLayout.Y_AXIS));
		// add message to south panel
		south.add(received);
		// add play buttons to south panel
		south.add(playButtons);
		// add "go back" button to south panel
		south.add(goToAdd);
		// add panel with the message and button to the South area
		cPane.add(BorderLayout.SOUTH, south);
		
		// add the song information to the content pane
		cPane.add(BorderLayout.NORTH,songInfo);
		
		// exit operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		// make visible
		this.setVisible(true);
	}
	
	public void playSong (Playlist playlist)
	{
		// if at the end
		if (currSong==null) {
			// go back to the beginning
			currSong = playlist.head;
			// play that
			currSong.song.play();
		}
		// else if not at the end
		else {
			// play the current song
		}
	}
	
	public void setSongInfo (Playlist playlist) 
	{
		// if the end has been reached
		if (currSong==null) {
			// go back to the beginning
			currSong = playlist.head;
			// set the song info 
			songInfo.setText("Current Song: " + currSong.song.getTitle() +"\nArtist: " + currSong.song.getArtist() 
											+ "\nAlbum: " + currSong.song.getAlbum());
		}
		// else if not at the end
		else {
			// set the song info 
			songInfo.setText("Current Song: " + currSong.song.getTitle() +"\nArtist: " + currSong.song.getArtist() 
											+ "\nAlbum: " + currSong.song.getAlbum());
		}

		// message to be used in server (sent back and forth)
		songMessage = currSong.song.getTitle() + " by " + currSong.song.getArtist();
	}
	
	public static void send(String message)
	{
		try {
		    // create a new socket to send data packets
			DatagramSocket ds = new DatagramSocket();
			
			// since DatagramPacket can only pack up byte array, we need to convert string to bytes
			byte[] data = message.getBytes();
			// create a new data packet, we must specify the IP address and port number here
			DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 10001);
			 
			// send the packet
			ds.send(dp);
		
			// close the resource
			ds.close();
	        } catch (Exception e) {
        		System.out.println("caught an exception in sending function");
       		}
	}
	
	public ListNode getCurrentSong()
	{
		return currSong;
	}
	
	public String getSongMessage ()
	{
		return songMessage;
	}
}
