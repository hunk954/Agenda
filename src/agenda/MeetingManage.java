package agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MeetingManage {
	private JFrame parentFrame;
	private JFrame jFrame = new JFrame("会议管理系统");
	private JButton listMeeting = new JButton("列出所有会议");
	private JButton addMeeting = new JButton("添加会议");
	private JButton deleteMeeting = new JButton("删除会议");
	private JButton quitMeeting = new JButton("退出会议");
	private JButton returnButton = new JButton("返回");
	public MeetingManage(JFrame parent) {
		parentFrame = parent;
		jFrame.setSize(400,600);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());//无边界布局
		//设置按右下角x后关闭窗口
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		//初始化，窗体内放其他的控件
		init();
		jFrame.setVisible(true);
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
				// TODO Auto-generated method stub
				String numOfParticipate;
				String meetingTitle = JOptionPane.showInputDialog("请输入会议标题");
				if(meetingTitle != null) {
					numOfParticipate = JOptionPane.showInputDialog("请输入参与会议的人数（不包括自己）");
					if(numOfParticipate != null){
						int n = Integer.parseInt(numOfParticipate);
						String []arr = new String[5];
						for(int i = 0; i < n; i++) {
							arr[i] = JOptionPane.showInputDialog("第" + (i+1) + "位参与会议的用户名字：");
						}
						String startDate = JOptionPane.showInputDialog("开始时间(格式：XXXX-XX-XX/XX:XX)");
						String endDate = JOptionPane.showInputDialog("结束时间:(格式：XXXX-XX-XX/XX:XX)");
						
						JOptionPane.showMessageDialog(null, "添加成功！");
					}
				}
			}
		});
		listMeeting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("模拟列出所有的会议");
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
				JOptionPane.showMessageDialog(null, "删除成功！");
			}
		});
		
		quitMeeting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String meetingTitle = JOptionPane.showInputDialog("请输入待退出会议标题");
				JOptionPane.showMessageDialog(null, "退出成功！");
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
