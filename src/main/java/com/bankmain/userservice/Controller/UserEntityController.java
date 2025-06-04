package com.bankmain.userservice.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.bankmain.UserEntityService.UserEntityService;
import com.bankmain.userservice.model.UserEntity;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserEntityController {

	private static final Logger LOGGER = LogManager.getLogger(UserEntityController.class);

	@Autowired
	public UserEntityService UserEntityService;

	@RequestMapping(value = "/save")
	public ResponseEntity<String> saveUserEntity(@RequestBody UserEntity UserEntity) {
		LOGGER.info("Save User Entity Details Info Start : " + UserEntity);
		UserEntityService.SaveUserEntityData(UserEntity);
		LOGGER.info("Save User Entity Details Info End");
		return ResponseEntity.ok("User Entity Created Successfully....!");

	}

	@GetMapping(value = "/get/{id}")
	public ResponseEntity<UserEntity> getUserEntity(@PathVariable Long id) {
		UserEntity userEntity = UserEntityService.getUserEntity(id);
		return ResponseEntity.ok(userEntity);

	}

	@PutMapping(value = "/update")
	public ResponseEntity<UserEntity> getUpdateUserEntity(@RequestBody UserEntity userEntity) {
		UserEntity UserEntity1 = UserEntityService.updateUserService(userEntity);
		return ResponseEntity.ok(UserEntity1);

	}

	@DeleteMapping(value = "/del/{id}")
	public ResponseEntity<String> getDeletedEntity(@PathVariable Long id) {

		UserEntityService.deleteUserEntityData(id);
		return ResponseEntity.ok("Your Service Is Blocked");

	}

	@GetMapping(value = "/getAllUsername")
	public ResponseEntity<List<String>> getAllUserName() {

		List<String> slist = UserEntityService.getAllUserName();
		return ResponseEntity.ok().body(slist);
	}

	@GetMapping(value = "/csvexport")
	public String WriteUserDataCSVFile(HttpServletResponse response) throws IOException {

		response.setContentType("text/csv");

		DateFormat dateformate = new SimpleDateFormat("yyy-MM-dd_HH-mm-ss");

		String currentDate = dateformate.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;fileName=users" + currentDate + ".csv";

		response.setHeader(headerKey, headerValue);
		List<UserEntity> list = UserEntityService.getAllData();

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = { "UID","FNAME","MNAME","LNAME","MNUMBER","ADDHARNO","PANNO","EMAIL","UNAME","PASS","STATUS","PERMANENTADDRESS","LOCALADDRESS","ROLE"};

		String[] nameMapping = { "uid","fname","mname","lname","mnumber","addharNo","panNo","email","uname","pass","status","permanentAddress","localaddress","role"};

		csvWriter.writeHeader(csvHeader);

		for (UserEntity u : list) {
			csvWriter.write(u, nameMapping);

		}
		csvWriter.close();

		return "Data Write in CSV Filie";

	}

}
