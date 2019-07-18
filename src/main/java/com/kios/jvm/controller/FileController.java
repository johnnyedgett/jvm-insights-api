package com.kios.jvm.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kios.jvm.service.ParseService;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@Autowired
	ParseService parseService;
	
	@CrossOrigin
	@PostMapping("/upload")
	public ResponseEntity<List<String>> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException{
		List<String> res = parseService.getBytecode(file);
		if(res.size() == 2) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
}
