package br.com.actx.back_end_spring;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorControllerImpl implements ErrorController {
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		System.err.println("\n\nDeu erro!\n\n");
		Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
		String errorMessage = (String) request.getAttribute("jakarta.servlet.error.message");

		List<String> attributeNames = new ArrayList<String>();
		Enumeration<String> namesEnumeration = request.getAttributeNames();
		while (namesEnumeration.hasMoreElements()) {
			attributeNames.add(namesEnumeration.nextElement());
		}
		System.err.println(attributeNames.toArray().toString());

		model.addAttribute("statusCode", statusCode);
		model.addAttribute("errorMessage", errorMessage != null ? errorMessage : "N/A");
		model.addAttribute("exception", throwable != null ? throwable : "N/A");
		model.addAttribute("attributeNames", attributeNames);

		return "error";
	}
}
