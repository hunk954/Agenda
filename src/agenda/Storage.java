package agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Storage {
	private ArrayList<User> userList = new ArrayList<User>();
	private ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
	private String userPath = "./data/user.csv";
	private String meetingPath = "./data/meeting.csv";
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
			File csv = new File(userPath);
			judeFileExists(csv);
			csv.setReadable(true);
			BufferedReader reader = new BufferedReader(new FileReader(csv));
			reader.readLine();//读表头
			String line = null;
			while ((line = reader.readLine()) != null) {
				User newUser = new User();
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
			File csv = new File(meetingPath);
			judeFileExists(csv);
			csv.setReadable(true);
			BufferedReader reader = new BufferedReader(new FileReader(csv));
			reader.readLine();//读表头
			String line = null;
			while ((line = reader.readLine()) != null) {
				Meeting newMeeting = new Meeting();
				String item[] = line.split(",");
				String[] p = item[1].split("&");
				Vector<String> pVector = new Vector<String>();
				for (int i = 0; i < p.length; i++)
					pVector.add(p[i]);
				
				newMeeting.setSponsor(item[0]);
				newMeeting.setParticipators(pVector);
				newMeeting.setStartDate(Date.stringToDate(item[2]));
				newMeeting.setEndDate(Date.stringToDate(item[3]));
				newMeeting.setTitle(item[4]);
				meetingList.add(newMeeting);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private void judeFileExists(File file) {
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private boolean writeToFile() {
		try {
			File csv = new File(userPath);
			judeFileExists(csv);
			csv.setWritable(true);
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			Iterator<User> iter = userList.iterator();
			bw.write("\"Name\"" + "," + "\"Password\"" + "," + "\"Email\"" + "," + "\"Phone\"");
			bw.newLine();
			while (iter.hasNext()) {
				User user = iter.next();
				bw.write("\"" + user.getName() + "\"" + "," + "\"" + user.getPassword() + "\"" + "," + 
						"\"" + user.getEmail() + "\"" + "," + "\"" + user.getPhone() + "\"");
				bw.newLine();
			}
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			File csv = new File(meetingPath);
			judeFileExists(csv);
			csv.setWritable(true);
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			Iterator<Meeting> iter = meetingList.iterator();
			bw.write("\"Sponsor\"" + "," + "\"Participators\"" + "," + "\"Start date\"" + "," + "\"End date\"" + "," + "\"Title\"");
			bw.newLine();
			while (iter.hasNext()) {
				Meeting meeting = iter.next();
				String participators = null;
				for (int i = 0; i < meeting.getParticipators().size(); i++) {
					participators += meeting.getParticipators().get(i);
					if (i != meeting.getParticipators().size()-1)
						participators += "&";
				}
				bw.write("\"" + meeting.getSponsor() + "\"" + "," + "\"" + participators + "\"" + "," + 
						"\"" + meeting.getStartDate() + "\"" + "," + "\"" + meeting.getEndDate() + "\"" + "," + "\"" + meeting.getTitle() + "\"");
				bw.newLine();
			}
			bw.close();	
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) {
		Storage test = new Storage();
		User test1 = new User("sb", "123", "123", "123");
		test.createUser(test1);
		test.finalize();
	}
}
