package Main;

import javax.swing.JOptionPane;

import Model.ChallengeModel;
import Views.ChallengedView;

public class ChallengeController {

	public static final int STATUS_READY = 1;
	public static final int STATUS_WAITING = 2;
	
	private int status = STATUS_READY;
	ChallengedView challengedView;
	
	public ChallengeController() {
		
	}
	
	public void displayChallenge(ChallengeModel challengeModel){
		challengedView = new ChallengedView(challengeModel.getChallenger(), challengeModel.getGameType());
	}
	
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
