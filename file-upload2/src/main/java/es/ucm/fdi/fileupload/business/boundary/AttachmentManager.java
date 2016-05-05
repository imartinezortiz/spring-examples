package es.ucm.fdi.fileupload.business.boundary;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadManager {

	private ResourceLoader loader;

	private String rootPath;

	private Path root;

	@Autowired
	public FileUploadManager(ResourceLoader loader, @Value("#{storagePrefs[rootPath]}") String rootPath) {
		this.loader = loader;
		this.rootPath = rootPath;
	}

	@PostConstruct
	private void init() {
		try {
			this.root = loader.getResource(rootPath).getFile().toPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Path addFile(NewFileCommand command) throws IOException {

		String name = command.getName();
		if (name.contains("/") || name.contains("\\")) {
			throw new IllegalArgumentException("Folder separators not allowed");
		}
		if (name.contains("..")) {
			throw new IllegalArgumentException("Relative pathnames not allowed");
		}

		MultipartFile uploadFile = command.getAttachment();
		Path file = root.resolve(name);
		if (!uploadFile.isEmpty()) {
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file.toFile()))){
				FileCopyUtils.copy(uploadFile.getInputStream(), stream);
			} catch (Exception e) {
				throw new IllegalArgumentException("You failed to upload " + name + " => " + e.getMessage());
			}
		} else {
			throw new IllegalArgumentException("You failed to upload " + name + " because the file was empty");
		}

		return file;
	}

	public List<Path> getFiles() {
		return fileList();
	}

	private List<Path> fileList() {
		List<Path> files = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(this.root)) {
			for (Path path : directoryStream) {
				files.add(path);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return files;
	}

}