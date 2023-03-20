package com.specialtyshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specialtyshop.entity.Product;
import com.specialtyshop.entity.PurchaseOrder;
import com.specialtyshop.entity.PurchaseOrderDetail;
import com.specialtyshop.entity.Supplier;
import com.specialtyshop.service.ProductService;
import com.specialtyshop.service.PurchaseOrderDetailService;
import com.specialtyshop.service.PurchaseOrderService;
import com.specialtyshop.service.SupplierService;

@Controller
@RequestMapping("/admin/purchase-order")
public class AdminPurchaseOrderController {

	private PurchaseOrderService purchaseOrderService;
	
	private SupplierService supplierService;
	
	private ProductService productService;
	
	private PurchaseOrderDetailService podService;

	@Autowired
	public AdminPurchaseOrderController(PurchaseOrderService purchaseOrderService, SupplierService supplierService,
			ProductService productService, PurchaseOrderDetailService podService) {
		this.purchaseOrderService = purchaseOrderService;
		this.supplierService = supplierService;
		this.productService = productService;
		this.podService = podService;
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//	    dateFormat.setLenient(false);
//	    dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}

	@GetMapping("/list")
    public String listPurchaseOrders(@RequestParam("page") Optional<Integer> page, Model model) {
    	
    	int currentPage = page.orElse(1);
    	Page<PurchaseOrder> purchaseOrderPage = purchaseOrderService.findPurchaseOrders(currentPage);
        List<PurchaseOrder> purchaseOrders = purchaseOrderPage.getContent();
        model.addAttribute("purchaseOrders", purchaseOrders);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", purchaseOrderPage.getTotalPages());
		
        return "admin/purchase-order-list";
    }

	@GetMapping("/add-form")
    public String showAddPurchaseOrderForm(Model model) {
		
		List<Supplier> suppliers = supplierService.findAll();
        model.addAttribute("purchaseOrder", new PurchaseOrder());
        model.addAttribute("suppliers", suppliers);
        return "admin/purchase-order-form";
    }

    @PostMapping("/save")
    public String savePurchaseOrder(@ModelAttribute("purchaseOrder") PurchaseOrder purchaseOrder) {
    	
    	PurchaseOrder savedPurchaseOrder = purchaseOrderService.save(purchaseOrder);
        return "redirect:/admin/purchase-order/add-product/" + savedPurchaseOrder.getId();
    }

    @GetMapping("/delete")
    public String deletePurchaseOrder(@RequestParam("purchaseOrderId") Integer purchaseOrderId, 
    		RedirectAttributes redirectAttributes) {
    	
    	purchaseOrderService.deleteById(purchaseOrderId);
    	redirectAttributes.addFlashAttribute("message", "Xóa thành công.");
        return "redirect:/admin/purchase-order/list";
    }
    
    @GetMapping("/add-product/{purchaseOrderId}")
    public String showProductsToAdd(@PathVariable("purchaseOrderId") Integer purchaseOrderId, 
    		@RequestParam(name="keyword", required=false) String keyword, 
    		Model model) {
    	
    	PurchaseOrder purchaseOrder = purchaseOrderService.findById(purchaseOrderId);
    	List<Product> products = productService.findBySupplier(purchaseOrder.getSupplier(), keyword);
        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "admin/purchase-order-add-product";
    }
    
    @GetMapping("/add-product-form")
    public String showAddProductForm(@RequestParam("productId") Integer productId, 
    		@RequestParam("purchaseOrderId") Integer purchaseOrderId, 
    		Model model) {
		
    	PurchaseOrderDetail pod = new PurchaseOrderDetail();
    	pod.setPurchaseOrder(purchaseOrderService.findById(purchaseOrderId));
    	pod.setProduct(productService.findById(productId));
        model.addAttribute("pod", pod);
        return "admin/purchase-order-detail-form";
    }
    
    @PostMapping("/save-detail")
    public String savePurchaseOrderDetail(@ModelAttribute("pod") PurchaseOrderDetail pod, 
    		RedirectAttributes redirectAttributes) {
    	
    	podService.save(pod);
    	Integer purchaseOrderId = pod.getPurchaseOrder().getId();
    	purchaseOrderService.updateAmount(purchaseOrderId);
    	redirectAttributes.addFlashAttribute("message", "Thêm thành công.");
    	return "redirect:/admin/purchase-order/add-product/" + purchaseOrderId;
    }
    
    @GetMapping("/detail/{purchaseOrderId}")
    public String viewDetail(@PathVariable("purchaseOrderId") Integer purchaseOrderId, Model model) {
		
    	PurchaseOrder purchaseOrder = purchaseOrderService.findById(purchaseOrderId);
        model.addAttribute("purchaseOrder", purchaseOrder);
        return "admin/purchase-order-detail";
    }
}
