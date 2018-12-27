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
import javax.swing.WindowConstants;

public class ListAccount {
	private JFrame parentFrame;
	private JFrame jFrame = new JFrame("会议管理系统");
	private ArrayList<User> users = new ArrayList<User>();
	public ListAccount(JFrame parent, ArrayList<User> users) {
		this.parentFrame = parent;
		jFrame.setSize(400, 600);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		init();
		jFrame.setVisible(true);
		this.users = users;
	}
	public void init() {
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new FlowLayout());
		JLabel title = new JLabel("所有用户");
		title.setFont(new Font("黑体",Font.PLAIN, 40));
		titlePane.add(title);
		jFrame.getContentPane().add(titlePane, "North");
		
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
