package com.bankmain.reportGernator;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class ReportRestController {

	@Autowired
	private ReportServiceIMPL ReportServiceIMPL;
	
	
	@GetMapping("/Excel")
	public void generatedExcelReport(HttpServletResponse response) throws IOException  {
		
		response.setContentType("application/octet-stream");
		
		String headerkey = "Content-Disposition";
		
		String headerValue = "attachment;filename=courses.xls";
		
		response.setHeader(headerkey, headerValue);
		
		
		ReportServiceIMPL.generateExcel(response);
	}
}
