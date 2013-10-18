package de.uni_potsdam.hpi.metanome.frontend.client.parameter;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class InputParameterString extends InputParameter {
	private static final long serialVersionUID = 7089599177559324612L;

	private String value;

	public InputParameterString(){
		super();
	}
	
	public InputParameterString(String identifier) {
		super(identifier);
	}

	// **** GETTERS & SETTERS ****
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public void setValue(Object obj) {
		this.value = (String) obj;
	}

	@Override
	public Widget getWidget() {
		return new TextBox();
	}
}