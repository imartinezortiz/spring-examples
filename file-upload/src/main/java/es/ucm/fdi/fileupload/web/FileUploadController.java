package es.ucm.fdi.fileupload.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.ucm.fdi.fileupload.business.boundary.FileUploadManager;
import es.ucm.fdi.fileupload.business.boundary.NewFileCommand;

@Controller
public class FileUploadController {

	private FileUploadManager manager;

	@Autowired
	public FileUploadController(FileUploadManager manager) {
		this.manager = manager;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/files")
	public ModelAndView archivos() {
		ModelAndView view = new ModelAndView("files");
		view.addObject("files", manager.getFiles());
		view.addObject("command", new NewFileCommand());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/files")
	public ModelAndView nuevoArchivo(@ModelAttribute("command") @Validated NewFileCommand command,
			BindingResult result) throws IOException {
		

		if (result.hasErrors()) {
			ModelAndView view = new ModelAndView("files");
			view.addObject("files", manager.getFiles());
			view.addObject("command", command);
			return view;
		}

		manager.addFile(command);

		return new ModelAndView("redirect:/files");
	}

}
