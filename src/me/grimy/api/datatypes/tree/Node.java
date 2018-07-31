package me.grimy.api.datatypes.tree;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class Node<V> {
	
    private Node<V> parent;
    private V value;

    private List<Node<V>> children = new ArrayList<>();

    public Node(V value) {
        this.value = value;
    }

    public Node(Node<V> parent, V value) {
        this.parent = parent;
        this.value = value;
    }

    /**
     * Get value returns the value the node holds. This can be null.
     *
     * @return The value the node holds
     */
    @Nullable
    public V getValue() {
        return value;
    }

    /**
     * This returns the parent of the node. This can be null.
     * If the parent node is null, then the node is presumed the root of the tree.
     *
     * @return the parent node
     */
    @Nullable
    public Node<V> getParent() {
        return parent;
    }

    /**
     * This creates a new child node and adds it to the current node children and sets the node's parent to this node.
     *
     * @param value The value to be added to the tree
     * @return The child node that was set.
     */
    @Nonnull
    public Node<V> addChild(V value) {
        Node<V> child = new Node<>(this, value);
        this.children.add(child);
        return child;
    }
}
