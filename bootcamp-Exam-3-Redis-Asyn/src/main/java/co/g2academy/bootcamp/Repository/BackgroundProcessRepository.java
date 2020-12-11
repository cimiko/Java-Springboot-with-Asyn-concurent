/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.Repository;

import co.g2academy.bootcamp.entity.BackgroundProcess;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author cimiko
 */
public interface BackgroundProcessRepository
        extends CrudRepository<BackgroundProcess, Integer> {

    
    List<BackgroundProcess> findByTypeAndStatus(String type, String status);
    
    BackgroundProcess findByTypeAndStatusAndTaskExecutorId(String type,
            String status, Integer taskExecutorId);
    
    
}
