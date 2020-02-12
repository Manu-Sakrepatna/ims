package com.example.ims.service;

import com.example.ims.models.Indent;
import com.example.ims.models.IndentItem;
import com.example.ims.repositories.IndentItemRepository;
import com.example.ims.repositories.IndentRepository;
import com.example.ims.view.IndentItemView;
import com.example.ims.view.IndentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndentItemService {

    @Autowired
    private IndentItemRepository indentItemRepository;
    @Autowired
    private IndentRepository indentRepository;

    public IndentItemView mapIndentItemToIndentItemView(IndentItem indentItem){
        if(indentItem != null) {
            IndentItemView newIndentItemView = new IndentItemView();
            newIndentItemView.setId(indentItem.getId());
            newIndentItemView.setPrice(indentItem.getPrice());
            newIndentItemView.setProduct_id(indentItem.getProduct_id());
            newIndentItemView.setQuantity(indentItem.getQuantity());
            newIndentItemView.setIndent_id(indentItem.getIndent().getId());
            return newIndentItemView;
        }else{
            return null;
        }
    }

    public IndentItem mapIndentItemViewToIndentItem(IndentItemView indentItemView){
        if(indentItemView != null) {
            IndentItem newIndentItem = new IndentItem();
            newIndentItem.setId(indentItemView.getId());
            newIndentItem.setPrice(indentItemView.getPrice());
            newIndentItem.setProduct_id(indentItemView.getProduct_id());
            newIndentItem.setQuantity(indentItemView.getQuantity());
            Indent dbIndent = indentRepository.findById(indentItemView.getIndent_id()).orElse(null);
            newIndentItem.setIndent(dbIndent);
            return newIndentItem;
        }else{
            return null;
        }

    }

}

