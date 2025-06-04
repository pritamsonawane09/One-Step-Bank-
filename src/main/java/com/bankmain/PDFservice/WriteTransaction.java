package com.bankmain.PDFservice;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankmain.accountTransaction.AccountTransaction;
import com.bankmain.bankdetailsserviceRepo.FundTransferRepository;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.FontSelector;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class WriteTransaction implements TransactionPdfInter {
	
	@Autowired
	private FundTransferRepository transaction;

	@Override
	public ResponseEntity<String> writeInPdf(HttpServletResponse response) throws DocumentException, IOException {
		
		
	  	    Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, response.getOutputStream());

		

		try {
			// Create a PdfWriter instance to write the document to a file
			PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));

			FontSelector fs = new FontSelector();
			Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
			font1.setColor(Color.RED);
			fs.addFont(font1);

			List<AccountTransaction> acc = transaction.findAll();

			Paragraph p = new Paragraph("TRASACTION DATA", font1);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			font1.setColor(Color.RED);

			Paragraph para = new Paragraph("/n");
			para.setExtraParagraphSpace(50);

			PdfPTable billTable = new PdfPTable(7);
			
			billTable.setWidthPercentage(100);
			billTable.setWidths(new float[] { 3, 3, 3, 3, 3, 3, 3 });
			billTable.setSpacingBefore(30.0f);

			billTable.addCell("Transaction ID");
			billTable.addCell("Transaction Date");
			billTable.addCell("Sender Name");
			billTable.addCell("Sender Account Number");
			billTable.addCell("Receiver Name");
			billTable.addCell("Receiver Account Number");
			billTable.addCell("Amount");
			
			for (AccountTransaction a : acc) {

				// Add content to the document

			 // one page contains 15 records
		

				billTable.addCell(String.valueOf(a.gettId()));
				billTable.addCell(a.gettDate());
				billTable.addCell(a.getSenderName());
				billTable.addCell(a.getSenderAccountNo());
				billTable.addCell(a.getReceivername());
				billTable.addCell(a.getReceiveraccountno());
				billTable.addCell(String.valueOf(a.getAmount()));

				

				// document.close();

			}
			// Close the document
			document.open();// PDF document opened........

			document.add(p);
			document.add(billTable);
			document.close();

			System.out.println("PDF created successfully.");
		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("PDF created successfully");
	}
}
