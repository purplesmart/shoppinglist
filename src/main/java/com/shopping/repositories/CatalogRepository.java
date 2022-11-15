package com.shopping.repositories;

import com.shopping.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface CatalogRepository extends JpaRepository<Catalog,String> {
    List<Catalog> findByProductName(String product);
}