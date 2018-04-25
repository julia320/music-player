/* Author: Julia Bristow
 * CS 2113
 * 
 * Linked list of songs for use in a file-based
 * music playing program
 */

import jm.util.*;

public class Playlist
{
	ListNode head;
	ListNode tail; 
	int size; 
	
	// add a song to the end of the list
	void add (Song song)
	{
		if (size!=0)
		{
			// make new node with song information
			ListNode node = new ListNode(song);
			// make the node after tail the new node
			tail.next = node;
			tail.next.song = song;
			// have the new node's prev point to the tail 
			node.prev = tail;
			// set the tail to the new node at the end
			tail = tail.next;
			// increase size by 1
			size++;
		}
		else // if list is empty
		{
			// make new node at the head
			head = new ListNode(song);
			head.song = song;
			// set tail equal to head since there is only 1 node
			tail = head;
			// increase size by 1
			size++;
		}
	} 
	
	public void playAll () 
	{
		// create a float[] the size of all songs
		int totalSize = 0;
		// go through each node in the list
		ListNode node = head;
		for (int x=0; x<this.size; x++)
		{
			// add size of the current song data to totalSize
			totalSize += node.song.getSongData().length;
			node = node.next;
		}
		// make a new float array the size of all songs combined
		float[] allSongData = new float[totalSize];
		
		// copy the data from each song over to the cumulative float array
		int count = 0;
		// start at the first node
		ListNode iter = head;
		for (int i=0; i<this.size; i++)
		{
			for (int j=0; j<iter.song.getSongData().length; j++)
			{
				allSongData[count] = iter.song.getSongData()[j];
				count++;
			}
			// advance the iterator node
			iter = iter.next;
		}
		
		// make it into one file that can be played
		Write.audio (allSongData, "mix.wav", 2, 44100, 16);
		Play.au("mix.wav");		
	}
}