package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.NoArgsConstructor;

/**
 * @author Vasu Dev
 */
@Entity
@NoArgsConstructor
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public int value;

    @ManyToOne(cascade = CascadeType.ALL)
    public Node left;

    @ManyToOne(cascade = CascadeType.ALL)
    public Node right;

    public Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}
