package agenda;

import java.util.ArrayList;
import java.util.Vector;

public class Service {
	private Storage storage;
	
	public Service( ) {
		startAgenda();
	}
	public void userLogIn(String userName, String password) throws Exception {
//		boolean isEmpty = storage.queryUser((User user) ->{
//			if (user.getName().equals(userName))
//				return true;
//			return false;
//		}).isEmpty();
//		if (isEmpty) {
//			throw new Exception("用户不存在");
//		}
		if (userName.equals(""))
			throw new Exception("用户名不能为空");
		if (password.equals(""))
			throw new Exception("密码不能为空");
		
		if (!storage.containUser(userName))
			throw new Exception("用户不存在");
		
		boolean isWrong = storage.queryUser((User user) ->{
			if (user.getName().equals(userName) && user.getPassword().equals(password))
				return true;
			return false;
		}).isEmpty();
		if (isWrong) {
			throw new Exception("密码错误");
		}
		storage.sync();
	}
	
	public void userRegister(String userName, String password, String email, String phone) throws Exception {
//		boolean isUnique = storage.queryUser((User user) ->{
//			if (user.getName().equals(userName))
//				return true;
//			return false;
//		}).isEmpty();
//		if (!isUnique) throw new Exception("用户已存在");
		String userNameRegex = "^[a-zA-Z][a-zA-Z0-9_]{2,9}$";
		String passwordRegex = "^[a-zA-Z0-9_-]{6,12}$";
		String phoneRegex = "^[1-9][0-9]{10}$";
		String emailRegex = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
		if (!userName.matches(userNameRegex)) {
			if (userName.equals(""))
				throw new Exception("用户名不能为空");
			throw new Exception("用户名：3~10位，必须以英文字母开头");
		}	
		if (!password.matches(passwordRegex)) {
			if (password.equals(""))
				throw new Exception("密码不能为空");
			throw new Exception("密码：6~12位数字、大小写字母、中划线、下划线");
		}
		if (!phone.matches(phoneRegex) && !phone.equals("")) {
			throw new Exception("电话：11位数字，不能以0开头");
		}
		if (!email.equals(emailRegex) && !email.equals("")) {
			throw new Exception("邮箱：请输入合法邮箱");
		}
		
		if(userName.isEmpty() || password.isEmpty()) 
			throw new Exception("用户名和密码不能为空");
		if (storage.containUser(userName))
			throw new Exception("用户已存在");
		
		boolean isUnique = storage.queryUser((User user) ->{
			if (user.getEmail().equals(email) && !user.getEmail().equals("")) {
//				System.out.println(user.getEmail().equals(""));
				return true;
			}	
			return false;
		}).isEmpty();
		if (!isUnique) throw new Exception("邮箱已存在");
		
		isUnique = storage.queryUser((User user) ->{
			if (user.getPhone().equals(phone) && !phone.equals(""))
				return true;
			return false;
		}).isEmpty();
		if (!isUnique) throw new Exception("电话号码已存在");

		User user = new User(userName, password, email, phone);
		storage.createUser(user);

		storage.sync();
	}
	
	public void deleteUser(String userName, String password) throws Exception {
		
		int count = storage.deleteUser((User user) ->{
			if (user.getName().equals(userName) && user.getPassword().equals(password))
				return true;
			return false;
		});
		if (count == 0) {
			throw new Exception("no match user!");
	    }
		
		storage.deleteMeeting((Meeting meeting) ->{
			if (meeting.getSponsor().equals(userName))
				return true;
			return false;
		});
		storage.updateMeeting((Meeting meeting) ->{
			if (meeting.isParticipator(userName))
				return true;
			return false;
		}, (Meeting meeting) ->{
			meeting.removeParticipator(userName);
		});
		storage.deleteMeeting((Meeting meeting) ->{
			if (meeting.getParticipators().isEmpty())
				return true;
			return false;
		});
		storage.sync();
	}
	
