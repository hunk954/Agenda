package agenda;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.*;

public class Register {
	private JFrame jFrame = new JFrame("�������ϵͳ");
	private Container container = jFrame.getContentPane();
	private JLabel userlabel = new JLabel("�û���");
	private JTextField r_user = new JTextField();
	private JLabel passwordlabel = new JLabel("����");
	private JTextField r_password = new JTextField();
	private JLabel phonelabel = new JLabel("�ֻ�");
	private JTextField r_phone = new JTextField();
	private JLabel emaillabel = new JLabel("����");
	
	public Register() {
		jFrame.setSize(400, 400);
		jFrame.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		
	}
}
