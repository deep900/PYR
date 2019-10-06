/**
 * 
 */
package com.pyr.templates;

/**
 * @author pradheep.p
 *
 */
public class EmailWelcomeMessage extends PyrTemplate {
	
	private String header;
	private String body;
	private String footer;
	private String unsubscribeKey;

	public EmailWelcomeMessage(String header, String footer, String body,String key) {
		this.header = header;
		this.body = body;
		this.footer = footer;
		this.unsubscribeKey = key;
	}

	@Override
	public String getFormatString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(header);
		buffer.append("<br>");
		buffer.append("<p>");
		buffer.append(body);
		buffer.append("</p>");
		buffer.append("<br>");
		buffer.append(footer);
		buffer.append("<hr>");
		buffer.append("Praise your redeemer ministry <br>");
		buffer.append("Kindly write to us at administrator@praiseyourredeemer.org if you wish to unsubscribe.");
		return buffer.toString();
	}

	/**
	 * Returns the type of template.
	 * 
	 * @see com.pyr.templates.PyrTemplate#getType()
	 */
	@Override
	public int getType() {		
		return TemplateType.EMAIL_WELCOME_TEMPLATE;
	}

}
