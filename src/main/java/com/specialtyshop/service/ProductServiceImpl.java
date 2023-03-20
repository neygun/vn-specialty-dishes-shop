package com.specialtyshop.service;

import static com.specialtyshop.specifications.ProductSpecs.containsKeyword;
import static com.specialtyshop.specifications.ProductSpecs.hasCategoryId;
import static com.specialtyshop.specifications.ProductSpecs.hasMainCategoryId;
import static com.specialtyshop.specifications.ProductSpecs.hasUnitPriceBetween;
import static com.specialtyshop.specifications.ProductSpecs.hasSupplier;
import static com.specialtyshop.specifications.ProductSpecs.orderBySizeOfOrderDetails;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.PurchaseOrderDetail;
import com.specialtyshop.entity.Supplier;
import com.specialtyshop.repository.ProductRepository;
import com.specialtyshop.repository.PurchaseOrderDetailRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final int PAGE_SIZE = 9;

	private ProductRepository productRepository;
	
	private PurchaseOrderDetailRepository podRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, PurchaseOrderDetailRepository podRepository) {
		this.productRepository = productRepository;
		this.podRepository = podRepository;
	}

	@Override
	public Product findById(Integer productId) {
		
		Optional<Product> result = productRepository.findById(productId);
		Product product = null;
        if (result.isPresent()) {
        	product = result.get();
        } else {
            throw new RuntimeException("Did not find product id - " + productId);
        }
        return product;
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}
	
	@Override
	public void deleteById(Integer productId) {
		productRepository.deleteById(productId);
	}
	
	private Sort getSort(String sortBy) {
		
		Sort sort = null;
		if (sortBy == null || sortBy.equals("default")) {
			sort = Sort.unsorted();
		} else if (sortBy.equals("price-asc")) {
			sort = Sort.by("unitPrice").ascending();
		} else if (sortBy.equals("price-desc")) {
			sort = Sort.by("unitPrice").descending();
		}
		return sort;
	}
	
	@Override
	public Page<Product> findAllProducts(Double minPrice, Double maxPrice, String sortBy, int page) {
		
		Specification<Product> specifications = null;
		
		if (minPrice != null && maxPrice != null) {
			specifications = Specification.where(specifications).and(hasUnitPriceBetween(minPrice, maxPrice));
		}
		Sort sort = getSort(sortBy);
		Pageable pageable = PageRequest.of(page-1, PAGE_SIZE, sort);
		
		return productRepository.findAll(specifications, pageable);
	}

	@Override
	public Page<Product> findByMainCategoryId(Integer mainCategoryId, Double minPrice, Double maxPrice, 
			String sortBy, int page) {
		
		Specification<Product> specifications = Specification.where(hasMainCategoryId(mainCategoryId));
		
		if (minPrice != null && maxPrice != null) {
			specifications = Specification.where(specifications).and(hasUnitPriceBetween(minPrice, maxPrice));
		}
		Sort sort = getSort(sortBy);
		Pageable pageable = PageRequest.of(page-1, PAGE_SIZE, sort);
		
		return productRepository.findAll(specifications, pageable);
	}

	@Override
	public Page<Product> findBySubCategoryId(Integer subCategoryId, Double minPrice, Double maxPrice, 
			String sortBy, int page) {
		
		Specification<Product> specifications = Specification.where(hasCategoryId(subCategoryId));
		
		if (minPrice != null && maxPrice != null) {
			specifications = Specification.where(specifications).and(hasUnitPriceBetween(minPrice, maxPrice));
		}
		Sort sort = getSort(sortBy);
		Pageable pageable = PageRequest.of(page-1, PAGE_SIZE, sort);
		
		return productRepository.findAll(specifications, pageable);
	}

	@Override
	public Page<Product> findBestSellingProducts(Double minPrice, Double maxPrice, String sortBy, int page) {
		
		Specification<Product> specifications = Specification.where(orderBySizeOfOrderDetails());
		
		if (minPrice != null && maxPrice != null) {
			specifications = Specification.where(specifications).and(hasUnitPriceBetween(minPrice, maxPrice));
		}
		Sort sort = getSort(sortBy);
		Pageable pageable = PageRequest.of(page-1, PAGE_SIZE, sort);
		
		return productRepository.findAll(specifications, pageable);
	}

	@Override
	public Page<Product> searchProducts(String keyword, Double minPrice, Double maxPrice, 
			String sortBy, int page) {
		
		Specification<Product> specifications = Specification.where(containsKeyword(keyword));
		
		if (minPrice != null && maxPrice != null) {
			specifications = Specification.where(specifications).and(hasUnitPriceBetween(minPrice, maxPrice));
		}
		Sort sort = getSort(sortBy);
		Pageable pageable = PageRequest.of(page-1, PAGE_SIZE, sort);
		
		return productRepository.findAll(specifications, pageable);
	}

//	@Override
//	public List<Product> findBySupplier(Supplier supplier) {
//		return productRepository.findBySupplier(supplier);
//	}
	
	@Override
	public List<Product> findBySupplier(Supplier supplier, String keyword) {
		
		Specification<Product> specifications = Specification.where(hasSupplier(supplier));
		
		if (keyword != null) {
			specifications = Specification.where(specifications).and(containsKeyword(keyword));
		}
		
		return productRepository.findAll(specifications, Sort.by("name").ascending());
	}

	@Override
	public void setCurrentPurchaseOrder(Integer productId, Integer purchaseOrderId) {

		PurchaseOrderDetail pod = podRepository.findByPurchaseOrderIdAndProductId(purchaseOrderId, productId);
		Product product = findById(productId);
		product.setCurrentPO(purchaseOrderId);
		product.setExpiryDate(pod.getExpiryDate());
		product.setQuantity(pod.getInStock());
		product.setAvailable(true);
		save(product);
	}

}
