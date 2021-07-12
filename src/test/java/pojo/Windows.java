
package pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "test",
    "test1",
    "test2",
    "test3"
})
public class Windows {

    @JsonProperty("test")
    private String test;
    @JsonProperty("test1")
    private String test1;
    @JsonProperty("test2")
    private String test2;
    @JsonProperty("test3")
    private String test3;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("test")
    public String getTest() {
        return test;
    }

    @JsonProperty("test")
    public void setTest(String test) {
        this.test = test;
    }

    @JsonProperty("test1")
    public String getTest1() {
        return test1;
    }

    @JsonProperty("test1")
    public void setTest1(String test1) {
        this.test1 = test1;
    }

    @JsonProperty("test2")
    public String getTest2() {
        return test2;
    }

    @JsonProperty("test2")
    public void setTest2(String test2) {
        this.test2 = test2;
    }

    @JsonProperty("test3")
    public String getTest3() {
        return test3;
    }

    @JsonProperty("test3")
    public void setTest3(String test3) {
        this.test3 = test3;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(test).append(test1).append(test2).append(test3).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Windows) == false) {
            return false;
        }
        Windows rhs = ((Windows) other);
        return new EqualsBuilder().append(test, rhs.test).append(test1, rhs.test1).append(test2, rhs.test2).append(test3, rhs.test3).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
