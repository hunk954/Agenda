package agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
public class MeetingManage {
	private JFrame parentFrame;
	private JFrame jFrame = new JFrame("�������ϵͳ");
	private JButton listMeeting = new JButton("�г����л���");
	private JButton addMeeting = new JButton("��ӻ���");
	private JButton deleteMeeting = new JButton("ɾ������");
	private JButton quitMeeting = new JButton("�˳�����");
	private JButton returnButton = new JButton("����");
	private Service service;
	private String userName;
	public MeetingManage(JFrame parent, Service service, String userName) {
		parentFrame = parent;
		jFrame.setSize(400,600);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());//�ޱ߽粼��
		//���ð����½�x��رմ���
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//��ʼ���������ڷ������Ŀؼ�
		init();
		jFrame.setVisible(true);
		this.service = service;
		this.userName = userName;
	}
	public void init() {
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());//��ʽ����
		JLabel title = new JLabel("�������");
		title.setFont(new Font("����",Font.PLAIN, 40));
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
					String meetingTitle = JOptionPane.showInputDialog("������������");
					while (meetingTitle.equals("")) 
						meetingTitle = JOptionPane.showInputDialog("������������");
					
					numOfParticipate = JOptionPane.showInputDialog("��������������������������Լ���");
					while (numOfParticipate.equals(""))
						numOfParticipate = JOptionPane.showInputDialog("��������������������������Լ���");
					while (!numOfParticipate.matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "���������ֺ���");
						numOfParticipate = JOptionPane.showInputDialog("��������������������������Լ���");
					}
						
					int n = Integer.parseInt(numOfParticipate);
					
					Vector<String> participators = new Vector<String>();
					for(int i = 0; i < n; i++) {
						String member = JOptionPane.showInputDialog("��" + (i+1) + "λ���������û����֣�");
						while (member.equals(""))
							member = JOptionPane.showInputDialog("��" + (i+1) + "λ���������û����֣�");
						
						if (member.equals(userName))
							throw new Exception("���ܽ��Լ����뵽������");
//								System.out.println(member);
						participators.addElement(member);
					}
					String startDate = JOptionPane.showInputDialog("��ʼʱ��(��ʽ��XXXX-XX-XX/XX:XX)");
					while (startDate.equals(""))
						startDate = JOptionPane.showInputDialog("��ʼʱ��(��ʽ��XXXX-XX-XX/XX:XX)");
					while (!startDate.matches("^(\\d{4})(-)(\\d{2})(-)(\\d{2})(/)(\\d{2})(:)(\\d{2})$")) {
						JOptionPane.showMessageDialog(null, "��������ȷʱ���ʽ��");
						startDate = JOptionPane.showInputDialog("��ʼʱ��(��ʽ��XXXX-XX-XX/XX:XX)");
					}
					String endDate = JOptionPane.showInputDialog("����ʱ��:(��ʽ��XXXX-XX-XX/XX:XX)");
					while (endDate.equals(""))
						endDate = JOptionPane.showInputDialog("����ʱ��:(��ʽ��XXXX-XX-XX/XX:XX)");
					while (!endDate.matches("^(\\d{4})(-)(\\d{2})(-)(\\d{2})(/)(\\d{2})(:)(\\d{2})$")) {
						JOptionPane.showMessageDialog(null, "��������ȷʱ���ʽ��");
						endDate = JOptionPane.showInputDialog("����ʱ��:(��ʽ��XXXX-XX-XX/XX:XX)");
					}
					
					service.createMeeting(userName, meetingTitle, Date.stringToDate(startDate), Date.stringToDate(endDate), participators);
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
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
//				System.out.println("ģ���г����еĻ���");
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
				
				try {
					String meetingTitle = JOptionPane.showInputDialog("�������ɾ���������");
					while (meetingTitle.equals("")) 
						meetingTitle = JOptionPane.showInputDialog("�������ɾ���������");
					service.deleteMeeting(userName, meetingTitle);
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					if (e1.getMessage() != null)
						JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		
		quitMeeting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					String meetingTitle = JOptionPane.showInputDialog("��������˳��������");
					while (meetingTitle.equals("")) 
						meetingTitle = JOptionPane.showInputDialog("��������˳��������");
					service.quitMeeting(userName, meetingTitle);
					JOptionPane.showMessageDialog(null, "�˳��ɹ���");
				} catch (Exception e1) {
					if (e1.getMessage() != null)
						JOptionPane.showMessageDialog(null, e1.getMessage());
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
