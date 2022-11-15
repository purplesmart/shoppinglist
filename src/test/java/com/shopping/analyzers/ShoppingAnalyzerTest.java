package com.shopping.analyzers;

import com.shopping.model.Catalog;
import com.shopping.model.Receipt;
import com.shopping.model.ShoppingItem;
import com.shopping.model.ShoppingList;
import com.shopping.repositories.CatalogRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.junit.Assert;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShoppingAnalyzerTest {

    private CatalogRepository catalogRepository;

    private ShoppingAnalyzer shoppingAnalyzer;

    @BeforeAll
    public void setUp() {
        catalogRepository = Mockito.mock(CatalogRepository.class);
        buildRepositoryMockResponses();
        shoppingAnalyzer = new ShoppingAnalyzer(catalogRepository);
    }


    @Test
    void Groceries2_Json_Data() {
        ShoppingList shoppingList = getShoppingList(getGroceries2JsonData());
        Receipt receipt = shoppingAnalyzer.getReceipt(shoppingList);
        Assert.assertNotNull(receipt);
        Assert.assertEquals("275.91",receipt.totalAmountDisplay);
        Assert.assertEquals(4,receipt.recordList.size());
    }

    @Test
    void Groceries1_Json_Data() {
        ShoppingList shoppingList = getShoppingList(getGroceries1JsonData());
        Receipt receipt = shoppingAnalyzer.getReceipt(shoppingList);
        Assert.assertNotNull(receipt);
        Assert.assertEquals("435.57",receipt.totalAmountDisplay);
        Assert.assertEquals(8,receipt.recordList.size());
    }

    private Map<String,Integer> getGroceries2JsonData(){
        Map<String, Integer> items = new HashMap<>();
        items.put("Now Foods, Real Food, Organic Triple Omega Seed Mix, 12 oz (340 g)",0);
        items.put("Nutiva, Organic, Hemp Protein Hi-Fiber, 3 lbs (1.36 kg)",2);
        items.put("General Mills, Cheerios, 12 oz (340 g)",12);
        items.put("Kashi, Cinnamon French Toast Cereal, 10 oz (283 g)",1);
        items.put("",10);
        return items;
    }

    private Map<String,Integer> getGroceries1JsonData(){
        Map<String, Integer> items = new HashMap<>();
        items.put("Yogi Tea, Detox, Caffeine Free, 32 Tea Bags, 2.04 oz (58 g)",2);
        items.put("Clif Bar, Energy Bar, Chocolate Brownie, 12 Bars, 2.40 oz (68 g) Each",1);
        items.put("Lakanto, Drinking Chocolate Mix, 10 oz",5);
        items.put("Sprout Living, Organic Coconut Water Powder, 8 oz (225 g)",3);
        items.put("Bergin Fruit and Nut Company, Banana Chips, 9 oz (255 g)",10);
        items.put("Chimes, Ginger Chews, Orange, 5 oz (141.8 g)",1);
        items.put("Organic Traditions, Dried Jujube Fruit, 6 oz (170 g)",5);
        items.put("Sunfood, Raw Organic Brazil Nuts, 8 oz (227 g)",2);
        return items;
    }

    private void buildRepositoryMockResponses(){
        buildGroceries2RepositoryMockResponses();
        buildGroceries1RepositoryMockResponses();
    }

    private void buildGroceries2RepositoryMockResponses(){
        Catalog catalog01 = new Catalog();
        catalog01.setProductId(52642);
        catalog01.setProductName("Now Foods, Real Food, Organic Triple Omega Seed Mix, 12 oz (340 g)");
        catalog01.setProductCompany("Now Foods, Seeds & Nuts");
        catalog01.setProductWeight(340);
        catalog01.setProductPrice(12.04);
        Mockito.when(catalogRepository.findByProductName(catalog01.getProductName()))
                .thenReturn(List.of(catalog01));

        Catalog catalog02 = new Catalog();
        catalog02.setProductId(11230);
        catalog02.setProductName("Nutiva, Organic, Hemp Protein Hi-Fiber, 3 lbs (1.36 kg)");
        catalog02.setProductCompany("Nutiva, Seeds & Nuts");
        catalog02.setProductWeight(1);
        catalog02.setProductPrice(66.5);
        Mockito.when(catalogRepository.findByProductName(catalog02.getProductName()))
                .thenReturn(List.of(catalog02));

        Catalog catalog03 = new Catalog();
        catalog03.setProductId(58797);
        catalog03.setProductName("General Mills, Cheerios, 12 oz (340 g)");
        catalog03.setProductCompany("General Mills, Breakfast");
        catalog03.setProductWeight(340);
        catalog03.setProductPrice(10.12);
        Mockito.when(catalogRepository.findByProductName(catalog03.getProductName()))
                .thenReturn(List.of(catalog03));

        Catalog catalog04 = new Catalog();
        catalog04.setProductId(80591);
        catalog04.setProductName("Kashi, Cinnamon French Toast Cereal, 10 oz (283 g)");
        catalog04.setProductCompany("Kashi, Breakfast");
        catalog04.setProductWeight(283);
        catalog04.setProductPrice(9.43);
        Mockito.when(catalogRepository.findByProductName(catalog04.getProductName()))
                .thenReturn(List.of(catalog04));
    }

    private void buildGroceries1RepositoryMockResponses(){
        Catalog catalog01 = new Catalog();
        catalog01.setProductId(52642);
        catalog01.setProductName("Yogi Tea, Detox, Caffeine Free, 32 Tea Bags, 2.04 oz (58 g)");
        catalog01.setProductCompany("Yogi Tea");
        catalog01.setProductWeight(58);
        catalog01.setProductPrice(9.77);
        Mockito.when(catalogRepository.findByProductName(catalog01.getProductName()))
                .thenReturn(List.of(catalog01));

        Catalog catalog02 = new Catalog();
        catalog02.setProductId(11230);
        catalog02.setProductName("Clif Bar, Energy Bar, Chocolate Brownie, 12 Bars, 2.40 oz (68 g) Each");
        catalog02.setProductCompany("Clif Bar,bars snack");
        catalog02.setProductWeight(68);
        catalog02.setProductPrice(36.72);
        Mockito.when(catalogRepository.findByProductName(catalog02.getProductName()))
                .thenReturn(List.of(catalog02));

        Catalog catalog03 = new Catalog();
        catalog03.setProductId(58797);
        catalog03.setProductName("Lakanto, Drinking Chocolate Mix, 10 oz");
        catalog03.setProductCompany("Lakanto,Cocoa");
        catalog03.setProductWeight(10);
        catalog03.setProductPrice(18.09);
        Mockito.when(catalogRepository.findByProductName(catalog03.getProductName()))
                .thenReturn(List.of(catalog03));

        Catalog catalog04 = new Catalog();
        catalog04.setProductId(80591);
        catalog04.setProductName("Sprout Living, Organic Coconut Water Powder, 8 oz (225 g)");
        catalog04.setProductCompany("Sprout Living,Coconut");
        catalog04.setProductWeight(225);
        catalog04.setProductPrice(39.79);
        Mockito.when(catalogRepository.findByProductName(catalog04.getProductName()))
                .thenReturn(List.of(catalog04));

        Catalog catalog05 = new Catalog();
        catalog05.setProductId(52642);
        catalog05.setProductName("Bergin Fruit and Nut Company, Banana Chips, 9 oz (255 g)");
        catalog05.setProductCompany("Bergin Fruit and Nut Company");
        catalog05.setProductWeight(225);
        catalog05.setProductPrice(7.07);
        Mockito.when(catalogRepository.findByProductName(catalog05.getProductName()))
                .thenReturn(List.of(catalog05));

        Catalog catalog06 = new Catalog();
        catalog06.setProductId(11230);
        catalog06.setProductName("Chimes, Ginger Chews, Orange, 5 oz (141.8 g)");
        catalog06.setProductCompany("Chimes,Fruit & Vegetables");
        catalog06.setProductWeight(141);
        catalog06.setProductPrice(5.99);
        Mockito.when(catalogRepository.findByProductName(catalog06.getProductName()))
                .thenReturn(List.of(catalog06));

        Catalog catalog07 = new Catalog();
        catalog07.setProductId(58797);
        catalog07.setProductName("Organic Traditions, Dried Jujube Fruit, 6 oz (170 g)");
        catalog07.setProductCompany("Organic Traditions");
        catalog07.setProductWeight(170);
        catalog07.setProductPrice(7.86);
        Mockito.when(catalogRepository.findByProductName(catalog07.getProductName()))
                .thenReturn(List.of(catalog07));

        Catalog catalog08 = new Catalog();
        catalog08.setProductId(80591);
        catalog08.setProductName("Sunfood, Raw Organic Brazil Nuts, 8 oz (227 g)");
        catalog08.setProductCompany("Sunfood, Seeds & Nuts");
        catalog08.setProductWeight(227);
        catalog08.setProductPrice(26.75);
        Mockito.when(catalogRepository.findByProductName(catalog08.getProductName()))
                .thenReturn(List.of(catalog08));
    }

    private ShoppingItem getRecord(String name, int quantity){
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setName(name);
        shoppingItem.setQuantity(quantity);
        return shoppingItem;
    }

    private ShoppingList getShoppingList(Map<String, Integer> items){
        List<ShoppingItem> shoppingItemList = new ArrayList<>();
        for (var entry : items.entrySet()) {
            ShoppingItem shoppingItem = new ShoppingItem();
            shoppingItem.setName(entry.getKey());
            shoppingItem.setQuantity(entry.getValue());
            shoppingItemList.add(shoppingItem);
        }
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.groceries = shoppingItemList;
        return shoppingList;
    }

}