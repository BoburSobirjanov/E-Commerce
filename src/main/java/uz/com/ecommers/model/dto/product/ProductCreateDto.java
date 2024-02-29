package uz.com.ecommers.model.dto.product;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreateDto {
    private String name;
    private Double price;
    private String description;
    private String category;
    private String state;
    private Integer count;
    private String color;
}
