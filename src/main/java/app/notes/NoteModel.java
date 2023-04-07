package app.notes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;

    // @ManyToMany
    // @JoinTable(name = "notes_tags", joinColumns = @JoinColumn(name = "note_id"),
    // inverseJoinColumns = @JoinColumn(name = "tag_id"))
    // private List<TagModel> tags;

    @ElementCollection
    @Column(name = "image_location")
    private List<String> imageLocation;

    @ElementCollection
    @Column(name = "attachment_location")
    private List<String> attachmentLocation;

    // @ManyToOne
    // @JoinColumn(name = "category_id", nullable = false)
    // private CategoryModel category;

    // @ManyToOne
    // @JoinColumn(name = "creator_id", nullable = false)
    // private UserModel creator;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "creator_email")
    private String creatorEmail;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "updated_date", nullable = false)
    private Date updatedDate;

    // public NoteModel(String title, String content, List<TagModel> tags,
    // List<String> imageLocation,
    // List<String> attachmentLocation, CategoryModel category, UserModel creator,
    // String creatorName,
    // String creatorEmail, Date createdDate, Date updatedDate) {
    // this.title = title;
    // this.content = content;
    // this.tags = tags;
    // this.imageLocation = imageLocation;
    // this.attachmentLocation = attachmentLocation;
    // this.category = category;
    // this.creator = creator;
    // this.creatorName = creatorName;
    // this.creatorEmail = creatorEmail;
    // this.createdDate = createdDate;
    // this.updatedDate = updatedDate;
    // }

}