	public ArrayList<User> listAllUsers() {
		return storage.queryUser((User user) ->{
			return true;
		});
	}
	
	public void createMeeting(String userName, String title, Date startDate, Date endDate, 
			Vector<String> participator) throws Exception {
		for (int i = 0; i < participator.size(); i++) {
			if (participator.elementAt(i).equals(userName)) {
				throw new Exception("can't add yourself as participator");
			}
			
		}
		
		for (int i = 0; i < participator.size(); i++) {
			for (int j = 0; j < participator.size(); j++) {
				if (i == j) continue;
				if (participator.elementAt(i).equals(participator.elementAt(j))) {
					throw new Exception("you add the same participators");
				}
			}
		}
		if (participator.isEmpty()) {
			throw new Exception("you can't have a meeting alone");
		}

		if (!Date.isValid(startDate) || !Date.isValid(endDate)) {
			throw new Exception("invalid date");
		}
		if (startDate.greaterOrEqual(endDate)) {
			throw new Exception("invalid time interval");
		}
		boolean isuserReg = !storage.queryUser((User user) ->{		//if user do not register, false
			if (user.getName().equals(userName))
				return true;
			return false;
		}).isEmpty();
		if (!isuserReg) {
			throw new Exception("there is participator who isn't registered");
		}
		
		for (int i = 0; i < participator.size(); i++) {
			if (!storage.containUser(participator.elementAt(i)))
				throw new Exception("there is participator who isn't registered"); 
		}
		
		boolean isExist = !storage.queryMeeting((Meeting meeting) ->{
			if (meeting.getTitle().equals(title)) return true;
			if (startDate.lessThan(meeting.getEndDate())  && startDate.greaterOrEqual(meeting.getStartDate())
				|| (startDate.lessThan(meeting.getStartDate()) && endDate.greaterThan(meeting.getStartDate()))) {
				if (userName.equals(meeting.getSponsor()) || meeting.isParticipator(userName)) return true;
				for (int i = 0; i < participator.size(); i++) {
					if (meeting.isParticipator(participator.elementAt(i)) || participator.elementAt(i).equals(meeting.getSponsor())) return true;
				}
			}
			return false;
		}).isEmpty();
		if (isExist) {
			throw new Exception("time conflict or name conflict");
		}

		Meeting meeting = new Meeting(userName, participator, startDate, endDate, title);
		storage.createMeeting(meeting);
		storage.sync();
	}
	
	public void addMeetingParicipator(String userName, String title, String participator) throws Exception {
		boolean isEmpty = storage.queryUser((User user) ->{
			if (user.getName().equals(participator))
				return true;
			return false;
		}).isEmpty();
		if (isEmpty) {
			throw new Exception("no user");
		}
		if (userName == participator) {
			throw new Exception("can't add yourself as participator");
		}
		ArrayList<Meeting> meetingList = meetingQuery(userName, title);
		boolean isOverlap = !storage.queryMeeting((Meeting meeting) ->{
			if ((meetingList.get(0).getStartDate().lessThan(meeting.getEndDate()) && meetingList.get(0).getStartDate().greaterOrEqual(meeting.getStartDate())) 
				|| (meetingList.get(0).getStartDate().lessThan(meeting.getStartDate()) && meetingList.get(0).getEndDate().greaterThan(meeting.getStartDate()))) {
				if (participator.equals(meeting.getSponsor()) || meeting.isParticipator(participator))
					return true;
			}
			return false;
		}).isEmpty();
		if (isOverlap) {
			throw new Exception("time conflict");
		}
		//std::list<Meeting> p_meeting = meetingQuery(participator, Date::dateToString(u_meeting.begin()->getStartDate()),
		//			Date::dateToString(u_meeting.begin()->getEndDate()));
		//if (!p_meeting.empty()) return false;

		int count = storage.updateMeeting((Meeting meeting) ->{
			if (meeting.getTitle().equals(title) && meeting.getSponsor().equals(userName)) {
				if (meeting.isParticipator(participator) || meeting.getSponsor().equals(participator))
					return false;
				else
					return true;
			}

			return false;
		}, (Meeting meeting) ->{
			meeting.addParticipator(participator);
		});
		if (count == 0) {
			throw new Exception("no this meeting");
		}
		storage.sync();
	}
	
