package it.cd79.test.security.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Example {

	private String text;

	public Example() {
		this(null);
	}

	public Example(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
