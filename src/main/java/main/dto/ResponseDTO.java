/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author murad_isgandar
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    
    private String message;
    private int statusCode;
    private Object obj;
    
}
