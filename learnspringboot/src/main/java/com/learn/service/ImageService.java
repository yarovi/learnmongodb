package com.learn.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import com.learn.data.Image;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ImageService {

	private static String UPLOAD_ROOT = "upload-dir";
	private final ResourceLoader resourceLoader;

	public ImageService(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Flux<Image> findAllImages() {
		try {
			return Flux.fromIterable(Files.newDirectoryStream(Paths.get(UPLOAD_ROOT)))
					.map(path -> new Image(String.valueOf(path.hashCode()), path.getFileName().toString()));
		} catch (IOException e) {
			return Flux.empty();
		}
	}

	public Mono<Resource> findOneImage(String filename) {
		return Mono.fromSupplier(() -> resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + filename));
	}

	public Mono<Void> createImage(Flux<FilePart> files) {
		return files.flatMap(file -> file.transferTo(Paths.get(UPLOAD_ROOT, file.filename()).toFile())).then();
	}

	public Mono<Void> deleteImage(String filename) {
		return Mono.fromRunnable(() -> {
			try {
				Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}

}
