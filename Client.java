
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener{

	static Socket kkSocket = null;
	static PrintWriter out = null;
	static BufferedReader in = null;
	
	/**
	 *Supressing WARNING
	 */
	private static final long serialVersionUID = 1L;
	
	static JLabel lblIP;
	static JLabel lblPort;
	static JLabel lblISBN;
	static JLabel lblAuthor;
	static JLabel lblTitle;
	static JLabel lblYear;
	static JLabel lblPublisher;
	static JLabel lblCommands;
	static JButton Connect;
	static JButton Disconnect;
	static JButton Send;
	static JButton SUBMIT;
	static JButton UPDATE;
	static JButton GET;
	static JButton REMOVE;
	static JTextArea text;
	static JTextArea result;
	static JTextField txtIP;
	static JTextField txtPort;
	static JTextField txtISBN;
	static JTextField txtAuthor;
	static JTextField txtTitle;
	static JTextField txtYear;
	static JTextField txtPublisher;
	static JCheckBox cb;
	
	

	
	public Client() {

		


		this.setTitle("CLIENT");
		this.setSize(900, 630);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(null);

		lblIP = new JLabel("IP address:  ");
		lblIP.setBounds(10, 10, 90, 21);
		this.add(lblIP);
		
		txtIP = new JTextField();
		txtIP.setBounds(105, 10, 90, 21);
		this.add(txtIP);
		
		lblPort = new JLabel("Port:  ");
		lblPort.setBounds(10, 35, 90, 21);
		this.add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setBounds(105, 35, 90, 21);
		this.add(txtPort);
		
		lblISBN = new JLabel("ISBN: ");
		lblISBN.setBounds(10, 70, 225, 21);
		this.add(lblISBN);
		
		txtISBN = new JTextField();
		txtISBN.setBounds(105, 70, 225, 21);
		this.add(txtISBN);
		
		lblTitle = new JLabel("Title: ");
		lblTitle.setBounds(10, 105, 225, 21);
		this.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(105, 105, 225, 21);
		this.add(txtTitle);
		
		lblYear = new JLabel("Year: ");
		lblYear.setBounds(10, 140, 225, 21);
		this.add(lblYear);
		
		txtYear = new JTextField();
		txtYear.setBounds(105, 140, 225, 21);
		this.add(txtYear);
		
		lblPublisher = new JLabel("Publisher: ");
		lblPublisher.setBounds(10, 175, 225, 21);
		this.add(lblPublisher);
		
		txtPublisher = new JTextField();
		txtPublisher.setBounds(105, 175, 225, 21);
		this.add(txtPublisher);
		
		lblAuthor = new JLabel("Author: ");
		lblAuthor.setBounds(10, 210, 225, 21);
		this.add(lblAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setBounds(105, 210, 225, 21);
		this.add(txtAuthor);
		
		lblCommands = new JLabel("Commands: ");
		lblCommands.setBounds(10, 245, 90, 21);
		this.add(lblCommands);
		
		Connect = new JButton("Connect");
		Connect.setBounds(200, 10, 90, 21);
		Connect.addActionListener(this);
		this.add(Connect);
		
		Disconnect = new JButton("Disconnect");
		Disconnect.setBounds(300, 10, 90, 21);
		Disconnect.addActionListener(this);
		this.add(Disconnect);
		
		Send = new JButton("Send");
		Send.setBounds(400,40,90,21);
		Send.addActionListener(this);
		
		SUBMIT = new JButton("SUBMIT");
		SUBMIT.setBounds(10, 280, 100, 21);
		this.add(SUBMIT);
		SUBMIT.addActionListener(this);

		UPDATE = new JButton("UPDATE");
		UPDATE.setBounds(150, 280, 100, 21);
		this.add(UPDATE);
		UPDATE.addActionListener(this);
		
		GET = new JButton("GET");
		GET.setBounds(10, 310, 100, 21);
		this.add(GET);
		GET.addActionListener(this);

		REMOVE = new JButton("REMOVE");
		REMOVE.setBounds(150, 310, 100, 21);
		this.add(REMOVE);
		REMOVE.addActionListener(this);

		result = new JTextArea();
		result.setBounds(430, 30, 430, 565);
		this.add(result);
		
		JLabel op = new JLabel("Output ");
		op.setBounds(620,10,70,15);
		op.setFont(new Font("Times New Roman",Font.BOLD,18));
		this.add(op);

		

		this.setVisible(true);	
	
	}
	public static void main(String[] args) {
		new Client();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton temp = (JButton)ae.getSource();

		String button = temp.getText();
		
		if(button == "Connect"){

			

			try {
				int port = Integer.parseInt(txtPort.getText());
				

				kkSocket = new Socket(txtIP.getText(), port);
				out = new PrintWriter(kkSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

				Thread thread = new Thread(new ThreadRead(result, in));
				thread.start();
				
				result.append("Connection SUCCESS!!!!" + "\n");
				

			
			} catch (UnknownHostException e) {
				System.err.println("Error: Can't find server");
			} catch (IOException e) {
				System.err.println("Error: Couldn't get I/O for the connection to server");
			}

			
		}

		else if(button == "Disconnect"){
			result.setText("");
			out.close();
		}
		

		else if(button.equals("SUBMIT") || button.equals("UPDATE") || button.equals("GET") || button.equals("REMOVE")){
			int yesorno = JOptionPane.YES_OPTION;
			String bibtexmode = "false";
			if (button.equals("REMOVE")) {
				yesorno = JOptionPane.showConfirmDialog(null, "Remove?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);
			}

			// if(false) {
			// 	bibtexmode = "true";
			// }

			if (yesorno != JOptionPane.NO_OPTION) {
				String request = button + "-" + txtISBN.getText() + "-" + txtTitle.getText() + "-"
                + txtAuthor.getText() + "-" + txtPublisher.getText() + "-" + txtYear.getText() + "-" + bibtexmode;
			
			out.println(request);

		
			}
		
		else{
			result.append("ISBN is not 13 digits..." + "\n");

		}
		}



	}
		
		
}



