/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author murad_isgandar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsDTO {
    
    private Integer id;
    private String name;
    private Integer quantity;
    private BigDecimal cost;
}
