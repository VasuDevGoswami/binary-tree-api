package com.example.demo.service;

import com.example.demo.dao.NodeRepository;
import com.example.demo.model.Node;
import com.example.demo.model.NodeResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vasu Dev
 */
@Service
public class NodeService {

    @Autowired
    NodeRepository nodeRepository;

    public Node createNode(int value) {
        BinaryTree binaryTree = new BinaryTree(nodeRepository.findTopByIdIsNotNull());
        binaryTree.add(value);
        return nodeRepository.save(binaryTree.getRoot());
    }

    public NodeResponse getDescendants(int value) {

        NodeResponse nodeResponse = new NodeResponse();

        BinaryTree binaryTree = new BinaryTree(nodeRepository.findTopByIdIsNotNull());
        binaryTree.getListTraversed().clear();
        binaryTree.traverseInOrder(binaryTree.getNode(value));


        nodeResponse.setExistingParentNode(binaryTree.getParentNode(binaryTree.getNode(value)).value);
        nodeResponse.setNewParentNode(binaryTree.getParentNode(binaryTree.getNode(value)).value);
        nodeResponse.setInOrderNodeTraversal(binaryTree.getListTraversed());
        nodeResponse.setHeightFromCurrentNode(binaryTree.getSizeFromCurrentNode(value));
        nodeResponse.setHeightOfOverallTree(binaryTree.getSize());
        nodeResponse.setCurrentNode(value);
        nodeResponse.setRootNode(binaryTree.getRoot().value);
        nodeResponse.setInOrderNodeTraversal(binaryTree.getListTraversed());
        return nodeResponse;
    }

    public NodeResponse changeParent(int value) {

        NodeResponse nodeResponse = new NodeResponse();

        BinaryTree binaryTree = new BinaryTree(nodeRepository.findTopByIdIsNotNull());
        // get parent node of the node whose parent is to update
        Node parentNode = binaryTree.getParentNode(binaryTree.getNode(value));

        nodeResponse.setExistingParentNode(parentNode.value);

        // delete node and rearrange the tree
        binaryTree.delete(parentNode.value);

        // re-insert node the deleted node

        binaryTree.add(parentNode.value);

        // get new parent
        Node newParent = binaryTree.getParentNode(binaryTree.getNode(value));
        nodeResponse.setNewParentNode(newParent.value);
        binaryTree.getListTraversed().clear();
        binaryTree.traverseInOrder(binaryTree.getRoot());

        nodeResponse.setInOrderNodeTraversal(binaryTree.getListTraversed());
        nodeResponse.setHeightFromCurrentNode(binaryTree.getSizeFromCurrentNode(value));
        nodeResponse.setHeightOfOverallTree(binaryTree.getSize());
        nodeResponse.setCurrentNode(value);
        nodeResponse.setRootNode(binaryTree.getRoot().value);

        nodeRepository.save(binaryTree.getRoot());
        return nodeResponse;
    }
}
