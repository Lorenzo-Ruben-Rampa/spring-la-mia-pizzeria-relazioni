package org.lessons.java.pizzeria_crud.model;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="Il nome non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Il nome può contenere solo lettere e spazi.")
    @Size(max = 100, message = "Il nome non può superare i 100 caratteri.")
    private String name;

    @Lob
    @NotBlank(message = "La descrizione non può essere vuota.")
    @Size(max = 455, message = "La descrizione non può superare i 455 caratteri.")
    private String description;

    @NotBlank(message="Questo campo è obbligatorio")
    @Column(name = "url_image")
    private String urlImage;

    @NotNull(message="Il prezzo non può essere vuoto o < di zero")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di zero")
    private BigDecimal price;

    //Getter e Setter
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	@Override
	public String toString(){
	  return  String.format(this.name, this.description, this.price);
    }

}