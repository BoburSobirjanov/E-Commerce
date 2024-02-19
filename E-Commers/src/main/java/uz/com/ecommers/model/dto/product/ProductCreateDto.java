package uz.com.ecommers.model.dto.product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreateDto {
    private String name;
    private Double price;
    private String description;
    private String type;
    private String state;
    private Integer count;
}
