package cc.controller;

import cc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public LivegoodsResult search(String city,String content,int page,@RequestParam(defaultValue = "5") int rows){
        return searchService.search(city,content,page,rows);
    }
}
