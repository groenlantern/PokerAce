package za.co.pokerface.baseCard.enums;

/**
 * Use in factory method for Card Evaluator Library methods 
 * 
 * @author Jean-Pierre Erasmus
 *
 */
public enum EvaluatorLibrary  {

	KATA("Kata Poker Master", "kata.");
	
	private String label;
	private String packagePrefix;

		
	/**
	 * 
	 * @param label
	 * @param prefx
	 */
	EvaluatorLibrary(String label, String prefx) {
		this.label = label;
		this.setPackagePrefix(prefx);
	}

	@Override
	public String toString() {
		return label;
	}

	public String getLabel() {
		return label;
	}

	public String getPackagePrefix() {
		return packagePrefix;
	}

	public void setPackagePrefix(String packagePrefix) {
		this.packagePrefix = packagePrefix;
	}

}
