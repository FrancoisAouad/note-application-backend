package app.notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.persistence.*;
import app.categories.CategoryModel;

@Entity
@Table(name = "notes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteModel {

    @Id
    private String id;
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "creator_email")
    private String creatorEmail;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;

    // @ManyToMany
    // @JoinTable(name = "notes_tags", joinColumns = @JoinColumn(name = "note_id"),
    // inverseJoinColumns = @JoinColumn(name = "tag_id"))
    // private List<TagModel> tags;

    // @ElementCollection
    // @Column(name = "image_location")
    // private List<String> imageLocation;

    // @ElementCollection
    // @Column(name = "attachment_location")
    // private List<String> attachmentLocation;

    // @ManyToOne
    // @JoinColumn(name = "creator_id", nullable = false)
    // private UserModel creator;

}
