package com.specialtyshop.specifications;

import java.util.Collection;

import org.springframework.data.jpa.domain.Specification;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.Supplier;

public class ProductSpecs {

	public static Specification<Product> hasCategoryId(Integer categoryId) {
		return (root, query, builder) -> {
			return builder.equal(root.get("category").get("id"), categoryId);
		};
	}
	
	public static Specification<Product> hasMainCategoryId(Integer categoryId) {
		return (root, query, builder) -> {
			return builder.equal(root.get("category").get("parent").get("id"), categoryId);
		};
	}
	
	public static Specification<Product> hasUnitPriceBetween(double minPrice, double maxPrice) {
		return (root, query, builder) -> {
			return builder.between(root.get("unitPrice"), minPrice, maxPrice);
		};
	}
	
	public static Specification<Product> hasSupplier(Supplier supplier) {
		return (root, query, builder) -> {
			return builder.equal(root.get("supplier"), supplier);
		};
	}
	
//	public static Specification<Product> orderByProductDate() {
//		return (root, query, builder) -> {
//			query.orderBy(builder.desc(root.get("productDate")));
//			return null;
//		};
//	}
	
	public static Specification<Product> orderBySizeOfOrderDetails() {
		return (root, query, builder) -> {
			query.orderBy(builder.desc(builder.size(root.<Collection>get("orderDetails"))));
			return null;
		};
	}
	
	public static Specification<Product> containsKeyword(String keyword) {
		return (root, query, builder) -> {
			return builder.or(
					builder.like(root.get("name"), "%" + keyword + "%"),
					builder.like(root.get("category").get("name"), "%" + keyword + "%"),
					builder.like(root.get("category").get("parent").get("name"), "%" + keyword + "%")
					);
		};
	}
}
