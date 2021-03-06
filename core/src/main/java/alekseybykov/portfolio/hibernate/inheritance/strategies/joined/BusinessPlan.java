package alekseybykov.portfolio.hibernate.inheritance.strategies.joined;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Aleksey Bykov
 * @since 10.11.2019
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "business_plan")
@EqualsAndHashCode(callSuper = true)
public class BusinessPlan extends Document {

    @Column(name = "product_description")
    private String productDescription;

    public BusinessPlan(String state, String productDescription) {
        super(state);
        this.productDescription = productDescription;
    }
}
