package com.shopping.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogInventoryConfiguration {
    @Value( "${catalogresourcepath}" )
    private String catalogResourcePath;

    public String getCatalogResourcePath(){
        return catalogResourcePath;
    }
}
