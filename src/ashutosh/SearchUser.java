package ashutosh;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JScrollPane;

public class SearchUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public SearchUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSerachContacts = new JLabel("Serach Contacts");
		lblSerachContacts.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSerachContacts.setBounds(196, 29, 200, 30);
		contentPane.add(lblSerachContacts);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(41, 98, 510, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Dialog", Font.PLAIN, 18));
		textArea.setBounds(41, 156, 510, 331);
		contentPane.add(textArea);
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/chatserver?user=root&password=ashutosh");
					String query = "select * from nusers";
					PreparedStatement st = cn.prepareStatement(query);
					ResultSet rs = st.executeQuery();
					textArea.setText("");
					while(rs.next())
					{
						String toSerach = textField.getText();
						if(rs.getString(2).contains(toSerach))
						{
							textArea.append(rs.getString(2)+"---->"+rs.getString(1)+"\n");
						}
					}
					//lblNewLabel.setText(useful);
					cn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
