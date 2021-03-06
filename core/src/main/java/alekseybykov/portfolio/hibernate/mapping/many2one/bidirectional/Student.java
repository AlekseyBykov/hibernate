package alekseybykov.portfolio.hibernate.mapping.many2one.bidirectional;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Aleksey Bykov
 * @since 09.06.2019
 */
@Entity
@Table(name = "STUDENT")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column(name = "STUDENT_FIRST_NAME")
    private String firstName;

    @JoinColumn(name = "UNIVERSITY_ID")
    @ManyToOne(optional = false)
    private University university;
}
