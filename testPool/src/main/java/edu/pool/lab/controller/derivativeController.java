package edu.pool.lab.controller;

import edu.pool.lab.dao.derivativeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/tuyen")
public class derivativeController {
    @Autowired
    private derivativeDAO derivativeDAO;

    @RequestMapping(value = "/derivative", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listDerivative( @RequestParam(required = false) String page, @RequestParam(required = false) String size){
        int PAGE = 1;
        int SIZE = 3;
        if(page == null && size == null){
        }else if(page == null || size == null){
            if(page == null){
                SIZE = Integer.parseInt(size);
            } else {
                PAGE = Integer.parseInt(page);
            }
        }else {
            PAGE = Integer.parseInt(page);
            SIZE = Integer.parseInt(size);
        }

        int totalElements = derivativeDAO.listAll().size();
        int totalPage = totalElements / SIZE;
        if(totalElements % SIZE != 0){
            totalPage+=1;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", derivativeDAO.listAll());
        map.put("currentPage", PAGE);
        map.put("totalPage", totalPage);
        map.put("totalElements", totalElements);
        return map;
    }
}
