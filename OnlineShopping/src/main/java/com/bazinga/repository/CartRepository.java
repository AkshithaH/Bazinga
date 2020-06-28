/**
 * 
 */
package com.bazinga.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bazinga.model.Cart;


/**
 * @author Akshitha H
 *
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

	/**
	 * @param i
	 * @return
	 */
	Cart findByproductId(int i);
	Cart save(Cart cart);
	/**
	 * @param productId
	 */
	int deleteByproductId(int productId);
	
}
