package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CartRepository extends CrudRepository<CartDto, Long> {

    CartDto saveProduct(ProductDto productDto);

    CartDto save(Long userId);

    @Override
    void deleteById(Long productId);


}
