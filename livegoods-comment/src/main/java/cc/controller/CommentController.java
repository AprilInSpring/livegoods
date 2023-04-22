package cc.controller;

import cc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/feelback")
    public LivegoodsResult comment(String orderId,String feelback){
        commentService.comment(orderId,feelback);
        return LivegoodsResult.ok();
    }

    @GetMapping("/comment")
    public LivegoodsResult getCommentByItemId(@RequestParam(value = "id") String itemId, int page, @RequestParam(defaultValue = "5") int rows){
        return commentService.findComments(itemId,page,rows);
    }
}
