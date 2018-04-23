public class Song
{
	String title;
	String artist;
	String album;
	String songPath;
	String imagePath;
	// pointers for list
	Song prev;
	Song next;
	
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
