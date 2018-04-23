/* Author: Julia Bristow
 * CS 2113
 * 
 * Song class for use in a file-based
 * music playing program
 */

public class Song
{
	String title;
	String artist;
	String album;
	String songPath;
	String imagePath;
	
	// constructor
	public Song (String title, String artist, String album, String songPath, String imagePath)
	{
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.songPath = songPath;
		this.imagePath = imagePath;
	}
}
