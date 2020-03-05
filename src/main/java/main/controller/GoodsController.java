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
import main.services.inter.GoodsFromServer;
import main.services.inter.GoodsServiceInter;
import main.tcpconnection.TCPClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author murad_isgandar
 */
// Rest Api client
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsServiceInter goodsServiceInter;

    private final GoodsFromServer goodsFromServer;

    public GoodsController(GoodsServiceInter goodsServiceInter, GoodsFromServer goodsFromServer) {
        this.goodsServiceInter = goodsServiceInter;
        this.goodsFromServer = goodsFromServer;
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


    @PutMapping(value = "/{id}")
    public ResponseEntity updateGoods(@PathVariable(value = "id") Integer id, @RequestBody GoodsDTO goodsDTO) {
        if (goodsServiceInter.updateGoods(id, goodsDTO)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(("Goods by id = " + id + " are updated successfully"), 200, goodsServiceInter.getGoodsById(id)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("Failed in update operation", 500, goodsDTO));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteGoods(@PathVariable(value = "id") Integer id) {
        if (goodsServiceInter.deleteGoods(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(("Goods by id = " + id + " are deleted successfully"), 200, ("Goods' id = " + id)));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("Failed in delete operation", 500, null));
        }
    }

    @GetMapping(value = "/gName/{quantity}")
    public ResponseEntity recieveGoods(@RequestParam(value = "name") String name, @PathVariable(value = "quantity") Integer quantity) throws Exception {
        TCPClient.sendMessage("We need "+quantity+" "+name+" computers");
        
        Integer quantityFromSupplier = goodsFromServer.recieveGoods(name, quantity);
        TCPClient.recieveMessage(); // recieve message from server

        if (quantityFromSupplier != null && quantityFromSupplier!=0) {
            
            Goods goods = goodsServiceInter.increaseGoodsQuantity(name, quantityFromSupplier);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO((quantity + " " + name + " computers are recieved from supplier"), 200, goods));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("There are not enough goods in supplier ", 500, false));
        }

    }

    @GetMapping(value = "/{gId}/{quantity}")
    public ResponseEntity sellGoods(@PathVariable(value = "gId") Integer id, @PathVariable(value = "quantity") Integer quantity) {
        Goods goods = goodsServiceInter.decreaseGoodsQuantity(id, quantity);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO((quantity + " " + goods.getName() + " computers are sold to customers"), 200, goods));
    }

}
