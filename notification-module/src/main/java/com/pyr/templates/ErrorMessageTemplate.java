/**
 * 
 */
package com.pyr.templates;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author pradheep.p
 *
 */
public class ErrorMessageTemplate extends PyrTemplate {

	private Exception exception;
	/* (non-Javadoc)
	 * @see com.pyr.templates.PyrTemplate#getFormatString()
	 */
	public ErrorMessageTemplate(Exception err){
		this.exception = err;
	}
	
	@Override
	public String getFormatString() {		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		this.exception.printStackTrace(pw);
		return sw.toString();
	}

	/* (non-Javadoc)
	 * @see com.pyr.templates.PyrTemplate#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return TemplateType.ERROR_MESSAGE_TEMPLATE;
	}

}
