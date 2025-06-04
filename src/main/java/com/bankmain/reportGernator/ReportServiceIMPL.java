package com.bankmain.reportGernator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankmain.UserEntityRepository.UserEntityRepository;
import com.bankmain.userservice.model.UserEntity;

@Service
public class ReportServiceIMPL implements ReportGeneratService {

	@Autowired
	private UserEntityRepository UserEntityRepository;
	

	public void generateExcel(HttpServletResponse response) throws IOException {
		List<UserEntity> userentity = UserEntityRepository.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet("All Detail Info");

		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("UID");
		row.createCell(1).setCellValue("FNAME");
		row.createCell(2).setCellValue("MNAME");
		row.createCell(3).setCellValue("LNAME");
		row.createCell(4).setCellValue("MNUMBER");
		row.createCell(5).setCellValue("ADDHARNO");
		row.createCell(6).setCellValue("PANNO");
		row.createCell(7).setCellValue("EMAIL");
		row.createCell(8).setCellValue("UNAME");
		row.createCell(9).setCellValue("PASS");
		row.createCell(10).setCellValue("STATUS");
		row.createCell(11).setCellValue("PERMANENTADD");
		row.createCell(12).setCellValue("LOCALADD");
		row.createCell(13).setCellValue("ROLE");

		int dataRowIndex = 1;

		for (UserEntity u : userentity) {

			HSSFRow datatrow = sheet.createRow(dataRowIndex);
			datatrow.createCell(0).setCellValue(u.getUid());
			datatrow.createCell(1).setCellValue(u.getFname());
			datatrow.createCell(2).setCellValue(u.getMname());
			datatrow.createCell(3).setCellValue(u.getLname());
			datatrow.createCell(4).setCellValue(u.getMnumber());
			datatrow.createCell(5).setCellValue(u.getAddharNo());
			datatrow.createCell(6).setCellValue(u.getPanNo());
			datatrow.createCell(7).setCellValue(u.getEmail());
			datatrow.createCell(8).setCellValue(u.getUname());
			datatrow.createCell(9).setCellValue(u.getPass());
			datatrow.createCell(10).setCellValue(u.getStatus().name());
			datatrow.createCell(11).setCellValue(u.getPermanentAddress().getCity());
			datatrow.createCell(12).setCellValue(u.getLocaladdress().getCity());
			datatrow.createCell(13).setCellValue(u.getRole().getRoleName());
			dataRowIndex++;
		}
		ServletOutputStream outputstream = response.getOutputStream();
		workbook.write(outputstream);
		workbook.close();
		outputstream.close();
	}
}
