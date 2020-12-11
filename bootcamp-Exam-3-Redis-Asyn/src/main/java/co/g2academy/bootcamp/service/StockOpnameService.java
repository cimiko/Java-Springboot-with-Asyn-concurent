/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.service;

import co.g2academy.bootcamp.entity.Product;
import java.util.List;

/**
 *
 * @author cimiko
 */
public interface StockOpnameService {
   
    public void stockOpname(List<Product> products, Integer taskExecutorId);
    
}
