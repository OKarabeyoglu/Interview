package com.trendyol.shoppingcard.generator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.dto.CartItemDTO;
import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.dto.CouponDTO;
import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.dto.UserDTO;
import com.trendyol.shoppingcard.entities.Cart;
import com.trendyol.shoppingcard.entities.CartItem;
import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.entities.Coupon;
import com.trendyol.shoppingcard.entities.Product;
import com.trendyol.shoppingcard.entities.User;
import com.trendyol.shoppingcard.intf.CampaignService;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.intf.CategoryService;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.util.CartStatus;
import com.trendyol.shoppingcard.util.DiscountType;

public class MockDataGenerator {
	
	public List<Class<? extends Object>> createFeeRateManagementApiClasses() {
        List<Class<? extends Object>> classes = new ArrayList<>();
        classes.add(ProductService.class);
        classes.add(CategoryService.class);
        classes.add(CampaignService.class);
        classes.add(CouponService.class);
        classes.add(CartService.class);
        return classes;
    }
	
	public static CampaignDTO createCampaignDTO() {
		CampaignDTO campaignDTO = new CampaignDTO();
		List<CategoryDTO> categoryDTOList = new ArrayList<>();
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle("Clothes");
		categoryDTO.setParentCategory(null);
		categoryDTO.setCampaignDTOList(null);
		categoryDTO.setId(1L);
		categoryDTOList.add(categoryDTO);
		campaignDTO.setCategoryDTOList(categoryDTOList);
		campaignDTO.setDiscountAmount(new BigDecimal(10));
		campaignDTO.setDiscountType(DiscountType.AMOUNT);
		campaignDTO.setMinNumberOfProducts(3);
		campaignDTO.setId(1L);
		return campaignDTO;
	}
	
	public static UserDTO createUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(2L);
		userDTO.setName("Ovunc");
		userDTO.setSurname("Karabeyoglu");
		userDTO.setEmail("ovunckarabeyoglu@gmail.com");
		List<CartDTO> cartDTOList = new ArrayList<CartDTO>();
		CartDTO cartDTO = new CartDTO();
		cartDTO.setId(1L);
		cartDTO.setStatus(CartStatus.ACTIVE);
		cartDTOList.add(cartDTO);
		userDTO.setCartDTOList(cartDTOList);
		return userDTO;
	}
	
	public static User createUserModel() {
		User model = new User();
		model.setId(2L);
		model.setName("Ovunc");
		model.setSurname("Karabeyoglu");
		model.setEmail("ovunckarabeyoglu@gmail.com");
		List<Cart> cartList = new ArrayList<Cart>();
		Cart cart = new Cart();
		cart.setStatus(CartStatus.ACTIVE);
		cart.setId(1L);
		List<CartItem> cartItemList = new ArrayList<>();
		CartItem cartItem = new CartItem();
		cartItem.setProduct(createProduct());
		cartItemList.add(cartItem);
		cart.setCartItemList(cartItemList);
		cartList.add(cart);
		model.setCartList(cartList);
		return model;
	}
	
	public static CategoryDTO createCategoryDTO() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle("Clothes");
		categoryDTO.setParentCategory(null);
		categoryDTO.setCampaignDTOList(null);
		categoryDTO.setProductDTOList(null);
		categoryDTO.setId(1L);
		return categoryDTO;
	}
	
	public static CouponDTO createCouponDTO() {
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCreateDate(LocalDate.now());
		couponDTO.setModifiedDate(LocalDate.now());
		couponDTO.setDiscountAmount(new BigDecimal(30));
		couponDTO.setDiscountType(DiscountType.AMOUNT);
		couponDTO.setMinimumCartAmount(new BigDecimal(200));
		couponDTO.setId(1L);
		return couponDTO;
	}
	
	public static Coupon createCouponModel() {
		Coupon model = new Coupon();
		model.setCreateDate(LocalDate.now());
		model.setModifiedDate(LocalDate.now());
		model.setId(1L);
		model.setDiscountAmount(new BigDecimal(50));
		model.setMinimumCartAmount(new BigDecimal(500));
		model.setDiscountType(DiscountType.AMOUNT);
		return model;
	}
	
	public static List<Coupon> createCouponModelList(){
		List<Coupon> modelList = new ArrayList<Coupon>();
		modelList.add(createCouponModel());
		return modelList;
	}
	
	public static ProductDTO createProductDTO() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(new BigDecimal(100));
		productDTO.setTitle("T-Shirt");
		productDTO.setCurrencyCode("TL");
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle("Clothes");
		categoryDTO.setParentCategory(null);
		categoryDTO.setCampaignDTOList(null);
		productDTO.setCategory(categoryDTO);
		productDTO.setId(1L);
		return productDTO;
	}
	
	public static Product createProduct() {
		Product product = new Product();
		product.setAmount(new BigDecimal(100));
		product.setTitle("T-Shirt");
		product.setCurrencyCode("TL");
		Category category = new Category();
		category.setTitle("Clothes");
		category.setParentCategory(null);
		category.setCampaign(null);
		product.setCategory(category);
		product.setId(1L);
		return product;
	}
	
	public static List<CartItem> createCartItemList(){
		List<CartItem> modelList = new ArrayList<>();
		CartItem model = new CartItem();
		model.setCart(createDefaultCart());
		model.setCreateDate(LocalDate.now());
		model.setModifiedDate(LocalDate.now());
		model.setProduct(createProduct());
		model.setQuantity(2);
		modelList.add(model);
		return modelList;
	}
	
	public static Cart createDefaultCart() {
		Cart cart = new Cart();
		cart.setId(1L);
		cart.setCoupon(null);
		cart.setCreateDate(LocalDate.now());
		cart.setModifiedDate(LocalDate.now());
		cart.setStatus(CartStatus.ACTIVE);
		cart.setUser(createUserModel());
		List<CartItem> cartItemList = new ArrayList<>();
		cart.setCartItemList(cartItemList);
		return cart;
	}
	
	public static Cart createAddedCart() {
		Cart cart = new Cart();
		cart.setId(1L);
		cart.setCoupon(null);
		cart.setCreateDate(LocalDate.now());
		cart.setModifiedDate(LocalDate.now());
		cart.setStatus(CartStatus.ACTIVE);
		cart.setUser(createUserModel());
		List<CartItem> cartItemList = createCartItemList();
		cart.setCartItemList(cartItemList);
		return cart;
	}
	
	public static CartDTO createCartDTO() {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setId(1L);
		cartDTO.setCouponDTO(null);
		cartDTO.setCreateDate(LocalDate.now());
		cartDTO.setModifiedDate(LocalDate.now());
		cartDTO.setStatus(CartStatus.ACTIVE);
		cartDTO.setUserDTO(createUserDTO());
		List<CartItemDTO> cartItemDTOList = new ArrayList<>();
		cartDTO.setCartItemList(cartItemDTOList);
		return cartDTO;
	}

}
