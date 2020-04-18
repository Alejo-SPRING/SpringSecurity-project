package com.practica.backEnd.app.web.model.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFilesServiceImpl implements IUploadFilesService {

	@Override
	public String uploadFile(MultipartFile file) {
		String name = UUID.randomUUID() + "_" + file.getOriginalFilename().replace(" ", "");
		Path path = Paths.get("uploads").resolve(name).toAbsolutePath();
		try {
			Files.copy(file.getInputStream(), path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
}
