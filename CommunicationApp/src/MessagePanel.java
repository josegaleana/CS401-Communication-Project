import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.ScrollPane;

public class MessagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Client client;
	private ChatRoom currentChat;
	//private List<Messages> messages;
	private ObjectOutputStream out;
    private ObjectInputStream in;
	private JFormattedTextField textBox;
	private JTextArea textMessages;
	private MessageListener listener;
	
	public interface MessageListener {
		void onSendMessage(String message);
	}
	
	public void setListener (MessageListener listener) {
		this.listener = listener;
	}

	public MessagePanel() {
		initUI();
	}

	public JTextArea getTextMessages() {
		return textMessages;
	}

	public void initUI () {
		setForeground(new Color(135, 206, 250));
		setBackground(new Color(21, 96, 130));
		setLayout(null);
		
		// Panel for text box 
		JPanel textBorder = new JPanel();
		textBorder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46)));
		textBorder.setBounds(3, 391, 431, 40);
		add(textBorder);
		textBorder.setLayout(new BorderLayout(0, 0));
		
		textBox = new JFormattedTextField();
		textBox.setToolTipText("Type message...");
		textBox.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textBox.setHorizontalAlignment(SwingConstants.LEFT);
		textBox.setColumns(30);
		textBorder.add(textBox);
		
		// Panel for send button... putting button inside a border makes modifications easy!
		JPanel sendBorder = new JPanel();
		sendBorder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46)));
		sendBorder.setBounds(450, 393, 75, 38);
		add(sendBorder);
		sendBorder.setLayout(new BorderLayout(0, 0));
		
		// Panel for text messages box
		JPanel textMessagesBorder = new JPanel();
		textMessagesBorder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46)));
		textMessagesBorder.setBounds(3, 30, 431, 351);
		add(textMessagesBorder);
		textMessagesBorder.setLayout(new BorderLayout(0, 0));
		
		textMessages = new JTextArea();
		textMessages.setEditable(false);
		textMessagesBorder.add(textMessages);
		textMessages.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textMessages.setWrapStyleWord(true);
		textMessages.setLineWrap(true);
		//textMessages.setText(testString);

		JButton btnNewButton = new JButton("SEND");
		btnNewButton.setToolTipText("Hit button to send message...");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 12));
		sendBorder.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.onSendMessage(textBox.getText());
					textMessages.setText("");
					textBox.requestFocus();
				}
            }
		});
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setForeground(new Color(216, 191, 216));
		scrollPane.setFont(new Font("Dubai Medium", Font.PLAIN, 5));
		scrollPane.setBackground(new Color(245, 245, 245));
		scrollPane.setBounds(3, 32, 422, 350);
		textMessagesBorder.add(scrollPane, BorderLayout.NORTH);
		scrollPane.add(textMessages);
		add(scrollPane);

		// Panel for participants
		JPanel participantsBorder = new JPanel();
		participantsBorder.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46), new Color(78, 167, 46)));
		participantsBorder.setBounds(3, 1, 431, 25);
		add(participantsBorder);
		participantsBorder.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRoomParticipants = new JLabel("Participants:");
		lblRoomParticipants.setBackground(new Color(240, 240, 240));
		lblRoomParticipants.setForeground(new Color(0, 0, 0));
		lblRoomParticipants.setFont(new Font("Monospaced", Font.PLAIN, 12));
		participantsBorder.add(lblRoomParticipants, BorderLayout.WEST);
		
		JTextArea roomMembers = new JTextArea();
		roomMembers.setText(" John (Online), Mark, Dave\r\n");
		roomMembers.setToolTipText("List of current room's members.");
		roomMembers.setEditable(false);
		roomMembers.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		participantsBorder.add(roomMembers, BorderLayout.CENTER);
	}

	/**
	 * Initializes the message panel for a specific chat room.
	 * 
	 * @param chatroom The chat room to set up the message panel for.
	 *                 This method will update the panel to display
	 *                 messages, participants, and other relevant information
	 *                 related to the provided chat room.
	 */
	public void setupChatroom(ChatRoom chatroom) {
		
	}

	// public void setCurrentChat(ChatRoom chatroom) {
	// 	this.currentChat = chatroom;
	// }

	public ChatRoom getCurrentChat() {
		return currentChat;
	}
}