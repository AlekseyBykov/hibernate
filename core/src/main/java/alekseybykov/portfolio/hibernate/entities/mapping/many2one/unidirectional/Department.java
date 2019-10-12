//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.hibernate.entities.mapping.many2one.unidirectional;

import lombok.Data;

import javax.persistence.*;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 2019-06-05
 */
@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String deptName;
}
