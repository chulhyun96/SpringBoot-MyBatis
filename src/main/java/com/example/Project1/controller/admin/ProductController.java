package com.example.Project1.controller.admin;

import com.example.Project1.entity.Category;
import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.service.CategoryService;
import com.example.Project1.service.DetailImgService;
import com.example.Project1.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequestMapping("/admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private static final String PRODUCTS_VIEW = "/admin/products";
    private static final String REDIRECT = "redirect:";

    private final ProductService service;

    private final CategoryService categoryService;

    private final DetailImgService detailImgService;

    @GetMapping
    public String list(@RequestParam(required = false) String type,
                       @RequestParam(defaultValue = "") String keyword,
                       Model model) {
        List<ProductView> list = service.getList(type, keyword.trim());
        model.addAttribute("list", list);
        return PRODUCTS_VIEW + "/list";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String edit(@RequestBody Product product) {
        service.edit(product);
        return "success";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductView product = service.getById(id);
        model.addAttribute("product", product);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping
    public String reg(@Valid @ModelAttribute Product product, BindingResult bindingResult,MultipartFile img,
                      HttpServletRequest req,
                      Long categoryId
            /*String paths*/) throws FileUploadException {

        if (bindingResult.hasFieldErrors("name")) {
            return REDIRECT + PRODUCTS_VIEW + "/reg";
        }
        Optional<String> productImg = uploadProductImage(img, req);
        product.setCategoryId(categoryId);
        product.setImgPath(productImg.orElseThrow(() -> new FileUploadException("File upload failed")));
        service.reg(product);
        /*detailImgService.regAll(paths, product.getId());*/
        return REDIRECT + PRODUCTS_VIEW;
    }

    private Optional<String> uploadProductImage(MultipartFile img, HttpServletRequest req) {
        String path = "/image/products";
        String realPath = req.getServletContext().getRealPath(path);
        return service.saveImg(img, realPath);
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return PRODUCTS_VIEW + "/reg";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> ids) {
        service.deleteAllById(ids);
        return REDIRECT + PRODUCTS_VIEW;
    }
}