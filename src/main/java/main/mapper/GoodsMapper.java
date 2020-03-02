/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.mapper;

import java.util.List;
import java.util.stream.Collectors;
import main.dto.GoodsDTO;
import main.entities.Goods;
import org.springframework.stereotype.Component;

/**
 *
 * @author murad_isgandar
 */
@Component
public class GoodsMapper {
    
    public GoodsDTO mapEntityToDto(Goods goods){
        return GoodsDTO.builder()
                .id(goods.getId())
                .name(goods.getName())
                .quantity(goods.getQuantity())
                .cost(goods.getCost())
                .build();
        
    }
    
    public List<GoodsDTO> mapEntityListToDtoList(List<Goods> goods){
        return goods.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
        
    }
    
    
    
}
