package agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class Account {
	private JFrame parentJFrame;
	private JFrame jFrame = new JFrame("会议管理系统");
	private JButton listButton = new JButton("列出所有用户");
	private JButton myMeetingButton = new JButton("会议管理 ");
	private JButton deleteAccountButton = new JButton("删除此用户");
	private JButton logOutButton = new JButton("登出");
	private Service service;
	private String userName;
	private String password;
	
	public Account(JFrame parent, Service service, String userName, String password) {
		parentJFrame = parent;
		jFrame.setSize(400, 400);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init();
		jFrame.setVisible(true);
		this.service = service;
		this.userName = userName;
		this.password = password;
	}
	public void init() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		JLabel title = new JLabel("用户操作");
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		titlePanel.add(title);
		jFrame.getContentPane().add(titlePanel, "North");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 1, 5, 5));
		listButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<User> users = service.listAllUsers();
				new ListAccount(jFrame, users);
				jFrame.setVisible(false);
			}
		});
		
		myMeetingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.setVisible(false);
				new MeetingManage(jFrame,service, userName);
//				new MeetingManage(jFrame);
			}
		});
		
		
		deleteAccountButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "确认删除此用户吗？","会议管理系统",JOptionPane.YES_NO_OPTION);
				
				if(n == 0) {
					try {
						service.deleteUser(userName, password);
						jFrame.setVisible(false);
						parentJFrame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}					
				}
			}
		});
		
		logOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
				parentJFrame.setVisible(true);
			}
		});
		
		buttonPanel.add(listButton);
		buttonPanel.add(myMeetingButton);
		buttonPanel.add(deleteAccountButton);
		buttonPanel.add(logOutButton);
		jFrame.getContentPane().add(buttonPanel,"Center");
	}
}
