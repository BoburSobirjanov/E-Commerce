package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.com.ecommers.exception.DataNotFoundException;
import uz.com.ecommers.exception.NotAcceptableException;
import uz.com.ecommers.model.dto.card.CardCreateDto;
import uz.com.ecommers.model.dto.card.CardForUser;
import uz.com.ecommers.model.entity.card.CardEntity;
import uz.com.ecommers.model.entity.card.CardType;
import uz.com.ecommers.model.entity.user.UserEntity;
import uz.com.ecommers.repository.CardRepository;
import uz.com.ecommers.repository.UserRepository;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.response.Status;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public StandardResponse<CardForUser> save(CardCreateDto cardCreateDto, Principal principal){
        UserEntity user = userRepository.findUserEntityByEmail(principal.getName());
        checkHasCard(cardCreateDto.getCardNumber(), CardType.valueOf(cardCreateDto.getCardType()));
        CardEntity card = modelMapper.map(cardCreateDto, CardEntity.class);
        card.setCardNumber(cardCreateDto.getCardNumber());
        card.setType(CardType.valueOf(cardCreateDto.getCardType()));
        card.setExpireDate(cardCreateDto.getExpireDate());
        card.setCreatedBy(user.getId());
        cardRepository.save(card);
        CardForUser cardForUser = modelMapper.map(card, CardForUser.class);
        return StandardResponse.<CardForUser>builder()
                .status(Status.SUCCESS)
                .message("Card added!")
                .data(cardForUser)
                .build();
    }

    public void checkHasCard(String number, CardType type){
        CardEntity card = cardRepository.findCardEntityByCardNumberAndType(number,type);
        if (card!=null){
            throw new NotAcceptableException("Card has already added!");
        }
    }

    public List<CardEntity> getCardByUser(UUID id){
        List<CardEntity> card = cardRepository.getCardEntitiesByCreatedBy(id);
        if (card==null){
            throw new DataNotFoundException("Card not found or this user did not add card!");
        }
        return card;
    }

    public StandardResponse<String> delete(String cardNumber, Principal principal){
        UserEntity user = userRepository.findUserEntityByEmail(principal.getName());
        CardEntity card = cardRepository.findCardEntityByCardNumber(cardNumber);
        if (card==null){
            throw new DataNotFoundException("Card not found!");
        }
        card.setDeletedBy(user.getId());
        card.setDeleted(true);
        card.setDeletedTime(LocalDateTime.now());
        cardRepository.save(card);
        return StandardResponse.<String>builder()
                .status(Status.SUCCESS)
                .message("Card deleted!")
                .data("DELETED SUCCESSFULLY!")
                .build();
    }

    public List<CardForUser> getByType(String type){
        List<CardForUser> cards = cardRepository.findCardEntityByType(type);
        if (cards==null){
            throw new DataNotFoundException("Cards not found!");
        }
        return cards;
    }

    public Optional<List<CardForUser>> getAll(){
        Optional<List<CardForUser>> card = cardRepository.getAll();
        if (card.isEmpty()){
            throw new DataNotFoundException("Cards not found!");
        }
        return card;
    }
}
