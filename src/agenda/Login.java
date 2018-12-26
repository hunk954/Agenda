package agenda;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	private JFrame  jFrame = new JFrame("�������ϵͳ-��½");
	private Container container = jFrame.getContentPane();
	private JLabel username = new JLabel("�û���");
	private JTextField l_username = new JTextField();
	private JLabel password = new JLabel("����");
	private JPasswordField l_password = new JPasswordField();
	private JButton registerbtn = new JButton("ע��");
	private JButton loginbtn = new JButton("��½");
	public Login() {
		jFrame.setSize(400,400);
		jFrame.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());//�ޱ߽粼��
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
		username.setBounds(60, 40, 100, 20);
		username.setFont(new Font("����",Font.PLAIN, 20));//PLAIN��ʾ��ϸ
		password.setBounds(60, 80, 100, 20);
		password.setFont(new Font("����",Font.PLAIN, 20));
		fieldPanel.add(username);
		fieldPanel.add(password);
		
		l_username.setBounds(140, 40, 120, 30);
		l_password.setBounds(140, 80, 120, 30);
		fieldPanel.add(l_username);
		fieldPanel.add(l_password);
		container.add(fieldPanel,"Center");
		
		//��ť
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		registerbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.setEnabled(false);
				new Register(jFrame);
			}
		});
		loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.setVisible(false);
				new Account(jFrame);
				
			}
		});
		buttonPanel.add(registerbtn);
		buttonPanel.add(loginbtn);
		container.add(buttonPanel,"South");
	}
}