package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.com.ecommers.model.dto.card.CardCreateDto;
import uz.com.ecommers.model.dto.card.CardForUser;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.CardService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping("/save")
    public StandardResponse<CardForUser> save(
            @RequestBody CardCreateDto cardCreateDto,
            Principal principal
            ){
        return cardService.save(cardCreateDto, principal);
    }

    @DeleteMapping("/delete")
    public StandardResponse<String> delete(
            @RequestParam String number,
            Principal principal
    ){
        return cardService.delete(number, principal);
    }

    @PostMapping("/get-by-type")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public List<CardForUser> getByType(
            @RequestParam String type
    ){
        return cardService.getByType(type);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public Optional<List<CardForUser>> getAll(){
        return cardService.getAll();
    }
}
