package com.adcamp;

public class AdCampaignException extends Exception {

	private static final long serialVersionUID = -3577000502200832865L;

	public AdCampaignException() {
		super();
	}

	public AdCampaignException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdCampaignException(String message) {
		super(message);
	}

	public AdCampaignException(Throwable cause) {
		super(cause);
	}

}
