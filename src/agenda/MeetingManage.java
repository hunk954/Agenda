package agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
public class MeetingManage {
	private JFrame parentFrame;
	private JFrame jFrame = new JFrame("会议管理系统");
	private JButton listMeeting = new JButton("列出所有会议");
	private JButton addMeeting = new JButton("添加会议");
	private JButton deleteMeeting = new JButton("删除会议");
	private JButton quitMeeting = new JButton("退出会议");
	private JButton returnButton = new JButton("返回");
	private Service service;
	private String userName;
	public MeetingManage(JFrame parent, Service service, String userName) {
		parentFrame = parent;
		jFrame.setSize(400,600);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());//无边界布局
		//设置按右下角x后关闭窗口
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//初始化，窗体内放其他的控件
		init();
		jFrame.setVisible(true);
		this.service = service;
		this.userName = userName;
	}
	public void init() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());//流式布局
		JLabel title = new JLabel("会议管理");
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		titlePanel.add(title);
		jFrame.getContentPane().add(titlePanel,"North");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5, 1, 5, 5));
		addMeeting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// TODO Auto-generated method stub
					String numOfParticipate;
					String meetingTitle = JOptionPane.showInputDialog("请输入会议标题");
					while (meetingTitle.equals("")) 
						meetingTitle = JOptionPane.showInputDialog("请输入会议标题");
					
					numOfParticipate = JOptionPane.showInputDialog("请输入参与会议的人数（不包括自己）");
					while (numOfParticipate.equals(""))
						numOfParticipate = JOptionPane.showInputDialog("请输入参与会议的人数（不包括自己）");
					while (!numOfParticipate.matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "请输入数字好吗？");
						numOfParticipate = JOptionPane.showInputDialog("请输入参与会议的人数（不包括自己）");
					}
						
					int n = Integer.parseInt(numOfParticipate);
					
					Vector<String> participators = new Vector<String>();
					for(int i = 0; i < n; i++) {
						String member = JOptionPane.showInputDialog("第" + (i+1) + "位参与会议的用户名字：");
						while (member.equals(""))
							member = JOptionPane.showInputDialog("第" + (i+1) + "位参与会议的用户名字：");
						
						if (member.equals(userName))
							throw new Exception("不能将自己加入到会议中");
//								System.out.println(member);
						participators.addElement(member);
					}
					String startDate = JOptionPane.showInputDialog("开始时间(格式：XXXX-XX-XX/XX:XX)");
					while (startDate.equals(""))
						startDate = JOptionPane.showInputDialog("开始时间(格式：XXXX-XX-XX/XX:XX)");
					while (!startDate.matches("^(\\d{4})(-)(\\d{2})(-)(\\d{2})(/)(\\d{2})(:)(\\d{2})$")) {
						JOptionPane.showMessageDialog(null, "请输入正确时间格式！");
						startDate = JOptionPane.showInputDialog("开始时间(格式：XXXX-XX-XX/XX:XX)");
					}
					String endDate = JOptionPane.showInputDialog("结束时间:(格式：XXXX-XX-XX/XX:XX)");
					while (endDate.equals(""))
						endDate = JOptionPane.showInputDialog("结束时间:(格式：XXXX-XX-XX/XX:XX)");
					while (!endDate.matches("^(\\d{4})(-)(\\d{2})(-)(\\d{2})(/)(\\d{2})(:)(\\d{2})$")) {
						JOptionPane.showMessageDialog(null, "请输入正确时间格式！");
						endDate = JOptionPane.showInputDialog("结束时间:(格式：XXXX-XX-XX/XX:XX)");
					}
					
					service.createMeeting(userName, meetingTitle, Date.stringToDate(startDate), Date.stringToDate(endDate), participators);
					JOptionPane.showMessageDialog(null, "添加成功！");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					if (e1.getMessage() != null)
						JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		listMeeting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("模拟列出所有的会议");
				ArrayList<Meeting> meetings = service.listAllMeetings(userName);
				new ListMeeting(jFrame, meetings);
				jFrame.setVisible(false);
			}
		});
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parentFrame.setVisible(true);
				jFrame.setVisible(false);
			}
		});
		
		deleteMeeting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String meetingTitle = JOptionPane.showInputDialog("请输入待删除会议标题");
				try {
					service.deleteMeeting(userName, meetingTitle);
					JOptionPane.showMessageDialog(null, "删除成功！");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "找不到此会议");
				}
				
			}
		});
		
		quitMeeting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String meetingTitle = JOptionPane.showInputDialog("请输入待退出会议标题");
				try {
					service.quitMeeting(userName, meetingTitle);
					JOptionPane.showMessageDialog(null, "退出成功！");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "找不到此会议");
					// TODO Auto-generated catch block
				}
				
			}
		});
		buttonPanel.add(listMeeting);
		buttonPanel.add(addMeeting);
		buttonPanel.add(deleteMeeting);
		buttonPanel.add(quitMeeting);
		buttonPanel.add(returnButton);
		jFrame.getContentPane().add(buttonPanel,"Center");
	}
}
