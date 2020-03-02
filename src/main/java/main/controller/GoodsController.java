/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import java.util.List;
import main.dto.GoodsDTO;
import main.entities.Goods;
import main.mapper.GoodsMapper;
import main.services.inter.GoodsServiceInter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author murad_isgandar
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    
    private final GoodsServiceInter goodsServiceInter;
    
    public GoodsController(GoodsServiceInter goodsServiceInter){
        this.goodsServiceInter = goodsServiceInter;
    }
    
    @GetMapping
    public List<GoodsDTO> goods() {
        
        List<Goods> goods = goodsServiceInter.getAllGoods();
        return new GoodsMapper().mapEntityListToDtoList(goods);
    }
    
    @GetMapping(path = "/{id}")
    public GoodsDTO goodsById(@PathVariable(value = "id") Integer id) {
        Goods goods = goodsServiceInter.getGoodsById(id);
        return new GoodsMapper().mapEntityToDto(goods);
    }
    
    @PostMapping
    public boolean addGoods(@RequestBody GoodsDTO goodsDTO) {
        goodsServiceInter.addGoods(goodsDTO);
        
        return true;
    }
    
    @PutMapping(value = "/{id}")
    public boolean updateGoods(@PathVariable(value = "id") Integer id , @RequestBody GoodsDTO goodsDTO) {
        goodsServiceInter.updateGoods(id,goodsDTO);
        return true;
    }
    
    @DeleteMapping(value = "/{id}")
    public boolean deleteGoods(@PathVariable(value = "id") Integer id) {
        goodsServiceInter.deleteGoods(id);
        return true;
    }
    
}
