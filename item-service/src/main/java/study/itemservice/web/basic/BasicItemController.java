package study.itemservice.web.basic;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.itemservice.domain.item.Item;
import study.itemservice.domain.item.ItemRepository;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    /*
    @PostMapping("/add")
    public String addItemV1(@RequestParam("itemName") String itemName,
                            @RequestParam("price") int price,
                            @RequestParam("quantity") Integer quantity,
                            Model model){
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }
    */
    /*
    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("Item") Item item,Model model){
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }
    */
    /*
    @PostMapping("/add")
    public String addItemV3(@ModelAttribute("Item") Item item,Model model){
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "redirect:/basic/items/" + item.getId();
    }
    */
    @PostMapping("/add")
    public String addItemV4(@ModelAttribute("Item") Item item,Model model, RedirectAttributes redirectAttributes){
        itemRepository.save(item);
        model.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status",true);

        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "/basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") long itemId, @ModelAttribute("item") Item item){
        itemRepository.update(itemId,item);

        return "redirect:/basic/items/{itemId}";
    }
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
