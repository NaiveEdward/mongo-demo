package org.example.service;

import org.example.web.dto.ItemListDTO;

import java.util.List;

public interface IItemService {
    List<ItemListDTO> list(Integer pageNum, Integer pageSize, String keyword,String sortDirection,String sortColumn);
}
