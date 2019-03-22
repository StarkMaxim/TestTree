package com.max.service;

import com.max.Entity.TreeElement;

import java.util.List;

public interface TreeElementService {

    TreeElement getTreeElementById(int id);
    List<TreeElement> getAllTreeElements();
    TreeElement saveTreeElement(TreeElement treeElement);
    List<TreeElement> getAllTreeElementsById(int id);
    void deleteTreeElementsById(int id);
    TreeElement updateTreeElement(TreeElement treeElement);


}
