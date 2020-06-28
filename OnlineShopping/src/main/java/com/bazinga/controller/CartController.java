package com.bazinga.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazinga.model.Cart;
import com.bazinga.service.ICartService;

@CrossOrigin
@RestController
public class CartController {

	@Autowired
	private ICartService cartservice;

	@ExceptionHandler
	@GetMapping("/products/{produtId}")
	public ResponseEntity<Cart> products(@PathVariable("produtId") String produtId) {
		Cart cart;
		try {
			cart = cartservice.products(Integer.parseInt(produtId));
			if (cart.ifexists(cart)) {
				return ResponseEntity.status(HttpStatus.OK).body(cart);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@PostMapping("/products/{productName}/{productPrice}/{productDescription}/{quantity}")
	public ResponseEntity<String> addProducts(@PathVariable("productName") String productName,@PathVariable("productPrice") String productPrice,@PathVariable("productDescription") String productDescription,@PathVariable("quantity") String quantity) throws ResourceNotFoundException, NullPointerException, NotFoundException {
		String data;
		try {
			Cart cart = new Cart();
			cart.setProductDescription(productDescription);
			cart.setProductName(productName);
			cart.setProductPrice(Double.parseDouble(productPrice));
			cart.setQuantity(Integer.parseInt(quantity));
			data = cartservice.addProducts(cart);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Data formate is not allowed");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("empty data is being send");
		}
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	@DeleteMapping("/product/{productId}")
	public synchronized String deleteProduct(@PathVariable("productId") String productId) throws NullPointerException {
		return cartservice.deleteProduct(Integer.parseInt(productId));
	}

	@PatchMapping("/product/{productId}/{quantity}")
	public synchronized String update(@PathVariable("productId") String productId,
			@PathVariable("quantity") String quantity) throws NullPointerException {
		return cartservice.updateProduct(Integer.parseInt(productId), Integer.parseInt(quantity));
	}
}
