/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.api.project.tree;

import org.eclipse.che.ide.api.project.node.HasProjectDescriptor;
import org.eclipse.che.ide.api.project.tree.generic.ProjectNode;
import org.eclipse.che.ide.ui.tree.TreeNodeElement;
import com.google.gwt.user.client.rpc.AsyncCallback;

import org.vectomatic.dom.svg.ui.SVGImage;

import javax.validation.constraints.NotNull;
import org.eclipse.che.commons.annotation.Nullable;
import java.util.List;

/**
 * Defines the requirements for an object that can be used as a tree node in a project's tree structure.
 * May also hold a reference to an associated object, using of which is left to the user.
 *
 * @param <T>
 *         the type of the associated data
 * @author Artem Zatsarynnyy
 */
public interface TreeNode<T> {

    /**
     * Returns this node's parent node, or {@code null} if this is the root node.
     *
     * @return the parent of this node, or {@code null} if none
     */
    TreeNode<?> getParent();

    /**
     * Sets the new parent node for this node.
     *
     * @param parent
     *         the new parent of this node
     */
    void setParent(TreeNode<?> parent);

    /**
     * Returns the object represented by this node                                   .
     *
     * @return the associated data
     */
    T getData();

    /**
     * Sets the new associated data for this node.
     *
     * @param data
     *         the new associated data
     */
    void setData(T data);

    /** Returns node ID that is unique within its peers in a given level in the tree. */
    @NotNull
    String getId();

    /** Returns {@link TreeStructure} which this node belongs. */
    @NotNull
    TreeStructure getTreeStructure();

    /**
     * Returns the {@code ProjectNode} that contains this node or this node if it is a project node.
     *
     * @throws IllegalStateException
     *         if this node is not owned by some project node
     */
    @NotNull
    HasProjectDescriptor getProject();

    /** Returns the node's display name. */
    @NotNull
    String getDisplayName();

    /** Provides an SVG icon to be used for graphical representation of the node. */
    @Nullable
    SVGImage getDisplayIcon();

    /** Set an SVG icon to be used for graphical representation of the node. */
    void setDisplayIcon(SVGImage icon);

    /**
     * Determines may the node be expanded.
     *
     * @return <code>true</code> - if node shouldn't never be expanded in the tree,
     * <code>false</code> - if node may be expanded
     */
    boolean isLeaf();

    /**
     * Returns an array of all this node's child nodes. The array will always
     * exist (i.e. never {@code null}) and be of length zero if this is
     * a leaf node.
     *
     * @return an array of all this node's child nodes
     */
    @NotNull
    List<TreeNode<?>> getChildren();

    /**
     * Set node's children.
     *
     * @param children
     *         array of new children for this node
     */
    void setChildren(List<TreeNode<?>> children);

    /**
     * Refresh node's children.
     *
     * @param callback
     *         callback to return node with refreshed children
     */
    void refreshChildren(AsyncCallback<TreeNode<?>> callback);

    /** Process an action on the node (e.g. double-click on the node in the view). */
    void processNodeAction();

    /** Defines whether the node may be renamed. */
    boolean isRenamable();

    /**
     * Override this method to provide a way to rename node.
     * <p/>
     * Sub-classes should invoke {@code super.delete} at the end of this method.
     *
     * @param newName
     *         new name
     * @param callback
     *         callback to return deleted node
     */
    void rename(String newName, RenameCallback callback);

    /** Defines whether the node may be deleted. */
    boolean isDeletable();

    /**
     * Override this method to provide a way to delete node.
     * <p/>
     * Sub-classes should invoke {@code super.delete} at the end of this method.
     *
     * @param callback
     *         callback to return renamed node
     */
    void delete(DeleteCallback callback);

    /**
     * Returns the rendered {@link org.eclipse.che.ide.ui.tree.TreeNodeElement} that is a representation of node.
     * <p/>
     * Used internally and not intended to be used directly.
     *
     * @return the rendered {@link org.eclipse.che.ide.ui.tree.TreeNodeElement}
     */
    TreeNodeElement<TreeNode<?>> getTreeNodeElement();

    /**
     * Sets the rendered {@link org.eclipse.che.ide.ui.tree.TreeNodeElement} that is a representation of node.
     * <p/>
     * Used internally and not intended to be used directly.
     *
     * @param treeNodeElement
     *         the rendered {@link org.eclipse.che.ide.ui.tree.TreeNodeElement}
     */
    void setTreeNodeElement(TreeNodeElement<TreeNode<?>> treeNodeElement);

    public interface RenameCallback {
        void onRenamed();

        void onFailure(Throwable exception);
    }

    public interface DeleteCallback {
        void onDeleted();

        void onFailure(Throwable exception);
    }
}
