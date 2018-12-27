package agenda;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Register {
	private JFrame jFrame = new JFrame("会议管理系统-注册");
	private Container container = jFrame.getContentPane();
	private JLabel userlabel = new JLabel("用户名");
	private JTextField r_user = new JTextField();
	private JLabel passwordlabel = new JLabel("密码");
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
		userlabel.setBounds(60,40,100,20);
		userlabel.setFont(new Font("黑体", Font.PLAIN, 20));
		passwordlabel.setBounds(60,80,100,20);
		passwordlabel.setFont(new Font("黑体", Font.PLAIN, 20));
		phonelabel.setBounds(60, 120, 100, 20);
		phonelabel.setFont(new Font("黑体", Font.PLAIN, 20));
		emaillabel.setBounds(60,160,100,20);
		emaillabel.setFont(new Font("黑体", Font.PLAIN, 20));
		fieldPanel.add(userlabel);
		fieldPanel.add(passwordlabel);
		fieldPanel.add(phonelabel);
		fieldPanel.add(emaillabel);
		
		r_user.setBounds(140, 40, 120, 30);
		r_password.setBounds(140,80,120,30);
		r_phone.setBounds(140,120,120,30);
		r_email.setBounds(140,160,120,30);
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
					service.userRegister(userName, password, email, phone);
					jFrame.setVisible(false);
					new Account(jFrame,service, userName, password);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
	
		returnbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				parentFrame.setEnabled(true);
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