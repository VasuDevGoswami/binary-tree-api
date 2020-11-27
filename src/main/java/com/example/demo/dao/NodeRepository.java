package com.example.demo.dao;

import com.example.demo.model.Node;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vasu Dev
 */
public interface NodeRepository extends CrudRepository<Node,Long> {
    Node findTopByIdIsNotNull();
}
