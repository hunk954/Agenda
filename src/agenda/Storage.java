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
	
	
	private static class Holder{
		private static Storage instance = new Storage();
	}
	private Storage() {
		readFromFile();
		dirty = false;
	}
	public static  Storage getInstance() {
		return Holder.instance;	
	}
	
	protected void finalize() throws Throwable { 
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
				newUser.setName(item[0].substring(1, item[0].length()-1));
				newUser.setPassword(item[1].substring(1, item[1].length()-1));
				newUser.setEmail(item[2].substring(1, item[2].length()-1));
				newUser.setPhone(item[3].substring(1, item[3].length()-1));
				userList.add(newUser);
			}
			reader.close();
			
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
				String[] p = item[1].substring(1, item[1].length()-1).split("&");
				Vector<String> pVector = new Vector<String>();
				for (int i = 0; i < p.length; i++)
					pVector.add(p[i]);
				
				newMeeting.setSponsor(item[0].substring(1, item[0].length()-1));
				newMeeting.setParticipators(pVector);
				newMeeting.setStartDate(Date.stringToDate(item[2].substring(1, item[2].length()-1)));
				newMeeting.setEndDate(Date.stringToDate(item[3].substring(1, item[3].length()-1)));
				newMeeting.setTitle(item[4].substring(1, item[4].length()-1));
				meetingList.add(newMeeting);
			}
			reader.close();
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
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv));
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
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv));
			Iterator<Meeting> iter = meetingList.iterator();
			bw.write("\"Sponsor\"" + "," + "\"Participators\"" + "," + "\"Start date\"" + "," + "\"End date\"" + "," + "\"Title\"");
			bw.newLine();
			while (iter.hasNext()) {
				Meeting meeting = iter.next();
				String participators = new String();
				for (int i = 0; i < meeting.getParticipators().size(); i++) {
					participators += meeting.getParticipators().get(i);
					if (i != meeting.getParticipators().size()-1)
						participators += "&";
				}
				bw.write("\"" + meeting.getSponsor() + "\"" + "," + "\"" + participators + "\"" + "," + 
						"\"" + Date.dateToString(meeting.getStartDate()) + "\"" + "," + "\"" + Date.dateToString(meeting.getEndDate()) + "\"" + "," + "\"" + meeting.getTitle() + "\"");
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
		ArrayList<User> temp = new ArrayList<User>();
		Iterator<User> iter = userList.iterator();
		while (iter.hasNext()) {
			User element = iter.next();
//			System.out.println(element.getName());
			if (filter.test(element))
				temp.add(element);
		}
		
		return temp;
	}
	
	public int updateUser(Predicate<User> filter, Consumer<User> switcher) {
		int count = 0;
		Iterator<User> iter = userList.iterator();
		while (iter.hasNext()) {
			User element = iter.next();
			if (filter.test(element)) {
				switcher.accept(element);
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
	
	public boolean containUser(String userName) {
		boolean isContain = !queryUser((User user) ->{		//if user do not register, false
			if (user.getName() == userName)
				return true;
			return false;
		}).isEmpty();
		
		if (isContain) return true;
		return false;
	}
	
	public void createMeeting(Meeting meeting) {
		meetingList.add(meeting);
		dirty = true;
	}
	
	@SuppressWarnings("null")
	public ArrayList<Meeting> queryMeeting(Predicate<Meeting> filter) {
		ArrayList<Meeting> temp = new ArrayList<Meeting>();
		Iterator<Meeting> iter = meetingList.iterator();
		while (iter.hasNext()) {
			Meeting element = iter.next();
			if (filter.test(element))
				temp.add(element);
		}
		
		return temp;
	}
	
	public int updateMeeting(Predicate<Meeting> filter, Consumer<Meeting> switcher) {
		int count = 0;
		Iterator<Meeting> iter = meetingList.iterator();
		while (iter.hasNext()) {
			Meeting element = iter.next();
			if (filter.test(element)) {
				switcher.accept(element);
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
	
//	public static void main(String[] args) throws Throwable {
//		Storage test = Storage.getInstance();
//		Vector<String> p = new Vector<String>();
//		p.add("sb");
//		p.add("sb2");
//		User test1 = new User("sb", "123", "123", "123");
//		Meeting mtest = new Meeting("sb",p,new Date(1999,1,2,10,10),new Date(1999,1,2,10,10),"12");
//		test.createUser(test1);
//		test.createMeeting(mtest);
//		test.finalize();
//	}
}
