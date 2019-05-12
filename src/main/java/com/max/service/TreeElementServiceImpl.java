package com.max.service;

import com.max.Entity.TreeElement;
import com.max.repository.TreeElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
public class TreeElementServiceImpl implements TreeElementService {

    @Autowired
    private TreeElementRepository treeElementRepository;

    @Override
    public TreeElement getTreeElementById(int id) {
        TreeElement treeElement = treeElementRepository.getById(id);
        return treeElement;
    }

    @Override
    public List<TreeElement> getAllTreeElements() {
        List<TreeElement> treeElementList = treeElementRepository.findAll();
        return treeElementList;
    }

    @Transactional
    @Override
    public TreeElement saveTreeElement(TreeElement treeElement) {

        if (treeElement.getParentid() == null ||
                treeElementRepository.updateAddParentTreeElement(treeElement.getParentid()) == 1) {
            treeElement.setCreatedate(Calendar.getInstance());
            treeElement.setUpdatedate(Calendar.getInstance());
            treeElement.setFinite(true);
            treeElementRepository.save(treeElement);
            return treeElement;

        }
        return null;
    }

    @Override
    public List<TreeElement> getAllTreeElementsById(int id) {
        List<TreeElement> treeElementList = treeElementRepository.fac(id);
        return treeElementList;
    }

    @Transactional
    @Override
    public void deleteTreeElementsById(int id) {
        treeElementRepository.updateDeleteParentTreeElementDel(id);
        treeElementRepository.deleteTreeElementsById(id);

    }

    @Transactional
    @Override
    public TreeElement updateTreeElement(TreeElement treeElement) {

        if (treeElement.getParentid() != treeElement.getId() && treeElement.getId() != null && treeElement.getParentid() != null &&
        treeElementRepository.updateDeleteParentTreeElementDel(treeElement.getId()) == 1 &&
                treeElementRepository.updateTreeElementById(treeElement.getId(),
                        treeElement.getName(), treeElement.getParentid()) == 1) {
            treeElementRepository.updateAddParentTreeElement(treeElement.getParentid());
            return treeElementRepository.getById(treeElement.getId());
        }

        else if (treeElement.getId() != null && treeElement.getParentid() == null){
            treeElementRepository.updateDeleteParentTreeElementDel(treeElement.getId());
            //treeElementRepository.updateTreeElementById(treeElement.getId(),
              //      treeElement.getName(), treeElement.getParentid());
            return treeElementRepository.getById(treeElement.getId());
        }

        return null;
    }


}
