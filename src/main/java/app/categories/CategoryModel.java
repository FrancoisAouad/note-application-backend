package app.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryModel {
    @Id
    private String id;
    private String name;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private String creatorID;
    private String creatorName;
    private String creatorEmail;
}
