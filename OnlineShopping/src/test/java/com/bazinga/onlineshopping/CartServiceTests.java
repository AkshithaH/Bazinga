/**
 * 
 */
package com.bazinga.onlineshopping;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bazinga.model.Cart;
import com.bazinga.service.CartService;

/**
 * @author Akshitha H
 *
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {

	@InjectMocks
	CartService cartService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddProduct() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Cart cart = new Cart(10, "vegitables", 1234.44, "green vegitables", 2);

		// when
		when(cartService.addProducts(cart)).thenReturn("added sucessfully");
	}

	@Test
	public void testFindProductById() {
		// given
		Cart cart1 = new Cart(1, "vegitables", 1234.44, "green vegitables", 2);

		try {
			when(cartService.products(cart1.getProductId())).thenReturn(cart1);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteProductById() {
		// given
		Cart cart1 = new Cart(1, "vegitables", 1234.44, "green vegitables", 2);

		when(cartService.deleteProduct(cart1.getProductId())).thenReturn("successfully deleted");
	}

}
