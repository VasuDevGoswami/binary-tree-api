package com.example.demo.resource;

import com.example.demo.model.Node;
import com.example.demo.model.NodeResponse;
import com.example.demo.service.NodeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vasu Dev
 */
@RestController
@RequestMapping("/api")
public class NodeResource {

    @Autowired
    NodeService nodeService;

    @GetMapping("/add/{value}")
    public Node addNode(@PathVariable("value") int value) {
        return nodeService.createNode(value);
    }

    @GetMapping("/get/{value}")
    public NodeResponse getDescendantNode(@PathVariable("value") int value) {
        return nodeService.getDescendants(value);
    }

    @GetMapping("change/{value}")
    public NodeResponse changeParent(@PathVariable("value") int value) {
        return nodeService.changeParent(value);
    }
}
