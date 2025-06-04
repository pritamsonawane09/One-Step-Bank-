package com.bankmain.reportGernator;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ReportGeneratService {

	
	void generateExcel(HttpServletResponse response) throws IOException;
}
