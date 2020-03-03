/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.services.impl;

import java.util.List;
import main.dao.GoodsRepository;
import main.dto.GoodsDTO;
import main.entities.Goods;
import main.services.inter.GoodsServiceInter;
import org.springframework.stereotype.Service;

/**
 *
 * @author murad_isgandar
 */
@Service
public class GoodsServiceImpl implements GoodsServiceInter {

    private final GoodsRepository goodsRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<Goods> getAllGoods() {
        try {
            List<Goods> goods = goodsRepository.findAll();
            return goods;
        } catch (Exception e) {
            System.out.println("There are not any goods in database");
        }

        return null;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        try {
            Goods goods = goodsRepository.findById(id).get();
            return goods;
        } catch (Exception e) {
            System.out.println("No such element founded");
        }

        return null;
    }

    @Override
    public boolean addGoods(GoodsDTO goodsDTO) {
        try {
            Goods g = new Goods();
            g.setName(goodsDTO.getName());
            g.setQuantity(goodsDTO.getQuantity());
            g.setCost(goodsDTO.getCost());

            goodsRepository.save(g);
            return true;

        } catch (Exception e) {
            System.out.println("Failed in insert operation ");
        }

        return false;

    }

    @Override
    public boolean updateGoods(Integer id, GoodsDTO goodsDTO) {
        try {
            Goods g = goodsRepository.findById(id).get();
            if (goodsDTO.getName() != null && !goodsDTO.getName().isEmpty()) {
                g.setName(goodsDTO.getName());
            }
            if (goodsDTO.getQuantity() != null) {
                g.setQuantity(goodsDTO.getQuantity());
            }
            if (goodsDTO.getCost() != null) {
                g.setCost(goodsDTO.getCost());
            }

            goodsRepository.save(g);
            return true;
        } catch (Exception e) {
            System.out.println("Failed in update operation");
        }

        return false;
    }

    @Override
    public boolean deleteGoods(Integer id) {
        try {
            goodsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Failed in delete operation");
        }

        return false;
    }

    @Override
    public Goods increaseGoodsQuantity(String name, Integer quantity) {
        Goods goods = goodsRepository.findByName(name);
        int newQuantity = goods.getQuantity() + quantity;
        goods.setQuantity(newQuantity);
        
        goodsRepository.save(goods);
        
        return goods;
    }

    @Override
    public Goods decreaseGoodsQuantity(Integer id, Integer quantity) {
        Goods goods = goodsRepository.findById(id).get();
        int q = goods.getQuantity();
        int newQuantity = q - quantity;
        
        goods.setQuantity(newQuantity);
        
        goodsRepository.save(goods);
        
        return goods;
        
        
    }

}
