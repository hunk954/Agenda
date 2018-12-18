package agenda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Storage {
	private ArrayList<User> userList;
	private ArrayList<Meeting> meetingList;
	private String userPath;
	private String meetingPath;
	boolean dirty;
	
	public Storage() {
		readFromFile();
		dirty = false;
	}
	
	protected void finalize() {
		sync();
	}
	
	public boolean sync() {
		if (dirty == false) return false;
		if (writeToFile() == false) return false;

		return true;
	}	
	
	@SuppressWarnings({ "resource", "null" })
	private boolean readFromFile() {
		//read from user
		try {
			BufferedReader reader = new BufferedReader(new FileReader(userPath));
			String line = null;
			while ((line = reader.readLine()) != null) {
				User newUser = null;
				String item[] = line.split(",");
				newUser.setName(item[0]);
				newUser.setPassword(item[1]);
				newUser.setEmail(item[2]);
				newUser.setPhone(item[3]);
				userList.add(newUser);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//read from meeting
		try {
			BufferedReader reader = new BufferedReader(new FileReader(meetingPath));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Meeting newMeeting = null;
				String item[] = line.split(",");
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean writeToFile() {
		
		return true;
	}

	public void createUser(User user) {
		userList.add(user);
		dirty = true;
	}
	
	@SuppressWarnings("null")
	public ArrayList<User> queryUser(Predicate<User> filter) {
		ArrayList<User> temp = null;
		Iterator<User> iter = userList.iterator();
		while (iter.hasNext()) {
			if (filter.test(iter.next()))
				temp.add(iter.next());
		}
		
		return temp;
	}
	
	public int updateUser(Predicate<User> filter, Consumer<User> switcher) {
		int count = 0;
		Iterator<User> iter = userList.iterator();
		while (iter.hasNext()) {
			if (filter.test(iter.next())) {
				switcher.accept(iter.next());
				count++;
			}
		}
		if (count != 0) dirty = true;
		return count;
	}
	
	public int deleteUser(Predicate<User> filter) {
		int count = 0;
		Iterator<User> iter = userList.iterator();
		while (iter.hasNext()) {
			if (filter.test(iter.next())) {
				iter.remove();
				count++;
			}
		}
		if (count != 0) dirty = true;
		return count;
	}
	
	public void createMeeting(Meeting meeting) {
		meetingList.add(meeting);
		dirty = true;
	}
	
	@SuppressWarnings("null")
	public ArrayList<Meeting> queryMeeting(Predicate<Meeting> filter) {
		ArrayList<Meeting> temp = null;
		Iterator<Meeting> iter = meetingList.iterator();
		while (iter.hasNext()) {
			if (filter.test(iter.next()))
				temp.add(iter.next());
		}
		
		return temp;
	}
	
	public int updateMeeting(Predicate<Meeting> filter, Consumer<Meeting> switcher) {
		int count = 0;
		Iterator<Meeting> iter = meetingList.iterator();
		while (iter.hasNext()) {
			if (filter.test(iter.next())) {
				switcher.accept(iter.next());
				count++;
			}
		}
		if (count != 0) dirty = true;
		return count;
	}
	
	public int deleteMeeting(Predicate<Meeting> filter) {
		int count = 0;
		Iterator<Meeting> iter = meetingList.iterator();
		while (iter.hasNext()) {
			if (filter.test(iter.next())) {
				iter.remove();
				count++;
			}
		}
		if (count != 0) dirty = true;
		return count;
	}
}
