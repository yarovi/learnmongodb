package com.learn.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import com.learn.data.Image;
import com.learn.model.ImageRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ImageServiceMongo {

	private static String UPLOAD_ROOT = "upload-dir";
	@Autowired
	private ImageRepository imageRepository;

	public Flux<Image> findAllImages() {
		return imageRepository.findAll().log("createImage-save");
	}
	
	/*
	 * public Flux<Image> findOne(String name) { return
	 * imageRepository.findByName(name).log("createImage-save"); }
	 */

	public Mono<Void> createImage(Flux<FilePart> files) {
		return files.flatMap(file -> {
			Mono<Image> saveDatabaseImage = imageRepository
					.save(new Image(UUID.randomUUID().toString(), file.filename()));
			Mono<Void> copyFile = Mono.just(Paths.get(UPLOAD_ROOT, file.filename()).toFile())
					.log("createImage-picktarget").map(destFile -> {
						try {
							destFile.createNewFile();
							return destFile;
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					}).log("createImage-newfile").flatMap(file::transferTo).log("createImage-copy");
			return Mono.when(saveDatabaseImage, copyFile).log("createImage-when");
		}).log("createImage-flatMap").then().log("createImage-done");
	}

	public Mono<Void> deleteImage(String filename) {
		Mono<Void> deleteDatabaseImage = imageRepository.findByName(filename).log("deleteImage-find")
				.flatMap(imageRepository::delete).log("deleteImage-record");
		Mono<Object> deleteFile = Mono.fromRunnable(() -> {
			try {
				Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}).log("deleteImage-file");
		return Mono.when(deleteDatabaseImage, deleteFile).log("deleteImage-when").then().log("deleteImage-done");

	}
}
