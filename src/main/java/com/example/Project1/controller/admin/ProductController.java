package com.example.Project1.controller.admin;

import com.example.Project1.entity.Category;
import com.example.Project1.entity.Product;
import com.example.Project1.entity.ProductView;
import com.example.Project1.service.CategoryService;
import com.example.Project1.service.DetailImgService;
import com.example.Project1.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        for (ProductView productView : list) {
            log.warn("ProductView = {}",productView);
        }

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
        ProductView productView = service.getById(id);
        log.warn("ProductView ID = {}",productView.getId());
        log.warn("ProductView IMG  = {}",productView.getImg());
        model.addAttribute("product", productView);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping
    public String reg(Product product,
                      MultipartFile img,
                      @RequestParam("sub-img") MultipartFile[] subImgs,
                      HttpServletRequest req,
                      Long categoryId) throws FileUploadException {

        String mainImgPath = "/image/products/main";
        Optional<String> productImg = saveImgToDir(img, req, mainImgPath);

        String subImgPath = "/image/products/sub";
        saveImgsToDir(subImgs, req, subImgPath);

        product.setCategoryId(categoryId);
        product.setImgPath(productImg.orElseThrow(() -> new FileUploadException("File upload failed")));

        service.reg(product);
        detailImgService.regAll(subImgs, product.getId());
        return REDIRECT + PRODUCTS_VIEW;
    }

    private void saveImgsToDir(MultipartFile[] subImgs, HttpServletRequest req, String subImgPath) {
        for (MultipartFile subImg : subImgs) {
            saveImgToDir(subImg, req, subImgPath);
        }
    }

    private Optional<String> saveImgToDir(MultipartFile img, HttpServletRequest req, String path) {
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