/**
 * 
 */
package com.bazinga.service;


import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.bazinga.model.Cart;


/**
 * @author Akshitha H
 *
 */
public interface ICartService {
	String addProducts(Cart products);
	Cart products(int productId) throws NullPointerException, NotFoundException;
	String deleteProduct(int productId);
	String updateProduct(int productId,int quantity);
}
