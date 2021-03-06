package com.bazinga.onlineshopping;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bazinga.controller.CartController;
import com.bazinga.model.Cart;




@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartControllerTests {
	
	@InjectMocks
    CartController cartController;
 
	
	@Test
	void contextLoads() {
	}
	@Test
	public void testAddProduct() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Cart cart = new Cart(10,"vegitables",1234.44, "green vegitables",2);
		
		//when
		try {
			when(cartController.addProducts("vegitables","1234.44", "green vegitables","2")).thenReturn(ResponseEntity.status(HttpStatus.OK).body("successfully added data"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//when(cartController.addProducts(cart)).thenCallRealMethod();
	}

	@Test
	public void testFindProductById() {
		// given
		Cart cart1 = new Cart(1,"vegitables",1234.44, "green vegitables",2);

		try {
			when(cartController.products(cart1.getProductId().toString())).thenReturn(ResponseEntity.status(HttpStatus.OK).body(cart1));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			when(cartController.products(cart1.getProductId().toString())).thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).body(cart1));
		}
	}
	@Test
	public void testDeleteProductById() {
		// given
		Cart cart1 = new Cart(1,"vegitables",1234.44, "green vegitables",2);

		when(cartController.deleteProduct(cart1.getProductId().toString())).thenReturn("successfully deleted");
	}
	
}
