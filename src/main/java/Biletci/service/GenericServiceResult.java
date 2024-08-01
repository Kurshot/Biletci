package Biletci.service;

import Biletci.enums.ResultMapping;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class GenericServiceResult implements Serializable {

    @Serial
    private static final long serialVersionUID = -665383216807903892L;

    private ResultMapping resultMapping;
    private Object responseData;

    public GenericServiceResult(ResultMapping resultMapping, Object responseData) {
        this.resultMapping = resultMapping;
        this.responseData = responseData;
    }

}
