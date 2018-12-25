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
	private JFrame  jFrame = new JFrame("会议管理系统");
	private Container container = jFrame.getContentPane();
	private JLabel user = new JLabel("用户名");
	private JTextField username = new JTextField();
	private JLabel password = new JLabel("密码");
	private JPasswordField passwordfield = new JPasswordField();
	private JButton okbtn = new JButton("注册");
	private JButton loginbtn = new JButton("登陆");
	public Login() {
		jFrame.setSize(400,400);
		jFrame.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		//设置按右下角x后关闭窗口
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		//初始化，窗体内放其他的控件
		init();
		jFrame.setVisible(true);
	}
	public void init() {
		//标题
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());//流式布局
		JLabel title = new JLabel("会议管理系统");
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		titlePanel.add(title);
		container.add(titlePanel,"North");
		//输入
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		user.setBounds(60, 40, 100, 20);
		password.setBounds(60, 80, 100, 20);
		fieldPanel.add(user);
		fieldPanel.add(password);
		user.setFont(new Font("黑体",Font.PLAIN, 20));
		username.setBounds(140, 40, 120, 20);
		passwordfield.setBounds(140, 80, 120, 20);
		password.setFont(new Font("黑体",Font.PLAIN, 20));
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
