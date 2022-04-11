package ee.Karu.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // database model
public class Product {
    // initiate variables
    @Id
    @GeneratedValue
    private Long id; // @ seotud id-ga
    private String name;
    private double price;
    private String imgSrc;
    private boolean active;
    private String description;
    private String category;


}