	public void removeMeetingParticipator(String userName, String title, String participator) throws Exception {
		int count = storage.updateMeeting((Meeting meeting) ->{
			if (meeting.getTitle().equals(title) && meeting.getSponsor().equals(userName) && meeting.isParticipator(participator))
				return true;
			return false;
		}, (Meeting meeting) ->{
			meeting.removeParticipator(participator);
		});
		if (count == 0) 
			throw new Exception("no this meeting");

		storage.deleteMeeting((Meeting meeting) ->{
			if (meeting.getParticipators().isEmpty())
				return true;
			return false;
		});
		storage.sync();
	}
	
	public void quitMeeting(String userName, String title) throws Exception {
		int count = storage.updateMeeting((Meeting meeting) ->{
			if (meeting.getTitle().equals(title) && !meeting.getSponsor().equals(userName) && meeting.isParticipator(userName))
				return true;
			return false;
		}, (Meeting meeting) ->{
			meeting.removeParticipator(userName);
		});
		if (count == 0)
			throw new Exception("no this meeting");
		storage.deleteMeeting((Meeting meeting) ->{
			if (meeting.getParticipators().isEmpty())
				return true;
			return false;
		});
		storage.sync();
	}
	
	public ArrayList<Meeting> meetingQuery(String userName, String title) {
		ArrayList<Meeting> tMeeting = storage.queryMeeting((Meeting meeting) ->{
			if (meeting.getTitle().equals(title))
				if (meeting.getSponsor().equals(userName) || meeting.isParticipator(userName))
					return true;

			return false;
		});

		return tMeeting;
	}
	
	public ArrayList<Meeting> listAllMeetings(String userName) {
		ArrayList<Meeting> tMeeting = storage.queryMeeting((Meeting meeting) ->{
			if (meeting.getSponsor().equals(userName) || meeting.isParticipator(userName))
				return true;

			return false;
		});

		return tMeeting;
	}
	
	public ArrayList<Meeting> listAllSponsorMeetings(String userName) {
		ArrayList<Meeting> tMeeting = storage.queryMeeting((Meeting meeting) ->{
			if (meeting.getSponsor().equals(userName))
				return true;

			return false;
		});

		return tMeeting;
	}
	
	public ArrayList<Meeting> listAllParticipateMeetings(String userName) {
		ArrayList<Meeting> tMeeting = storage.queryMeeting((Meeting meeting) ->{
			if (meeting.isParticipator(userName))
				return true;

			return false;
		});

		return tMeeting;
	}
	
	public void deleteMeeting(String userName, String title) throws Exception {
		int count = storage.deleteMeeting((Meeting meeting) ->{
			if (meeting.getTitle().equals(title) && meeting.getSponsor().equals(userName))
				return true;
			return false;
		});

		if (count == 0) 
			throw new Exception("no this meeting");
		storage.sync();
	}
	
	public void deleteAllMeetings(String userName, String title) throws Exception {
		int count = storage.deleteMeeting((Meeting meeting) ->{
			if (meeting.getSponsor().equals(userName))
				return true;
			return false;
		});

		if (count == 0) 
			throw new Exception("no this meeting");
		storage.sync();
	}
	
	public void startAgenda() {
		storage = Storage.getInstance();
	}
	
	public void quitAgenda() {
		storage.sync();
	}
//	public static void main(String[] args) throws Exception {
//		Service service = new Service();
//		service.userRegister("sb2", "sb2", "sb2", "sb2");
//		service.quitAgenda();
//	}
}
