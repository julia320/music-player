package src;

/* Author: Julia Bristow
 * CS 2113
 * 
 * Node class for use in a linked list of songs
 */

public class ListNode 
{
	Song song;
	ListNode next;
	ListNode prev;
	
	// constructor
	public ListNode (Song song)
	{
		this.song = song;
		next = null;
		prev = null; 
	}
}
