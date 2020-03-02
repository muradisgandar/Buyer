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
        List<Goods> goods = goodsRepository.findAll();
        return goods;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        Goods goods = null;
        try {
            goods = goodsRepository.findById(id).get();
        } catch (Exception e) {
//            System.out.println("No such element founded");
            System.out.println(e.getLocalizedMessage());
        }
        
        return goods;
    }

    @Override
    public boolean addGoods(GoodsDTO goodsDTO) {
        Goods g = new Goods();
        g.setName(goodsDTO.getName());
        g.setQuantity(goodsDTO.getQuantity());
        g.setCost(goodsDTO.getCost());
        
        goodsRepository.save(g);
        return true;
        
    }

    @Override
    public boolean updateGoods(Integer id, GoodsDTO goodsDTO) {
        Goods g =  goodsRepository.findById(id).get();
        if(goodsDTO.getName()!=null && !goodsDTO.getName().isEmpty()){
            g.setName(goodsDTO.getName());
        }
        if(goodsDTO.getQuantity()!=null){
            g.setQuantity(goodsDTO.getQuantity());
        }
        if(goodsDTO.getCost()!=null){
            g.setCost(goodsDTO.getCost());
        }
        
        goodsRepository.save(g);
        return true;
    }

    @Override
    public boolean deleteGoods(Integer id) {
        goodsRepository.deleteById(id);
        return true;
    }

}
