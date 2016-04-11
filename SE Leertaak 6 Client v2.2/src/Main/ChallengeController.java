package Main;

import javax.swing.JOptionPane;

import Model.ChallengeModel;
import Views.ChallengedView;

/**
 * Controller for when a challenge is send or received.
 * 
 * @author Chris de Windt
 * @author Tobias Schlichter
 * @version 1.0
 */
public class ChallengeController {

	public static final int STATUS_READY = 1;
	public static final int STATUS_WAITING = 2;
	
	private int status = STATUS_READY;
	private ChallengedView challengedView;
	
	/**
	 * Controller for a ChallengeController object.
	 */
	public ChallengeController() {
	}
	
	/**
	 * Creates and shows a view when a challenge is received.
	 * 
	 * @param challengeModel model for the challenge.
	 */
	public void displayChallenge(ChallengeModel challengeModel){
		challengedView = new ChallengedView(challengeModel.getChallenger(), challengeModel.getGameType());
	}
	
	/**
	 * Waits until the challenge is answered and returns true if the challenge is accepted.
	 * 
	 * @return boolean if the challenge is accepted.
	 */
	public boolean challengeAccepted(){
		status = STATUS_WAITING;
		while (status == STATUS_WAITING) {
			if (challengedView.getChoice() != ChallengedView.ORIGINAL_VALUE) {
				status = STATUS_READY;
				return challengedView.getChoice() == JOptionPane.YES_OPTION;
			}
		}
		return false;
	}
}
