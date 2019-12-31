package ashutosh;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextArea;

import java.awt.TextArea;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Client extends JFrame {
	JLabel lblNewLabel;
	TextArea textArea;

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(185, 24, 325, 30);
		contentPane.add(lblNewLabel);
		
		textArea = new TextArea();
		textArea.setFont(new Font("Dialog", Font.PLAIN, 18));
		textArea.setBounds(33, 72, 544, 358);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(33, 452, 544, 68);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("Me ---> "+textField.getText()+"\n");
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/chatserver?user=root&password=ashutosh");
					String query = "insert into messages values(?,?,?)";
					PreparedStatement st = cn.prepareStatement(query);
					st.setString(2,lblNewLabel.getText());
					st.setString(1, Front.textField.getText());
					st.setString(3, textField.getText());
					st.executeUpdate();
					cn.close();
					textField.setText("");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Error "+ e.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(215, 533, 97, 25);
		contentPane.add(btnNewButton);
	}
}
