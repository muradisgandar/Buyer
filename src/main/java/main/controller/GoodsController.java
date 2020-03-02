/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.controller;

import java.util.List;
import main.dto.GoodsDTO;
import main.dto.ResponseDTO;
import main.entities.Goods;
import main.mapper.GoodsMapper;
import main.services.inter.GoodsServiceInter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public GoodsController(GoodsServiceInter goodsServiceInter) {
        this.goodsServiceInter = goodsServiceInter;
    }

    @GetMapping
    public ResponseEntity goods() {
        List<Goods> goods = goodsServiceInter.getAllGoods();
        if (goods != null && !goods.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Goods are founded", 200, new GoodsMapper().mapEntityListToDtoList(goods)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("There are not any goods in database", 500, null));
        }

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity goodsById(@PathVariable(value = "id") Integer id) {
        Goods goods = goodsServiceInter.getGoodsById(id);
        if (goods != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Goods are founded", 200, new GoodsMapper().mapEntityToDto(goods)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("No such element founded", 500, null));
        }
    }

    @PostMapping
    public ResponseEntity addGoods(@RequestBody GoodsDTO goodsDTO) {
        if (goodsServiceInter.addGoods(goodsDTO)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Goods are inserted successfully", 200, goodsDTO));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("Failed in insert operation", 500, goodsDTO));
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateGoods(@PathVariable(value = "id") Integer id, @RequestBody GoodsDTO goodsDTO) {
        if(goodsServiceInter.updateGoods(id, goodsDTO)){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(("Goods by id = "+ id + " are updated successfully"), 200, goodsServiceInter.getGoodsById(id)));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("Failed in update operation", 500, goodsDTO));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteGoods(@PathVariable(value = "id") Integer id) {
        if(goodsServiceInter.deleteGoods(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(("Goods by id = "+ id + " are deleted successfully"), 200, ("Goods' id = "+id)));
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("Failed in delete operation", 500, null));
        }
    }

}
