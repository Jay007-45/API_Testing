
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
    "MacBook",
    "Windows"
})
public class Prod {

    @JsonProperty("MacBook")
    private MacBook macBook;
    @JsonProperty("Windows")
    private Windows windows;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("MacBook")
    public MacBook getMacBook() {
        return macBook;
    }

    @JsonProperty("MacBook")
    public void setMacBook(MacBook macBook) {
        this.macBook = macBook;
    }

    @JsonProperty("Windows")
    public Windows getWindows() {
        return windows;
    }

    @JsonProperty("Windows")
    public void setWindows(Windows windows) {
        this.windows = windows;
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
        return new HashCodeBuilder().append(macBook).append(windows).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Prod) == false) {
            return false;
        }
        Prod rhs = ((Prod) other);
        return new EqualsBuilder().append(macBook, rhs.macBook).append(windows, rhs.windows).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
