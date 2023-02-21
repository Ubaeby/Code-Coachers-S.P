package com.codecoachers.project.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codecoachers.project.message.ResponseFile;
import com.codecoachers.project.message.ResponseMessage;
import com.codecoachers.project.models.FileInfo;
import com.codecoachers.project.services.FileService;

//File Upload is based off of resource https://www.bezkoder.com/spring-boot-file-upload/.

@Controller
@CrossOrigin("http://localhost:8080")
public class FilesController {
	
	
	@Autowired
	private FileService newService;
	
	 
	@PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(
			  @RequestParam("file") MultipartFile file, FileInfo newFile) {
	    String message = "";
	    try {
	      newService.store(file);
	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      
	      //attempt to put coach and file together :3
//	      Coach coach = cServ.findCoachId(theCoach.getId());
//	      FileInfo fileinfo = newService.getFile(newFile.getId());
//	      fileinfo.setCoach(coach);
	      
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	  @GetMapping("/files")
	  public ResponseEntity<List<ResponseFile>> getListFiles() {
	    List<ResponseFile> files = newService.getAllFiles().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getId())
	          .toUriString();

	      return new ResponseFile(
	          dbFile.getName(),
	          fileDownloadUri,
	          dbFile.getType(),
	          dbFile.getData().length);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }

	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(
			  @PathVariable String id) {
	    FileInfo fileDB = newService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
	        .body(fileDB.getData());
	  }
}
