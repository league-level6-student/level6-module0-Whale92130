

package _01_intro_to_APIs.data_transfer_objects;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CatFact {

@SerializedName("data")
@Expose
private List<String> data;

public List<String> getData() {
return data;
}

public void setData(List<String> data) {
this.data = data;
}

}