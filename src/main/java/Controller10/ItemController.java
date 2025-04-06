package Controller10;

import DTO.Request.ItemRequest;
import DTO.Response.ItemResponse;
import Entity.ItemEntity;
import Service.CartService;
import Service.ItemService;
import Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "home";
    }


    @GetMapping("/items")
    public Page<ItemEntity> itemAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size, @RequestParam String sortBy) {
        return itemService.findAll(page,size,sortBy);
    }

    @PostMapping("/item")
    public ItemEntity addItem(@RequestBody ItemEntity item){
        return itemService.addItem(item);
    }

    @PutMapping("/item/{id}")
    public ItemResponse fixItem(@Valid @RequestBody ItemRequest item, @PathVariable int id){
        ItemResponse itemResponse = itemService.fixItem(item,id);
        System.out.println(itemResponse);
        return itemResponse;
    }

    @DeleteMapping("/item/{id}")
    public void delete(@PathVariable int id){
        itemService.delete(id);
    }




}
