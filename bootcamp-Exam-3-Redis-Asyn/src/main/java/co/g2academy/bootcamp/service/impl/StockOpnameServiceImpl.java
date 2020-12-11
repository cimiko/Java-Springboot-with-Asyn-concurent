/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.service.impl;

import co.g2academy.bootcamp.Repository.BackgroundProcessRepository;
import co.g2academy.bootcamp.Repository.ProductRepository;
import co.g2academy.bootcamp.entity.BackgroundProcess;
import co.g2academy.bootcamp.entity.Product;
import co.g2academy.bootcamp.service.StockOpnameService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author cimiko
 */
@Service
public class StockOpnameServiceImpl implements StockOpnameService{
    
    private static final Logger LOG
            = LoggerFactory.getLogger(StockOpnameServiceImpl.class);
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private BackgroundProcessRepository backgroundProcessRepository;
    
//    @Async
//    @Override
//    public void stockOpname(){
//        Iterable<Product> products = productRepository.findAll();
//        List<Product> productList = new ArrayList<>();
//    }
    
//    @Async
//    public void stockOpname(List<Product> products){
//        for(Product p : products){
//            //stock opname dummy process! update stock product by 1
//            p.setStock(p.getStock() + 1);
//            productRepository.save(p);
//            //increase current process by 1 in background process
//            BackgroundProcess stockOpname = 
//                    backgroundProcessRepository.findByTypeAndStatus("STOCK_OPNAME", "ONPROGRESS");
//            
//            if(stockOpname != null){
//                stockOpname.setCurrentProgress(stockOpname.getCurrentProgress() + 1);
//                backgroundProcessRepository.save(stockOpname);
//            }
//            
//            LOG.info("Updating product " + p.getId());
//        }
//    }
    
    @Async
    @Override
    public void stockOpname(List<Product> products, Integer taskExecutorId){
        BackgroundProcess stockOpnameProcess = new BackgroundProcess();
        stockOpnameProcess.setMaximumValue(Long.valueOf(products.size()));
        stockOpnameProcess.setCurrentProgress(0L);
        stockOpnameProcess.setType("STOCK_OPNAME");
        stockOpnameProcess.setStatus("ON_PROGRESS");
        stockOpnameProcess.setTaskExecutorId(taskExecutorId);
        backgroundProcessRepository.save(stockOpnameProcess); 
        
        for(Product p : products){
            //stock opname dummy process! update stock product by 1
            p.setStock(p.getStock() + 1);
            productRepository.save(p);
            //increase current process by 1 in background process
            BackgroundProcess stockOpname = 
                    backgroundProcessRepository.findByTypeAndStatusAndTaskExecutorId(
                            "STOCK_OPNAME", "ON_PROGRESS", taskExecutorId);
            
            if(stockOpname != null){
                stockOpname.setCurrentProgress(stockOpname.getCurrentProgress() + 1);
                backgroundProcessRepository.save(stockOpname);
            }
            
            LOG.info("Updating product " + p.getId());
        }
    }
    
//    @Async
//    @Override
//    public CompletableFuture<List<Product>> stockOpname(List<Product> products){
//        for(Product p : products){
//            //stock opname dummy process! update stock product by 1
//            p.setStock(p.getStock() + 1);
//            productRepository.save(p);
//            //increase current process by 1 in background process
//            BackgroundProcess stockOpname = 
//                    backgroundProcessRepository.findByTypeAndStatus("STOCK_OPNAME", "ONPROGRESS");
//            
//            if(stockOpname != null){
//                stockOpname.setCurrentProgress(stockOpname.getCurrentProgress() + 1);
//                backgroundProcessRepository.save(stockOpname);
//            }
//            
//            LOG.info("Updating product " + p.getId());
//        }
//        return CompletableFuture.completedFuture(products);
//    }
    
}
