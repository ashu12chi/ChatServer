package ashutosh;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChatNow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public ChatNow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 400, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(12, 23, 254, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client client = new Client();
				client.setVisible(true);
				client.lblNewLabel.setText(textField.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/chatserver?user=root&password=ashutosh");
					String query = "select * from messages where sender=? and reciever=? or sender=? and reciever=?";
					PreparedStatement st = cn.prepareStatement(query);
					st.setString(1, client.lblNewLabel.getText());
					st.setString(2, Front.textField.getText());
					st.setString(4, client.lblNewLabel.getText());
					st.setString(3, Front.textField.getText());
					ResultSet rs = st.executeQuery();
					while(rs.next())
					{
						if(rs.getString(1).equals(Front.textField.getText()))
							client.textArea.append("Me ---> "+rs.getString(3)+"\n");
						else
						    client.textArea.append(rs.getString(1)+" ---> "+rs.getString(3)+"\n");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(90, 114, 97, 25);
		contentPane.add(btnNewButton);
	}
}
