/* Authors: Julia Bristow and Neel Bhagwat
 * Song class
 * Purpose: to make a song class that stores cover art, title, artist, album, and songData.
 * Plays the specified song. 
 * 
 */

import java.awt.*;
import javax.swing.ImageIcon;
import jm.util.*;

public class Song
{
	private String title;
	private String artist;
	private String album;
	private String songPath;
	private float[] songData;
	private String imagePath;
	private Image image;
	
	// constructor
	public Song (String title, String artist, String album, String songPath, String imagePath)
	{
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.songPath = songPath;
		this.imagePath = imagePath;
		this.songData = Read.audio(songPath);
		this.image = new ImageIcon(imagePath).getImage();
	}
	
	// getter and setter methods
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}
	
	public String getSongPath() {
		return songPath;
	}
	
	public void setSongData(float[] songData) {
		this.songData = songData;
	}
	
	public float[] getSongData() {
		return songData;
	}
		
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}
}