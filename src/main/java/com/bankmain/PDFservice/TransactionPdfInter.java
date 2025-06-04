package com.bankmain.PDFservice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.lowagie.text.DocumentException;

public interface TransactionPdfInter {

	ResponseEntity<String> writeInPdf(HttpServletResponse response) throws DocumentException, IOException;
}
