package com.ewolff.microservice.catalog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ewolff.microservice.catalog.Item;
import com.ewolff.microservice.catalog.ItemRepository;

@Controller
public class CatalogController {

	private final ItemRepository itemRepository;

	@Autowired
	public CatalogController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView Item(@PathVariable("id") long id) {
		System.out.println("**** in id.html -- 7");
		return new ModelAndView("item", "item", itemRepository.findOne(id));
	}

	@RequestMapping("/list.html")
	public ModelAndView ItemList() {
		System.out.println("**** in id.html -- 8");
		return new ModelAndView("itemlist", "items", itemRepository.findAll());
	}

	@RequestMapping(value = "/form.html", method = RequestMethod.GET)
	public ModelAndView add() {
		System.out.println("**** in id.html -- 9");
		return new ModelAndView("item", "item", new Item());
	}

	@RequestMapping(value = "/form.html", method = RequestMethod.POST)
	public ModelAndView post(Item Item) {
		System.out.println("**** in id.html -- 10 ");
		Item = itemRepository.save(Item);
		return new ModelAndView("success");
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.PUT)
	public ModelAndView put(@PathVariable("id") long id, Item item) {
		item.setId(id);
		System.out.println("**** in id.html -- 11");
		itemRepository.save(item);
		return new ModelAndView("success");
	}

	@RequestMapping(value = "/searchForm.html", produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView searchForm() {
		System.out.println("**** in id.html -- 12");
		return new ModelAndView("searchForm");
	}

	@RequestMapping(value = "/searchByName.html", produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView search(@RequestParam("query") String query) {
		System.out.println("**** in id.html -- 14");
		return new ModelAndView("itemlist", "items",
				itemRepository.findByNameContaining(query));
	}

	@RequestMapping(value = "/{id}.html", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") long id) {
		System.out.println("**** in id.html -- 13");
		itemRepository.delete(id);
		return new ModelAndView("success");
	}

}

