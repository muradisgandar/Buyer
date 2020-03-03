/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

import main.entities.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author murad_isgandar
 */
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer>  {
    
    Goods findByName(String name);
}
