package agenda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.TableColumn;

public class ListMeeting {
	private JFrame parentFrame;
	private JFrame jFrame = new JFrame("会议管理系统");
	private ArrayList<Meeting> meetings;
	public ListMeeting(JFrame parent, ArrayList<Meeting> meetings) {
		this.parentFrame = parent;
		jFrame.setSize(500, 600);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jFrame.setVisible(true);
		this.meetings = meetings;
		init();
	}
	public void init() {
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new FlowLayout());
		JLabel title = new JLabel("所有会议");
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		titlePane.add(title);
		jFrame.getContentPane().add(titlePane, "North");
		
		JPanel accountPane = new JPanel();
		accountPane.setLayout(new FlowLayout());
		Object []columnNames = {"会议发起人", "参与者", "起始时间", "结束时间"};
		System.out.println(meetings.size());
		Object [][]meetingDetails = new Object[meetings.size()][4];
		for(int i = 0; i < meetings.size(); i++) {
			meetingDetails[i][0] = meetings.get(i).getSponsor();
			meetingDetails[i][1] = meetings.get(i).getParticipators().get(0);
			for(int j = 1; j < meetings.get(i).getParticipators().size(); j++) {
				meetingDetails[i][1] += ", " + meetings.get(i).getParticipators().get(j);
			}
			meetingDetails[i][2] = Date.dateToString(meetings.get(i).getStartDate());
			meetingDetails[i][3] = Date.dateToString(meetings.get(i).getEndDate());
		}
		JTable table = new JTable(meetingDetails, columnNames);
		table.setEnabled(false);
		for(int i = 1; i < 4 ;i++) {
			TableColumn column1 = table.getColumnModel().getColumn(i);
			column1.setPreferredWidth(120);
		}
		accountPane.add(table.getTableHeader(), BorderLayout.NORTH);
		accountPane.add(table, BorderLayout.CENTER);
		jFrame.getContentPane().add(accountPane, "Center");
		
		JPanel buttonPane = new JPanel();
		JButton returnButton = new JButton("返回");
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.setVisible(false);
				parentFrame.setVisible(true);
			}
		});
		buttonPane.add(returnButton);
		jFrame.getContentPane().add(buttonPane, "South");
	}
}
