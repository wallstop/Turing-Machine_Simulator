/* @Description: Driver class for the provided Turing Machine examples 
 * @Author: Eli Pinkerton
 */

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuringMachineDriver implements ActionListener{
	
	static TuringMachine currentTuringMachine;	
	
	public static void main(String [] args)
	{
		initGUI();
	}

	
	public static void initGUI()
	{
		TuringMachineDriver actionListener = new TuringMachineDriver();
		JFrame container;
		
		JButton turingMachine1;
		JButton turingMachine2;
		JButton turingMachine3;
		JButton exitButton;
		container = new JFrame();

		turingMachine1 = new JButton("Machine 1");
		turingMachine1.setSelected(true);
		turingMachine1.addActionListener(actionListener);
		turingMachine2 = new JButton("Machine 2");
		turingMachine2.addActionListener(actionListener);
		turingMachine3 = new JButton("Machine 3");
		turingMachine3.addActionListener(actionListener);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(actionListener);
		

		container.setLayout(new FlowLayout());
		container.add(turingMachine1);
		container.add(turingMachine2);
		container.add(turingMachine3);
		container.add(exitButton);
		
		container.setVisible(true);
		container.setSize(300, 200);
		container.setLocation(500,500);
		container.setDefaultCloseOperation(3);
		container.setResizable(false);
		
		
	}
	
	public void actionPerformed(ActionEvent someEvent)
	{
	    String inputString;	    
		
		switch(someEvent.getActionCommand())
		{
		case "Machine 1":
			inputString = JOptionPane.showInputDialog("Please input a string for this machine: "); 
			currentTuringMachine = new TuringMachine1(inputString);
			break;
		case "Machine 2":
			inputString = JOptionPane.showInputDialog("Please input a string for this machine: "); 
			currentTuringMachine = new TuringMachine2(inputString);
			break;
		case "Machine 3":
			inputString = JOptionPane.showInputDialog("Please input a string for this machine: "); 
			currentTuringMachine = new TuringMachine3(inputString);
			break;
		default:
			System.exit(1);
			break;		
		}
		
		runMachine();
		
	}
	
	public static void runMachine()
	{
		if(currentTuringMachine.run())
			JOptionPane.showMessageDialog(null, "The machine accepts the string!", "Sucess", JOptionPane.QUESTION_MESSAGE); 
		else
			JOptionPane.showMessageDialog(null, "The machine does not accept the string!", "Failure", JOptionPane.ERROR_MESSAGE); 
	}

}
