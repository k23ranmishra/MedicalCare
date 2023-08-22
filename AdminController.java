package rkm.ecom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import rkm.ecom.userDAO;
import rkm.ecom.OrderDAO;
import rkm.ecom.ProductDAO;
import rkm.ecom.Product;
import rkm.ecom.User;

import rkm.ecom.ProductForm;
import rkm.ecom.OrderDetailInfo;
import rkm.ecom.OrderInfo;
import rkm.ecom.PaginationResult;
import rkm.ecom.ProductFormValidator;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
public class AdminController {

   @Autowired
   private OrderDAO orderDAO;

   @Autowired
   private ProductDAO productDAO;
   
   @Autowired
   private userDAO userDAO;

   @Autowired
   private ProductFormValidator productFormValidator;

   @InitBinder
   public void myInitBinder(WebDataBinder dataBinder) {
      Object target = dataBinder.getTarget();
      if (target == null) {
         return;
      }
      System.out.println("Target=" + target);

      if (target.getClass() == ProductForm.class) {
         dataBinder.setValidator(productFormValidator);
      }
   }

   // GET: Show Login Page
   @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
   public String login(Model model) {

      return "login";
   }
   
   // GET: Show Payment Page
   @RequestMapping(value = { "/paymentCustomer" }, method = RequestMethod.GET)
   public String Paytm(HttpServletRequest request, Model model) {
	      CartInfo cartInfo = Utils.getCartInSession(request);

	      if (cartInfo == null || cartInfo.isEmpty()) {

	         return "redirect:/shoppingCart";
	      } else if (!cartInfo.isValidCustomer()) {

	         return "redirect:/shoppingCartCustomer";
	      }
	      model.addAttribute("myCart", cartInfo);
      return "homePayment";
   }
   
 
   
// GET: Enter registeration information.
   @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
   public String User(HttpServletRequest request, Model model) {

	      User user= new User();
	      model.addAttribute("user", user);

      return "/register";
   }
   
// POST: Save User
   @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
   public String userSave(Model model, //
	         @ModelAttribute("user") @Validated User user, //
             BindingResult result, //
         final RedirectAttributes redirectAttributes) {

      if (result.hasErrors()) {
    	  
     	 System.out.println("result.hasError() evaluated true  "); 
         userDAO.saveUser(user);

 
         return "register";
      }
      try {
    	  
    	 System.out.println("Start Calling userDAO.saveUser method "); 
         userDAO.saveUser(user);
    	 System.out.println("End Calling userDAO.saveUser method "); 

      } catch (Exception e) {
     	 System.out.println("Exception occured while Calling userDAO.saveUser method "); 

          return "/register";
      }

      return "/login";
   }

      @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
   public String accountInfo(Model model) {

      UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      System.out.println(userDetails.getPassword());
      System.out.println(userDetails.getUsername());
      System.out.println(userDetails.isEnabled());

      model.addAttribute("userDetails", userDetails);
      return "accountInfo";
   }

   @RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
   public String orderList(Model model, //
         @RequestParam(value = "page", defaultValue = "1") String pageStr) {
      int page = 1;
      try {
         page = Integer.parseInt(pageStr);
      } catch (Exception e) {
      }
      final int MAX_RESULT = 5;
      final int MAX_NAVIGATION_PAGE = 10;

      PaginationResult<OrderInfo> paginationResult //
            = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

      model.addAttribute("paginationResult", paginationResult);
      return "orderList";
   }

   
   // GET: Show product.
   @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
   public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
      ProductForm productForm = null;

      if (code != null && code.length() > 0) {
         Product product = productDAO.findProduct(code);
         if (product != null) {
            productForm = new ProductForm(product);
         }
      }
      if (productForm == null) {
         productForm = new ProductForm();
         productForm.setNewProduct(true);
      }
      model.addAttribute("productForm", productForm);
      return "product";
   }

   // POST: Save product
   @RequestMapping(value = { "/product" }, method = RequestMethod.POST)
   public String productSave(Model model, //
         @ModelAttribute("productForm") @Validated ProductForm productForm, //
         BindingResult result, //
         final RedirectAttributes redirectAttributes) {

      if (result.hasErrors()) {
         return "product";
      }
      try {
         productDAO.save(productForm);
      } catch (Exception e) {
        // Throwable rootCause = ExceptionUtils.getRootCause(e);
         //String message = rootCause.getMessage();
         //model.addAttribute("errorMessage", message);
         // Show product form.
         return "product";
      }

      return "redirect:/productList";
   }

   @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
   public String orderView(Model model, @RequestParam("orderId") String orderId) {
      OrderInfo orderInfo = null;
      if (orderId != null) {
         orderInfo = this.orderDAO.getOrderInfo(orderId);
      }
      if (orderInfo == null) {
         return "redirect:/orderList";
      }
      List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
      orderInfo.setDetails(details);

      model.addAttribute("orderInfo", orderInfo);

      return "order";
   }

}
