package agenda;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	private JFrame  jFrame = new JFrame("�������ϵͳ");
	private Container container = jFrame.getContentPane();
	private JLabel user = new JLabel("�û���");
	private JTextField username = new JTextField();
	private JLabel password = new JLabel("����");
	private JPasswordField passwordfield = new JPasswordField();
	private JButton okbtn = new JButton("ע��");
	private JButton loginbtn = new JButton("��½");
	public Login() {
		jFrame.setSize(400,400);
		jFrame.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		//���ð����½�x��رմ���
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		//��ʼ���������ڷ������Ŀؼ�
		init();
		jFrame.setVisible(true);
	}
	public void init() {
		//����
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());//��ʽ����
		JLabel title = new JLabel("�������ϵͳ");
		title.setFont(new Font("����",Font.PLAIN, 40));
		titlePanel.add(title);
		container.add(titlePanel,"North");
		//����
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		user.setBounds(60, 40, 100, 20);
		password.setBounds(60, 80, 100, 20);
		fieldPanel.add(user);
		fieldPanel.add(password);
		user.setFont(new Font("����",Font.PLAIN, 20));
		username.setBounds(140, 40, 120, 20);
		passwordfield.setBounds(140, 80, 120, 20);
		password.setFont(new Font("����",Font.PLAIN, 20));
		fieldPanel.add(username);
		fieldPanel.add(passwordfield);
		container.add(fieldPanel,"Center");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(okbtn);
		buttonPanel.add(loginbtn);
		container.add(buttonPanel,"South");
	}
	public  static void main(String args[]) {
		new Login();
	}
}
