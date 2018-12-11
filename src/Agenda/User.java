package Agenda;

public class User {
	private String m_name;
	private String m_password;
	private String m_email;
	private String m_phone;
	
	public User(final String t_userName, final String t_userPassword, 
			final String t_userEmail, final String t_userPhone) {
		m_name = t_userName;
		m_password = t_userPassword;
		m_email = t_userEmail;
		m_phone = t_userPhone;
	}
	public User(final User t_user) {
		m_name = t_user.m_name;
		m_password = t_user.m_password;
		m_email = t_user.m_email;
		m_phone = t_user.m_phone;
	}
	public String getName() {
		return m_name;
	}
	public void setName(final String t_name) {
		m_name = t_name;
	}
	public String getPassword() {
		return m_password;
	}
	public void setPassword(final String t_password) {
		m_password = t_password;
	}
	public String getEmail() {
		return m_email;
	}
	public void setEmail(final String t_mail) {
		m_email = t_mail;
	}
	public String getPhone() {
		return m_phone;
	}
	public void setPhone(final String t_phone) {
		m_phone = t_phone;
	}
	
}
