package com.shopping.services;

import com.shopping.ExceptionsHandle.Exceptions.*;
import com.shopping.model.*;
import com.shopping.parsers.*;
import com.shopping.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private  CatalogParser catalogParser;

    @PostConstruct
    private void initData() throws CatalogSourceLoadingFailureException, CatalogSourceNotFoundException {
        List<Catalog> inventory = catalogParser.getCatalog();
        catalogRepository.saveAll(inventory);
    }

}
