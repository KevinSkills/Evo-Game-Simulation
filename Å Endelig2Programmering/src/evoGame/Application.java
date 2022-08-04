package evoGame;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import engine.GameCanvas;

public class Application extends JFrame {

	private JPanel contentPane;
	private JTextField speedVar;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
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
	public Application() {
		
		//Start of Program -- Runs on eventQueue Thread
		
		//configure game ( For now, make in gui later)
		
		//Conf 1 - Speed and radius cost a little bit and 100 food
		
		Config sc1 = new Config();
		sc1.blobCount = 50;
		sc1.foodCount = 100;
		
		sc1.foodToReproduce = 3;
		sc1.foodToSurvive = 2;
		sc1.updateDirectionDelay = 5;
		sc1.startEnergy = 100;
		sc1.senseRadiusCost = 1;
		sc1.speedCost = 1;
		
		Genes startGene = new Genes();
		
		startGene.senseRadius = 5;
		startGene.speed = 20;
		startGene.energyUsage = 1;
		startGene.radius = 1;
		
		sc1.startGene = startGene;
		

		//Conf 2 - Speed actually gives energy, population bigger to start with. Radius is expensive but big to start with.
		
		Config sc2 = new Config();
		sc2.blobCount = 70;
		sc2.foodCount = 100;
		
		sc2.foodToReproduce = 4;
		sc2.foodToSurvive = 4;
		sc2.updateDirectionDelay = 5;
		sc2.startEnergy = 100;
		sc2.senseRadiusCost = 5;
		sc2.speedCost = -1;
		
		startGene = new Genes();
		
		startGene.senseRadius = 10;
		startGene.speed = 20;
		startGene.energyUsage = 1;
		startGene.radius = 1;
		
		sc2.startGene = startGene;
		
		//Conf 3 - Too much food
		
		Config sc3 = new Config();
		sc3.blobCount = 50;
		sc3.foodCount = 300;
		
		sc3.foodToReproduce = 2;
		sc3.foodToSurvive = 1;
		sc3.updateDirectionDelay = 5;
		sc3.startEnergy = 60;
		sc3.senseRadiusCost = 1;
		sc3.speedCost = 1;
		
		startGene = new Genes();
		
		startGene.senseRadius = 6;
		startGene.speed = 15;
		startGene.energyUsage = 1;
		startGene.radius = 1;
		
		sc3.startGene = startGene;
		
		
		
		
		//start game with configuration, users job is done now
		EvoGame game = new EvoGame(sc1);
		new Thread(game).start(); //Game runs on seperate Thread, allows for time control and multiple games

		
		
		//------UI Stuff---------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//make the GameCanvas that renders the game
		GameCanvas panel = new GameCanvas(game, 120);
		panel.setBackground(Color.CYAN);
		panel.setBounds(274, 10, 600, 600);
		contentPane.add(panel);
		
		
		//now other UI stuff
		speedVar = new JTextField();
		speedVar.setText("1");
		speedVar.setBounds(20, 93, 189, 19);
		contentPane.add(speedVar);
		speedVar.setColumns(10);
		
		JButton btnNewButton = new JButton("Set speed");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setSpeed(Float.parseFloat(speedVar.getText()));
			}
		});
		btnNewButton.setBounds(20, 112, 189, 21);
		contentPane.add(btnNewButton);
		
		JLabel alive = new JLabel("");
		alive.setBounds(32, 358, 148, 13);
		contentPane.add(alive);
		
		btnNewButton_1 = new JButton("Start Round");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.manager.nextRound = true;
			}
		});
		
		
		btnNewButton_1.setBounds(22, 249, 187, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Alive:");
		lblNewLabel.setBounds(22, 334, 187, 13);
		contentPane.add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Toggle Automatic");
		chckbxNewCheckBox.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
               boolean b = e.getStateChange()==1; 
               game.manager.automaticNewRound = b;
            }    
         });  
		
		chckbxNewCheckBox.setBounds(20, 506, 187, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnNewButton_2 = new JButton("Show Over timePlots");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EvoPlots plots = game.manager.getPlots();
				
				EvoPlots.showPlot("Average speed over time", plots.avgSpeed, "Gen", "Average Speed");
				EvoPlots.showPlot("Average senseRadius over time", plots.avgSense, "Gen", "Average Sense");
				EvoPlots.showPlot("Population over time", plots.populationOverTime, "Gen", "Population");
				
				

				
				
			}
		});
		btnNewButton_2.setBounds(20, 558, 189, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Show Gen Plots");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EvoPlots plots = game.manager.getPlots();
				EvoPlots.showPlot("Speed for creatures this generation", plots.speedForCurrentGen, "Creature Number", "Speed");
				EvoPlots.showPlot("Sense for creatures this generation", plots.senseForCurrentGen, "Creature Number", "Speed");
			}
		});
		btnNewButton_3.setBounds(20, 589, 189, 21);
		contentPane.add(btnNewButton_3);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Show Sense Radius");
		chckbxNewCheckBox_1.setSelected(true);
		chckbxNewCheckBox_1.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
               boolean b = e.getStateChange()==1; 
               game.manager.showSenseRadius = b;
            }    
         }); 
		chckbxNewCheckBox_1.setBounds(20, 483, 160, 21);
		contentPane.add(chckbxNewCheckBox_1);
		
		
		
		
		
		ActionListener updateTask = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alive.setText("" + game.manager.blobsAlive.size());
			}
		};
		Timer timer = new Timer(300, updateTask);
		timer.start();
		
		

        

        
		
		
		
		
	}
}
