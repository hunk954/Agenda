package agenda;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

public class ListAccount {
	private JFrame parentFrame;
	private JFrame jFrame = new JFrame("�������ϵͳ");
	public ListAccount(JFrame parent) {
		this.parentFrame = parent;
		jFrame.setSize(400, 600);
		jFrame.setLocationRelativeTo(null);
		jFrame.getContentPane().setLayout(new BorderLayout());
		jFrame.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);
		init();
		jFrame.setVisible(true);
	}
	public void init() {
		JPanel titlePane = new JPanel();
		titlePane.setLayout(new FlowLayout());
		JLabel title = new JLabel("�����û�");
		title.setFont(new Font("����",Font.PLAIN, 40));
		titlePane.add(title);
		jFrame.getContentPane().add(titlePane, "North");
		
		JPanel buttonPane = new JPanel();
		JButton returnButton = new JButton("����");
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