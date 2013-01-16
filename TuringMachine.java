import javax.swing.JOptionPane;

/* @Description: This class describes a TuringMachine. Individual Turing Machines that you wish to implement must extend this class and provide their own initStates() and initTransitions() method contents.
 * 		Turing Machines consist of a tape, a collection of states, and the knowledge of the state that it is currently in.
 */
public abstract class TuringMachine {
	
	protected TapeMemory tape;
	protected State [] stateCollection;
	
	protected State currentState;
	
	//Variables used for readability-sake in instanced Turing Machine code
	protected int qaccept;
	protected int qreject;
	//In correspondence with Transition constructors
	protected boolean moveRight = false;
	protected boolean moveLeft = true;
	
	public TuringMachine(String inputString)
	{
		tape = new TapeMemory(inputString);
		initStates();
		initTransitions();
	}
	
	/* Some Turing Machines may require additional space at the beginning and/or end of the string for proper operation */
	public TuringMachine(String inputString, int blankSpace)
	{
		tape = new TapeMemory(inputString, blankSpace);
		initStates();
		initTransitions();
	}
	
	public TuringMachine(int blankSpace, String inputString)
	{
		tape = new TapeMemory(blankSpace, inputString);
		initStates();
		initTransitions();
	}
	
	public TuringMachine(int preBlankSpace, String inputString, int postBlankSpace)
	{
		tape = new TapeMemory(preBlankSpace, inputString, postBlankSpace);
		initStates();
		initTransitions();
	}
	
	/* The default run method. Turing Machines start in some initial state and execute transitions based on what is read from the tape until they reach the accept or reject state.
	 * 		In this code, the accept state is second from the end of the collection of states, and the reject state is the last state in the array.
	 * 
	 * @NOTE: OVERRIDE this method if your specific Turing Machine does not start in a state at the beginning of the state array, or does not have the accept/reject state placed as above
	 */
	public boolean run()
	{
		TuringMachineGUI guiRepresentation;		
		int response;
		
	    response = JOptionPane.showConfirmDialog(null,"Do you want verbose output?", "Verbose Output Selection", JOptionPane.YES_NO_OPTION);
	    guiRepresentation = new TuringMachineGUI();
		currentState = stateCollection[0];		
		
		if(response == 0)
		{
			guiRepresentation.setVisible(true);
			guiRepresentation.addLine("State: " + currentState.getName() + ", Memory: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
		}
		
		while(currentState != stateCollection[stateCollection.length - 2] && currentState != stateCollection[stateCollection.length - 1])
		{
			currentState = transition(currentState, tape.read());
			if(response == 0)
				guiRepresentation.addLine("State: " + currentState.getName() + ", Memory: " + tape.getTape().substring(0, tape.getPosition()) + "[" + tape.read() + "]" + tape.getTape().substring(tape.getPosition() + 1, tape.getTape().length()) + "\n");
		}
		
		return currentState == stateCollection[stateCollection.length - 2];		
	}
	
	/* The default transition method. Looks for a transition for some character that the current state has. If there is none, a NullPointerException is thrown, which symbolizes that the state doesn't have a transition for that character, and the machine will immediately reject.
	 * 
	 * @NOTE: OVERRIDE this method if you want some non-typical transition behavior for your Turing Machine. This should not be necessary.
	 */
	public State transition(State currentState, char currentChar)
	{		
		try
		{
			tape.write(currentState.getTransition(currentChar).getWriteChar());
			
			if(currentState.getTransition(currentChar).getMoveLeft())
				tape.moveLeft();
			else
				tape.moveRight();	
			
			return currentState.getTransition(currentChar).getTransitionState();
		}
		catch(Exception NullPointerException)
		{
			return stateCollection[stateCollection.length - 1];
		}
	}
	
	// @OVERRIDE these functions in any specific implementation to properly initialize the States and Transitions for some Turing Machine. Look at the provided example Turing Machines for guidance.
	public abstract void initStates();
	public abstract void initTransitions();;
}
