package com.bankmain.fundTransferService;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bankmain.UserEntityRepository.AccountDeetailsRepository;
import com.bankmain.accountTransaction.AccountTransaction;
import com.bankmain.bankdetailsserviceRepo.FundTransferRepository;
import com.bankmain.userservice.model.AccountDetails;
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
public class FundTransferServiceIMPL implements FundTransferService {

	@Autowired
	private AccountDeetailsRepository AccountDeetailsRepository;

	@Autowired
	private FundTransferRepository FundTransferRepository;

	@Override
	public ResponseEntity<String> bankToBankTransfer(String accountNumber, double amount, String pin) {

		AccountDetails AccountOne = AccountDeetailsRepository.findByAccountNumber(accountNumber);
		AccountDetails AccountTwo = AccountDeetailsRepository.findByPin(pin);

		if (AccountOne != null && AccountTwo != null) {
			double transferAmount = AccountOne.getActulbalance();

			if (transferAmount > amount) {

				transferAmount = transferAmount - amount;

				AccountOne.setActulbalance(AccountTwo.getActulbalance() + amount);

				AccountTwo.setActulbalance(transferAmount);

				AccountDeetailsRepository.save(AccountOne);
				AccountDeetailsRepository.save(AccountTwo);

				AccountTransaction transferRequest = new AccountTransaction();
				transferRequest.setSenderAccountNo(AccountTwo.getAccountNumber());
				transferRequest.setSenderName(AccountTwo.getUser().getUname());
				transferRequest.setReceivername(AccountOne.getUser().getUname());
				transferRequest.setReceiveraccountno(AccountOne.getAccountNumber());

				transferRequest.setAmount(amount);

				Date date = new Date();
				SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date1 = simpledate.format(date);
				transferRequest.settDate(date1);

				transferRequest.setAccountDetails(AccountTwo);

				FundTransferRepository.save(transferRequest);

				return ResponseEntity.ok("Transacgtion Success" + " " + "Ammouunt Debited From Your Account No :-"
						+ AccountTwo.getAccountNumber() + " " + "Current Balance" + AccountTwo.getActulbalance());
			} else {
				return ResponseEntity.ok("In Sufficient Balance");
			}
		} else if (AccountOne == null) {
			return ResponseEntity.ok("Invalid Account Number");
		} else if (AccountTwo == null) {
			ResponseEntity.ok("Invalid Pin");
		}
		return ResponseEntity.ok("Invalid Ac{tion");
	}

	@Override
	public ResponseEntity<String> credit(String accountNumber, double amount) {
		AccountDetails accountdetails = AccountDeetailsRepository.findByAccountNumber(accountNumber);
		if (accountdetails != null) {
			accountdetails.setActulbalance(accountdetails.getActulbalance() + amount);
			AccountDeetailsRepository.save(accountdetails);
			return ResponseEntity
					.ok("Your Account Creadited " + amount + "  current balance :-" + accountdetails.getActulbalance());
		}
		return ResponseEntity.ok("Invalid Details");

	}

	@Override
	public ResponseEntity<String> withDrow(String accountNumber, double amount) {
		AccountDetails accountDetails = AccountDeetailsRepository.findByAccountNumber(accountNumber);
		if (accountDetails != null) {
			double g = accountDetails.getActulbalance();
			if (g > amount) {
				g = g - amount;
				accountDetails.setActulbalance(g);
				AccountDeetailsRepository.save(accountDetails);
				return ResponseEntity.ok("Your account is debited :-" + amount + "   current balance"
						+ accountDetails.getActulbalance());
			} else {
				return ResponseEntity.ok("Insufficient Balance!");
			}
		}
		return ResponseEntity.ok("Invalid Account Number!");
	}

	@Override
	public void generateStatement(HttpServletResponse response, String accoountNo, String tDate1, String tDate2)
			throws DocumentException, IOException {
		// List<AccountTransaction> acc = transactionRepo.findByTDateBetween(date1,
		// date2);
		// System.out.println("TRansaction between Date to Date "+acc);

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		try {
			// Create a PdfWriter instance to write the document to a file
			PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));

			FontSelector fs = new FontSelector();
			Font font1 = FontFactory.getFont(FontFactory.TIMES_BOLD, 18);
			font1.setColor(Color.RED);
			fs.addFont(font1);

			List<AccountTransaction> acc = FundTransferRepository.findByTDateBetween(tDate1, tDate2);

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
	}
}
