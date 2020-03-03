/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.services.inter;

import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author murad_isgandar
 */
@FeignClient(value = "supplier", url = "http://localhost:8080")
public interface GoodsFromServer {
    
}
