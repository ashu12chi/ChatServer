package ashutosh;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Front extends JFrame {
	JPanel contentPane;
	static JTextField textField;
	JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Front frame = new Front();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Front() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\images.png"));
		lblNewLabel_1.setBounds(191, 13, 283, 249);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Mobile:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(137, 298, 67, 25);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(224, 300, 264, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(137, 360, 80, 25);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(224, 359, 264, 25);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/chatserver?user=root&password=ashutosh");
					String query = "select * from nusers where pno=?";
					PreparedStatement st = cn.prepareStatement(query);
					st.setInt(1, Integer.parseInt(textField.getText()));
					ResultSet rs = st.executeQuery();
					if(rs.next())
					{
						byte [] salt = rs.getBytes(4);
						String regeneratedPassowrdToVerify = getSecurePassword(passwordField.getText(), salt);
						String passwordToCheck = rs.getString(3);
						if(regeneratedPassowrdToVerify.equals(passwordToCheck))
						{
							User user = new User();
							user.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Invalid Password");
							passwordField.setText("");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid User");
						textField.setText("");
						passwordField.setText("");
					}
					cn.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(192, 493, 110, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUser user = new AddUser();
				user.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(337, 493, 110, 25);
		contentPane.add(btnNewButton_1);
	}
	 private static String getSecurePassword(String passwordToHash, byte[] salt)
	    {
	        String generatedPassword = null;
	        try {
	            // Create MessageDigest instance for MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            //Add password bytes to digest
	            md.update(salt);
	            //Get the hash's bytes
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            //This bytes[] has bytes in decimal format;
	            //Convert it to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            generatedPassword = sb.toString();
	        }
	        catch (NoSuchAlgorithmException e) {
	            JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
	        }
	        return generatedPassword;
	    }
	 private static byte[] getSalt()
	    {
	        //Always use a SecureRandom generator
	        SecureRandom sr = null;
			try {
				sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
			}
	        //Create array for salt
	        byte[] salt = new byte[16];
	        //Get a random salt
	        sr.nextBytes(salt);
	        //return salt
	        return salt;
	    }
}
