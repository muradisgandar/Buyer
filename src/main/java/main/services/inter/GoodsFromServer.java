/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.services.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author murad_isgandar
 */
@FeignClient(value = "supplier", url = "http://localhost:8080/goods")
public interface GoodsFromServer {
    
    @GetMapping(value = "/gName/{quantity}")
    public Integer recieveGoods(@RequestParam(value = "gName") String name, @PathVariable(value = "quantity") Integer quantity);
    
}
