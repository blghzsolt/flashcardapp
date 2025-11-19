package hu.unideb.inf.flashcardapp.controller;

import hu.unideb.inf.flashcardapp.service.KartyaService;
import hu.unideb.inf.flashcardapp.service.dto.KartyaDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cards")
public class KartyaController {
    final KartyaService kartyaService;

    public KartyaController(KartyaService kartyaService) {
        this.kartyaService = kartyaService;
    }

    @GetMapping
    public List<KartyaDto> findAll(){
        return kartyaService.findAll();
    }

    @GetMapping("/byId")
    public KartyaDto findById(@RequestParam Long id){
        return kartyaService.findById(id);
    }

    @GetMapping("/byDeck")
    public List<KartyaDto> findByDeck(@RequestParam Long deckId) {
        return kartyaService.findByCsomagId(deckId);
    }

    @DeleteMapping("/deleteById")
    public void delById(@RequestParam Long id){
        kartyaService.deleteById(id);
    }

    @PostMapping("/save")
    public KartyaDto save(@RequestBody KartyaDto kartyaDto){
        return kartyaService.save(kartyaDto);
    }

}
