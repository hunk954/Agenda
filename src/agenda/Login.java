package agenda;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login {
	private JFrame  jFrame = new JFrame("会议管理系统-登陆");
	private Container container = jFrame.getContentPane();
	private JLabel username = new JLabel("用户名");
	private JTextField l_username = new JTextField();
	private JLabel password = new JLabel("密码");
	private JPasswordField l_password = new JPasswordField();
	private JButton registerbtn = new JButton("注册");
	private JButton loginbtn = new JButton("登陆");
	private String userName;
	private String userPassword;
	private Service service;
	public Login(Service service) {
		jFrame.setSize(400,400);
		jFrame.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());//无边界布局
		//设置按右下角x后关闭窗口
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//初始化，窗体内放其他的控件
		init();
		jFrame.setVisible(true);
		this.service = service;
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
		username.setBounds(60, 40, 100, 20);
		username.setFont(new Font("黑体",Font.PLAIN, 20));//PLAIN表示粗细
		password.setBounds(60, 80, 100, 20);
		password.setFont(new Font("黑体",Font.PLAIN, 20));
		fieldPanel.add(username);
		fieldPanel.add(password);
		
		l_username.setBounds(140, 40, 120, 30);
		l_password.setBounds(140, 80, 120, 30);
		
		l_username.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				String userNameRegex = "^[a-zA-Z][a-zA-Z0-9_]{2,9}$";
				if (!l_username.getText().matches(userNameRegex) && !l_username.getText().equals("")) {
					try {
						throw new Exception("用户名：3~10位，必须以英文字母开头");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						// TODO Auto-generated catch block
					}
				}	
				
			}
			
		});
		
		//添加键盘事件监听
		l_username.addKeyListener(new KeyListener() {
			
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
						userName = l_username.getText();
						userPassword = String.valueOf(l_password.getPassword());
						l_password.setText("");
						if (userName.equals(""))
							throw new Exception("你还没有输入用户名");
						if (userPassword.equals(""))
							throw new Exception("你还没有输入密码");
						service.userLogIn(userName, userPassword);
						jFrame.setVisible(false);
						new Account(jFrame,service, userName, userPassword);	
					} catch(Exception e1) {
						if (e1.getMessage().equals("你还没有输入用户名") || e1.getMessage().equals("你还没有输入密码"))
							JOptionPane.showMessageDialog(null, e1.getMessage());
						else
							JOptionPane.showMessageDialog(null, "用户名不存在或密码错误");
//						System.out.println(e1.getMessage());
					}
				}
			}
		});
		l_password.addKeyListener(new KeyListener() {
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
						userName = l_username.getText();
						userPassword = String.valueOf(l_password.getPassword());
						l_password.setText("");
						if (userName.equals(""))
							throw new Exception("你还没有输入用户名");
						if (userPassword.equals(""))
							throw new Exception("你还没有输入密码");
						service.userLogIn(userName, userPassword);
						jFrame.setVisible(false);
						new Account(jFrame,service, userName, userPassword);	
					} catch(Exception e1) {
						if (e1.getMessage().equals("你还没有输入用户名") || e1.getMessage().equals("你还没有输入密码"))
							JOptionPane.showMessageDialog(null, e1.getMessage());
						else
							JOptionPane.showMessageDialog(null, "用户名不存在或密码错误");
//						System.out.println(e1.getMessage());
					}
				}
			}
		});
		
		fieldPanel.add(l_username);
		fieldPanel.add(l_password);
		container.add(fieldPanel,"Center");
		
		//按钮
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		registerbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				jFrame.setEnabled(false);
				new Register(jFrame,service);
			}
		});
		loginbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					userName = l_username.getText();
					userPassword = String.valueOf(l_password.getPassword());
					if (userName.equals(""))
						throw new Exception("你还没有输入用户名");
					if (userPassword.equals(""))
						throw new Exception("你还没有输入密码");
					service.userLogIn(userName, userPassword);
					jFrame.setVisible(false);
					new Account(jFrame,service, userName, userPassword);	
				} catch(Exception e1) {
					if (e1.getMessage().equals("你还没有输入用户名") || e1.getMessage().equals("你还没有输入密码"))
						JOptionPane.showMessageDialog(null, e1.getMessage());
					else
						JOptionPane.showMessageDialog(null, "用户名不存在或密码错误");
//					System.out.println(e1.getMessage());
				}
				
			}
		});
		buttonPanel.add(registerbtn);
		buttonPanel.add(loginbtn);
		container.add(buttonPanel,"South");
	}
}
