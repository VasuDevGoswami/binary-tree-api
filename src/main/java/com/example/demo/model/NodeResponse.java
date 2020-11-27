package com.example.demo.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vasu Dev
 */
@Getter
@Setter
public class NodeResponse {

    int currentNode;
    int rootNode;

    int existingParentNode;
    int newParentNode;

    int heightFromCurrentNode;
    int heightOfOverallTree;

    List<Integer> inOrderNodeTraversal;

}
