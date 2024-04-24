package com.example.Project1.domain.service.product;

import com.example.Project1.domain.dto.request.img.UploadImg;
import com.example.Project1.domain.dto.request.product.ProductListView;
import com.example.Project1.domain.dto.request.product.ProductRequest;
import com.example.Project1.domain.entity.Category;
import com.example.Project1.domain.entity.DeliveryType;
import com.example.Project1.domain.entity.DetailImg;
import com.example.Project1.domain.entity.Product;
import com.example.Project1.domain.repository.ProductRepository;
import com.example.Project1.domain.service.product.img.ImgStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ImgStore imgStore;

    @Transactional
    public void reg(ProductRequest regRequest) throws IOException {
        //Product
        Product product = repository.findById(regRequest.getId());
        Optional<UploadImg> uploadImg = imgStore.storeMainImg(regRequest.getImage(), product);
        Product newProduct = Product.toEntity(regRequest,
                uploadImg.orElseGet(() -> new UploadImg("Non-Img", "Non-Img")));
        repository.saveProduct(newProduct);

        //DetailImg
        List<DetailImg> foundImgs = repository.findImgs(regRequest.getId());
        List<DetailImg> from = imgStore.storeSubImgs(regRequest.getImages(), foundImgs)
                .map(uploadImgs -> DetailImg.from(uploadImgs, newProduct))
                .orElseGet(() -> DetailImg.from(
                        List.of(new UploadImg("Non-Img", "Non-Img")), newProduct));
        repository.saveSubImg(from);

    }

    @Transactional
    public void update(ProductRequest updateRequest) throws IOException {
        //Update Main image
        Product foundProduct = repository.findById(updateRequest.getId());
        Optional<UploadImg> updateImg = imgStore.storeMainImg(updateRequest.getImage(), foundProduct);
        Product updatedProduct = foundProduct.update(updateRequest,
                updateImg.orElseGet(() -> new UploadImg("Non-Img", "Non-Img")));
        repository.updateProduct(updatedProduct);


        //Update Sub image
        List<DetailImg> foundImgs = repository.findImgs(updateRequest.getId());
        List<DetailImg> updateDetailImgs = imgStore.storeSubImgs(updateRequest.getImages(), foundImgs)
                .map(updateUploadImg -> DetailImg.updateOf(updateUploadImg, foundImgs))
                .orElseGet(() -> DetailImg.updateOf(
                        List.of(new UploadImg("Non-Img", "Non-Img")), foundImgs)
                );
        updateSubImgs(updateRequest, foundImgs, updateDetailImgs);
    }

    private void updateSubImgs(ProductRequest updateRequest, List<DetailImg> foundImgs, List<DetailImg> updateDetailImgs) {
        // Request is more than FindImgs
        if (foundImgs.size() < updateRequest.getImagesSize()) {
            List<DetailImg> extraImgs = updateDetailImgs.subList(foundImgs.size(), updateRequest.getImagesSize());
            repository.saveSubImg(extraImgs.stream()
                    .map(newDetailImg -> DetailImg.builder()
                            .productId(updateRequest.getId())
                            .path(newDetailImg.getPath())
                            .build())
                    .toList());
            return;
        }
        // FindImgs is more than Request
        if (foundImgs.size() > updateRequest.getImagesSize()) {
            List<DetailImg> remainingImgs = foundImgs.subList(0, updateRequest.getImagesSize());
            List<DetailImg> deleteImgs = foundImgs.subList(updateRequest.getImagesSize(), foundImgs.size());
            repository.deleteSubImgs(deleteImgs);
            repository.updateSubImgs(remainingImgs);
            return;
        }
        repository.updateSubImgs(updateDetailImgs);
    }

    public List<ProductListView> getList() {
        return repository.findAll();
    }

    public List<Category> getCategories() {
        return repository.findCategories();
    }

    public List<DeliveryType> getDeliveryTypes() {
        return repository.findDeliveryTypes();
    }

    public ProductRequest getProductById(Long id) {
        Product foundProduct = repository.findById(id);
        return ProductRequest.toRequest(foundProduct);
    }
}
