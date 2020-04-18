package com.practica.backEnd.app.web.model.services;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFilesService {

	public String uploadFile(MultipartFile file);
	
}
