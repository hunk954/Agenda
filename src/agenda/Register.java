package agenda;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Register {
	private JFrame jFrame = new JFrame("会议管理系统-注册");
	private Container container = jFrame.getContentPane();
	private JLabel userlabel = new JLabel("*用户名");
	private JTextField r_user = new JTextField();
	private JLabel passwordlabel = new JLabel("*密码");
	private JPasswordField r_password = new JPasswordField();
	private JLabel phonelabel = new JLabel("手机");
	private JTextField r_phone = new JTextField();
	private JLabel emaillabel = new JLabel("邮箱");
	private JTextField r_email = new JTextField("");
	private JButton surebtn = new JButton("确认");
	private JButton resetbtn = new JButton("重置");
	private JButton returnbtn = new JButton("返回");
	private JFrame parentFrame;
	private Service service;
	protected String userName;
	protected String password;
	protected String email;
	protected String phone;
	private String userNameRegex = "^[a-zA-Z][a-zA-Z0-9_]{2,9}$";
	private String passwordRegex = "^[a-zA-Z0-9_-]{6,12}$";
	private String phoneRegex = "^[1-9][0-9]{10}$";
	private String emailRegex = "^(\\\\w)+(\\\\.\\\\w+)*@(\\\\w)+((\\\\.\\\\w+)+)$";
	private String hintUser = " 3~10位，必须以英文字母开头";
	private String passwordHint = " 6~12位，区分大小写";
	private String hintPhone = " 11位数字，不能以0开头";
	private String hintEmail = " 输入合法邮箱";
	public Register(JFrame parentFrame, Service service) {
		this.parentFrame = parentFrame; 
		jFrame.setSize(400, 400);
		jFrame.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		init();
		jFrame.setVisible(true);
		this.service = service;
	}
	public void init() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel title = new JLabel("注册用户");
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		titlePanel.add(title);
		container.add(titlePanel, "North");
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		userlabel.setBounds(40,40,100,20);
		userlabel.setFont(new Font("黑体", Font.PLAIN, 20));
		passwordlabel.setBounds(40,80,100,20);
		passwordlabel.setFont(new Font("黑体", Font.PLAIN, 20));
		phonelabel.setBounds(40, 120, 100, 20);
		phonelabel.setFont(new Font("黑体", Font.PLAIN, 20));
		emaillabel.setBounds(40,160,100,20);
		emaillabel.setFont(new Font("黑体", Font.PLAIN, 20));
		fieldPanel.add(userlabel);
		fieldPanel.add(passwordlabel);
		fieldPanel.add(phonelabel);
		fieldPanel.add(emaillabel);
		
		
		r_user.setBounds(140, 40, 200, 30);
		
		r_user.setText(hintUser);
		r_user.setForeground(Color.gray);
		textFocus userFocus = new textFocus(r_user, hintUser);
		r_user.addFocusListener(userFocus);
		r_user.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int chCode = e.getKeyCode();
				if(chCode == KeyEvent.VK_ENTER) {
					try {
						userName = r_user.getText();
						password = String.valueOf(r_password.getPassword());
						email = r_email.getText();
						phone = r_phone.getText();
						if (!userName.matches(userNameRegex)) {
							if (userName.equals(hintUser))
								throw new Exception("用户名不能为空");
							throw new Exception("用户名：3~10位，必须以英文字母开头");
						}	
						if (!password.matches(passwordRegex)) {
							if (password.equals(passwordHint))
								throw new Exception("密码不能为空");
							throw new Exception("密码：6~12位数字、大小写字母、中划线、下划线");
						}
						if (!phone.matches(phoneRegex)) {
							if (phone.equals(hintPhone))
								phone = "";
							else
								throw new Exception("电话：11位数字，不能以0开头");
						}
						if (!email.equals(emailRegex)) {
							if (email.equals(hintEmail)) 
								email = "";
							else 
								throw new Exception("邮箱：请输入合法邮箱");
						}
						r_password.setText("");
						service.userRegister(userName, password, email, phone);
						JOptionPane.showMessageDialog(null, "注册成功！");
						jFrame.setVisible(false);
						parentFrame.setVisible(true);
//						new Account(jFrame,service, userName, password);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		r_password.setBounds(140,80,200,30);
		r_password.setEchoChar((char)0);
		
		r_password.setText(passwordHint);
		r_password.setForeground(Color.gray);
		passwordFocus passwordFoc = new passwordFocus(r_password, passwordHint);
		r_password.addFocusListener(passwordFoc);
		r_password.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int chCode = e.getKeyCode();
				if(chCode == KeyEvent.VK_ENTER) {
					try {
						userName = r_user.getText();
						password = String.valueOf(r_password.getPassword());
						email = r_email.getText();
						phone = r_phone.getText();
						
						if (!userName.matches(userNameRegex)) {
							if (userName.equals(hintUser))
								throw new Exception("用户名不能为空");
							throw new Exception("用户名：3~10位，必须以英文字母开头");
						}	
						if (!password.matches(passwordRegex)) {
							if (password.equals(passwordHint))
								throw new Exception("密码不能为空");
							throw new Exception("密码：6~12位数字、大小写字母、中划线、下划线");
						}
						if (!phone.matches(phoneRegex)) {
							if (phone.equals(hintPhone))
								phone = "";
							else
								throw new Exception("电话：11位数字，不能以0开头");
						}
						if (!email.equals(emailRegex)) {
							if (email.equals(hintEmail)) 
								email = "";
							else 
								throw new Exception("邮箱：请输入合法邮箱");
						}
						r_password.setText("");
						service.userRegister(userName, password, email, phone);
						JOptionPane.showMessageDialog(null, "注册成功！");
						jFrame.setVisible(false);
						parentFrame.setVisible(true);
//						new Account(jFrame,service, userName, password);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		r_phone.setBounds(140,120,200,30);

		r_phone.setText(hintPhone);
		r_phone.setForeground(Color.gray);
		textFocus phoneFocus = new textFocus(r_phone, hintPhone);
		r_phone.addFocusListener(phoneFocus);
		r_phone.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int chCode = e.getKeyCode();
				if(chCode == KeyEvent.VK_ENTER) {
					try {
						userName = r_user.getText();
						password = String.valueOf(r_password.getPassword());
						email = r_email.getText();
						phone = r_phone.getText();
						if (!userName.matches(userNameRegex)) {
							if (userName.equals(hintUser))
								throw new Exception("用户名不能为空");
							throw new Exception("用户名：3~10位，必须以英文字母开头");
						}	
						if (!password.matches(passwordRegex)) {
							if (password.equals(passwordHint))
								throw new Exception("密码不能为空");
							throw new Exception("密码：6~12位数字、大小写字母、中划线、下划线");
						}
						if (!phone.matches(phoneRegex)) {
							if (phone.equals(hintPhone))
								phone = "";
							else
								throw new Exception("电话：11位数字，不能以0开头");
						}
						if (!email.equals(emailRegex)) {
							if (email.equals(hintEmail)) 
								email = "";
							else 
								throw new Exception("邮箱：请输入合法邮箱");
						}
						r_password.setText("");
						service.userRegister(userName, password, email, phone);
						JOptionPane.showMessageDialog(null, "注册成功！");
						jFrame.setVisible(false);
						parentFrame.setVisible(true);
//						new Account(jFrame,service, userName, password);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		
		r_email.setBounds(140,160,200,30);
		
		r_email.setText(hintEmail);
		r_email.setForeground(Color.gray);
		textFocus emailFocus = new textFocus(r_email, hintEmail);
		r_email.addFocusListener(emailFocus);
		r_email.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int chCode = e.getKeyCode();
				if(chCode == KeyEvent.VK_ENTER) {
					try {
						userName = r_user.getText();
						password = String.valueOf(r_password.getPassword());
						email = r_email.getText();
						phone = r_phone.getText();
						if (!userName.matches(userNameRegex)) {
							if (userName.equals(hintUser))
								throw new Exception("用户名不能为空");
							throw new Exception("用户名：3~10位，必须以英文字母开头");
						}	
						if (!password.matches(passwordRegex)) {
							if (password.equals(passwordHint))
								throw new Exception("密码不能为空");
							throw new Exception("密码：6~12位数字、大小写字母、中划线、下划线");
						}
						if (!phone.matches(phoneRegex)) {
							if (phone.equals(hintPhone))
								phone = "";
							else
								throw new Exception("电话：11位数字，不能以0开头");
						}
						if (!email.equals(emailRegex)) {
							if (email.equals(hintEmail)) 
								email = "";
							else 
								throw new Exception("邮箱：请输入合法邮箱");
						}
						r_password.setText("");
						service.userRegister(userName, password, email, phone);
						JOptionPane.showMessageDialog(null, "注册成功！");
						jFrame.setVisible(false);
						parentFrame.setVisible(true);
//						new Account(jFrame,service, userName, password);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
		fieldPanel.add(r_user);
		fieldPanel.add(r_password);
		fieldPanel.add(r_phone);
		fieldPanel.add(r_email);
		container.add(fieldPanel, "Center");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonListen rstEvent = new buttonListen(r_user,r_password, r_phone, r_email);
		resetbtn.addActionListener(rstEvent);
		surebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					userName = r_user.getText();
					password = String.valueOf(r_password.getPassword());
					email = r_email.getText();
					phone = r_phone.getText();
					if (!userName.matches(userNameRegex)) {
						if (userName.equals(hintUser))
							throw new Exception("用户名不能为空");
						throw new Exception("用户名：3~10位，必须以英文字母开头");
					}	
					if (!password.matches(passwordRegex)) {
						if (password.equals(passwordHint))
							throw new Exception("密码不能为空");
						throw new Exception("密码：6~12位数字、大小写字母、中划线、下划线");
					}
					if (!phone.matches(phoneRegex)) {
						if (phone.equals(hintPhone))
							phone = "";
						else
							throw new Exception("电话：11位数字，不能以0开头");
					}
					if (!email.equals(emailRegex)) {
						if (email.equals(hintEmail)) 
							email = "";
						else 
							throw new Exception("邮箱：请输入合法邮箱");
					}
					r_password.setText("");
					service.userRegister(userName, password, email, phone);
					JOptionPane.showMessageDialog(null, "注册成功！");
					jFrame.setVisible(false);
					parentFrame.setVisible(true);
//					new Account(jFrame,service, userName, password);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
	
		returnbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				parentFrame.setEnabled(true);
				jFrame.setVisible(false);
			}
		});
		buttonPanel.add(surebtn);
		buttonPanel.add(resetbtn);
		buttonPanel.add(returnbtn);
		container.add(buttonPanel,"South");
	}
//	public static void main(String args[]) {
//		Register myRegister = new Register();
//	}
}

class buttonListen implements ActionListener{
	private JTextField user;
	private JTextField password;
	private JTextField phone;
	private JTextField email;
	public buttonListen(JTextField user, JTextField password, JTextField phone, JTextField email) {
		this.user = user;
		this.password = password;
		this.phone = phone;
		this.email = email;
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command == "重置") {
			user.setText("");
			phone.setText("");
			email.setText("");
			password.setText("");
		}
	}
}

class textFocus implements FocusListener{
	private JTextField textField;
	private String hintText;
//	private JPasswordField password;
	public textFocus(JTextField textField, String hintText) {
		this.textField = textField;
		this.hintText = hintText;
	}
//	public textFocus(JPasswordField password) {
//		this.password = password;
//	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		String temp = textField.getText();
		if(temp.equals(hintText)) {
			textField.setText("");
			textField.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
		String temp = textField.getText();
//		if (!temp.equals("") && !temp.matches(regex)) {
//			JOptionPane.showMessageDialog(null, "12");
////			textField.grabFocus();
//		}	
		if(temp.equals("")) {
			textField.setForeground(Color.GRAY);
			textField.setText(hintText);
		}
	}
	
}

class passwordFocus implements FocusListener{
	private JPasswordField textField;
	private String hintText;
//	private JPasswordField password;
	public passwordFocus(JPasswordField textField, String hintText) {
		this.textField = textField;
		this.hintText = hintText;
	}
//	public textFocus(JPasswordField password) {
//		this.password = password;
//	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		String temp = String.valueOf(textField.getPassword());
		if(temp.equals(hintText)) {
			textField.setEchoChar('*');
			textField.setText("");
			textField.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		String temp = String.valueOf(textField.getPassword());
//		if (!temp.matches(regex) && !temp.equals("")) {
//			JOptionPane.showMessageDialog(null, msg);
////			textField.grabFocus();
//		}
		if(temp.equals("")) {
			textField.setEchoChar((char)0);
			textField.setForeground(Color.GRAY);
			textField.setText(hintText);
		}
	}
	
}