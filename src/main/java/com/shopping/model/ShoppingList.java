package com.shopping.model;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class ShoppingList {

    @Size(min=1)
    @NotNull(message = "Shopping list can't be empty")
    public List<@Valid ShoppingItem> groceries;
}
