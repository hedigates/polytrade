package com.polymec.web;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


//import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.github.dandelion.core.util.StringUtils;
import com.polymec.model.Person;
import com.polymec.service.PersonService;
import com.polymec.model.Famille;
import com.polymec.service.FamilleService;
import com.polymec.model.Article;
import com.polymec.service.ArticleService;
import com.polymec.model.ArticleFrns;
import com.polymec.service.ArticleFrnsService;

/**
 * <p>
 * Controller for all examples contained in the sample.
 * 
 * @author Thibault Duchateau
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class SampleController {

	@Autowired
    private ArticleFrnsService articleFrnsService;	
	
	@Autowired
	private FamilleService familleService;
	/**
	 * <p>
	 * Populates the model with the domain objects. Used in all examples that
	 * use a DOM source.
	 * 
	 * @return a list of persons for display.
	 */


	@ModelAttribute("allFamille")
    public List<Famille> populateallFamille() {
        return this.familleService.findAllValid();
    }	

	@ModelAttribute("allArticleFrns")
    public List<ArticleFrns> populateArticleFrns() {
        return this.articleFrnsService.findAllValid();
    }
	
	
	@RequestMapping(value = "articlesReport", method = RequestMethod.POST)
	public ModelAndView getArticlesReport(ModelMap modelMap, ModelAndView modelAndView) {
		Map<String,Object> parameterMap = new HashMap<String,Object>(); 
		List<ArticleFrns> arts= this.articleFrnsService.findAllValid();
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(arts);
        parameterMap.put("datasource", JRdataSource);		
		return new ModelAndView("articlesReport", parameterMap);
	}

	@RequestMapping(value = "/")
	public String goToIndex(Model model) {
		model.addAttribute("famille", this.familleService.findById(1L));
		return "index";
	}
	
	@RequestMapping(value = "/bootstrap2/{page}")
	public String goToBootstrap2Example(@PathVariable String page) {
		return "bootstrap2." + page;
	}
	
	@RequestMapping(value = "/bootstrap3/{page}")
	public String goToBootstrap3Example(@PathVariable String page) {
		return "bootstrap3." + page;
	}
	
	
	@RequestMapping(value = "/jqueryui/{page}")
	public String goToBasicJqueryuiExample(@PathVariable String page, HttpServletRequest request) {
		String themeName = request.getParameter("theme");
		if(StringUtils.isNotBlank(themeName)){
			request.setAttribute("themeName", themeName);
		}
		
		return "jqueryui." + page;
	}
}