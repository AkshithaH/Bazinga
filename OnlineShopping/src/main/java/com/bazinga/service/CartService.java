/**
 * 
 */
package com.bazinga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bazinga.model.Cart;
import com.bazinga.repository.CartRepository;

/**
 * @author Akshitha H
 *
 */
@Service
public class CartService implements ICartService{

	@Autowired
	CartRepository cartRepository;
	
	@Override
	public String addProducts(Cart products) throws ResourceNotFoundException,NullPointerException{
		 Cart cart = cartRepository.save(products);
	     return cart.ifexists(cart)? "added sucessfully" : "failed to update data";
	}
	@Override
	public String deleteProduct(int productId) throws NullPointerException{
		int returnValue  = cartRepository.deleteByproductId(productId);
		return returnValue== 0?"deleted sucessfully" : "failed to deleted data/doesn't esist";
	}
	@Override
	public Cart products(int productId) throws NullPointerException,NotFoundException{
		return cartRepository.findByproductId(productId);
	}
	@Override
	public String updateProduct(int productId, int quantity) throws NullPointerException{
		Cart cart = cartRepository.findByproductId(productId);
		cart.setQuantity(quantity);
		boolean returnValue = cartRepository.save(cart).ifexists(cart);
		return returnValue? "updated sucessfully" : "failed to update data";
	}

}
