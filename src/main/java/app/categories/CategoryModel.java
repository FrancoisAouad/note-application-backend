package app.categories;

// Javax
import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.*;
// Lombok
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// Services
import app.auth.AuthModel;

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    @Id
    private String id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    // @JsonIgnore
    private AuthModel creator;

}
