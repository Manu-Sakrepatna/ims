package com.example.ims.service;

import com.example.ims.models.Indent;
import com.example.ims.models.IndentItem;
import com.example.ims.models.Location;
import com.example.ims.models.Product;
import com.example.ims.repositories.IndentItemRepository;
import com.example.ims.repositories.IndentRepository;
import com.example.ims.repositories.LocationRepository;
import com.example.ims.utils.IndentType;
import com.example.ims.view.IndentItemView;
import com.example.ims.view.IndentView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IndentService {

    @Autowired
    private IndentRepository indentRepository;
    @Autowired
    private IndentItemRepository indentItemRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private IndentItemService indentItemService;
    @Autowired
    private ProductService productService;



    private Logger logger = LoggerFactory.getLogger(IndentService.class);

    public IndentView createIndent(IndentView indentView){

        Indent newIndent =  mapIndentViewToIndent(indentView);
        Indent savedIndent = indentRepository.saveAndFlush(newIndent);
        //TBD: On saving newIndent all indent items shall be saved. But for some reason it is not saving in the db.
        // So explicit code is added to save each indent_items separately. It needs to be corrected.

        if (savedIndent != null){
            int indentId = savedIndent.getId();
            Set<IndentItem> indent_items = newIndent.getIndent_items();
            Set<IndentItem> savedIndentItemsSet = new HashSet<IndentItem>();

            for (IndentItem item: indent_items){
                //Fetch the product based on the product_id supplied in the  indent_item.
                Product dbProduct = productService.fetchProductOnId(item.getProduct_id());
                //based on the indent type the current stock of the product shall be updated.
                switch(savedIndent.getType()){
                    case INWARD:  dbProduct.setCurrentStockCount(dbProduct.getCurrentStockCount() + item.getQuantity());
                                  break;
                    case OUTWARD:
                    case RETURN:  dbProduct.setCurrentStockCount(dbProduct.getCurrentStockCount() - item.getQuantity());
                                  break;
                }
                productService.updateProduct(dbProduct);
                item.setIndent(savedIndent);
                IndentItem savedIndentItem = indentItemRepository.saveAndFlush(item);
                savedIndentItemsSet.add(savedIndentItem);
            }

            savedIndent.setIndent_items(savedIndentItemsSet);

        }

        return mapIndentToIndentView(savedIndent);
    }


    public Indent mapIndentViewToIndent(IndentView indentView){
        Indent newIndent = new Indent();
        newIndent.setType(indentView.getType());
        newIndent.setTotal_price(indentView.getTotal_price());
        newIndent.setIndent_date(indentView.getIndent_date());

        Set<IndentItemView> indent_item_views = indentView.getIndent_item_views();
        Set<IndentItem> savedIndentItemsSet = new HashSet<IndentItem>();

        for (IndentItemView itemView: indent_item_views){
            IndentItem item = new IndentItem();
            item.setIndent(null);
            item.setQuantity(itemView.getQuantity());
            item.setPrice(itemView.getPrice());
            item.setProduct_id(itemView.getProduct_id());
            savedIndentItemsSet.add(item);
        }
        newIndent.setIndent_items(savedIndentItemsSet);

        Set<Location> locationSet = new HashSet<Location>();
        for (int i: indentView.getLocation_ids()){
            locationSet.add(locationRepository.findById(i).orElse(null));
        }
        newIndent.setLocations(locationSet);

        return (newIndent);
    }




    public IndentView mapIndentToIndentView(Indent indent){
        IndentView  newIndentView = new IndentView();
        newIndentView.setId(indent.getId());
        newIndentView.setIndent_date(indent.getIndent_date());
        newIndentView.setTotal_price(indent.getTotal_price());
        newIndentView.setType(indent.getType());

        Set<IndentItemView> newIndentItemViewSet = new HashSet<IndentItemView>();

        for(IndentItem i : indent.getIndent_items()){
            IndentItemView newIndentItemView = indentItemService.mapIndentItemToIndentItemView(i);
            newIndentItemViewSet.add(newIndentItemView);
        }

        newIndentView.setIndent_item_views(newIndentItemViewSet);

        int[] location_ids = new int[indent.getLocations().size()];
        int k = 0;
        for (Location l: indent.getLocations()){
            location_ids[k++] = l.getId();
        }
        newIndentView.setLocation_ids(location_ids);

        return newIndentView;
    }



    public List<IndentView> list(){
        List<IndentView> indentViewList = new ArrayList<>();
        List<Indent> indentList = indentRepository.findAll();
        for(Indent i: indentList){
            indentViewList.add(mapIndentToIndentView(i));
        }
        return indentViewList;
    }


    public IndentView get(int id){
      Indent dbIndent = indentRepository.findById(id).orElse(null);

        if (dbIndent != null)
            return mapIndentToIndentView(dbIndent);
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }
    }


    public IndentView updateIndent(int id, IndentView indent){
        Indent dbIndent = indentRepository.findById(id).orElse(null);
        if (dbIndent != null) {
            Indent incomingIndent = mapIndentViewToIndent(indent);
            BeanUtils.copyProperties(incomingIndent, dbIndent, "id");
            return  mapIndentToIndentView(indentRepository.saveAndFlush(dbIndent));
        }
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }
    }
}
