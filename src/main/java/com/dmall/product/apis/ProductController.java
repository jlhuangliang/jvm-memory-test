package com.dmall.product.apis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.dmall.product.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private List<Product> products = null;

	public ProductController() throws ParseException {
		this.products = Arrays.asList(
				new Product("p001", "Iphone 6s", formatter.parse("2015-04-23")),
				new Product("p002", "Xiaomi", formatter.parse("2015-05-12")),
				new Product("p003", "Oppo R11", formatter.parse("2015-04-27")));
	}

	@GetMapping
	public List<Product> getCommentsByTaskId() {

		List<ProductImage> images = new ArrayList<>();

		for (int i= 0; i < 1000; i ++) {
			images.add(new ProductImage());
		}

		return products;
	}

	@RequestMapping(value = "/gc", method = RequestMethod.GET)
	public String gc() {

		System.gc();

		return "gc is successful~";
	}


	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Product getProductbyId(@PathVariable("productId") final String productId) {
		Optional<Product> product = products.stream().filter(c -> Objects.equals(c.getProdcutId(), productId)).findAny();

		return product.isPresent() ? product.get() : null;
	}

	class ProductImage {
		private Integer[] context;

		public ProductImage() {
			context = new Integer[100000];
			for (int i= 0; i < 100000; i ++) {
				context[i] = i;
			}
		}
	}
}
