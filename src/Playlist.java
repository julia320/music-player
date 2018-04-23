/* Author: Julia Bristow
 * CS 2113
 * 
 * Linked list of songs for use in a file-based
 * music playing program
 */

public class Playlist
{
	ListNode first;
	ListNode last; 
	int size; 
	
	void add (Song song)
	{
		if (size!=0)
		{
			ListNode node = new ListNode(song);
			last.next = node;
			last.next.song = song;
			node.prev = last;
			last = last.next;
			size++;
		}
		else
		{
			first = new ListNode(song);
			first.song = song;
			last = first;
			size++;
		}
	}
}