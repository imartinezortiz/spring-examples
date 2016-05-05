package es.ucm.fdi.datatable.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.ucm.fdi.datatable.business.boundary.DataManager;
import es.ucm.fdi.datatable.business.boundary.DataPage;
import es.ucm.fdi.datatable.business.entity.Datum;

@Controller
public class DataController {

	private DataManager data;

	@Autowired
	public DataController(DataManager data) {
		this.data = data;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/table", produces=MediaType.TEXT_HTML_VALUE)
	public ModelAndView tablePage() {
		ModelAndView result = new ModelAndView("table");
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/table", produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablePage<Datum> tableData(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
		
		DataPage dataPage = this.data.getData(start, length);
		
		DataTablePage<Datum> tablePage = new DataTablePage<Datum>(draw);
		tablePage.setData(dataPage.getData());
		tablePage.setRecordsTotal(dataPage.getRecordsTotal());
		
		return tablePage;
	}
		
}