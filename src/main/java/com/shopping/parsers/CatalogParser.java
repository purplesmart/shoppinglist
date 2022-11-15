package com.shopping.parsers;

import com.shopping.Configuration.CatalogInventoryConfiguration;
import com.shopping.ExceptionsHandle.Exceptions.*;
import com.shopping.model.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.*;
import java.util.*;

@Component
public class CatalogParser {

    private final String fieldDelimiter = ",";
    private final char nameDelimiter = '"';
    private final String weightDelimiter = " ";
    private final int PRODUCT_ID = 0;
    private final int PRODUCT_COMPANY = 1;
    private final int PRODUCT_CATEGORY = 2;
    private final int PRODUCT_WEIGHT = 3;
    private final int PRODUCT_PRICE = 4;

    @Autowired
    private CatalogInventoryConfiguration catalogInventoryConfiguration;

    @Bean
    public List<Catalog> getCatalog() throws CatalogSourceNotFoundException, CatalogSourceLoadingFailureException {
        URL resource = getResource();
        return parseCatalog(resource);
    }

    private List<Catalog> parseCatalog(URL resource) throws CatalogSourceNotFoundException, CatalogSourceLoadingFailureException {

        List<Catalog> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(resource.toURI()));) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                Catalog record = getCatalogFromLine(scanner.nextLine().trim());
                records.add(record);
            }
        } catch (FileNotFoundException ex) {
            throw new CatalogSourceNotFoundException(ex.getMessage());
        } catch (URISyntaxException e) {
            throw new CatalogSourceLoadingFailureException(e.getMessage());
        }
        return records;
    }

    private URL getResource() throws CatalogSourceNotFoundException {
        URL resource = getClass()
                .getClassLoader()
                .getResource(catalogInventoryConfiguration.getCatalogResourcePath());
        if(resource == null){
            throw new
                    CatalogSourceNotFoundException("URL Resource null: " + catalogInventoryConfiguration.getCatalogResourcePath());
        }
        return resource;
    }

    private Catalog getCatalogFromLine(String source) {
        Catalog catalog = new Catalog();

        int nameStartIndex = source.indexOf(nameDelimiter);
        int nameEndIndex = source.indexOf(nameDelimiter, nameStartIndex + 1);
        catalog.setProductId(Integer.parseInt(source.substring(PRODUCT_ID, nameStartIndex - 1)));
        catalog.setProductName(source.substring(nameStartIndex + 1, nameEndIndex));
        String[] values = source.substring(nameEndIndex + 1).split(fieldDelimiter);
        catalog.setProductCompany(values[PRODUCT_COMPANY].trim());
        catalog.setProductCategory(values[PRODUCT_CATEGORY].trim());
        catalog.setProductWeight(tryParseDouble(values[PRODUCT_WEIGHT].split(weightDelimiter)[0]));
        catalog.setProductPrice(tryParseDouble(values[PRODUCT_PRICE]));
        return catalog;
    }

    private double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception ex) {
            return 0.0;
        }
    }

}
