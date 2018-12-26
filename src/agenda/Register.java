package agenda;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;

public class Register {
	private JFrame jFrame = new JFrame("�������ϵͳ-ע��");
	private Container container = jFrame.getContentPane();
	private JLabel userlabel = new JLabel("�û���");
	private JTextField r_user = new JTextField();
	private JLabel passwordlabel = new JLabel("����");
	private JTextField r_password = new JTextField();
	private JLabel phonelabel = new JLabel("�ֻ�");
	private JTextField r_phone = new JTextField();
	private JLabel emaillabel = new JLabel("����");
	private JTextField r_email = new JTextField("");
	private JButton surebtn = new JButton("ȷ��");
	private JButton resetbtn = new JButton("����");
	private JButton returnbtn = new JButton("����");
	private JFrame parentFrame;
	public Register(JFrame parentFrame) {
		this.parentFrame = parentFrame; 
		jFrame.setSize(400, 400);
		jFrame.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);
		init();
		jFrame.setVisible(true);
	}
	public void init() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel title = new JLabel("ע���û�");
		title.setFont(new Font("����",Font.PLAIN, 40));
		titlePanel.add(title);
		container.add(titlePanel, "North");
		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		userlabel.setBounds(60,40,100,20);
		userlabel.setFont(new Font("����", Font.PLAIN, 20));
		passwordlabel.setBounds(60,80,100,20);
		passwordlabel.setFont(new Font("����", Font.PLAIN, 20));
		phonelabel.setBounds(60, 120, 100, 20);
		phonelabel.setFont(new Font("����", Font.PLAIN, 20));
		emaillabel.setBounds(60,160,100,20);
		emaillabel.setFont(new Font("����", Font.PLAIN, 20));
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
				parentFrame.setEnabled(true);
				jFrame.setVisible(false);
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
		if(command == "����") {
			user.setText("");
			phone.setText("");
			email.setText("");
			password.setText("");
		}
	}
}