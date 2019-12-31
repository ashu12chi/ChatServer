package ashutosh;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class User extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public User() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 642, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(5, 5, 614, 0);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Chat Now!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(260, 13, 133, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnSearchContacts = new JButton("Search Contacts");
		btnSearchContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchUser user = new SearchUser();
				user.setVisible(true);
			}
		});
		btnSearchContacts.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSearchContacts.setBounds(256, 202, 160, 25);
		contentPane.add(btnSearchContacts);
		
		JButton btnNewButton = new JButton("Chat Now!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatNow chat = new ChatNow();
				chat.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(256, 312, 160, 25);
		contentPane.add(btnNewButton);
	}
}
