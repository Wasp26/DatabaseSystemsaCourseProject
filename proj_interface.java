import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class proj_interface {
	 public static Connection con;
	 PreparedStatement ps= null;
	 ResultSetMetaData rsmd=null;
	 int apps;
	    public static Statement stmt;
	  JLayeredPane layeredPane=null;
	  ResultSet rs=null;
	  String tag;
	  int pid=30;
	private JFrame frmFootballDatabase;
	private JTextField player_name;
	private JTextField userf;
	private JTextField pwdf;
	private JTextField namefield;
	private JTextField posfield;
	private JTextField agefield;
	private JTextField foot;
	private JTextField dobfield;
	private JTextField country;
	private JTextField appearances;
	private JTextField goals;
	private JTextField assists;
	private JTextField key_passes;
	private JTextField shots;
	private JTextField dribbles;
	private JTextField offsides;
	private JTextField tackles;
	private JTextField interceptions;
	private JTextField clearances;
	private JTextField blocks;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField yel1;
	private JTextField re1;
	private JTextField fl1;
	private JTextField pname;
	private JTextField cname;
	private JTextField pl1;
	private JTextField pl2;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try
		{Class.forName("oracle.jdbc.OracleDriver");
		con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","\"FLampard@08\"");
		stmt= con.createStatement();
		}
		
		catch(Exception e)
		{e.printStackTrace();}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					proj_interface window = new proj_interface();
					window.frmFootballDatabase.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	public void switchPanel(JPanel panel)
	{ layeredPane.removeAll();
	  layeredPane.add(panel);
	  layeredPane.repaint();
	  layeredPane.revalidate();
	  
	}
	/**
	 * Create the application.
	 */
	public proj_interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFootballDatabase = new JFrame();
		frmFootballDatabase.setTitle("Football Database");
		frmFootballDatabase.setBounds(100, 100, 686, 450);
		frmFootballDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFootballDatabase.getContentPane().setLayout(null);
		
		
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 37, 650, 363);
		frmFootballDatabase.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel search_panel = new JPanel();
		layeredPane.add(search_panel, "name_2103480430336");
		search_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Player Name");
		lblNewLabel.setBounds(10, 60, 111, 28);
		search_panel.add(lblNewLabel);
		
		player_name = new JTextField();
		player_name.setBounds(10, 98, 111, 20);
		search_panel.add(player_name);
		player_name.setColumns(10);
		
		JTextArea reslabel = new JTextArea();
		reslabel.setBounds(131, 11, 220, 107);
		search_panel.add(reslabel);
		
		JTextArea reslabel2 = new JTextArea();
		reslabel2.setBounds(131, 125, 220, 130);
		search_panel.add(reslabel2);
		
		JTextArea reslabel3 = new JTextArea();
		reslabel3.setBounds(131, 260, 220, 92);
		search_panel.add(reslabel3);
		
		JTextArea reslabel4 = new JTextArea();
		reslabel4.setBounds(361, 11, 279, 244);
		search_panel.add(reslabel4);
		
		JTextArea reslabel5 = new JTextArea();
		reslabel5.setBounds(361, 260, 279, 92);
		search_panel.add(reslabel5);
		
		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n= player_name.getText();
				
				
				String q1= "select * from Player where name = ?";
				int x=0;
				try
				{ ps= con.prepareStatement(q1);
				  ps.setString(1,n);
				  rs=ps.executeQuery();
				  while(rs.next())
				  x= rs.getInt(1);
				  rs=ps.executeQuery();
				  while(rs.next())
				  { String res="Name: "+rs.getString(2)+"\n"+"Position: "+rs.getString(3)+"\n"+
						   "Age: "+rs.getInt(4)+"\n"+"DOB: "+rs.getDate(6)+"\n"+"Country: "+rs.getString(7);
				   reslabel.setText(res);
				  }
				  reslabel.setEditable(false);
				}
				catch(Exception r)
				{r.printStackTrace();}
				String q2="select * from off_stats where pid=? ";
				String q3="select * from def_stats where pid=?";
				String q4= "select * from discipline where pid=?";
				String q5= "select * from player_hons where pid=?";
				String q6= "select club.name,year_j,year_l from player,club,played_for "
						+ "where player.pid=played_for.pid and club.cid=played_for.cid and player.pid=?";
				try
				{ ps=con.prepareStatement(q2);
				  ps.setInt(1,x);
				  rs=ps.executeQuery();
				  while(rs.next())
				  { reslabel2.setText("Appearances: "+rs.getInt(2)+"\n"+"Goals: "+rs.getInt(3)+"\n"+
						  "Assists: "+rs.getInt(4)+"\n"+"Shots: "+rs.getInt(5)+"\n"+"Key Passes: "+rs.getInt(6)+"\n"+
						  "Dribbles: "+rs.getInt(7)+"\n"+"Offsides: "+rs.getInt(8));
					  
				  }
				  reslabel2.setEditable(false);
				  ps=con.prepareStatement(q3);
				  ps.setInt(1, x);
				  rs=ps.executeQuery();
				  while(rs.next())
				  { reslabel2.setText("Appearances: "+rs.getInt(2)+"\n"+"Tackles: "+rs.getInt(3)+"\n"+
				  "Interceptions: "+rs.getInt(4)+"\n"+"Clearances: "+rs.getInt(5)+"\n"+"Blocks"+rs.getInt(6)+"\n"); 
					  
				  }
				  reslabel2.setEditable(false);
				  ps=con.prepareStatement(q4);
				  ps.setInt(1, x);
				  rs=ps.executeQuery();
				  while(rs.next())
				  { reslabel3.setText("Yellow Cards: "+rs.getInt(3)+"\n"+"Red Cards"+rs.getInt(4)
				  +"\n"+"Fouls: "+rs.getInt(5));
					  
				  }
				  reslabel3.setEditable(false);
				  ps=con.prepareStatement(q6);
				  ps.setInt(1,x);
				  rs=ps.executeQuery();
				  String ry="Played for     \tPlayed from\tPlayed Until\n";
				  while(rs.next())
				  { ry+= rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\n";}
				  reslabel5.setText(ry+"\n0 indicates he hasn't left the club yet");
				  reslabel5.setEditable(false);
				  ps=con.prepareStatement(q5);
				  ps.setInt(1,x);
				  rs=ps.executeQuery();
				  ry="Honour achieved\tYear achieved\n";
				  while(rs.next())
				  { ry+=rs.getString(2)+"\t\t"+rs.getInt(3)+"\n";
				  }
				  reslabel4.setText(ry);
				  reslabel4.setEditable(false);
				}
				catch(Exception r)
				{r.printStackTrace();}
				
			}
		});
		btnGetDetails.setBounds(10, 146, 111, 23);
		search_panel.add(btnGetDetails);
		
		
		JPanel offrank = new JPanel();
		layeredPane.add(offrank, "name_6918186208388");
		offrank.setLayout(null);
		
		JTextArea offrankarea = new JTextArea();
		offrankarea.setBounds(10, 74, 630, 278);
		offrank.add(offrankarea);
		
		
		JButton btnNewButton_2 = new JButton("Goals");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q1="Select Name,Goals,Apps,Goals/apps from player,off_stats where player.pid=off_stats.pid order by goals/apps desc";
				String res="   Name"+"\t"+"Goals"+"\t"+"Apps"+"\t"+"Goals per app"+"\n";
				try
				{ ps=con.prepareStatement(q1);
				  rs=ps.executeQuery();
				  int i=0;
				  while(rs.next()&&i<10)
				  { res+= i+") "+rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getFloat(4)+"\n";
				    i++;
				  }
				}
				catch(Exception f)
				{f.printStackTrace();}
				
				offrankarea.setText(res);
			}
		});
		btnNewButton_2.setBounds(27, 11, 89, 23);
		offrank.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Assists");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q1="Select Name,Assists,Apps,Assists/apps from player,off_stats where player.pid=off_stats.pid order by assists/apps desc";
				String res="   Name"+"\t"+"Assists"+"\t"+"Apps"+"\t"+"Assists per app"+"\n";
				try
				{ ps=con.prepareStatement(q1);
				  rs=ps.executeQuery();
				  int i=0;
				  while(rs.next()&&i<10)
				  { res+= i+") "+rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getFloat(4)+"\n";
				    i++;
				  }
				}
				catch(Exception f)
				{f.printStackTrace();}
				
				offrankarea.setText(res);
			}
		});
		btnNewButton_3.setBounds(147, 11, 89, 23);
		offrank.add(btnNewButton_3);
		
		JButton btnKeyPasses = new JButton("Key Passes");
		btnKeyPasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q1="Select Name,key_passes,Apps,key_passes/apps from player,off_stats where player.pid=off_stats.pid order by key_passes/apps desc";
				String res="   Name"+"\t"+"Key Passes"+"\t"+"Apps"+"\t"+"Key Passes per app"+"\n";
				try
				{ ps=con.prepareStatement(q1);
				  rs=ps.executeQuery();
				  int i=0;
				  while(rs.next()&&i<10)
				  { res+= i+") "+rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getFloat(4)+"\n";
				    i++;
				  }
				}
				catch(Exception f)
				{f.printStackTrace();}
				
				offrankarea.setText(res);
			}
		});
		btnKeyPasses.setBounds(266, 11, 89, 23);
		offrank.add(btnKeyPasses);
		
		JButton btnDribbles = new JButton("Dribbles");
		btnDribbles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String q1="Select Name,Dribbles,Apps,dribbles/apps from player,off_stats where player.pid=off_stats.pid order by dribbles/apps desc";
					String res="   Name"+"\t"+"dribbles"+"\t"+"Apps"+"\t"+"dribbles per app"+"\n";
					try
					{ ps=con.prepareStatement(q1);
					  rs=ps.executeQuery();
					  int i=0;
					  while(rs.next()&&i<10)
					  { res+= i+") "+rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getFloat(4)+"\n";
					    i++;
					  }
					}
					catch(Exception f)
					{f.printStackTrace();}
					
					offrankarea.setText(res);
			}
		});
		btnDribbles.setBounds(499, 11, 89, 23);
		offrank.add(btnDribbles);
		
		JButton btnNewButton_4 = new JButton("Tackles");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q1="Select Name,tackles,Apps,tackles/apps from player,def_stats where player.pid=def_stats.pid order by tackles/apps desc";
				String res="   Name"+"\t"+"tackles"+"\t"+"Apps"+"\t"+"tackles per app"+"\n";
				try
				{ ps=con.prepareStatement(q1);
				  rs=ps.executeQuery();
				  int i=0;
				  while(rs.next()&&i<10)
				  { res+= i+") "+rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getFloat(4)+"\n";
				    i++;
				  }
				}
				catch(Exception f)
				{f.printStackTrace();}
				
				offrankarea.setText(res);
			}
		});
		btnNewButton_4.setBounds(384, 11, 89, 23);
		offrank.add(btnNewButton_4);
		
		JButton btnInterceptions = new JButton("Interceptions");
		btnInterceptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q1="Select Name,Interceptions,Apps,Interceptions/apps from player,def_stats where player.pid=def_stats.pid order by Interceptions/apps desc";
				String res="   Name"+"\t"+"Interceptions"+"\t"+"Apps"+"\t"+"Interceptions per app"+"\n";
				try
				{ ps=con.prepareStatement(q1);
				  rs=ps.executeQuery();
				  int i=0;
				  while(rs.next()&&i<10)
				  { res+= i+") "+rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getFloat(4)+"\n";
				    i++;
				  }
				}
				catch(Exception f)
				{f.printStackTrace();}
				
				offrankarea.setText(res);

			}
		});
		btnInterceptions.setBounds(27, 45, 104, 23);
		offrank.add(btnInterceptions);
		
		JPanel Add = new JPanel();
		layeredPane.add(Add, "name_16593719744407");
		Add.setLayout(null);
		
		JPanel add_player = new JPanel();
		layeredPane.add(add_player, "name_20445696814648");
		add_player.setLayout(null);
		
		JLabel lblPersonalDetails = new JLabel("Personal Details");
		lblPersonalDetails.setBounds(39, 21, 113, 14);
		add_player.add(lblPersonalDetails);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(10, 58, 46, 14);
		add_player.add(lblNewLabel_2);
		
		JLabel lblAge = new JLabel("Position");
		lblAge.setBounds(10, 90, 46, 14);
		add_player.add(lblAge);
		
		JLabel lblAge_1 = new JLabel("Age");
		lblAge_1.setBounds(10, 128, 46, 14);
		add_player.add(lblAge_1);
		
		JLabel lblFoot = new JLabel("Foot");
		lblFoot.setBounds(10, 165, 46, 14);
		add_player.add(lblFoot);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(10, 213, 46, 14);
		add_player.add(lblDob);
		
		namefield = new JTextField();
		namefield.setBounds(66, 55, 86, 20);
		add_player.add(namefield);
		namefield.setColumns(10);
		
		posfield = new JTextField();
		posfield.setBounds(66, 87, 86, 20);
		posfield.setColumns(10);
		add_player.add(posfield);
		
		agefield = new JTextField();
		agefield.setBounds(66, 125, 86, 20);
		agefield.setColumns(10);
		add_player.add(agefield);
		
		foot = new JTextField();
		foot.setBounds(66, 162, 86, 20);
		foot.setColumns(10);
		add_player.add(foot);
		
		dobfield = new JTextField();
		dobfield.setBounds(66, 210, 86, 20);
		dobfield.setColumns(10);
		add_player.add(dobfield);
		
		JLabel lblNewLabel_3 = new JLabel("DD-MON_YYYY");
		lblNewLabel_3.setBounds(66, 241, 86, 14);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		add_player.add(lblNewLabel_3);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(10, 269, 46, 14);
		add_player.add(lblCountry);
		
		country = new JTextField();
		country.setBounds(66, 266, 86, 20);
		country.setColumns(10);
		add_player.add(country);
		
		JLabel lblAppearances = new JLabel("Appearances");
		lblAppearances.setBounds(10, 307, 69, 14);
		add_player.add(lblAppearances);
		
		appearances = new JTextField();
		appearances.setBounds(66, 319, 86, 20);
		add_player.add(appearances);
		appearances.setColumns(10);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(193, 21, 447, 331);
		add_player.add(layeredPane_1);
		layeredPane_1.setLayout(new CardLayout(0, 0));
		
		JPanel selectplayertype = new JPanel();
		layeredPane_1.add(selectplayertype, "name_92250335892957");
		selectplayertype.setLayout(null);
		
		JPanel defaddpanel = new JPanel();
		layeredPane_1.add(defaddpanel, "name_91815037030911");
		defaddpanel.setLayout(null);
		
		JButton btnNewButton_5 = new JButton("Defensive Player");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			switchPanel(defaddpanel);
			tag="def";
			}
		});
		btnNewButton_5.setBounds(162, 94, 141, 23);
		selectplayertype.add(btnNewButton_5);
		
		JPanel offaddpanel = new JPanel();
		layeredPane_1.add(offaddpanel, "name_91794315130137");
		offaddpanel.setLayout(null);
		
		JButton btnOffensivePlayer = new JButton("Offensive Player");
		btnOffensivePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(offaddpanel);
				tag="off";
			}
		});
		btnOffensivePlayer.setBounds(162, 171, 141, 23);
		selectplayertype.add(btnOffensivePlayer);
		
		
		
		JLabel lblOffenseStats = new JLabel("Offense stats");
		lblOffenseStats.setBounds(103, 11, 198, 14);
		offaddpanel.add(lblOffenseStats);
		
		JLabel lblNewLabel_6 = new JLabel("Goals");
		lblNewLabel_6.setBounds(31, 62, 26, 14);
		offaddpanel.add(lblNewLabel_6);
		
		JLabel lblAssists = new JLabel("Assists");
		lblAssists.setBounds(31, 87, 33, 14);
		offaddpanel.add(lblAssists);
		
		JLabel lblShots = new JLabel("Shots");
		lblShots.setBounds(31, 128, 27, 14);
		offaddpanel.add(lblShots);
		
		JLabel lblKeyPasses = new JLabel("Key Passes");
		lblKeyPasses.setBounds(31, 163, 66, 14);
		offaddpanel.add(lblKeyPasses);
		
		JLabel lblDribbles = new JLabel("Dribbles");
		lblDribbles.setBounds(31, 200, 38, 14);
		offaddpanel.add(lblDribbles);
		
		JLabel lblOffsides = new JLabel("Offsides");
		lblOffsides.setBounds(31, 242, 40, 14);
		offaddpanel.add(lblOffsides);
		
		goals = new JTextField();
		goals.setBounds(99, 59, 86, 20);
		offaddpanel.add(goals);
		goals.setColumns(10);
		
		assists = new JTextField();
		assists.setColumns(10);
		assists.setBounds(99, 87, 86, 20);
		offaddpanel.add(assists);
		
		key_passes = new JTextField();
		key_passes.setColumns(10);
		key_passes.setBounds(99, 160, 86, 20);
		offaddpanel.add(key_passes);
		
		shots = new JTextField();
		shots.setColumns(10);
		shots.setBounds(99, 125, 86, 20);
		offaddpanel.add(shots);
		
		dribbles = new JTextField();
		dribbles.setColumns(10);
		dribbles.setBounds(103, 197, 86, 20);
		offaddpanel.add(dribbles);
		
		offsides = new JTextField();
		offsides.setColumns(10);
		offsides.setBounds(99, 239, 86, 20);
		offaddpanel.add(offsides);
		
		JLabel label_1 = new JLabel("Yellows");
		label_1.setBounds(255, 62, 46, 14);
		offaddpanel.add(label_1);
		
		yel1 = new JTextField();
		yel1.setColumns(10);
		yel1.setBounds(323, 59, 86, 20);
		offaddpanel.add(yel1);
		
		JLabel label_4 = new JLabel("Reds");
		label_4.setBounds(255, 102, 46, 14);
		offaddpanel.add(label_4);
		
		re1 = new JTextField();
		re1.setColumns(10);
		re1.setBounds(323, 99, 86, 20);
		offaddpanel.add(re1);
		
		JLabel label_5 = new JLabel("Fouls");
		label_5.setBounds(255, 151, 46, 14);
		offaddpanel.add(label_5);
		
		fl1 = new JTextField();
		fl1.setColumns(10);
		fl1.setBounds(323, 145, 86, 20);
		offaddpanel.add(fl1);
		
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setBounds(305, 267, 89, 23);
		offaddpanel.add(btnAddPlayer);
		btnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  String n=namefield.getText();
			  String pos=posfield.getText();
			  int ag=Integer.parseInt(agefield.getText());
			  String ft= foot.getText();
			  String db= dobfield.getText();
			  String cnt=country.getText();
			  apps=Integer.parseInt(appearances.getText());
			  int yells=Integer.parseInt(yel1.getText());
			  int rds=Integer.parseInt(re1.getText());
			  int fls=Integer.parseInt(fl1.getText());
			  pid++;
			  String quer= "insert into player values(?,?,?,?,?,?,?)";
			  String quer2="insert into discipline values(?,?,?,?,?)";
			  String quer3;
			  try
				{ps=con.prepareStatement(quer);
				 ps.setInt(1,pid);
				 ps.setString(2,n);
				 ps.setString(3,pos);
				 ps.setInt(4, ag);
				 ps.setString(5,ft);
				 ps.setString(6,db);
				 ps.setString(7,cnt);
				 rs=ps.executeQuery();
				 ps=con.prepareStatement(quer2);
				 ps.setInt(1, pid);
				 ps.setInt(2,apps);
				 ps.setInt(3, yells);
				 ps.setInt(4, rds);
				 ps.setInt(5, fls);
				 rs=ps.executeQuery();
				}
				catch(Exception rt)
				{}
			  int l,m,x,o,p,q;
			   l= Integer.parseInt(goals.getText());
				m=  Integer.parseInt(assists.getText());
				x= Integer.parseInt(shots.getText());
				o=Integer.parseInt(key_passes.getText());
				p=Integer.parseInt(dribbles.getText());
				q=Integer.parseInt(offsides.getText());
				
				
				quer3 ="insert into off_stats values(?,?,?,?,?,?,?,?)";
				try
				{ ps=con.prepareStatement(quer3);
				  ps.setInt(1,pid);
				  ps.setInt(2,apps);
				  ps.setInt(3, l);
				  ps.setInt(4,m);
				  ps.setInt(5, x);
				  ps.setInt(6, o);
				  ps.setInt(7,p);
				  ps.setInt(8, q);
				  rs=ps.executeQuery();
				}
				catch(Exception er)
				{}
				
			  
			  
			  
			}
		});
		
		
		
		JLabel lblDefenseStats = new JLabel("Defense stats");
		lblDefenseStats.setBounds(189, 11, 87, 14);
		defaddpanel.add(lblDefenseStats);
		
		JLabel lblNewLabel_7 = new JLabel("Tackles");
		lblNewLabel_7.setBounds(23, 57, 46, 14);
		defaddpanel.add(lblNewLabel_7);
		
		JLabel lblInterceptions = new JLabel("Interceptions");
		lblInterceptions.setBounds(23, 96, 79, 14);
		defaddpanel.add(lblInterceptions);
		
		JLabel lblClearances = new JLabel("Clearances");
		lblClearances.setBounds(23, 145, 79, 14);
		defaddpanel.add(lblClearances);
		
		JLabel lblBlocks = new JLabel("Blocks");
		lblBlocks.setBounds(23, 195, 46, 14);
		defaddpanel.add(lblBlocks);
		
		tackles = new JTextField();
		tackles.setBounds(129, 54, 86, 20);
		defaddpanel.add(tackles);
		tackles.setColumns(10);
		
		interceptions = new JTextField();
		interceptions.setColumns(10);
		interceptions.setBounds(129, 93, 86, 20);
		defaddpanel.add(interceptions);
		
		clearances = new JTextField();
		clearances.setColumns(10);
		clearances.setBounds(129, 142, 86, 20);
		defaddpanel.add(clearances);
		
		blocks = new JTextField();
		blocks.setColumns(10);
		blocks.setBounds(129, 192, 86, 20);
		defaddpanel.add(blocks);
		
		JLabel label = new JLabel("Yellows");
		label.setBounds(270, 57, 46, 14);
		defaddpanel.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(326, 54, 86, 20);
		defaddpanel.add(textField);
		
		JLabel label_2 = new JLabel("Reds");
		label_2.setBounds(270, 96, 46, 14);
		defaddpanel.add(label_2);
		
		JLabel label_3 = new JLabel("Fouls");
		label_3.setBounds(270, 145, 46, 14);
		defaddpanel.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(326, 93, 86, 20);
		defaddpanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(326, 142, 86, 20);
		defaddpanel.add(textField_2);
		
		JButton button = new JButton("Add Player");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n=namefield.getText();
				  String pos=posfield.getText();
				  int ag=Integer.parseInt(agefield.getText());
				  String ft= foot.getText();
				  String db= dobfield.getText();
				  String cnt=country.getText();
				  apps=Integer.parseInt(appearances.getText());
				  int yells=Integer.parseInt(yel1.getText());
				  int rds=Integer.parseInt(re1.getText());
				  int fls=Integer.parseInt(fl1.getText());
				  pid++;
				  String quer= "insert into player values(?,?,?,?,?,?,?)";
				  String quer2="insert into discipline values(?,?,?,?,?)";
				  try
					{ps=con.prepareStatement(quer);
					 ps.setInt(1,pid);
					 ps.setString(2,n);
					 ps.setString(3,pos);
					 ps.setInt(4, ag);
					 ps.setString(5,ft);
					 ps.setString(6,db);
					 ps.setString(7,cnt);
					 rs=ps.executeQuery();
					 ps=con.prepareStatement(quer2);
					 ps.setInt(1, pid);
					 ps.setInt(2,apps);
					 ps.setInt(3, yells);
					 ps.setInt(4, rds);
					 ps.setInt(5, fls);
					 rs=ps.executeQuery();
					}
					catch(Exception rt)
					{}
				int l,m,x,o;
				   l=Integer.parseInt(tackles.getText());
				    m=Integer.parseInt(interceptions.getText());
				    x=Integer.parseInt(clearances.getText());
				    o=Integer.parseInt(blocks.getText());
				    apps=Integer.parseInt(appearances.getText());
				    
				    String quer3= "insert into def_stats values(?,?,?,?,?,?)";
				    try
				    { ps=con.prepareStatement(quer3);
				      ps.setInt(1,pid);
				      ps.setInt(2, apps);
				      ps.setInt(3, l);
				      ps.setInt(4,m);
				      ps.setInt(5,x);
				      ps.setInt(6,o);
				      rs=ps.executeQuery();
				    }
				    catch(Exception ewt)
				    {}
			}
		});
		button.setBounds(336, 297, 89, 23);
		defaddpanel.add(button);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(160, 124, 135, 47);
		Add.add(lblNewLabel_1);
		
		userf = new JTextField();
		userf.setBounds(272, 137, 140, 20);
		Add.add(userf);
		userf.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(160, 168, 135, 47);
		Add.add(lblPassword);
		
		pwdf = new JTextField();
		pwdf.setFont(UIManager.getFont("PasswordField.font"));
		pwdf.setBounds(272, 181, 140, 20);
		Add.add(pwdf);
		pwdf.setColumns(10);
		
		JLabel conflabel = new JLabel("");
		conflabel.setHorizontalAlignment(SwingConstants.CENTER);
		conflabel.setBounds(100, 258, 412, 31);
		Add.add(conflabel);
		
		
		
		JButton login = new JButton("LOGIN");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u=userf.getText();
				String p=pwdf.getText();
				if(u.equals("admin")&&p.equals("pswd"))
				{ switchPanel(add_player);
				}
					else
				{ conflabel.setText("You are not authorized to make changes");
				}
				
			}
		});
		login.setBounds(228, 226, 135, 23);
		Add.add(login);
		
		JPanel companel = new JPanel();
		layeredPane.add(companel, "name_3782046047141");
		companel.setLayout(null);
		
		JLabel lblEnterPlayerName = new JLabel("Enter Player Name");
		lblEnterPlayerName.setBounds(24, 29, 113, 24);
		companel.add(lblEnterPlayerName);
		
		pname = new JTextField();
		pname.setBounds(24, 68, 86, 20);
		companel.add(pname);
		pname.setColumns(10);
		
		JLabel lblCompareAgainst = new JLabel("Compare against");
		lblCompareAgainst.setBounds(24, 105, 113, 24);
		companel.add(lblCompareAgainst);
		
		cname = new JTextField();
		cname.setBounds(24, 140, 86, 20);
		companel.add(cname);
		cname.setColumns(10);
		
		JLabel p1msg = new JLabel("");
		p1msg.setBounds(24, 280, 134, 20);
		companel.add(p1msg);
		
		JLabel p2msg = new JLabel("");
		p2msg.setBounds(24, 318, 126, 24);
		companel.add(p2msg);
		
		JTextArea comparea = new JTextArea();
		comparea.setBounds(160, 11, 480, 341);
		companel.add(comparea);
		
		JButton defcomp = new JButton("Compare Defense");
		defcomp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String p1= pname.getText();
				String p2= cname.getText();
				int pid1=0,pid2=0;
				String cq="select pid from player where name=? and pid in (select pid from def_stats)";
				try
				{ PreparedStatement psc= con.prepareStatement(cq);
				  psc.setString(1,p1);
				  ResultSet rsc1=psc.executeQuery();
				  while(rsc1.next())
				  {pid1=rsc1.getInt(1);}
				  psc= con.prepareStatement(cq);
				  psc.setString(1,p2);
				  rsc1=psc.executeQuery();
				  
				  while(rsc1.next())
				  {pid2=rsc1.getInt(1);}
				  if(pid1==0)
				  {p1msg.setText(p1+" Is not a defensive player!");}
				  if(pid2==0)
				  {p2msg.setText(p2+" Is not a defensive player!");}
				}
				catch(Exception ee)
				{}
				if(pid1!=0&&pid2!=0)
				{ String quer="select round(tackles/apps,2),round(interceptions/apps,2),round(clearances/apps,2),round(blocks/apps,2) from def_stats where pid=?";
				  try
				  {PreparedStatement ps1=con.prepareStatement(quer);
				   ps1.setInt(1, pid1);
				   ResultSet rs1=ps1.executeQuery();
				   PreparedStatement ps2=con.prepareStatement(quer);
				   ps2.setInt(1, pid2);
				   ResultSet rs2=ps2.executeQuery();
				   String res="____ Per Game  \t"+p1+"\t"+p2+"\n\n";
				   rs1.next();
				   rs2.next();
				   float tk1,tk2,it1,it2,cl1,cl2,bl1,bl2;
				   tk1=rs1.getFloat(1);
				   it1=rs1.getFloat(2);
				   cl1=rs1.getFloat(3);
				   bl1=rs1.getFloat(4);
				   tk2=rs2.getFloat(1);
				   it2=rs2.getFloat(2);
				   cl2=rs2.getFloat(3);
				   bl2=rs2.getFloat(4);
				   res+="Tackles        \t"+rs1.getFloat(1)+"\t\t"+rs2.getFloat(1)+"\n";
				   res+="Interceptions  \t"+rs1.getFloat(2)+"\t\t"+rs2.getFloat(2)+"\n";
				   res+="Clearances     \t"+rs1.getFloat(3)+"\t\t"+rs2.getFloat(3)+"\n";
				   res+="Blocks         \t"+rs1.getFloat(4)+"\t\t"+rs2.getFloat(4)+"\n\n\n";
				   
				   if(tk1>tk2)
				   {res=rescomp(p1,p2,"Tackles per game",res);}
				   else
				   {res=rescomp(p2,p1,"Tackles per game",res);}
				   if(it1>it2)
				   {res=rescomp(p1,p2,"Interceptions per game",res);}
				   else
				   {res=rescomp(p2,p1,"Interceptions per game",res);}
				   if(cl1>cl2)
				   {res=rescomp(p1,p2,"Clearances per game",res);}
				   else
				   {res=rescomp(p2,p1,"Clearances per game",res);}
				   if(bl1>bl2)
				   {res=rescomp(p1,p2,"Blocks per game",res);}
				   else
				   {res=rescomp(p2,p1,"Blocks per game",res);}
				   comparea.setText(res);
				  }
				  catch(Exception eee)
				  {}
				  
				}
			}
		});
		defcomp.setBounds(24, 184, 126, 23);
		companel.add(defcomp);
		
		
		JButton offcomp = new JButton("Compare Offense");
		offcomp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String p1= pname.getText();
				String p2= cname.getText();
				int pid1=0,pid2=0;
				String cq="select pid from player where name=? and pid in(Select pid from off_stats)";
				try
				{ PreparedStatement psc= con.prepareStatement(cq);
				  psc.setString(1,p1);
				  ResultSet rsc1=psc.executeQuery();
				  while(rsc1.next())
				  {pid1=rsc1.getInt(1);}
				  psc= con.prepareStatement(cq);
				  psc.setString(1,p2);
				  rsc1=psc.executeQuery();
				  while(rsc1.next())
				  {pid2=rsc1.getInt(1);}
				  if(pid1==0)
				  {p1msg.setText(p1+" Is not an offensive player!");}
				  if(pid2==0)
				  {p2msg.setText(p2+" Is not an offensive player!");}
				}
				catch(Exception ee)
				{}
				if(pid1!=0&&pid2!=0)
				{ String quer="select round(goals/apps,2),round(assists/apps,2),round(shots/apps,2),round(key_passes/apps,2),round(dribbles/apps,2),round(offsides/apps,2) from off_stats where pid=?";
				  try
				  {PreparedStatement ps1=con.prepareStatement(quer);
				   ps1.setInt(1, pid1);
				   ResultSet rs1=ps1.executeQuery();
				   PreparedStatement ps2=con.prepareStatement(quer);
				   ps2.setInt(1, pid2);
				   ResultSet rs2=ps2.executeQuery();
				   float g1,g2,a1,a2,s1,s2,kp1,kp2,dr1,dr2,of1,of2;
				   String res= "___ Per Game   \t"+p1+"\t\t"+p2+"\n\n";
				   rs1.next();
				   rs2.next();
				   g1=rs1.getFloat(1);
				   a1=rs1.getFloat(2);
				   s1=rs1.getFloat(3);
				   kp1=rs1.getFloat(4);
				   dr1=rs1.getFloat(5);
				   of1=rs1.getFloat(6);
				   g2=rs2.getFloat(1);
				   a2=rs2.getFloat(2);
				   s2=rs2.getFloat(3);
				   kp2=rs2.getFloat(4);
				   dr2=rs2.getFloat(5);
				   of2=rs2.getFloat(6);
				   res+="Goals          \t"+rs1.getFloat(1)+"\t\t"+rs2.getFloat(1)+"\n";
				   res+="Assists        \t"+rs1.getFloat(2)+"\t\t"+rs2.getFloat(2)+"\n";
			       res+="Shots          \t"+rs1.getFloat(3)+"\t\t"+rs2.getFloat(3)+"\n";
				   res+="Key Passes     \t"+rs1.getFloat(4)+"\t\t"+rs2.getFloat(4)+"\n";
				   res+="Dribbles       \t"+rs1.getFloat(5)+"\t\t"+rs2.getFloat(5)+"\n";
				   res+="Offsides       \t"+rs1.getFloat(6)+"\t\t"+rs2.getFloat(6)+"\n";
				   
				   if(g1>g2)
				   {res=rescomp(p1,p2,"Goals",res);}
				   if(g1==g2)
				   {res+=p1+" and "+p2+" have the same amount of Goals per game\n";}
				   else
				   {res=rescomp(p2,p1,"Goals",res);}
				   if(a1>a2)
				   {res=rescomp(p1,p2,"Assists",res);}
				   if(a1==a2)
				   {res+=p1+" and "+p2+" have the same amount of Assists per game\n";}
				   else
				   {res=rescomp(p2,p1,"Assists",res);}
				   if(s1>s2)
				   {res=rescomp(p1,p2,"Shots",res);}
				   if(s1==s2)
				   {res+=p1+" and "+p2+" have the same amount of Shots per game\n";}
				   else
				   {res=rescomp(p2,p1,"Shots",res);}
				   if(kp1>kp2)
				   {res=rescomp(p1,p2,"Key Passes",res);}
				   if(kp1==kp2)
				   {res+=p1+" and "+p2+" have the same amount of Key Passes per game\n";}
				   else
				   {res=rescomp(p2,p1,"Key Passes",res);}
				   if(dr1>dr2)
				   {res=rescomp(p1,p2,"Dribbles",res);}
				   if(dr1==dr2)
				   {res+=p1+" and "+p2+" have the same amount of Dribbles per game\n";}
				   else
				   {res=rescomp(p2,p1,"Dribbles",res);}
				   if(of1>of2)
				   {res=rescomp(p1,p2,"Offsides",res);}
				   if(of1==of2)
				   {res+=p1+" and "+p2+" have the same amount of Offsides per game\n";}
				   else
				   {res=rescomp(p2,p1,"Offsides",res);}
				   comparea.setText(res); 
				  }
				  catch(Exception eee)
				  {}
				}
			}
		});
		offcomp.setBounds(24, 218, 126, 24);
		companel.add(offcomp);
		
		JPanel comptpanel = new JPanel();
		layeredPane.add(comptpanel, "name_5410523396432");
		comptpanel.setLayout(null);
		
		JTextArea pl1titarea = new JTextArea();
		pl1titarea.setBounds(10, 45, 324, 254);
		comptpanel.add(pl1titarea);
		
		JTextArea pl2titarea = new JTextArea();
		pl2titarea.setBounds(344, 45, 296, 254);
		comptpanel.add(pl2titarea);
		
		JLabel lblNewLabel_4 = new JLabel("Enter player name");
		lblNewLabel_4.setBounds(10, 11, 102, 26);
		comptpanel.add(lblNewLabel_4);
		
		pl1 = new JTextField();
		pl1.setBounds(121, 14, 86, 20);
		comptpanel.add(pl1);
		pl1.setColumns(10);
		
		JButton btnNewButton_7 = new JButton("Compare");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String p1= pl1.getText();
				String p2=pl2.getText();
				
			}
		});
		btnNewButton_7.setBounds(276, 13, 89, 23);
		comptpanel.add(btnNewButton_7);
		
		JTextArea verdictar = new JTextArea();
		verdictar.setBounds(10, 303, 630, 49);
		comptpanel.add(verdictar);
		
		JLabel lblEnterPlayerName_1 = new JLabel("Enter Player Name");
		lblEnterPlayerName_1.setBounds(398, 17, 102, 14);
		comptpanel.add(lblEnterPlayerName_1);
		
		pl2 = new JTextField();
		pl2.setBounds(510, 14, 86, 20);
		comptpanel.add(pl2);
		pl2.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("Player Search");
		btnNewButton.setBounds(28, 11, 129, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(search_panel);
			}
		});
		frmFootballDatabase.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Rank");
		btnNewButton_1.setBounds(167, 11, 114, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(offrank);
			}
		});
		frmFootballDatabase.getContentPane().add(btnNewButton_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(291, 11, 114, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(Add);
			}
		});
		frmFootballDatabase.getContentPane().add(btnAdd);
		
		JButton btnCompare = new JButton("Compare Stats");
		btnCompare.setHorizontalAlignment(SwingConstants.LEFT);
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(companel);
			}
		});
		btnCompare.setBounds(428, 11, 108, 23);
		frmFootballDatabase.getContentPane().add(btnCompare);
		
		JButton btnNewButton_6 = new JButton("Compare titles");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanel(comptpanel);
			}
		});
		btnNewButton_6.setBounds(546, 11, 114, 23);
		frmFootballDatabase.getContentPane().add(btnNewButton_6);
	}
	String rescomp(String p1,String p2, String attr,String res)
	{res+=p1+" has "+" more "+attr+" per game than "+p2+"\n";
	 return res;}
}
