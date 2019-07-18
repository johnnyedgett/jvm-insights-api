package com.kios.jvm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParseService {

	private static final Logger LOG = LoggerFactory.getLogger(ParseService.class);
	
	private ProcessBuilder pb;
	
	public List<String> getBytecode(MultipartFile file) throws IOException{
		List<String> data = new ArrayList<>();
		
		File tempFile = File.createTempFile("processFile", ".class");
		file.transferTo(tempFile);
	    
		// TODO: Remove hardcoded value
		String[] command = {"/home/script1.sh", tempFile.getAbsolutePath() };
		pb = new ProcessBuilder(command);
		
		List<String> output = new ArrayList<>();
		
		try {
			Process p = pb.start(); 
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        
	        while ((line = br.readLine()) != null) {
	        	output.add(line);
	        }
			data.add(String.join("\n", output));
			
			output = new ArrayList<>();
			
			// TODO: Remove hardcoded value
			command[0] = "/home/script2.sh";
			command[1] = tempFile.getAbsolutePath();
			
			pb = new ProcessBuilder(command);
			p = pb.start();
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			while((line = br.readLine()) != null) {
				output.add(line);
			}
			
			data.add(String.join("\n", output));
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return data;
	}
}
