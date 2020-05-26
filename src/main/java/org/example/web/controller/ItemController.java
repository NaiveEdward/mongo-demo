package org.example.web.controller;

import org.example.service.IItemService;
import org.example.web.dto.ItemListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @GetMapping("/list/{pageNum}/{pageSize}")
    public List<ItemListDTO> list(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
                                  String keyword,
                                  String sortDirection,
                                  String sortColumn) {
        return itemService.list(pageNum, pageSize, keyword,sortDirection,sortColumn);
    }
}
