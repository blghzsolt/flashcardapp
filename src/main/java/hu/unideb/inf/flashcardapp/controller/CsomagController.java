package hu.unideb.inf.flashcardapp.controller;

import hu.unideb.inf.flashcardapp.service.CsomagService;
import hu.unideb.inf.flashcardapp.service.dto.CsomagDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/decks")
public class CsomagController {
    final CsomagService csomagService;

    public CsomagController(CsomagService csomagService) {
        this.csomagService = csomagService;
    }

    @GetMapping("/findById")
    public CsomagDto findById(@RequestParam Long id){
        return csomagService.findById(id);
    }

    @GetMapping("/findByUserId")
    public List<CsomagDto> findByUserId(@RequestParam Long id){return csomagService.findByUserId(id);}

    @GetMapping
    public List<CsomagDto> findAll(){
        return csomagService.findAll();
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam Long id){
        csomagService.deleteById(id);
    }

    @PostMapping("/save")
    public CsomagDto save(@RequestBody CsomagDto csomagDto){
        return csomagService.save(csomagDto);
    }
}
