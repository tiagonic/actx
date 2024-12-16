package br.com.actx.back_end_spring;

import java.util.Comparator;

public class OrdenarAtributos implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		return s1.compareTo(s2);
	}

}
