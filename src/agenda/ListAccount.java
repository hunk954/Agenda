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

public class ListAccount {
	private JFrame parentFrame;
	private JFrame jFrame = new JFrame("会议管理系统");
	private ArrayList<User> users;
	public ListAccount(JFrame parent, ArrayList<User> users) {
		this.parentFrame = parent;
		jFrame.setSize(400, 600);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jFrame.setVisible(true);
		this.users = users;
		init();
	}
	public void init() {
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new FlowLayout());
		JLabel title = new JLabel("所有用户");
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		titlePane.add(title);
		jFrame.getContentPane().add(titlePane, "North");
		
		JPanel accountPane = new JPanel();
		accountPane.setLayout(new FlowLayout());
		Object []columnNames = {"用户名", "手机号", "邮箱"};
//		System.out.println(users.size());
		Object [][]userDetails = new Object[users.size()][3];
		for(int i = 0; i < users.size(); i++) {
			userDetails[i][0] = users.get(i).getName();
			userDetails[i][1] = users.get(i).getPhone();
			userDetails[i][2] = users.get(i).getEmail();
		}
		JTable table = new JTable(userDetails, columnNames);
		for(int i = 0; i < 3 ;i++) {
			TableColumn column1 = table.getColumnModel().getColumn(i);
			column1.setPreferredWidth(120);
		}
		table.setEnabled(false);
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
